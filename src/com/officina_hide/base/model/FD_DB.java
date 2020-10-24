package com.officina_hide.base.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Items;

/**
 * データベース操作クラス<br>
 * @author officine-hide.com
 * @version 1.00 新規作成
 * @since 2020/10/08
 */
public class FD_DB implements I_DB {
	/** データベース接続情報	 */
	protected static Connection conn;
	/** 項目リスト */
	protected FD_Items  itemList = new FD_Items();
	/** 日付フォーマッ */
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public void createDBTable(FD_EnvData env, String tableName) {
		
	}

	/**
	 * DB更新<br>
	 * @author officine-hide.com
	 * @since 2020/10/08
	 * @param env 環境情報
	 * @param sql SQL文
	 */
	public void DBexecute(FD_EnvData env, String sql) {
		Statement stmt = null;
		connection(env);
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, null);
		}
	}

	/**
	 * データベース接続<br>
	 * @author officina-hide.com
	 * @since 2020/10/09
	 * @param env 環境情報
	 */
	public void connection(FD_EnvData env) {
		if(conn == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				StringBuffer url  = new StringBuffer().append("jdbc:mysql://")
						.append(env.getDB_Host())
						.append(":3306/")
						.append(env.getDB_Name());
				conn = DriverManager.getConnection(url.toString(), env.getDB_User(), env.getDB_Password());
				System.out.println(new Date() + " : "+"Database Connected.");
//				env.getLog().add(env, FD_Logging.TYPE_MESSAGE, FD_Logging.MODE_DEBAG, "Database Connected.");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}

	/**
	 * データベースクローズ(statement)<br>
	 * @author officina-hide.com
	 * @since 2020/10/10
	 * @param stmt ステートメント
	 * @param rs レザルトセット
	 */
	public void close(Statement stmt, ResultSet rs) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * データベースクローズ(statement)<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/24
	 * @param stmt ステートメント
	 */
	public void close(Statement stmt) {
		close(stmt, null);
	}

	/**
	 * 項目リストセット<br>
	 * <p>項目リストの指定された項目名を持つ情報に対して、指定された項目情報をセットする。</p>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/15
	 * @param env 環境情報
	 * @param columnName テーブル項目名
	 * @param columnData テーブル項目情報
	 */
	public void setValueByName(FD_EnvData env, String columnName, Object columnData) {
		if(itemList.setData(columnName, columnData) == false) {
			System.out.println(new Date()+" : "+"Column Name Not Found!! ["+columnName+"]");
			new Exception();
		}
	}
	
	/**
	 * 情報保存<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/15
	 * @param env 環境情報
	 * @param tableName テーブル名
	 */
	public void save(FD_EnvData env, String tableName) {
		StringBuffer sql = new StringBuffer();
		StringBuffer setItem = new StringBuffer();
		
		//情報IDチェック
		if(itemList.getItemByName(tableName+"_ID").getIntOfValue() == 0) {
			itemList.setData(tableName+"_ID",  getNewID(env, tableName));
		}
		
		//登録日、更新日設定
		if(itemList.getValueOfItem(COLUMNNAME_FD_CREATE) == null) {
			itemList.setData(COLUMNNAME_FD_CREATE, new Date());
			itemList.setData(COLUMNNAME_FD_UPDATE, new Date());
			itemList.setData(COLUMNNAME_FD_CREATED, env.getLogin_User_ID());
			itemList.setData(COLUMNNAME_FD_UPDATED, env.getLogin_User_ID());
		} else {
			itemList.setData(COLUMNNAME_FD_UPDATE, new Date());
			itemList.setData(COLUMNNAME_FD_UPDATED, env.getLogin_User_ID());
		}

		sql.append("INSERT INTO ").append(tableName).append(" SET ");
		
		for(String columnName : itemList.getNameList()) {
			if(setItem.length() > 0) {
				setItem.append(",");
			}
			setItem.append(itemList.getSQLString(columnName));
		}
		sql.append(setItem.toString());

		DBexecute(env, sql.toString());
	}

	/**
	 * 新規情報ID取得<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/16
	 * @param env 環境情報
	 * @param tableName テーブル名
	 */
	private int getNewID(FD_EnvData env, String tableName) {
		int tableId = 0;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT Current_Number, Initial_Number, FD_Table.FD_Table_ID FROM ").append(I_FD_Numbering.Table_Name).append(" ");
			sql.append("LEFT JOIN FD_Table ON FD_Table.FD_Table_ID = FD_Numbering.FD_Table_ID ");
			sql.append("WHERE FD_Table.Table_Name = ").append(FD_SQ).append(tableName).append(FD_SQ).append(" ");
			sql.append("FOR UPDATE");
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				if(rs.getInt(I_FD_Numbering.COLUMNNAME_Current_Number) == 0) {
					tableId = rs.getInt(I_FD_Numbering.COLUMNNAME_Initial_Number);
				} else {
					tableId = rs.getInt(I_FD_Numbering.COLUMNNAME_Current_Number) + 1;
				}
				//現在値の更新
				sql = new StringBuffer();
				sql.append("UPDATE ").append(I_FD_Numbering.Table_Name).append(" SET ");
				sql.append(I_FD_Numbering.COLUMNNAME_Current_Number).append(" = ").append(tableId).append(" ");
				sql.append("WHERE FD_Table_ID = ").append(rs.getInt(I_FD_Table.COLUMNNAME_FD_Table_ID));
				DBexecute(env, sql.toString());
			} else {
				System.out.println(new Date()+" : "+"ERROR!! Numbering Data Not Found!!");
				// TODO エラー処理方法要件等(2020/10/19 ueno)
				new Exception();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}
		
		return tableId;
	}

	/**
	 * エスケープ処理<br>
	 * <p>SQLインジェクション対策の為、指定されたデータのコードを表示するエスケープ処理する。</p>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/20
	 * @param data 処理対象情報
	 * @return エスケープ処理済情報
	 */
	public String changeEscape(String data) {
		String out = data;
		out = out.replaceAll("\'", "\'\'");
		out = out.replaceAll("\\\\", "\\\\\\\\");
		return out;
	}
}

package com.officina_hide.base.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;
import com.officina_hide.base.common.FD_Items;

/**
 * データベース操作クラス[Database operation class]<br>
 * @author officine-hide.com
 * @version 1.30
 * @since 2020/12/08
 */
public class FD_DB implements I_FD_DB {
	/** データベース接続情報	 */
	protected static Connection conn;
	/** 項目リスト */
	protected FD_Items  itemList = new FD_Items();

	/**
	 * データベース更新実行[Database update execution]<br>
	 * @author officine-hide.com
	 * @since 2020/12/08
	 * @param env 環境情報
	 * @param sql SQL文
	 */
	public void DBUpdateExecution(FD_EnvData env, String sql) {
		Statement stmt = null;
		connection(env);
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBclose(stmt, null);
		}
	}

	/**
	 * データベースクローズ[Database close]<br>
	 * @author officine-hide.com
	 * @since 1.30 2020/12/09
	 * @param stmt ステートメント
	 */
	public void DBclose(Statement stmt) {
		DBclose(stmt, null);
	}

	/**
	 * データベースクローズ[Database close]<br>
	 * @author officine-hide.com
	 * @since 1.30 2020/12/09
	 * @param stmt ステートメント
	 * @param rs 検索結果
	 */
	public void DBclose(Statement stmt, ResultSet rs) {
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
	 * データベース接続[Database connection]<br>
	 * @author officine-hide.com
	 * @since 2020/12/09
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
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * エスケープ処理<br>
	 * <p>SQLインジェクション対策の為、指定されたデータのコードをエスケープ処理する。<br>
	 * Escape the code of the specified data to prevent SQL injection.</p>
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
		StringBuffer where = new StringBuffer();
		
		//情報IDチェック
		int id = itemList.getItemByName(tableName+"_ID").getIntOfValue();
		if(id == 0) {
//			itemList.setData(tableName+"_ID",  getNewID(env, tableName));
//			sql.append("INSERT INTO ").append(tableName).append(" SET ");
		} else {
//			if(existsId(env, tableName, id)) {
//				sql.append("UPDATE ").append(tableName).append(" SET ");
//				where.append("WHERE ").append(tableName).append("_ID = ")
//					.append(itemList.getItemByName(tableName+"_ID").getIntOfValue());
//			} else {
				sql.append("INSERT INTO ").append(tableName).append(" SET ");
//			}
		}
		
		//登録日、更新日設定
		if(itemList.getItemByName(COLUMNNAME_FD_CREATE).isNullData()) {
			itemList.setData(COLUMNNAME_FD_CREATE, new Date());
			itemList.setData(COLUMNNAME_FD_UPDATE, new Date());
			itemList.setData(COLUMNNAME_FD_CREATED, env.getLoginUserID());
			itemList.setData(COLUMNNAME_FD_UPDATED, env.getLoginUserID());
		} else {
			itemList.setData(COLUMNNAME_FD_UPDATE, new Date());
			itemList.setData(COLUMNNAME_FD_UPDATED, env.getLoginUserID());
		}
		if(tableName.equals(I_FD_Process.Table_Name) == false) {
			itemList.setData(COLUMNNAME_FD_Process_ID, env.getActiveProcessID());
		}

		for(String columnName : itemList.getNameList()) {
			if(setItem.length() > 0) {
				setItem.append(",");
			}
			setItem.append(itemList.getSQLString(columnName));
		}
		sql.append(setItem.toString()).append(" ");
		if(where != null && where.length() > 9) {
			sql.append(where.toString());
		}

//		System.out.println(sql.toString());
		
		DBUpdateExecution(env, sql.toString());
		FDLog log = new FDLog();
		log.addLog(env, I_FD_Log.LOGTYPE_Data_Update, changeEscape(sql.toString()));
	}

	/**
	 * 項目の数値情報を返す。<br>
	 * @author officine-hide.com
	 * @since 1.10 2020/11/03
	 * @param columnName 項目名
	 * @return 数値情報
	 */
	public int getintOfValue(String columnName) {
		FD_Item item = itemList.getItemByName(columnName);
		return item.getIntOfValue();
	}

}

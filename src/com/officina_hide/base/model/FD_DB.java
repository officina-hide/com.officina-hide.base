package com.officina_hide.base.model;

import java.sql.Connection;
import java.sql.DriverManager;
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
			close(stmt);
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
	 */
	public void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
	
}

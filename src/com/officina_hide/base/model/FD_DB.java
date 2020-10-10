package com.officina_hide.base.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;

/**
 * データベース操作クラス<br>
 * @author officine-hide.com
 * @version 1.00 新規作成
 * @since 2020/10/08
 */
public class FD_DB implements I_DB {
	/** データベース接続情報	 */
	protected static Connection conn;

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
				System.out.println(new Date() + "Database Connected.");
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
	
}

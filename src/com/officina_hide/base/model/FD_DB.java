package com.officina_hide.base.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;

/**
 * データベース操作クラス[Database operation class]<br>
 * @author officine-hide.com
 * @version 1.30
 * @since 2020/12/08
 */
public class FD_DB implements I_FD_DB {
	/** データベース接続情報	 */
	protected static Connection conn;

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

}

package com.officina_hide.base.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;

/**
 * DB処理クラス[DB processing class]
 * @author officina-hide.net
 * @version 1.50 新規作成
 * @since 2022/03/19 Ver. 1.50
 */
public class FD_DB {

	/** データベース接続情報[Database connection information] */
	private static Connection conn;
	
	/**
	 * データベース接続[Database connection]
	 * @author officina-hide.net
	 * @since 2022/03/21 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 */
	public void connection(FD_EnvData env) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			StringBuffer url = new StringBuffer();
			url.append("jdbc:mysql:")
				.append(env.getDBPath()).append(":").append(env.getDBPort()).append("/").append(env.getDBName());
			conn = DriverManager.getConnection(url.toString(), env.getDBUser(), env.getDBPass());
			System.out.println(new Date() + " : "+"Database Connected.");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConn() {
		return conn;
	}
}

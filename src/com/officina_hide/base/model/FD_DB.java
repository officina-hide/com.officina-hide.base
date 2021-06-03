package com.officina_hide.base.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;

/**
 * DB操作クラス[DB operation class]<br>
 * @author officina-hide.net
 * @version 1.00
 * @since 2021/05/22
 */
public class FD_DB {
	/** 
	 * データベース接続情報[Database connection information]
	 */
	protected static Connection conn;

	/**
	 * データベース接続[Database connection]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/05/25
	 * @param env 環境情報[Evvironment Information]
	 */
	public void connection(FD_EnvData env) {
		if(conn == null) {
			try {
				// TODO 接続に関する情報を環境情報から取得する(2021/05/25)
				Class.forName("com.mysql.cj.jdbc.Driver");
//				StringBuffer url  = new StringBuffer().append("jdbc:mysql://www.officina-hide.com:3306/FDBASE");
				StringBuffer url  = new StringBuffer().append("jdbc:mysql://www.officina-hide.net:3306/FDBASE");
				conn = DriverManager.getConnection(url.toString(), "fdadmin", "fdadminqAz*01");
				System.out.println(new Date() + " : "+"Database Connected.");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * テーブル削除[Drop Table]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/06/03
	 * @param env 環境情報[Environment Information]
	 * @param tableName テーブル名[Table Name]
	 */
	public void deleteTable(FD_EnvData env, String tableName) {
		try {
			connection(env);
			StringBuffer sql = new StringBuffer();
			sql.append("DROP TABLE IF EXISTS ?");
			PreparedStatement ptmt = conn.prepareStatement(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

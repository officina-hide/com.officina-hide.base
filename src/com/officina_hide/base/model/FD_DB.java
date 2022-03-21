package com.officina_hide.base.model;

import java.sql.Connection;

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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

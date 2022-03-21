package com.officina_hide.base.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 環境情報[Environment information]
 * @author officina-hide.net
 * @version 1.50 新規作成
 * @since 2022/03/19 Ver. 1.50
 */
public class FD_EnvData {

	/** 項目 : 処理レベル[Run level] */
	private String runLevel;
	/** 項目 : データベースPath[Database path] */
	private final static String DBPATH = "DBPath";
	private String DBPath;
	/** 項目 : データベースポート[Database port] */
	private final static String DBPORT = "DBPort";
	private String DBPort;
	/** 項目 : データベース名[Database name] */
	private final static String DBNAME = "DDBName";
	private String DBName;
	/** 項目 : データベースログインユーザー名[Database login user name] */
	private final static String DBUSER = "DBUser";
	private String DBUser;
	/** 項目 : データベースパスワード[Database password] */
	private final static String DBPASS = "DBPass";
	private String DBPass;

	/**
	 * コンストラクタ[Constructor]
	 * 指定されたプロパティファイルから環境情報を取得する。<br>
	 * Acquires environment information from the specified property file.<br>
	 * @author officina-hide.net
	 * @since 2022/03/21 Ver. 1.50
	 * @param fileName プロパティファイル名[Properties file name]
	 */
	public FD_EnvData(String fileName) {
		try {
			File pfile = new File(".\\document\\"+fileName);
			Properties prop = new Properties();
			prop.load(new FileInputStream(pfile));
			setDBPass(prop.getProperty(DBPATH, "localhost"));
			setDBPort(prop.getProperty(DBPORT, "3306"));
			setDBName(prop.getProperty(DBNAME, "Base"));
			setDBUser(prop.getProperty(DBUSER, "admin"));
			setDBPass(prop.getProperty(DBPASS, null));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getRunLevel() {
		return runLevel;
	}
	public void setRunLevel(String runLevel) {
		this.runLevel = runLevel;
	}

	public String getDBPath() {
		return DBPath;
	}
	public void setDBPath(String dBPath) {
		DBPath = dBPath;
	}
	public String getDBPort() {
		return DBPort;
	}
	public void setDBPort(String dBPort) {
		DBPort = dBPort;
	}
	public String getDBName() {
		return DBName;
	}
	public void setDBName(String dBName) {
		DBName = dBName;
	}
	public String getDBUser() {
		return DBUser;
	}
	public void setDBUser(String dBUser) {
		DBUser = dBUser;
	}
	public String getDBPass() {
		return DBPass;
	}
	public void setDBPass(String dBPass) {
		DBPass = dBPass;
	}
}

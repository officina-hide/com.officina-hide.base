package com.officina_hide.base.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 環境情報[Environmental information]<br>
 * @author officina-hide.com
 * @version 1.30
 * @since 2020/12/07
 */
public class FD_EnvData implements I_FD_EnvData  {
	/** データベース名 */
	private String DB_Name;
	/** データベースURI */
	private String DB_Host;
	/** データベースユーザー名 */
	private String DB_User;
	/** データベースパスワード */
	private String DB_Password;
	/** 処理プロセス情報ID */
	private int activeProcessID;
	/** ログインユーザー情報ID */
	private int loginUserID;

	/**
	 * コンストラクター[constructor]<br>
	 * 実体化時に、指定されたパスが環境情報を所得する。<br>
	 * @param propPath
	 */
	public FD_EnvData(String propPath) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File(propPath)));
			setEnvData(prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 環境情報の項目セット[Item set of environment information]<br>
	 * @author officina-hide.com
	 * @since 2020/12/07
	 * @param prop 環境ファイル
	 */
	private void setEnvData(Properties prop) {
		setDB_Host(prop.getProperty(DB_HOST));
		setDB_Name(prop.getProperty(DB_NAME));
		setDB_User(prop.getProperty(DB_USER));
		setDB_Password(prop.getProperty(DB_PASWORD));
		setLoginUserID(Integer.parseInt(prop.getProperty(LOGIN_USER_ID)));
	}

	public String getDB_Name() {
		return DB_Name;
	}
	public void setDB_Name(String dB_Name) {
		DB_Name = dB_Name;
	}
	public String getDB_Host() {
		return DB_Host;
	}
	public void setDB_Host(String dB_Host) {
		DB_Host = dB_Host;
	}
	public String getDB_User() {
		return DB_User;
	}
	public void setDB_User(String dB_User) {
		DB_User = dB_User;
	}
	public String getDB_Password() {
		return DB_Password;
	}
	public void setDB_Password(String dB_Password) {
		DB_Password = dB_Password;
	}
	public int getActiveProcessID() {
		return activeProcessID;
	}
	public void setActiveProcessID(int activeProcessID) {
		this.activeProcessID = activeProcessID;
	}
	public int getLoginUserID() {
		return loginUserID;
	}
	public void setLoginUserID(int loginUserID) {
		this.loginUserID = loginUserID;
	}

}

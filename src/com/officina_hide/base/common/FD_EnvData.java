package com.officina_hide.base.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 環境情報<br>
 * @author officine-hide.com
 * @version 1.00 新規作成
 * @version 1.10 プロセス情報ID追加
 * @since 2020/10/08
 */
public class FD_EnvData implements I_FD_EnvData {

	/**
	 * コンストラクター<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/08
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
	 * 環境情報設定<br>
	 * <p>指定されたプロパティファイルより環境情報をセットする。</p>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/08
	 * @param prop プロパティファイル
	 */
	public void setEnvData(Properties prop) {
		setDB_Host(prop.getProperty(DB_HOST));
		setDB_Name(prop.getProperty(DB_NAME));
		setDB_User(prop.getProperty(DB_USER));
		setDB_Password(prop.getProperty(DB_PASWORD));
		setSystem_User_ID(Integer.parseInt(prop.getProperty(SYSTEM_USER_ID)));
		setLogin_User_ID(Integer.parseInt(prop.getProperty(LOGIN_USER_ID)));
//		String logPath = prop.getProperty(LOG_FILE_PATH);
	}

	private String DB_Name;
	private String DB_Host;
	private String DB_User;
	private String DB_Password;
	private int System_User_ID;
	private int Login_User_ID;
	/** 
	 * 実行プロセスID
	 * @since 1.10 2020/10/31
	 */
	private int activeProcessID;
	
	public int getSystem_User_ID() {
		return System_User_ID;
	}

	public void setSystem_User_ID(int system_User_ID) {
		System_User_ID = system_User_ID;
	}

	public int getLogin_User_ID() {
		return Login_User_ID;
	}

	public void setLogin_User_ID(int login_User_ID) {
		Login_User_ID = login_User_ID;
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

	/**
	 * @return activeProcessID
	 */
	public int getActiveProcessID() {
		return activeProcessID;
	}

	/**
	 * @param activeProcessID セットする activeProcessID
	 */
	public void setActiveProcessID(int activeProcessID) {
		this.activeProcessID = activeProcessID;
	}
}

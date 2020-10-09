package com.officina_hide.base.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 環境情報<br>
 * @author officine-hide.com
 * @version 1.00
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
//		setSystemUserID(Integer.parseInt(prop.getProperty(SYSTEM_USER_ID)));
//		setLoginUserID(Integer.parseInt(prop.getProperty(LOGIN_USER_ID)));
//		String logPath = prop.getProperty(LOG_FILE_PATH);
	}

	private String DB_Name;
	private String DB_Host;
	private String DB_User;
	private String DB_Password;
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
}

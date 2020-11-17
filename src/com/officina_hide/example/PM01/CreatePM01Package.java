package com.officina_hide.example.PM01;

import java.io.File;
import java.io.IOException;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.tools.CreateBaseEnvironment;

/**
 * パッケージ管理環境構築<br>
 * @author officina-hide.com
 * @version 1.00
 * @since 2020/11/16
 */
public class CreatePM01Package {

	/**
	 * パッケージ管理環境構築実行<br>
	 * @author officine-hide.com
	 * @since 2020/11/16
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * データベースを事前に構築する。
		 * CREATE DATABASE IF NOT EXISTS PM01DB DEFAULT CHARACTER SET = utf8;<br>
		 * GRANT ALL ON PM01DB.* TO root;<br>
		 * CREATE USER 'pmuser'@'%' IDENTIFIED BY 'Pmuser*001';<br>
		 * TODO パスワードは早いうちに暗号化する。(2020/11/17 ueno)
		 * GRANT ALL PRIVILEGES ON PM01DB.* to 'pmuser'@'%' with Grant option;
		 * GRANT ALL ON PM01DB.* TO pmuser;
		 */
		//環境情報取得
		FD_EnvData env = null;
		try {
			String propPath = new File(".").getCanonicalPath()+"\\data\\pm01.properties";
			env = new FD_EnvData(propPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//ベース環境構築
		CreateBaseEnvironment cbe = new CreateBaseEnvironment();
		cbe.create(env);
		
	}

}

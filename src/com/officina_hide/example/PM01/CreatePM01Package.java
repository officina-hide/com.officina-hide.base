package com.officina_hide.example.PM01;

import java.io.File;
import java.io.IOException;

import com.officina_hide.base.common.FD_EnvData;

/**
 * パッケージ管理環境構築<br>
 * @author officina-hide.com
 * @version 1.00
 * @since 2020/11/16
 */
public class CreatePM01Package {

	/**
	 * パッケージ管理環境構築実行<br>
	 * @param args
	 */
	public static void main(String[] args) {
		//環境情報取得
		try {
			String propPath = new File(".").getCanonicalPath()+"\\data\\pm01.properties";
			FD_EnvData env = new FD_EnvData(propPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package com.officina_hide.base.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 環境情報<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/10/08
 */
public class FD_EnvData {

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
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}

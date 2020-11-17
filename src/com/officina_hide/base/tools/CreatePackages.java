package com.officina_hide.base.tools;

import java.io.File;
import java.io.IOException;

import com.officina_hide.base.common.FD_EnvData;

/**
 * パッケージの構築のベースモデル検証用クラス<br>
 * <p>本クラスでは、各実用例からのベースパッケージ構築の呼び出しを検証する。<br>
 * ※Git上のブランチは[BaseCreate]を使用する。</p>
 * @author officina-hide.com
 * @version 1.20
 * @since 2020/11/16
 */
public class CreatePackages {

	public static void main(String[] args) {
		/*
		 * 環境情報は[base.properties]を使用する。
		 */
		//環境情報のPathを設定する。
		FD_EnvData env = createEnv();
		//ベース環境構築
		CreateBaseEnvironment cbe = new CreateBaseEnvironment();
		cbe.create(env);
	}

	/**
	 * 環境情報の設定<br>
	 * @author officina-hide.com
	 * @since 1.20 2020/11/16
	 * @return 環境情報
	 */
	private static FD_EnvData createEnv() {
		FD_EnvData env = null;
		try {
			String propPath = new File(".").getCanonicalPath()+"\\data\\base.properties";
			//環境情報の取込
			env = new FD_EnvData(propPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return env;
	}

}

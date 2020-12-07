package com.officina_hide.base.tools;

import java.io.File;
import java.io.IOException;

import com.officina_hide.base.common.FD_EnvData;

/**
 * パッケージ生成[Package generation]<br>
 * <p>本クラスではパッケージの生成を行う。<br>
 * [This class creates a package.]</p>
 * @author officine-hide.com
 * @version 1.30
 * @since 2020/12/05
 */
public class CreatePackages {

	/** 環境情報 */
	private static FD_EnvData env;
	
	public static void main(String[] args) {
		/*
		 * 環境情報の取得[Acquisition of environmental information]
		 */
		try {
			String propPath = new File(".").getCanonicalPath()+"\\data\\fdbase.properties";
			env = new FD_EnvData(propPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * 基盤テーブルの構築[Building a base table]
		 */
//		CreateBaseInformation(env);
	}

}

package com.officina_hide.base.tools;

import java.io.File;
import java.io.IOException;

import com.officina_hide.base.common.FD_EnvData;

/**
 * パッケージ生成[Package generation]<br>
 * <p>本クラスではパッケージの生成を行う。<br>
 * [This class creates a package.]</p>
 * 
 * CREATE DATABASE IF NOT EXISTS FDBASE DEFAULT CHARACTER SET = utf8;
 * CREATE USER 'fdadmin'@'%' IDENTIFIED BY 'fdadminqAz*01';
 * GRANT ALL ON FDBASE.* TO fdadmin;
 * GRANT ALL PRIVILEGES ON FDBASE.* to 'fdadmin'@'%' with Grant option;
 * 
 * @author officine-hide.com
 * @version 1.30
 * @since 2020/12/05
 */
public class CreatePackages {

	/** 環境情報 */
	private static FD_EnvData env;
	/**
	 * 処理レベル<br>
	 * 0 = ベース構築
	 * 1 = タスク情報構築
	 */
	private static int runLevel = 1;
	
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
		if(runLevel == 0) {
			CreateBaseInformation cbi = new CreateBaseInformation();
			cbi.execute(env);
		}
		
		/*
		 * Fx画面用基盤構築z
		 */
		if(runLevel <= 1) {
			CreateFxBaseInformation fbi = new CreateFxBaseInformation();
			fbi.execute(env);
		
		/*
		 * タスク関連情報の構築
		 */
		if(runLevel <= 2) {
			CreateTaskInformation ctf = new CreateTaskInformation();
			ctf.execute(env);
		}
		}
	}

}

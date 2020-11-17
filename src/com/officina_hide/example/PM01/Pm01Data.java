package com.officina_hide.example.PM01;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.tools.FDProcess;
import com.officina_hide.project.tools.FDProject;

/**
 * パッケージ管理データクラス<br>
 * <p>パッケージ管理に関するデータを管理する。</p>
 * @author officine-hide.com
 * @version 1.10
 * @since 2020/11/18
 */
public class Pm01Data {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * パッケージ開発のためのプロジェクトを立ち上げる。
		 */
		//環境情報取得
		FD_EnvData env = null;
		try {
			String propPath = new File(".").getCanonicalPath()+"\\data\\pm01.properties";
			env = new FD_EnvData(propPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//プロセス登録
		FDProcess process = new FDProcess();
		int processId = process.createProcess(env,Pm01Data.class.getSimpleName());
		env.setActiveProcessID(processId);
		//プロジェクト情報登録
		FDProject project = new FDProject();
		project.addData(env, "PackageManagement", "パッケージ構築管理", "PM-0000");

		/*
		 * タスクを登録し、一覧表示する。
		 */
		
		//プロセス終了
		process.endProcess(env, new Date());
	}

}

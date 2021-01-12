package com.officina_hide.base.tests;

import java.io.File;
import java.io.IOException;

import com.officina_hide.base.common.FD_EnvData;

/**
 * タスク情報テスト用クラス<br>
 * Class for testing task information.
 * @author officine-hide.com
 * @version 1.30
 * @since 2021/01/12
 */
public class FD_Task_Test {

	public static void main(String[] args) {
		/*
		 * 環境設定
		 */
		FD_EnvData env = null;
		try {
			String propPath = new File(".").getCanonicalPath()+"\\data\\fdbase.properties";
			env = new FD_EnvData(propPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * タスク情報の登録
		 */
		X_FD_Task task = new X_FD_Task(env);
	}

}

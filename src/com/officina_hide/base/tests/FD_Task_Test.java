package com.officina_hide.base.tests;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.I_FD_Task;
import com.officina_hide.base.model.X_FD_Task;

/**
 * タスク情報テスト用クラス<br>
 * Class for testing task information.
 * @author officine-hide.com
 * @version 1.30
 * @since 2021/01/12
 */
public class FD_Task_Test implements I_FD_Task {

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
		task.setValueByName(env, COLUMNNAME_FD_Task_ID, 0);
		task.setValueByName(env, COLUMNNAME_Task_Subject, "プロジェクト開発");
		task.setValueByName(env, COLUMNNAME_Task_Status, null);
		task.setValueByName(env, COLUMNNAME_Task_StartDateTime, new Date());
	}

}

package com.officina_hide.workshop.task.tasklist;

import java.io.File;
import java.io.IOException;

import com.officina_hide.base.common.FD_EnvData;

/**
 * タスク情報のテストを行う。<br>
 * Test task information.
 * @author officina-hide.com
 * @version 1.30
 * @since 2021/01/11
 */
public class TskTest {

	/** 環境情報 */
	private static FD_EnvData env;
	/** プロセス情報 */
	private static final int TASK_PROCESS_ID = 301;
	
	public static void main(String[] args) {
		/*
		 * 環境情報セット
		 */
		try {
			String propPath = new File(".").getCanonicalPath()+"\\data\\fdbase.properties";
			env = new FD_EnvData(propPath);
			env.setActiveProcessID(TASK_PROCESS_ID);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * タスク情報を登録する。
		 */
		
		//タスク情報一覧画面表示
		FX_TaskList.main(env);
		System.out.println("test");
	}

}

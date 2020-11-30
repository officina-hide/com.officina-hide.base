package com.officina_hide.base.tests;

import java.io.File;
import java.io.IOException;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_Process;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.tools.FDNumbering;
import com.officina_hide.base.tools.FDProcess;
import com.officina_hide.base.tools.FDTable;

/**
 * 採番のテストを行う。<br>
 * <p>Perform a numbering test.</p>
 * @author officine-hide.com
 * @version 1.21
 * @since 2020/11/30
 */
public class NumberingTest {
	
	/** 環境情報 */
	private static FD_EnvData env;
	/**テストプロセス番号 */
	private static int processNo = 999;

	public static void main(String[] args) {
		/*
		 * テスト環境設定
		 */
		createTestEnvironment();
		
		/*
		 * テスト : テーブル情報を追加する際の採番テスト
		 */
		FDNumbering num = new FDNumbering();
		num.addData(env, 999, 999, 0, 1000001);
		int id = num.getNewID(env, "numTest");
		if(id == 1000001) {
			System.out.println("Test OK : "+id);
		} else {
			System.out.println("Test Error : "+id);
			return;
		}
		id = num.getNewID(env, "numTest");
		if(id == 1000002) {
			System.out.println("Test OK : "+id);
		} else {
			System.out.println("Test Error : "+id);
			return;
		}
		
		/*
		 * テスト2 : 採番Keyをセットした場合の採番テスト
		 */
		id = num.getNewID(env, "numTest", "SSSS-0000");
		if(id == 1) {
			System.out.println("Test OK : "+id);
		} else {
			System.out.println("Test Error : "+id);
			return;
		}
		id = num.getNewID(env, "numTest", "SSSS-0000");
		if(id == 2) {
			System.out.println("Test OK : "+id);
		} else {
			System.out.println("Test Error : "+id);
			return;
		}
		
//		//関連情報リセット処理
//		resetBaseData(env, 0);
//		resetBaseData(env, processNo);
	}

	/**
	 * テスト環境設定
	 */
	private static void createTestEnvironment() {
		try {
			String propPath = new File(".").getCanonicalPath()+"\\data\\base.properties";
			//環境情報の取込
			env = new FD_EnvData(propPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//関連情報リセット処理
		resetBaseData(env, 0);
		resetBaseData(env, processNo);
		
		//プロセス情報生成
		FDProcess process = new FDProcess();
		int processId = process.createProcess(env, processNo, "Test");
		env.setActiveProcessID(processId);
		
		//テスト用のテーブル情報追加
		FDTable table = new FDTable();
		table.addData(env, processId, "numTest", "採番テストテーブル情報", null);
	}

	/**
	 * 関連情報削除
	 * @param env 環境情報
	 * @param processNo プロセス情報ID
	 */
	private static void resetBaseData(FD_EnvData env, int processId) {
		FD_DB_Utility dbutil = new FD_DB_Utility();
		dbutil.deleteDataByProcessId(env, I_FD_Process.Table_Name, processId);
		dbutil.deleteDataByProcessId(env, I_FD_Log.Table_Name, processId);
		dbutil.deleteDataByProcessId(env, I_FD_Table.Table_Name, processId);
		dbutil.deleteDataByProcessId(env, I_FD_Numbering.Table_Name, processId);
	}

}

package com.officina_hide.base.tools;

import java.io.File;
import java.io.IOException;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Table;

public class BaseTest {

	/**
	 * ベースパッケージテストクラス
	 * @param args
	 */
	public static void main(String[] args) {
		FD_EnvData env = null;
		try {
			String propPath = new File(".").getCanonicalPath()+"\\data\\base.properties";
			//環境情報の取込
			env = new FD_EnvData(propPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * 特定の採番を行うための改良テスト
		 */
		FDProcess process = new FDProcess();
		int processId = process.createProcess(env, "Test");
		env.setActiveProcessID(processId);
		
		FDTableColumn column = new FDTableColumn();
		int columnId = column.getColumnId(env, I_FD_Table.TABLE_ID, I_FD_Table.COLUMNNAME_Table_Name);
		FDNumbering num = new FDNumbering();
		num.addData(env, 0, I_FD_Table.TABLE_ID, 0, 1, columnId, "SSDS");
		FD_DB DB = new FD_DB();
		int id = DB.getNewID(env, I_FD_Table.Table_Name, columnId, "SSDS");
		int tid = DB.getNewID(env, I_FD_Table.Table_Name);
		System.out.println(id+":"+tid);
	}

}

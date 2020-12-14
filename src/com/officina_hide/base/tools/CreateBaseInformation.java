package com.officina_hide.base.tools;

import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FDLog;
import com.officina_hide.base.model.FDNumbering;
import com.officina_hide.base.model.FDProcess;
import com.officina_hide.base.model.FDReference;
import com.officina_hide.base.model.FDTable;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_Process;
import com.officina_hide.base.model.I_FD_Reference;
import com.officina_hide.base.model.I_FD_Table;

/**
 * 基盤情報生成[Basic information generation]<br>
 * @author officina-hide.com
 * @version 1.30
 * @since 2020/12/07
 */
public class CreateBaseInformation {
	//プロセスID
	private final static int ThisProcess_ID = 101;

	/**
	 * 生成実行[Execution of generation]<br>
	 * @author officina-hide.com
	 * @since 1.30 2020/12/07
	 * @param env
	 */
	public void execute(FD_EnvData env) {
		//プロセス情報IDセット
		env.setActiveProcessID(ThisProcess_ID);
		//開始時刻保存
		Date startDate = new Date();

		/*
		 * 基盤情報で使用する、テーブルの生成と情報の登録を行う。<br>
		 * Create a table and register information to be used in the basic information.
		 * 生成する情報は以下の通り。
		 * ・ログ情報
		 * ・テーブル情報
		 */
		//ログ情報構築
		FDLog log = new FDLog();
		log.createTable(env);
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, "基盤環境構築開始");
		//プロセス情報構築
		FDProcess process = new FDProcess(); 
		process.createTable(env);
		
		//プロセス情報登録
		process.create(env, ThisProcess_ID,  CreateBaseInformation.class.getSimpleName(), startDate);
		
		//テーブル情報構築
		FDTable table = new FDTable();
		table.createTable(env);
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.createTable(env);
		//リファレンス情報構築
		FDReference ref = new FDReference();
		ref.createTable(env);
		
		//先行構築テーブルのテーブル情報登録
		table.addData(env, I_FD_Log.TABLE_ID, I_FD_Log.Table_Name, I_FD_Log.NAME, I_FD_Log.COMMENT);
		table.addData(env, I_FD_Process.TABLE_ID, I_FD_Process.Table_Name, I_FD_Process.NAME, I_FD_Process.COMMENT);
		table.addData(env, I_FD_Table.TABLE_ID, I_FD_Table.Table_Name, I_FD_Table.NAME, I_FD_Table.COMMENT);
		table.addData(env, I_FD_Numbering.TABLE_ID, I_FD_Numbering.Table_Name, I_FD_Numbering.NAME, I_FD_Numbering.COMMENT);
		table.addData(env, I_FD_Reference.TABLE_ID, I_FD_Reference.Table_Name, I_FD_Reference.NAME, I_FD_Reference.COMMENT);
		//先行構築テーブルの採番情報登録
		num.addData(env,I_FD_Table.TABLE_ID, I_FD_Table.TABLE_ID, 0, 1000001);
		num.addData(env, I_FD_Process.TABLE_ID, I_FD_Process.TABLE_ID, 0, 1000001);
		num.addData(env, I_FD_Numbering.TABLE_ID, I_FD_Numbering.TABLE_ID, 0, 1000001);
		num.addData(env, I_FD_Reference.TABLE_ID, I_FD_Reference.TABLE_ID, 0, 1000001);
		
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, "基盤環境構築完了");
	}

}

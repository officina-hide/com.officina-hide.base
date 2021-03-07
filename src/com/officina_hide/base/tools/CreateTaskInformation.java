package com.officina_hide.base.tools;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FDLog;
import com.officina_hide.base.model.FDProcess;
import com.officina_hide.base.model.FDTask;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_Process;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.model.I_FD_Task;
import com.officina_hide.fx.model.FDView;
import com.officina_hide.fx.model.FDViewColumn;
import com.officina_hide.fx.model.I_FD_View;

/**
 * タスク関連情報構築<br>
 * Building information related to tasks.<br>
 * @author officina-hide.com
 * @version 1.30
 * @since 2020/12/28
 */
public class CreateTaskInformation {
	//プロセスID
	private final static int ThisProcess_ID = 201;

	/**
	 * 構築実行<br>
	 * Execution of generation<br>
	 * @param env 環境情報
	 */
	public void execute(FD_EnvData env) {
		/*
		 * 環境リセット<br>
		 * 使用するプロセス情報を尊重持った情報を削除する。<br>
		 */
		resetBaseData(env, 0);
		resetBaseData(env, ThisProcess_ID);
		//プロセス情報IDセット
		env.setActiveProcessID(ThisProcess_ID);
		//プロセス情報登録
		FDProcess process = new FDProcess();
		process.create(env, ThisProcess_ID,  CreateBaseInformation.class.getSimpleName(), new Date());
		//開始メッセージ
		FDLog log = new FDLog();
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, "タスク関連情報構築開始");
		
		FDView view = new FDView();
		FDViewColumn vcolumn = new FDViewColumn();
		//タスク一覧画面情報生成
		int tlId = view.addData(env, 0, "Task_List", 600, 400);
		vcolumn.addData(env, 0, tlId, "status", "状況");
		
		//タスク照会画面情報生成
		int tvId = view.addData(env, 0, "Task_View", 400, 400);
		vcolumn.addData(env, 0, tvId, "status", "状況");
		
		//タスク情報生成
		FDTask task = new FDTask();
		task.createTable(env);
		Calendar cal = new GregorianCalendar(new Locale("ja", "JP"));
		cal.setTime(new Date());
		task.addData(env, "AAAAA", cal, I_FD_Task.Taks_Status_Code_NotStarted);
		task.addData(env, "BBBBBB", cal, I_FD_Task.Taks_Status_Code_NotStarted);
		
		//終了メッセージ
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, "タスク関連情報構築完了");
	}

	/**
	 * 環境リセット<br>
	 * @author officina-hide.com
	 * @since 1.30 2020/12/31
	 * @param env 環境情報
	 * @param processId プロセス情報ID 
	 */
	private void resetBaseData(FD_EnvData env, int processId) {
		FD_DB_Utility dbUtil = new FD_DB_Utility();
		dbUtil.deleteDataByProcessId(env, I_FD_Log.Table_Name, processId);
		dbUtil.deleteDataByProcessId(env, I_FD_Process.Table_Name, processId);
		dbUtil.deleteDataByProcessId(env, I_FD_Table.Table_Name, processId);
		dbUtil.deleteDataByProcessId(env, I_FD_TableColumn.Table_Name, processId);
		dbUtil.deleteDataByProcessId(env, I_FD_Numbering.Table_Name, processId);
		dbUtil.deleteDataByProcessId(env, I_FD_View.Table_Name, processId);
	}

}

package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_Process;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.fx.model.FDView;

/**
 * Fx画面用基盤構築<br>
 * Infrastructure construction for Fx screen.
 * @author officine-hide.com
 * @version 1.31
 * @since 2021/02/02
 */
public class CreateFxBaseInformation {
	//プロセスID
	private final static int ThisProcess_ID = 202;

	/**
	 * 構築実行<br>
	 * Execution of generation<br>
	 * @author officine-hide.com
	 * @since 1.31 2021/02/04
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
		
		//画面情報登録
		FDView view  = new FDView();
		view.createTable(env);
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
	}

}

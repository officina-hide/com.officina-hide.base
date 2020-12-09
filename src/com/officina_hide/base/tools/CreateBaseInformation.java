package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FDLog;
import com.officina_hide.base.model.FDTable;

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
		//テーブル情報構築
		FDTable table = new FDTable();
		table.createTable(env);
	}

}

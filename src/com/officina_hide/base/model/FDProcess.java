package com.officina_hide.base.model;

import java.util.Date;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;

/**
 * プロセス情報クラス[Process information class]<br>
 * @author officine-hide.com
 * @version 1.30
 * @since 2020/12/11
 */
public class FDProcess extends FD_DB implements I_FD_Process {

	/**
	 * プロセス情報テーブル構築[Process information table construction]<br>
	 * @author officine-hide.com
	 * @since 1.30 2020/12/11
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		StringBuffer sqlDrop = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		
		FDLog log = new FDLog();
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築開始");
		
		//既に登録されているプロセス情報を削除する。
		sqlDrop.append("DROP TABLE IF EXISTS ").append(Table_Name);
		DBUpdateExecution(env, sqlDrop.toString());
		
		//プロセス情報テーブル生成
		sql.append("CREATE TABLE IF NOT EXISTS ").append(Table_Name).append(" (");
		
		sql.append(I_FD_Process.COLUMNNAME_FD_Process_ID).append(" INT UNSIGNED NOT NULL PRIMARY KEY ")
			.append("COMMENT ").append(FD_SQ).append(I_FD_Process.NAME_FD_Process_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Process_Name).append(" VARCHAR(100) ")
			.append("COMMENT ").append(FD_SQ).append(NAME_Process_Name).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Process_StartTime).append(" DATETIME ")
			.append("COMMENT ").append(FD_SQ).append(NAME_Process_StartTime).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Process_EndTime).append(" DATETIME ")
			.append("COMMENT ").append(FD_SQ).append(NAME_Process_EndTime).append(FD_SQ).append(",");
		
		FD_DB_Utility dutil = new FD_DB_Utility();
		sql.append(dutil.getBaseSQLStrings(NAME));		
		DBUpdateExecution(env, sql.toString());
		
		//ログ情報の構築をログ情報に登録する。
		log.addLog(env, I_FD_Log.LOGTYPE_Table_Drop_ID, changeEscape(sqlDrop.toString()));
		log.addLog(env, I_FD_Log.LOGTYPE_Table_Create_ID, changeEscape(sql.toString()));
		
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築完了");
	}

	/**
	 * プロセス情報生成[Process information generation]<br>
	 * @author officine-hide.com
	 * @since 1.20 2020/12/11
	 * @param processID プロセス情報ID
	 * @param processName プロセス名
	 * @param startCal 開始時間
	 * @return プロセス情報ID
	 */
	public int create(FD_EnvData env, int processID, String processName, Date startCal) {
		X_FD_Process process = new X_FD_Process(env);
		process.setValueByName(env, I_FD_Process.COLUMNNAME_FD_Process_ID, processID);
		process.setValueByName(env, COLUMNNAME_Process_Name, processName);
		process.setValueByName(env, COLUMNNAME_Process_StartTime, startCal);
		process.save(env);
		
		return process.getintOfValue(I_FD_Process.COLUMNNAME_FD_Process_ID);
	}

}

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
	/** ログ情報 */
	private FDLog log = new FDLog();

	/**
	 * プロセス情報テーブル構築[Process information table construction]<br>
	 * @author officine-hide.com
	 * @since 1.30 2020/12/11
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		StringBuffer sqlDrop = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		
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

	/**
	 * プロセス情報テーブル項目情報登録
	 * @author officine-hide.com
	 * @since 1.10 2020/10/30
	 * @param env 環境情報
	 */
	public void addTableColumn(FD_EnvData env) {
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"のテーブル項目情報登録開始");

		FDTableColumn column = new FDTableColumn();
		
		column.addData(env, 0, TABLE_ID, I_FD_Process.COLUMNNAME_FD_Process_ID, I_FD_Process.NAME_FD_Process_ID
				, I_FD_Process.COMMENT_FD_Process_ID, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 10, "Y", "Y");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_Process_Name, NAME_Process_Name, COMMENT_Process_Name
				, COLUMNTYPE_ID_FD_Text, null, 100, 20, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_Process_StartTime, NAME_Process_StartTime, COMMENT_Process_StartTime
				, COLUMNTYPE_ID_FD_Date, null, 0, 30, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_Process_EndTime, NAME_Process_EndTime, COMMENT_Process_EndTime
				, COLUMNTYPE_ID_FD_Date, null, 0, 40, "N", "N");

		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_CREATE, NAME_FD_CREATE, COMMENT_FD_CREATE
				, COLUMNTYPE_ID_FD_Date, null, 0, 900, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_CREATED, NAME_FD_CREATED, COMMENT_FD_CREATED
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 910, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_UPDATE, NAME_FD_UPDATE, COMMENT_FD_UPDATE
				, COLUMNTYPE_ID_FD_Date, null, 0, 920, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_UPDATED, NAME_FD_UPDATED, COMMENT_FD_UPDATED
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 930, "N", "N");

		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"のテーブル項目情報登録完了");
	}

}

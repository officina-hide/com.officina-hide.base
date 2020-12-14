package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;

/**
 * 採番情報クラス[Numbering information class]<br>
 * @author officine-hide.com
 * @version 1.30
 * @since 2020/12/13
 */
public class FDNumbering extends FD_DB implements I_FD_Numbering {

	/**
	 * 採番情報テーブル構築[Numbering information table construction]
	 * @author officine-hide.com
	 * @since 1.30 2020/12/13
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
		
		//テーブル情報の再構築
		sql.append("CREATE TABLE IF NOT EXISTS ").append(Table_Name).append(" (");
		
		sql.append(COLUMNNAME_FD_Numbering_ID).append(" INT UNSIGNED NOT NULL PRIMARY KEY COMMENT ")
			.append(FD_SQ).append(NAME_FD_Numbering_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_Table_ID).append(" INT UNSIGNED NOT NULL COMMENT ")
			.append(FD_SQ).append(NAME_FD_Table_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Current_Number).append(" INT UNSIGNED DEFAULT 0 COMMENT ")
			.append(FD_SQ).append(NAME_Current_Number).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Initial_Number).append(" INT UNSIGNED DEFAULT 0 COMMENT ")
			.append(FD_SQ).append(NAME_Initial_Number).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Numbering_Key).append(" VARCHAR(100) ")
			.append(" COMMENT ").append(FD_SQ).append(NAME_Numbering_Key).append(FD_SQ).append(",");
		
		FD_DB_Utility dutil = new FD_DB_Utility();
		sql.append(dutil.getBaseSQLStrings(NAME));		
		DBUpdateExecution(env, sql.toString());
		
		//ログ情報の構築をログ情報に登録する。
		log.addLog(env, I_FD_Log.LOGTYPE_Table_Drop_ID, changeEscape(sqlDrop.toString()));
		log.addLog(env, I_FD_Log.LOGTYPE_Table_Create_ID, changeEscape(sql.toString()));
		
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築完了");
	}

	/**
	 * テーブル用採番情報登録<br>
	 * @author officina-hide.com
	 * @since 1.21 2020/11/21
	 * @param env 環境情報
	 * @param numberingId 採番情報ID
	 * @param tableId テーブル情報ID
	 * @param initNo 初期値
	 * @param currentNo 現在値
	 * @param initNo
	 */
	public void addData(FD_EnvData env, int numberingId, int tableId, int currentNo, int initNo) {
		addData(env, numberingId, tableId, currentNo, initNo, null);
	}

	/**
	 * 採番情報登録<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/13
	 * @param env 環境情報
	 * @param numberingId 採番情報ID
	 * @param tableId テーブル情報ID
	 * @param initNo 初期値
	 * @param currentNo 現在値
	 * @param columnId テーブル項目情報ID
	 * @param key 採番Key
	 */
	public void addData(FD_EnvData env, int numberingId, int tableId, int currentNo, int initNo,  String  key) {
		X_FD_Numbering num = new X_FD_Numbering(env);
		num.setValueByName(env, COLUMNNAME_FD_Numbering_ID, numberingId);
		num.setValueByName(env, COLUMNNAME_FD_Table_ID, tableId);
		num.setValueByName(env, COLUMNNAME_Current_Number, currentNo);
		num.setValueByName(env, COLUMNNAME_Initial_Number, initNo);
		num.setValueByName(env, COLUMNNAME_Numbering_Key, key);
		num.save(env);
	}

}

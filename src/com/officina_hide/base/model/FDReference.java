package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;

/**
 * リファレンス情報クラス[Reference information class]<br>
 * @author officina-hide.com
 * @version 1.30
 * @since 2020/12/14
 */
public class FDReference extends FD_DB implements I_FD_Reference {

	/**
	 * リファレンス情報テーブル構築[Reference information table construction]<br>
	 * @author officina-hide.com
	 * @since 1.30 2020/12/14
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		FDLog log = new FDLog();
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築開始");

		StringBuffer sqlDrop = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		//既に登録されているテーフル情報を削除する。
		sqlDrop.append("DROP TABLE IF EXISTS ").append(Table_Name);
		DBUpdateExecution(env, sqlDrop.toString());
		
		//テーブル情報の再構築
		sql.append("CREATE TABLE IF NOT EXISTS ").append(Table_Name).append(" (");

		sql.append(COLUMNNAME_FD_Reference_ID).append(" INT UNSIGNED NOT NULL PRIMARY KEY COMMENT ")
			.append(FD_SQ).append(NAME_FD_Reference_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Reference_Name).append(" Varchar(100) COMMENT ")
			.append(FD_SQ).append(NAME_Reference_Name).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_Name).append(" Varchar(100) COMMENT ")
			.append(FD_SQ).append(NAME_FD_Name).append(FD_SQ).append(",");

		FD_DB_Utility dutil = new FD_DB_Utility();
		sql.append(dutil.getBaseSQLStrings(NAME));		
		DBUpdateExecution(env, sql.toString());
		
		//ログ情報の構築をログ情報に登録する。
		log.addLog(env, I_FD_Log.LOGTYPE_Table_Drop_ID, changeEscape(sqlDrop.toString()));
		log.addLog(env, I_FD_Log.LOGTYPE_Table_Create_ID, changeEscape(sql.toString()));

		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築完了");
	}

}

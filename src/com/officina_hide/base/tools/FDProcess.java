package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_Process;

/**
 * プロセス情報クラス<br>
 * @author officine-hide.com
 * @version 1.10
 * @since 2020/10/29
 */
public class FDProcess extends FD_DB implements I_FD_Process {

	/**
	 * プロセス情報テーブル構築<br>
	 * @author officine-hide.com
	 * @since 1.10 2020/10/29
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築開始");
		
		StringBuffer sqlDrop = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		
		//既に登録されているプロセス情報を削除する。
		sqlDrop.append("DROP TABLE IF EXISTS ").append(Table_Name);
		DBexecute(env, sqlDrop.toString());
		//プロセス情報テーブル構築
		sql.append("CREATE TABLE IF NOT EXISTS ").append(Table_Name).append(" (");
		
		sql.append(I_FD_Process.COLUMNNAME_FD_Process_ID).append(" INT UNSIGNED NOT NULL PRIMARY KEY COMMENT ")
			.append(FD_SQ).append(I_FD_Process.NAME_FD_Process_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Process_Name).append(" VARCHAR(100) ")
			.append("COMMENT ").append(FD_SQ).append(NAME_Process_Name).append(FD_SQ).append(",");
		
		sql.append(COLUMNNAME_FD_CREATE).append(" DATETIME COMMENT ")
			.append(FD_SQ).append(NAME_FD_CREATE).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_CREATED).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_FD_CREATED).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATE).append(" DATETIME COMMENT ")
			.append(FD_SQ).append(NAME_FD_UPDATE).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATED).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_FD_UPDATED).append(FD_SQ).append(" ");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=").append(FD_SQ).append(NAME).append(FD_SQ);
		
		DBexecute(env, sql.toString());
		
		//プロセス情報の構築をログ情報に登録する。
		addLog(env, I_FD_Log.LOGTYPE_Table_Drop_ID, changeEscape(sqlDrop.toString()));
		
	}

}

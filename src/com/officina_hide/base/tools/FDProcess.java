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
		StringBuffer sqlDrop = new StringBuffer();
		//既に登録されているログ情報を削除する。
		sqlDrop.append("DROP TABLE IF EXISTS ").append(Table_Name);
		DBexecute(env, sqlDrop.toString());
		
		//ログ情報の構築をログ情報に登録する。
		addLog(env, I_FD_Log.LOGTYPE_Table_Drop_ID, changeEscape(sqlDrop.toString()));
	}

}

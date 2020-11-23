package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_ReferenceList;

/**
 * リファレンスリスト情報クラス<br>
 * @author officina-hide.com
 * @version 1.21
 * @since 2020/11/23
 */
public class FDReferenceList extends FD_DB implements I_FD_ReferenceList {

	/**
	 * リファレンスリスト情報テーブル構築<br>
	 * @author officina-hide.com
	 * @since 1.21 2020/11/23
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME + "テーブル構築開始");

		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, TABLE_ID, Table_Name, NAME, COMMENT);
		
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		FD_DB_Utility dutil = new FD_DB_Utility();
		dutil.baseColumnEntry(env, TABLE_ID);
	
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME + "テーブル構築開始");
}

}

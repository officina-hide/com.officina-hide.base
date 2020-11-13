package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_UniqueIndex;

/**
 * ユニーク制約インデックス情報クラス<br>
 * @author officine-hide.com
 * @version 1.20
 * @since 2020/11/13
 */
public class FDUniqueIndex extends FD_DB implements I_FD_UniqueIndex {

	/**
	 * ユニーク制約インデックス情報テーブル構築<br>
	 * @author officine-hide.com
	 * @since 1.20 2020/11/13
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築開始");

		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, TABLE_ID, Table_Name, NAME, COMMENT);
		
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築完了");
	}

}

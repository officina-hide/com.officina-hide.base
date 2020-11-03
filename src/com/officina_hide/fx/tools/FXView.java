package com.officina_hide.fx.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.tools.FDTable;
import com.officina_hide.fx.model.I_FX_View;

/**
 * Fx画面情報クラス<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/10/29
 */
public class FXView extends FD_DB implements I_FX_View {

	/**
	 * Fx画面情報テーブル構築<br>
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築開始");
		
		//テーブル情報構築
		FDTable table = new FDTable();
		table.addData(env, TABLE_ID, Table_Name, NAME, COMMENT);

	}

}

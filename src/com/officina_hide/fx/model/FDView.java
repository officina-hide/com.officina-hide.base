package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FDLog;
import com.officina_hide.base.model.FDTable;
import com.officina_hide.base.model.FDTableColumn;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Log;

/**
 * Fx画面情報クラス[Fx screen information class]<br>
 * @author officine-hide.com
 * @version 1.31
 * @since 2021/02/04
 */
public class FDView extends FD_DB implements I_FD_View {

	/**
	 * Fx画面テーブル構築[Fx screen table construction]<br>
	 * @author officine-hide.com
	 * @since 1.31 2021/02/04
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		FDLog log = new FDLog();
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築開始");
		
		//テーブル情報登録
		FDTable table = new FDTable();
		int tableId = table.addData(env, TABLE_ID, Table_Name, NAME, COMMENT);
		
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.addBaseTableColumnData(env, tableId);
	}

}

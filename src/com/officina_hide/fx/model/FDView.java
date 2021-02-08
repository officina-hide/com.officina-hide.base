package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FDLog;
import com.officina_hide.base.model.FDNumbering;
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
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_View_ID, NAME_FD_View_ID, COMMENT_FD_View_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 10, "Y", "Y");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_View_Name, NAME_View_Name, COMMENT_View_Name
				, COLUMNTYPE_ID_FD_Field_Text, null, 100, 20, "N", "N");
		column.addBaseTableColumnData(env, tableId);
		
		//テーブル生成
		FD_DB_Utility dbUtil = new FD_DB_Utility();
		dbUtil.createDBTable(env, TABLE_ID);
		
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.addData(env, TABLE_ID, TABLE_ID, 0, 1000001);

		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築完了");
	}

	/**
	 * 情報登録[Entry of information.]
	 * @author officina-hide.com
	 * @since 1.31 2021/02/08
	 * @param env 環境情報
	 * @param viewId Fx画面情報ID
	 * @param viewName Fx画面情報名
	 */
	public void addData(FD_EnvData env, int viewId, String viewName) {
		X_FD_View view = new X_FD_View(env);
		view.setValueByName(env, COLUMNNAME_FD_View_ID, viewId);
		view.setValueByName(env, COLUMNNAME_View_Name, viewName);
		view.save(env);
	}

}

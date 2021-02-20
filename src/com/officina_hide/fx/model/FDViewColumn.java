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
 * Fx画面項目情報[Fx screen item information]<br>
 * @author officina-hide.com
 * @version 1.31
 * @since 2021/02/20
 */
public class FDViewColumn extends FD_DB implements I_FD_ViewColumn {

	/**
	 * Fx画面項目テーブル構築[Fx screen item table construction]<br>
	 * @author officina-hide.com
	 * @since 1.31 2021/02/20
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		FDLog log = new FDLog();
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築開始");
		
		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, TABLE_ID, Table_Name, NAME, COMMENT);
		
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_ViewColumn_ID, NAME_FD_ViewColumn_ID, COMMENT_FD_ViewColumn_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 10, "Y", "Y");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_View_ID, NAME_FD_View_ID, COMMENT_FD_View_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 20, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_ViewColumn_Name, NAME_ViewColumn_Name, COMMENT_ViewColumn_Name
				, COLUMNTYPE_ID_FD_Text, null, 100, 20, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Name, NAME_FD_Name, COMMENT_FD_Name
				, COLUMNTYPE_ID_FD_Text, null, 100, 40, "N", "N");
		column.addBaseTableColumnData(env, TABLE_ID);
		
		//テーブル生成
		FD_DB_Utility dbUtil = new FD_DB_Utility();
		dbUtil.createDBTable(env, TABLE_ID);

		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.addData(env, TABLE_ID, TABLE_ID, 0, 1000001);

		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築完了");
	}

	/**
	 * Fx画面項目情報登録[Fx screen item information registration]<br>
	 * @author officina-hide.com
	 * @since 1.31 2021/02/20
	 * @param env 環境情報
	 * @param viewColunId Fx画面項目情報ID
	 * @param viewId Fx画面情報ID
	 * @param viewName FX画面項目名
	 * @param name Fx画面項目表示名
	 */
	public void addData(FD_EnvData env, int viewColumnId, int viewId, String viewName, String name) {
		X_FD_ViewColumn vcolumn = new X_FD_ViewColumn(env);
		vcolumn.setValueByName(env, COLUMNNAME_FD_ViewColumn_ID, viewColumnId);
		vcolumn.setValueByName(env, COLUMNNAME_FD_View_ID, viewId);
		vcolumn.setValueByName(env, COLUMNNAME_ViewColumn_Name, viewName);
		vcolumn.setValueByName(env, COLUMNNAME_FD_Name, name);
		vcolumn.save(env);
	}

}

package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;

/**
 * タスク情報クラス[Task information class]<br>
 * @author officina-hide.com
 * @version 1.30
 * @since 2021/01/04
 */
public class FDTask extends FD_DB implements I_FD_Task {

	/**
	 * タスク情報テーブル構築[Build a table of task information.]<br>
	 * @author officina-hide.com
	 * @since 1.30 2021/01/04
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, TABLE_ID, Table_Name, NAME, COMMENT);
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Task_ID, NAME_FD_Task_ID, COMMENT_FD_Task_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 10, "Y", "Y");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_Task_Subject, NAME_Task_Subject, COMMENT_Task_Subject
				, COLUMNTYPE_ID_FD_Text, null, 200, 20, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_Task_Status, NAME_Task_Status, COMMENT_Task_Status
				, COLUMNTYPE_ID_FD_List, null, 0, 30, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_Task_StartDateTime, NAME_Task_StartDateTime, COMMENT_Task_StartDateTime
				, COLUMNTYPE_ID_FD_Date, null, 0, 40, "N", "N");
		column.addBaseTableColumnData(env, TABLE_ID);
		//テーブル生成
		FD_DB_Utility dbUtil = new FD_DB_Utility();
		dbUtil.createDBTable(env, TABLE_ID);
		
	}
	
}

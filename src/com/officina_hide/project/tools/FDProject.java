package com.officina_hide.project.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.tools.FDTable;
import com.officina_hide.base.tools.FDTableColumn;
import com.officina_hide.project.model.I_FD_Project;

/**
 * プロジェクト情報クラス<br>
 * @author officine-hide.com
 * @version 1.20
 * @since 2020/11/06
 */
public class FDProject extends FD_DB implements I_FD_Project {

	/**
	 * プロジェクト情報テーブル構築<br>
	 * @author officine-hide.com
	 * @since 1.20 2020/11/06
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築開始");
		
		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, TABLE_ID, Table_Name, NAME, COMMENT);
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Project_ID, NAME_FD_Project_ID, COMMENT_FD_Project_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 10, "Y", "Y");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_Project_Name, NAME_Project_Name, COMMENT_Project_Name
				, COLUMNTYPE_ID_FD_Text, null, 100, 20, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Name, NAME_FD_Name, COMMENT_FD_Name
				, COLUMNTYPE_ID_FD_Text, null, 100, 30, "N", "N");
		
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Process_ID, NAME_FD_Process_ID, COMMENT_FD_Process_ID
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 900, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_CREATE, NAME_FD_CREATE, COMMENT_FD_CREATE
				, COLUMNTYPE_ID_FD_Date, null, 0, 910, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_CREATED, NAME_FD_CREATED, COMMENT_FD_CREATED
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 920, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_UPDATE, NAME_FD_UPDATE, COMMENT_FD_UPDATE
				, COLUMNTYPE_ID_FD_Date, null, 0, 930, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_UPDATED, NAME_FD_UPDATED, COMMENT_FD_UPDATED
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 940, "N", "N");
		//テーブル構築
		createDBTable(env, TABLE_ID);
		
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築完了");
	}

}

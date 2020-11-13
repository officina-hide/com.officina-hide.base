package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_UniqueIndex;
import com.officina_hide.base.model.X_FD_UniqueIndex;

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
		
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_UniqueIndex_ID, NAME_FD_UniqueIndex_ID, COMMENT_FD_UniqueIndex_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 10, "Y", "Y");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Table_ID, NAME_FD_Table_ID, COMMENT_FD_Table_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 20, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_Index_Name, NAME_Index_Name, COMMENT_Index_Name
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
		
		//テーブル生成
		createDBTable(env, TABLE_ID);
		
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.addData(env, TABLE_ID, TABLE_ID, 0, 1000001, 0, null);
		
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築完了");
	}

	public void addData(FD_EnvData env, int uniqueInexId, int tableId, String indexName) {
		X_FD_UniqueIndex uidx = new X_FD_UniqueIndex(env);
		uidx.setValueByName(env, COLUMNNAME_FD_UniqueIndex_ID, uniqueInexId);
		uidx.setValueByName(env, COLUMNNAME_FD_Table_ID, tableId);
		uidx.setValueByName(env, COLUMNNAME_Index_Name, indexName);
		uidx.save(env);
	}

}

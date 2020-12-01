package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_UniqueColumn;
import com.officina_hide.base.model.X_FD_UniqueColumn;

/**
 * ユニーク制約項目情報<br>
 * @author officine-hide.com
 * @version 1.20
 * @since 2020/11/13
 */
public class FDUniqueColumn extends FD_DB implements I_FD_UniqueColumn {

	/**
	 * ユニーク制約項目情報構築<br>
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
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_UniqueColumn_ID, NAME_FD_UniqueColumn_ID, COMMENT_FD_UniqueColumn_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 10, "Y", "Y");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_UniqueIndex_ID, NAME_FD_UniqueIndex_ID, COMMENT_FD_UniqueIndex_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 20, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_TableColumn_ID, NAME_FD_TableColumn_ID, COMMENT_FD_TableColumn_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 30, "N", "N");
		
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
		
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.addData(env, TABLE_ID, TABLE_ID, 0, 1000001);
		
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築完了");		
	}

	/**
	 * 情報登録<br>
	 * @author officina-hide.com
	 * @since 1.20 2020/11/14
	 * @param env 環境情報
	 * @param uniqueClmId ユニーク制約項目情報ID
	 * @param uniqueIdxId ユニーク制約インデックス情報ID
	 * @param columnId テーブル項目情報ID
	 */
	/**
	 * @param env
	 * @param uniqueClmId
	 * @param uniqueIdxId
	 * @param columnId
	 */
	public void addData(FD_EnvData env, int uniqueClmId, int uniqueIdxId, int columnId) {
		X_FD_UniqueColumn uclm = new X_FD_UniqueColumn(env);
		uclm.setValueByName(env, COLUMNNAME_FD_UniqueColumn_ID, 0);
		uclm.setValueByName(env, COLUMNNAME_FD_UniqueIndex_ID, uniqueIdxId);
		uclm.setValueByName(env, COLUMNNAME_FD_TableColumn_ID, columnId);
		uclm.save(env);
	}

}

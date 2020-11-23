package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_ColumnForm;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.X_FD_ColumnForm;

/**
 * 項目書式情報クラス<br>
 * @author officine-hide.com
 * @version 1.21 新規作成
 * @since 2020/11/20
 */
public class FDColumnForm extends FD_DB implements I_FD_ColumnForm {

	/**
	 * 項目書式情報テーブル構築<br>
	 * @author officine-hide.com
	 * @since 1.21 2020/11/20
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME + "テーブル構築開始");

		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, TABLE_ID, Table_Name, NAME, COMMENT);
		
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_ColumnForm_ID, NAME_FD_ColumnForm_ID, COMMENT_FD_ColumnForm_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 10, "Y", "Y");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_ColumnForm_Name, NAME_ColumnForm_Name, COMMENT_ColumnForm_Name
				, COLUMNTYPE_ID_FD_Text, null, 100, 20, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Name, NAME_FD_Name, COMMENT_FD_Name
				, COLUMNTYPE_ID_FD_Text, null, 100, 30, "N", "N");
		
		FD_DB_Utility dutil = new FD_DB_Utility();
		dutil.baseColumnEntry(env, TABLE_ID);
		
		//テーブル構築
		createDBTable(env, TABLE_ID);
		
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.addData(env, TABLE_ID, TABLE_ID, 0, 1000001);
		
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME + "テーブル構築完了");
	}

	/**
	 * 項目書式情報登録<br>
	 * @author officina-hide.com
	 * @since 1.20 2020/11/22
	 * @param env 環境情報
	 * @param columnFormName 項目書式名
	 * @param name 項目書式表示名
	 * @return 項目書式情報ID
	 */
	public int addData(FD_EnvData env, String columnFormName, String name) {
		X_FD_ColumnForm clmForm = new X_FD_ColumnForm(env);
		clmForm.setValueByName(env, COLUMNNAME_FD_ColumnForm_ID, 0);
		clmForm.setValueByName(env, COLUMNNAME_ColumnForm_Name, columnFormName);
		clmForm.setValueByName(env, COLUMNNAME_FD_Name, name);
		clmForm.save(env);
		return clmForm.getintOfValue(COLUMNNAME_FD_ColumnForm_ID);
	}
		
}

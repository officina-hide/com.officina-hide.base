package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * テーブル参照情報クラス[Table reference information class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/12/21 Ver. 1.00
 */
public class FD_TableReference extends FD_DB implements I_FD_TableReference{

	/**
	 * テーブル構築[Table construction]<br>
	 * @author officina-hide.net
	 * @since 2021/12/21 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
		//採番情報登録
		FD_Numbering num = new FD_Numbering();
		num.add(env, 0, Table_ID, 1000001, 0);
		//辞書情報登録
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FD_TableReference_ID, NAME_FD_TableReference_ID, COMMENT_FD_TableReference_ID);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, Table_ID, COLUMNNAME_FD_TableReference_ID, FD_ITEM_ID, 0, false, true, "0");
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Reference_ID, FD_ITEM_ID, 0, true, false, "0");
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Table_ID, FD_ITEM_ID, 0, true, false, null);
		addCommonColumn(env, Table_ID);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

	/**
	 * 情報登録[Data entry]<br>
	 * @author officina-hide.net
	 * @since 2021/12/21 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tableReferenceId テーブル参照情報ID[Table reference information ID]
	 * @param referenceId 参照情報ID[Reference information ID]
	 * @param tableId テーブル情報ID[Table information ID]
	 */
	public void add(FD_EnvData env, int tableReferenceId, long referenceId, long tableId) {
		X_FD_TableReference tref = new X_FD_TableReference(env, 0);
		tref.setFD_TableReference_ID(tableReferenceId);
		tref.setFD_Reference_ID(referenceId);
		tref.setFD_Table_ID(tableId);
		tref.setFD_Group_ID(env.getActionUserID());
		tref.save(env);
	}

}

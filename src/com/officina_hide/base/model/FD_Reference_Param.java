package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * 参照変数情報クラス[Reference variable information class]<br>
 * @author officina-hide.net
 * @version 新規作成
 * @since 2021/12/13 Ver. 1.00
 */
public class FD_Reference_Param extends FD_DB implements I_FD_Reference_Param {

	/**
	 * 参照変数情報テーブル構築[Reference variable information Table Generate]<br>
	 * @author officina-hide.net
	 * @since 2021/12/13 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
		//採番情報登録
		FD_Numbering num = new FD_Numbering();
		num.add(env, Table_ID, Table_ID, 100001, 0);
		//辞書情報登録
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FD_Reference_Param_ID, NAME_FD_Reference_Param_ID, COMMENT_FD_Reference_Param_ID);
		dd.add(env, 0, COLUMNNAME_FD_Reference_Param_Name, NAME_FD_Reference_Param_Name, COMMENT_FD_Reference_Param_Name);
		dd.add(env, 0, COLUMNNAME_FD_Reference_Param_Numeric, NAME_FD_Reference_Param_Numeric, COMMENT_FD_Reference_Param_Numeric);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Reference_Param_ID, FD_ITEM_ID, 0, false, true, "0");
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Reference_Param_Name, FD_ITEM_String, 100, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Reference_ID, FD_ITEM_ID, 0, false, false, "0");
		column.add(env, 0, Table_ID, COLUMNNAME_FD_TypeItem_ID, FD_ITEM_ID, 0, true, false, "0");
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Reference_Param_Numeric, FD_ITEM_BigInt, 0, true, false, "0");
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Name, FD_ITEM_String, 100, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Description, FD_ITEM_Text, 0, true, false, null);
		addCommonColumn(env, Table_ID);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

	/**
	 * 情報登録[Data Entry]
	 * @param env 環境情報[Environment information]
	 * @param referenceParamId 参照変数情報ID[Reference variable information ID]
	 * @param referenceId [Reference information ID]
	 * @param referenceParamType [Reference variable type name]
	 * @param id [Table ID]
	 */
	public void add(FD_EnvData env, int referenceParamId, long referenceId, String referenceParamType, long id) {
		
	}

}

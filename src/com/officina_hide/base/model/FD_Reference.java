package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * 参照情報クラス[Reference information class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/12/10 Ver. 1.00
 */
public class FD_Reference extends FD_DB implements I_FD_Reference {

	/**
	 * 参照情報テーブル構築[Generate reference information table]<br>
	 * @author officina-hide.net
	 * @since 2021/12/10 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル登録
		FD_Table table = new FD_Table();
		table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
		//採番情報登録
		FD_Numbering num = new FD_Numbering();
		num.add(env, Table_ID, Table_ID, 100001, 0);
		//辞書情報登録
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FD_Reference_ID, NAME_FD_Reference_ID, COMMENT_FD_Reference_ID);
		dd.add(env, 0, COLUMNNAME_FD_Reference_Name, NAME_FD_Reference_Name, COMMENT_FD_Reference_Name);
		dd.add(env, 0, COLUMNNAME_FD_ReferenceType_ID, NAME_FD_ReferenceType_ID, COMMENT_FD_ReferenceType_ID);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Reference_ID, FD_ITEM_ID, 0, false, true, "0");
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Reference_Name, FD_ITEM_String, 100, false, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_ReferenceType_ID, FD_ITEM_ID, 0, true, false, null);
		addCommonColumn(env, Table_ID);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

	/**
	 * @param env
	 */
	public void createReferenceType(FD_EnvData env) {
		
	}

}

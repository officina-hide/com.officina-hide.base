package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * 処理変数情報クラス[Processing variable information class]<br>
 * @author officine-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/11 Ver. 1.00
 */
public class FD_ProcessParam extends FD_DB implements I_FD_ProcessParam {

	/**
	 * 処理変数情報テーブル構築[Process variable information table construction]<br>
	 * @author officina-hide.net
	 * @since 2021/10/11 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
		//採番情報登録
		FD_Numbering num = new FD_Numbering();
		num.add(env, 0, Table_ID, 101, 0);
		//辞書情報
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FD_ProcessParam_ID, NAME_FD_ProcessParam_ID, COMMENT_FD_ProcessParam_ID);
		dd.add(env, 0, COLUMNNAME_FD_ProcessParam_Name, NAME_FD_ProcessParam_Name, COMMENT_FD_ProcessParam_Name);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, Table_ID, COLUMNNAME_FD_ProcessParam_ID, FD_Item_ID, 0, false, true, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_ProcessParam_Name, FD_Item_String, 100, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_TypeItem_ID, FD_Item_ID, 0, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Process_ID, FD_Item_ID, 0, true, false, null);
		addCommonColumn(env, Table_ID);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

}

package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_DataDictionary;
import com.officina_hide.base.model.FD_Table;

/**
 * メニュー情報クラス[Menu information class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/16 Ver. 1.00
 */
public class FX_Menu extends FD_DB implements I_FX_Menu {

	/**
	 * メニュー情報テーブル生成[Menu information table generation]<br>
	 * @author officina-hide.net
	 * @since 2021/10/16 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
		//辞書情報登録
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FX_Menu_ID, NAME_FX_Menu_ID, COMMENT_FX_Menu_ID);
		dd.add(env, 0, COLUMNNAME_FX_Menu_Name, NAME_FX_Menu_Name, COMMENT_FX_Menu_Name);
		dd.add(env, 0, COLUMNNAME_FX_Target_ID, NAME_FX_Target_ID, COMMENT_FX_Target_ID);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, Table_ID, COLUMNNAME_FX_Menu_ID, FD_ITEM_ID, 0, false, true, "0");
		column.add(env, 0, Table_ID, COLUMNNAME_FX_Menu_Name, FD_ITEM_String, 100, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FX_Target_ID, FD_ITEM_ID, 0, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_TypeItem_ID, FD_ITEM_ID, 0, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Name, FD_ITEM_String, 100, true, false, null);
		addCommonColumn(env, Table_ID);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

}

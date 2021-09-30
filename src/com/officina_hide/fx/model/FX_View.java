package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_DataDictionary;
import com.officina_hide.base.model.FD_Table;

/**
 * FX画面情報クラス[FX Screen information class]<br>
 * @author officina-hide.net
 * @version 1.00
 * @since 2021/09/25
 */
public class FX_View extends FD_DB implements I_FX_View {

	/**
	 * テーブル生成
	 * @param env 環境情報[Enfironment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
		//辞書情報登録
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FX_View_ID, NAME_FX_View_ID, COMMENT_FX_View_ID);
		dd.add(env, 0, COLUMNNAME_FX_View_Name, NAME_FX_View_Name, COMMENT_FX_View_Name);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, Table_ID, COLUMNNAME_FX_View_ID, FD_Item_ID, 0, true, true, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FX_View_Name, FD_Item_String, 100, false, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Name, FD_Item_String, 100, false, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Description, FD_Item_Text, 0, false, false, null);
		addCommonColumn(env, Table_ID);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

}

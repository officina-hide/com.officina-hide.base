package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_DataDictionary;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;

/**
 * FX画面タブ情報クラス[FX screen tab information class]<br>
 * @author officina-hide.net
 * @version 1.00
 * @since 2021/09/13
 */
public class FX_Tab extends FD_DB implements I_FX_Tab {

	/**
	 * FX画面タブ情報テーブル構築[FX screen tab information table construction]<br>
	 * @author officina-hide.net
	 * @since 2021/10/03 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
		//採番情報登録
		FD_Numbering num = new FD_Numbering();
		num.add(env, Table_ID, Table_ID, 101, 0);
		//辞書情報登録
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FX_Tab_ID, NAME_FX_Tab_ID, COMMENT_FX_Tab_ID);
		dd.add(env, 0, COLUMNNAME_FX_Tab_Name, NAME_FX_Tab_Name, COMMENT_FX_Tab_Name);
		dd.add(env, 0, COLUMNNAME_FX_Tab_Level, NAME_FX_Tab_Level, COMMENT_FX_Tab_Level);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, Table_ID, COLUMNNAME_FX_Tab_ID, FD_ITEM_ID, 0, false, true, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FX_Tab_Name, FD_ITEM_String, 100, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FX_Tab_Level, FD_ITEM_Unsigned_Int, 0, true, false, "0");
		column.add(env, 0, Table_ID, COLUMNNAME_FX_View_ID, FD_ITEM_ID, 0, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Table_ID, FD_ITEM_ID, 0, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Name, FD_ITEM_String, 100, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Description, FD_ITEM_Text, 0, true, false, null);
		addCommonColumn(env, Table_ID);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

	/**
	 * 情報登録[Save data]<br>
	 * @author officina-hide.net
	 * @since 2021/10/03 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tabID FXタブ情報ID[FX tab information ID]
	 * @param tabName タブ識別名[Tab distinguished name]
	 * @param viewId FX画面基盤情報ID[FX screen base information ID]
	 * @param tableId テーブル情報ID[Table information ID]
	 * @param name 表示名[Display name]
	 * @param description 説明[Description]
	 * @param level FXタブレベル[Tab level]
	 * @return tabID FXタブ情報ID[FX tab information ID]
	 */
	public long add(FD_EnvData env, int tabID, String tabName, long viewId, long tableId, String name, String description,
			int level) {
		X_FX_Tab tab = new X_FX_Tab(env, 0);
		tab.setFX_Tab_ID(0);
		tab.setFX_Tab_Name(tabName);
		tab.setFX_View_ID(viewId);
		tab.setFD_Name(name);
		tab.setFD_Description(description);
		tab.setFD_Table_ID(tableId);
		tab.setFX_Tab_Level(level);
		tab.setFD_Group_ID(env.getActionUserID());
		tab.save(env);
		
		return tab.getFX_Tab_ID();
	}
}

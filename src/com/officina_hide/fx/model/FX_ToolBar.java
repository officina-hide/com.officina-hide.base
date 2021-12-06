package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_DataDictionary;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;

/**
 * ツールバー情報クラス[Toolbar information class]
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/12/02 Ver. 1.00
 */
public class FX_ToolBar extends FD_DB implements I_FX_ToolBar  {

	/**
	 * ツールバー情報テーブル構築[Toolbar information table construction]<br>
	 * @author officina-hide.net
	 * @since 2021/12/02 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
		//辞書情報登録
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FX_ToolBar_ID, NAME_FX_ToolBar_ID, COMMENT_FX_ToolBar_ID);
		dd.add(env, 0, COLUMNNAME_FX_ToolBar_Name, NAME_FX_ToolBar_Name, COMMENT_FX_ToolBar_Name);
		//採番情報登録
		FD_Numbering num = new FD_Numbering();
		num.add(env, 0, Table_ID, 101, 0);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, Table_ID, COLUMNNAME_FX_ToolBar_ID, FD_ITEM_ID, 0, false, true, "0");
		column.add(env, 0, Table_ID, COLUMNNAME_FX_ToolBar_Name, FD_ITEM_String, 100, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Name, FD_ITEM_String, 100, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Description, FD_ITEM_Text, 0, true, false, null);
		addCommonColumn(env, Table_ID);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

	/**
	 * 標準ツールバー登録[Standard toolbar registration]<br>
	 * @author officina-hide.net
	 * @since 2021/12/06 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void addCommonButton(FD_EnvData env) {
		add(env, 0, "TB_Save","保存", "表示されている情報を保存する。");
	}

	/**
	 * 情報登録[Data entry]<br>
	 * @author officina-hide.net
	 * @since 2021/12/06 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param toolBarId ツールバー情報ID[Toolbar information ID]
	 * @param toolbarName ツールバー名[Toolbar name]
	 * @param name ツールバー表示名[Toolbar display name]
	 * @param description ツールバー説明[Toolbar description]
	 */
	public void add(FD_EnvData env, int toolBarId, String toolbarName, String name, String description) {
		X_FX_Toolbar toolbar = new X_FX_Toolbar(env, 0);
		toolbar.setFX_ToolBar_ID(toolBarId);
		toolbar.setFX_ToolBar_Name(toolbarName);
		toolbar.setFD_Name(name);
		toolbar.setFD_Description(description);
		toolbar.save(env);
	}

}

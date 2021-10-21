package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.fx.model.FX_Menu;
import com.officina_hide.fx.model.FX_Tab;
import com.officina_hide.fx.model.FX_View;
import com.officina_hide.fx.model.V_FX_TableColumn;

/**
 * システム関連画面構成[System related screen configuration]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/21 Ver. 1.00
 */
public class CreateFxSystemInformation implements I_FD_DB {

	/** 環境情報[Environment information] */
	private FD_EnvData env;
	
	/**
	 * コンストラクタ[Constructor]
	 * @param env 環境情報[Environment information]
	 */
	public CreateFxSystemInformation(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 構築実行[Build execution]<br>
	 * @author officina-hide.net
	 * @since 2021/10/21 Ver. 1.00
	 */
	public void execute() {
		/*
		 * 1. テーブル・カラムの構築情報登録
		 */
		addTableColumn();
	}

	/**
	 * テーブル・カラム関連情報登録[Table / column related information registration]<br>
	 * @author officina-hide.net
	 * @since 2021/10/21 Ver. 1.00
	 */
	private void addTableColumn() {
		FX_View view = new FX_View();
		FX_Tab tab = new FX_Tab();
		//テーブル・カラム画面登録
		long viewId = view.add(env, 0, "V_TableColumn", "テーブル・カラム情報", "テーブルに関する情報を登録・更新する画面");
		tab.add(env, 0, V_FX_TableColumn.FX_TAB_Table, viewId, I_FD_Table.Table_ID,
				V_FX_TableColumn.FX_TAB_Table_Name, V_FX_TableColumn.FX_TAB_Table_Description);
		
		//メニュー情報登録
		FX_Menu menu = new FX_Menu();
		menu.add(env, 0, "V_FX_Table", viewId, FD_Menu_View, "テーブル・カラム");
	}

}

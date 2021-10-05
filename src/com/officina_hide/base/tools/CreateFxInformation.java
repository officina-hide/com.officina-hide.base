package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.fx.model.FX_Tab;
import com.officina_hide.fx.model.FX_View;
import com.officina_hide.fx.model.V_FX_TableColumn;

/**
 * FX画面用基盤情報構築[Construction of basic information for FX screens]
 * @author officina-hide.net
 * @version 1.00
 * @since 2021/09/25
 */
public class CreateFxInformation {

	/** 環境情報 */
	private FD_EnvData env;
	
	public CreateFxInformation(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 構築実行[Build execution]<br>
	 * @author officina-hide.net
	 * @since 1.00 2021/09/25
	 */
	public void execute() {
		/*
		 * 1. 画面情報テーブル生成,
		 * 2. 画面項目情報テーブル生成
		 */
		//1.
		FX_View view = new FX_View();
		view.createTable(env);
		long viewId = view.add(env, 0, V_FX_TableColumn.FX_View_Name, V_FX_TableColumn.FX_Name, V_FX_TableColumn.FX_Description);
		//2.
		FX_Tab tab = new FX_Tab();
		tab.createTable(env);
		tab.add(env, 0, V_FX_TableColumn.FX_TAB_Table, viewId,
				V_FX_TableColumn.FX_TAB_Table_Name, V_FX_TableColumn.FX_TAB_Table_Description);
	}
	
}

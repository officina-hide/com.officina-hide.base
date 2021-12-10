package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Process;
import com.officina_hide.base.model.FD_ProcessParam;
import com.officina_hide.base.model.FD_Type;
import com.officina_hide.base.model.FD_TypeItem;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.model.I_FD_Login;
import com.officina_hide.fx.model.FX_Field;
import com.officina_hide.fx.model.FX_Menu;
import com.officina_hide.fx.model.FX_Tab;
import com.officina_hide.fx.model.FX_TabProcess;
import com.officina_hide.fx.model.FX_ToolBar;
import com.officina_hide.fx.model.FX_View;
import com.officina_hide.fx.model.V_FX_Login;

/**
 * FX画面用基盤情報構築[Construction of basic information for FX screens]
 * @author officina-hide.net
 * @version 1.00
 * @since 2021/09/25
 */
public class CreateFxInformation implements I_FD_DB {

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
		 * 2. タブ情報テーブル構築
		 * 3. 画面項目属性情報テーブル構築
		 * 4. 画面項目情報テーブル構築、関連情報登録
		 * 5. プロセス情報テーブル構築
		 * 6-1. メニュー情報テーブル構築
		 * 6-2-1. ツールバー情報テーブル構築
		 * 6-2-2. 標準ツールバー登録
		 * 7. ログイン画面構成
		 * 8. 総合メニュー画面情報登録
		 */
		//1.
		FX_View view = new FX_View();
		view.createTable(env);
		//2.
		FX_Tab tab = new FX_Tab();
		tab.createTable(env);
		//3.
		addFieldType(env);
		//4.
		FX_Field field = new FX_Field();
		field.createTable(env);
		//5.
		FX_TabProcess tp = new FX_TabProcess();
		tp.createTable(env);
		//6-1.
		FX_Menu menu = new FX_Menu();
		menu.createTable(env);
		//6-2-1.
		FX_ToolBar toolbar = new FX_ToolBar();
		toolbar.createTable(env);
		//6-2-2.
		toolbar.addCommonButton(env);
		
		/*
		 * 7. 
		 * 7-1. 画面項目情報登録
		 * 7-2. タブ情報登録
		 * 7-3. 画面項目登録
		 * 7-4. タブ処理情報登録(ログイン)
		 * 7-5. タブ処理情報登録(キャンセル）
		 */
		//7-1.
		long viewId = view.add(env, 0, V_FX_Login.FX_View_Name, V_FX_Login.FX_Name, V_FX_Login.FX_Description);
		//7-2.
		long tabId = tab.add(env, 0, V_FX_Login.FX_Tab_Name, viewId,
				I_FD_Login.Table_ID ,V_FX_Login.FX_Tab_Disp_Name, V_FX_Login.FX_Tab_Description, 0);
		//7-3.
		field.add(env, 0, I_FD_Login.Table_Name, I_FD_Login.COLUMNNAME_FD_User_Name, tabId, FD_Field_SimpleText);
		field.add(env, 0, I_FD_Login.Table_Name, I_FD_Login.COLUMNNAME_FD_Login_Password, tabId, FD_Field_Password);
		//7-4.
		FD_Process process = new FD_Process();
		FD_ProcessParam pp = new FD_ProcessParam();
		long processId = process.add(env, 0, "FX_Login_Entry", "com.officina_hide.fx.process.FX_LoginProcess");
		pp.add(env, 0, "stage", FD_Param_Object, processId);
		tp.add(env, 0, tabId, "ログイン", processId);
		//7-5.
		processId = process.add(env, 0, "FX_Cancel_Entry", "com.officina_hide.fx.process.FX_WindowCancel");
		pp.add(env, 0, "stage", FD_Param_Object, processId);
		tp.add(env, 0, tabId, "キャンセル", processId);
		//8.
		entryMenu(env);
	}

	/**
	 * 画面項目種別登録[Screen item attribute entry]<br>
	 * @author officina-hide.net
	 * @since 2021/10/08 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	private void addFieldType(FD_EnvData env) {
		FD_Type type = new FD_Type();
		FD_TypeItem typeItem = new FD_TypeItem();
		
		long typeID = type.add(env, 0, FD_Field_Type, "画面項目属性", "画面項目の属性を管理する。");
		typeItem.add(env, 0, FD_Field_SimpleText, typeID, "1行テキスト", "1行のみのテキスト情報(ClassはString)");
		typeItem.add(env, 0, FD_Field_Password, typeID, "パスワード", "パスワード情報(ClassはString)");
		typeItem.add(env, 0, FD_Field_Text, typeID, "複数行テキスト", "複数行のテキスト情報(ClassはString)");
		typeItem.add(env, 0, FD_Field_Date, typeID, "日付", "日付情報(ClassはCalendar)");
		typeItem.add(env, 0, FD_Field_List, typeID, "リスト", "プルダウンリスト情報(Classはlong)");
	}

	/**
	 * メニュー情報登録[Menu information entry]<br>
	 * @author officina-hide.net
	 * @since 2021/10/18 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	private void entryMenu(FD_EnvData env) {
		/*
		 * 1. 属性関連登録
		 * 2. 画面情報登録
		 */
		//1.
		FD_Type type = new FD_Type();
		long typeId = type.add(env, 0, FD_MENU_Type, "メニュー種別", "メニューの遷移先となる情報（画面・処理等）の種別");
		FD_TypeItem typeItem = new FD_TypeItem();
		typeItem.add(env, 0, FD_Menu_View, typeId, "画面", "遷移先が画面");
		typeItem.add(env, 0, FD_Menu_View, typeId, "処理", "遷移先が処理");
		//2.
		FX_View view = new FX_View();
		view.add(env, 0, "V_MainMenu", "総合メニュー", "パッケージの最初に表示されるメニュー画面");
	}
	
}

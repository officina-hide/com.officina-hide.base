package com.officina_hide.account.model;

import java.io.File;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_DataDictionary;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Reference;
import com.officina_hide.base.model.FD_Table;
import com.officina_hide.fx.model.FX_Field;
import com.officina_hide.fx.model.FX_Menu;
import com.officina_hide.fx.model.FX_Tab;
import com.officina_hide.fx.model.FX_View;

/**
 * 現金出納クラス[Cash balance class]
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2022/02/10 Ver. 1.00
 */
public class AC_CashBalance extends FD_DB implements I_AC_CashBalance, I_FV_CashBalance{

	/**
	 * テーブル構築[Table construction]
	 * @author officina-hide.net
	 * @since 2022/02/10 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		long tableId = table.add(env, 0, Table_Name, Table_Disp_Name, Table_Comment);
		//採番情報登録
		FD_Numbering num = new FD_Numbering();
		num.add(env, 0, tableId, 100001, 0);
		//辞書情報登録
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_AC_CashBalance_ID, NAME_AC_CashBalance_ID, COMMENT_AC_CashBalance_ID);
		dd.add(env, 0, COLUMNNAME_AC_CashBalance_Date, NAME_AC_CashBalance_Date, COMMENT_AC_CashBalance_Date);
		dd.add(env, 0, COLUMNNAME_AC_AccountTitle_ID, NAME_AC_AccountTitle_ID, COMMENT_AC_AccountTitle_ID);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, tableId, COLUMNNAME_AC_CashBalance_ID, FD_ITEM_ID, 0, false, true, "0");
		column.add(env, 0, tableId, COLUMNNAME_AC_CashBalance_Date, FD_ITEM_Date, 0, true, false, null);
		column.add(env, 0, tableId, COLUMNNAME_AC_AccountTitle_ID, FD_ITEM_ID, 0, true, false, "0");
		addCommonColumn(env, tableId);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
		//I/Oクラス生成
		createIO(env, Table_Name, classPath);
	}

	/**
	 * I/Oクラス生成[I/O class generation]
	 * @author officina-hide.net
	 * @since 2022/02/12 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name]
	 * @param classpath クラス保管場所[Class storage location]
	 */
	private void createIO(FD_EnvData env, String tableName, String classpath) {
		String path = new File(".").getAbsolutePath();
		System.out.println(path+"\\"+classpath.replaceAll("/", "\\\\"));
	}

	/**
	 * 画面項目登録[Screen item registration]
	 * @author officina-hide.net
	 * @since 2022/02/12 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createField(FD_EnvData env) {
		FD_Column column = new FD_Column();
		
		//画面情報登録
		FX_View view = new FX_View();
		long viewId = view.add(env, 0, VIEWNAME, VIEWDispName, VIEWComment);
		//タブ情報登録
		FX_Tab tab = new FX_Tab();
		FD_Table table = new FD_Table();
		long tabId = tab.add(env, 0, VIEWNAME, viewId, table.getTableId(Table_Name),
				VIEWDispName, VIEWComment, 0);
		//画面項目登録
		FX_Field field = new FX_Field();
		field.add(env, 0, COLUMNNAME_AC_CashBalance_Date, "出納日", tabId,
				column.getColumnID(env, Table_Name, COLUMNNAME_AC_CashBalance_Date), FD_Field_Date, 0);
		FD_Reference ref = new FD_Reference();
		long refId = ref.getIdByName(env,"AC_JournalSlip_List");
		field.add(env, 0, COLUMNNAME_AC_AccountTitle_ID, "勘定科目", tabId,
				column.getColumnID(env, Table_Name, COLUMNNAME_AC_AccountTitle_ID), FD_Field_List, refId);
	
		//メニュー情報登録
		FX_Menu menu = new FX_Menu();
		menu.add(env, 0, VIEWNAME, viewId, FD_Menu_View, "現金出納");
	
	}

}

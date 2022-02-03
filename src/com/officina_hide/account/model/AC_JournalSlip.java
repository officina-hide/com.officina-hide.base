package com.officina_hide.account.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_DataDictionary;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Reference;
import com.officina_hide.base.model.FD_Table;
import com.officina_hide.base.model.FD_TableReference;
import com.officina_hide.fx.model.FX_Field;
import com.officina_hide.fx.model.FX_Menu;
import com.officina_hide.fx.model.FX_Tab;
import com.officina_hide.fx.model.FX_View;

/**
 * 仕分伝票情報クラス[Journal Slip information class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/12/09 Ver. 1.00
 */
public class AC_JournalSlip extends FD_DB implements I_AC_JournalSlip {

	/**
	 * 仕分伝票テーブル生成[Generate journal slip table]<br>
	 * @author officina-hide.net
	 * @since 2021/12/09 Ver. 1.00
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
		dd.add(env, 0, COLUMNNAME_AC_JournalSlip_ID, NAME_AC_JournalSlip_ID, COMMENT_AC_JournalSlip_ID);
		dd.add(env, 0, COLUMNNAME_AC_IssueDate, NAME_AC_IssueDate, COMMENT_AC_IssueDate);
		dd.add(env, 0, COLUMNNAME_AC_Credit_AccountTitle_ID, NAME_AC_Credit_AccountTitle_ID, COMMENT_AC_Credit_AccountTitle_ID);
		dd.add(env, 0, COLUMNNAME_AC_Credit_Amount, NAME_AC_Credit_Amount, COMMENT_AC_Credit_Amount);
		dd.add(env, 0, COLUMNNAME_AC_Credit_Memo, NAME_AC_Credit_Memo, COMMENT_AC_Credit_Memo);
		dd.add(env, 0, COLUMNNAME_AC_Debit_AccountTitle_ID, NAME_AC_Debit_AccountTitle_ID, COMMENT_AC_Debit_AccountTitle_ID);
		dd.add(env, 0, COLUMNNAME_AC_Debit_Amount, NAME_AC_Debit_Amount, COMMENT_AC_Debit_Amount);
		dd.add(env, 0, COLUMNNAME_AC_Debit_Memo, NAME_AC_Debit_Memo, COMMENT_AC_Debit_Memo);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, tableId, COLUMNNAME_AC_JournalSlip_ID, FD_ITEM_ID, 0, false, true, null);
		column.add(env, 0, tableId, COLUMNNAME_AC_IssueDate, FD_ITEM_Date, 0, true, false, null);
		column.add(env, 0, tableId, COLUMNNAME_AC_Credit_AccountTitle_ID, FD_ITEM_ID, 0, true, false, null);
		column.add(env, 0, tableId, COLUMNNAME_AC_Credit_Amount, FD_ITEM_Amount, 0, true, false, "0");
		column.add(env, 0, tableId, COLUMNNAME_AC_Credit_Memo, FD_ITEM_Text, 0, true, false, null);
		column.add(env, 0, tableId, COLUMNNAME_AC_Debit_AccountTitle_ID, FD_ITEM_ID, 0, true, false, "0");
		column.add(env, 0, tableId, COLUMNNAME_AC_Debit_Amount, FD_ITEM_Amount, 0, true, false, "0");
		column.add(env, 0, tableId, COLUMNNAME_AC_Debit_Memo, FD_ITEM_Text, 0, true, false, null);
		addCommonColumn(env, tableId);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

	/**
	 * 画面項目登録[Screen item registration]
	 * @author officina-hide.net
	 * @since 2022/02/03 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createField(FD_EnvData env) {
		FX_View view = new FX_View();
		FX_Tab tab = new FX_Tab();
		FX_Field field = new FX_Field();
		FD_Column column = new FD_Column();
		FD_Table table = new FD_Table();
		
		long viewId = view.add(env, 0, I_FV_JournalSlip.VIEWNAME, "", "");
		long tabId = tab.add(env, 0, I_FV_JournalSlip.VIEWNAME, viewId, table.getTableId(I_AC_JournalSlip.Table_Name),
				I_AC_JournalSlip.Table_Disp_Name, I_AC_JournalSlip.Table_Comment, 0);
		//発行日
		field.add(env, 0, "IssueDate", "発行日", tabId,
				column.getColumnID(env, I_AC_JournalSlip.Table_Name, I_AC_JournalSlip.COLUMNNAME_AC_IssueDate),
				FD_Field_Date , 0);
		//勘定科目リファレンス
		FD_Reference ref = new FD_Reference();
		long refId = ref.add(env, 0, "AC_JournalSlip_List", FD_Reference_Table);
		FD_TableReference tref = new FD_TableReference();
		tref.add(env, 0, refId, table.getTableId(I_AC_AccountTitle.Table_Name));
		//貸方勘定科目
		field.add(env, 0, "Credit_AccountTitle", "貸方勘定科目", tabId,
				column.getColumnID(env, I_AC_JournalSlip.Table_Name, I_AC_JournalSlip.COLUMNNAME_AC_Credit_AccountTitle_ID),
				FD_Field_List, refId);
		//貸方金額
		field.add(env, 0, "Credit_Amount", "貸方金額", tabId,
				column.getColumnID(env, I_AC_JournalSlip.Table_Name, I_AC_JournalSlip.COLUMNNAME_AC_Credit_Amount),
				FD_Field_Amount, 0);
		//貸方適用
		field.add(env, 0, "Credit_Memo", "貸方適用", tabId,
				column.getColumnID(env, I_AC_JournalSlip.Table_Name, I_AC_JournalSlip.COLUMNNAME_AC_Credit_Memo),
				FD_Field_SimpleText, 0);
		//借方勘定科目
		field.add(env, 0, "Debit_AccountTitle", "借方勘定科目", tabId,
				column.getColumnID(env, I_AC_JournalSlip.Table_Name, I_AC_JournalSlip.COLUMNNAME_AC_Debit_AccountTitle_ID),
				FD_Field_List, refId);
		//借方金額
		field.add(env, 0, "Debit_Amount", "借方金額", tabId,
				column.getColumnID(env, I_AC_JournalSlip.Table_Name, I_AC_JournalSlip.COLUMNNAME_AC_Debit_Amount),
				FD_Field_Amount, 0);
		//借方適用
		field.add(env, 0, "Debit_Memo", "借方適用", tabId,
				column.getColumnID(env, I_AC_JournalSlip.Table_Name, I_AC_JournalSlip.COLUMNNAME_AC_Debit_Memo),
				FD_Field_SimpleText, 0);
	
		//メニュー情報登録
		FX_Menu menu = new FX_Menu();
		menu.add(env, 0, I_FV_JournalSlip.VIEWNAME, viewId, FD_Menu_View, "仕分伝票");
	}

}

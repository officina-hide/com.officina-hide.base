package com.officina_hide.account.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_DataDictionary;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;

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
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, tableId, COLUMNNAME_AC_JournalSlip_ID, FD_ITEM_ID, 0, true, true, null);
		column.add(env, 0, tableId, COLUMNNAME_AC_IssueDate, FD_ITEM_Date, 0, true, false, null);
		column.add(env, 0, tableId, COLUMNNAME_AC_Credit_AccountTitle_ID, FD_ITEM_ID, 0, true, false, null);
		addCommonColumn(env, tableId);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

}

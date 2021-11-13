package com.officina_hide.account.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_DataDictionary;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;

/**
 * 勘定科目情報[Account title information]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/11/13 Ver. 1.00
 */
public class AC_AccountTitle extends FD_DB implements I_AC_AccountTitle {

	/**
	 * テーブル構築[Table construction]<br>
	 * @author officina-hide.net
	 * @since 2021/11/13 Ver. 1.00
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
		dd.add(env, 0, COLUMNNAME_AC_AccountTitle_ID, NAME_AC_AccountTitle_ID, COMMENT_AC_AccountTitle_ID);
		dd.add(env, 0, COLUMNNAME_AC_AccountTitle_Code, NAME_AC_AccountTitle_Code, COMMENT_AC_AccountTitle_Code);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, tableId, COLUMNNAME_AC_AccountTitle_ID, FD_ITEM_ID, 0, false, true, null);
		column.add(env, 0, tableId, COLUMNNAME_AC_AccountTitle_Code, FD_ITEM_Text, 100, true, false, null);
		column.add(env, 0, tableId, COLUMNNAME_FD_Name, FD_ITEM_String, 100, true, false, null);
		addCommonColumn(env, tableId);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

	/**
	 * 勘定科目情報追加[add account of title]<br>
	 * @author officina-hide.net
	 * @since 2021/11/13 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void addTitle(FD_EnvData env) {
		add(env, 0, "1018", "現金");
	}

	/**
	 * 勘定科目情報登録[Account information registration]<br>
	 * @author officina-hide.net
	 * @since 2021/11/13 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param accoutTitleId 勘定科目情報ID
	 * @param code 勘定科目コード
	 * @param name 勘定科目名
	 */
	private void add(FD_EnvData env, int accoutTitleId, String code, String name) {
		
	}

}

package com.officina_hide.page.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_DataDictionary;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;

/**
 * メニュー情報クラス[Menu information class]<br>
 * HTML用メニュー情報[HTML menu information]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2022/02/01 Ver. 1.00
 */
public class FM_Menu extends FD_DB implements I_FM_Menu {

	/**
	 * テーブル情報構築[Table information construction]
	 * @author officina-hide.net
	 * @since 2022/02/01 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		long tableId = table.add(env, 0, Table_Name, Table_Disp_Name, Table_Comment);
		//採番情報
		FD_Numbering num = new FD_Numbering();
		num.add(env, 0, tableId, 100001, 0);
		//辞書情報
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FM_Menu_ID, NAME_FM_Menu_ID, COMMENT_FM_Menu_ID);
		dd.add(env, 0, COLUMNNAME_FM_MenuGroup_ID, NAME_FM_MenuGroup_ID, COMMENT_FM_MenuGroup_ID);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, tableId, COLUMNNAME_FM_Menu_ID, FD_ITEM_ID, 0, false, true, "0");
		column.add(env, 0, tableId, COLUMNNAME_FM_MenuGroup_ID, FD_ITEM_ID, 0, true, false, null);
		addCommonColumn(env, tableId);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

}

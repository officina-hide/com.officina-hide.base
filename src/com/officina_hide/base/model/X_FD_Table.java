package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * テーブル情報I/Oクラス<br>
 * @author officina-hide.com
 * @version 1.00
 * @since 2020/10/12
 */
public class X_FD_Table extends FD_DB implements I_FD_Table {

	/**
	 * コンストラクター<br>
	 * 実体化時に、項目の初期化を行う。<br>
	 * @param env 環境情報
	 */
	public X_FD_Table(FD_EnvData env) {
		initializeItemList();
	}

	/**
	 * 項目リスト初期化<br>
	 * @author officina-hide.com
	 * @since 1.00 2020/10/12
	 */
	private void initializeItemList() {
		itemList.clear();
		itemList.add(new FD_Item(COLUMNNAME_FD_Table_ID, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(COLUMNNAME_Table_Name, null, COLUMN_TYPE_TEXT));
		itemList.add(new FD_Item(COLUMNNAME_FD_Name, null, COLUMN_TYPE_TEXT));
		itemList.add(new FD_Item(COLUMNNAME_FD_Comment, null, COLUMN_TYPE_FIELD_TEXT));
		itemList.add(new FD_Item(COLUMNNAME_FD_CREATE, null, COLUMN_TYPE_DATE));
		itemList.add(new FD_Item(COLUMNNAME_FD_CREATED, null, COLUMN_TYPE_INFORMATION_ID));
		itemList.add(new FD_Item(COLUMNNAME_FD_UPDATE, null, COLUMN_TYPE_DATE));
		itemList.add(new FD_Item(COLUMNNAME_FD_UPDATED, null, COLUMN_TYPE_INFORMATION_ID));
	}

}

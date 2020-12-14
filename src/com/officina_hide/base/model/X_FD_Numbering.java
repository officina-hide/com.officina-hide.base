package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * 採番情報I/Oクラス[Numbering Information I/O class]<br>
 * @author officina-hide.com
 * @version 1.30
 * @since 2020/12/14
 */
public class X_FD_Numbering extends FD_DB implements I_FD_Numbering {

	/**
	 * コンストラクター<br>
	 * インスタンス化時にアイテムを初期化します。<br>
	 * Initialize the items at the time of instancing.<br>
	 * @author officina-hide.com
	 * @since 1.30 2020/12/14
	 * @param env 環境情報
	 */
	public X_FD_Numbering(FD_EnvData env) {
		initializeItemList();
	}

	/**
	 * 項目リスト初期化<br>
	 * Item list initialization<br>
	 * @author officina-hide.com
	 * @since 1.30 2020/12/14
	 */
	private void initializeItemList() {
		itemList.clear();
		itemList.add(COLUMNNAME_FD_Numbering_ID, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_FD_Table_ID, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_Current_Number, null, COLUMNTYPE_FD_Number);
		itemList.add(COLUMNNAME_Initial_Number, null, COLUMNTYPE_FD_Number);
		itemList.add(COLUMNNAME_Numbering_Key, null, COLUMNTYPE_FD_Text);
		itemList.add(COLUMNNAME_FD_Process_ID, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_FD_CREATE, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_CREATED, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_FD_UPDATE, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_UPDATED, null, COLUMNTYPE_FD_Information_ID);
	}

	/**
	 * 情報保存[Information storage]<br>
	 * @author officina-hide.com
	 * @since 1.30 2020/12/14
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

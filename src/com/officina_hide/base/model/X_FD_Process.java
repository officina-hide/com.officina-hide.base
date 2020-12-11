package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * プロセス情報I/Oクラス[Process information I / O class]<br>
 * @author officine-hide.com
 * @version 1.30
 * @since 2020/12/11
 */
public class X_FD_Process extends FD_DB implements I_FD_Process {

	/**
	 * コンストラクター<br>
	 * インスタンス化時にアイテムを初期化します。<br>
	 * Initialize the items at the time of instancing.<br>
	 * @param env 環境情報
	 */
	public X_FD_Process(FD_EnvData env) {
		initializeItemList();
	}

	/**
	 * 項目リスト初期化<br>
	 * Item list initialization<br>
	 * @author officina-hide.com
	 * @since 1.00 2020/10/12
	 */
	private void initializeItemList() {
		itemList.clear();
		itemList.add(I_FD_Process.COLUMNNAME_FD_Process_ID, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_Process_Name, null, COLUMNTYPE_FD_Text);
		itemList.add(COLUMNNAME_Process_StartTime, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_Process_EndTime, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_CREATE, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_CREATED, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_FD_UPDATE, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_UPDATED, null, COLUMNTYPE_FD_Information_ID);
	}

}

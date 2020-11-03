package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * リファレンス情報I/Oクラス<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/10/15
 */
public class X_FD_Reference extends FD_DB implements I_FD_Referecne {

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、項目の初期化を行います。</p>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/15
	 * @param env 環境情報
	 */
	public X_FD_Reference(FD_EnvData env) {
		initializeItemList();
	}

	/**
	 * 項目テーブル初期化<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/15
	 */
	private void initializeItemList() {
		itemList.clear();
		itemList.add(COLUMNNAME_FD_Reference_ID, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_Reference_Name, null, COLUMNTYPE_FD_Text);
		itemList.add(COLUMNNAME_FD_Name, null, COLUMNTYPE_FD_Text);
		
		itemList.add(COLUMNNAME_FD_Process_ID, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_FD_CREATE, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_CREATED, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_FD_UPDATE, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_UPDATED, null, COLUMNTYPE_FD_Information_ID);
	}

	/**
	 * 情報保存<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/15
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

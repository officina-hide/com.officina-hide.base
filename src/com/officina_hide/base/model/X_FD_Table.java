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
	 * コンストラクター<br>
	 * @author officine-hide.com
	 * @since 1.10 2020/11/05
	 * @param env 環境情報
	 * @param tableId テーブル情報ID
	 */
	public X_FD_Table(FD_EnvData env, int tableId) {
		//項目の初期化
		initializeItemList();
		//情報読み取り
		load(env, Table_Name, tableId);
	}

	/**
	 * 項目リスト初期化<br>
	 * @author officina-hide.com
	 * @since 1.00 2020/10/12
	 */
	private void initializeItemList() {
		itemList.clear();
		itemList.add(COLUMNNAME_FD_Table_ID, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_Table_Name, null, COLUMNTYPE_FD_Text);
		itemList.add(COLUMNNAME_FD_Name, null, COLUMNTYPE_FD_Text);
		itemList.add(COLUMNNAME_FD_Comment, null, COLUMNTYPE_FD_Field_Text);
		
		itemList.add(COLUMNNAME_FD_Process_ID, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_FD_CREATE, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_CREATED, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_FD_UPDATE, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_UPDATED, null, COLUMNTYPE_FD_Information_ID);
	}

	/**
	 * 情報保存<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/12
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

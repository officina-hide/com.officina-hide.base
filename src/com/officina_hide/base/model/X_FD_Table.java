package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * テーブル情報I/Oクラス[Table information I / O class]<br>
 * @author officine-hide.com
 * @version 1.30
 * @since 2020/12/10
 */
public class X_FD_Table extends FD_DB implements I_FD_Table {

	/**
	 * コンストラクター<br>
	 * インスタンス化時にアイテムを初期化します。<br>
	 * Initialize the items at the time of instancing.<br>
	 * @param env 環境情報
	 */
	public X_FD_Table(FD_EnvData env) {
		initializeItemList();
	}

	/**
	 * コンストラクタ<br>
	 * <p>インスタンス時にテーブル項目を初期化し、引数のテーブル情報IDを持つテーブル情報を取得する。<br>
	 * Initialize the table item at the time of instance and get the table information with the table information ID of the argument.</p>
	 * @author officina-hide.com
	 * @since 1.30 2021/01/04
	 * @param env 環境情報
	 * @param tableId テーブル情報ID[Table Information ID]
	 */
	public X_FD_Table(FD_EnvData env, int tableId) {
		initializeItemList();
		load(env, tableId);
	}

	/**
	 * 情報取得
	 * @param env
	 * @param dataId 
	 */
	private void load(FD_EnvData env, int dataId) {
		load(env, Table_Name, dataId);
	}

	/**
	 * 項目リスト初期化<br>
	 * Item list initialization<br>
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
	 * 情報保存[Information storage]<br>
	 * @author officine-hide.com
	 * @since 1.30 2020/12/11
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

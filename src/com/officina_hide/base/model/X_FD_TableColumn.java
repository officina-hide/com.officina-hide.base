package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * テーブル項目情報I/Oクラス[Table item information I/O class]<br>
 * @author officine-hide.com
 * @version 1.30
 * @since 2020/12/16
 */
public class X_FD_TableColumn extends FD_DB implements I_FD_TableColumn {

	/**
	 * コンストラクター<br>
	 * インスタンス化時にアイテムを初期化します。<br>
	 *  Initialize the items at the time of instancing.<br>
	 *  @author officine-hide.com
	 *  @since 1.30 2020/12/16
	 * @param env 環境情報
	 */
	public X_FD_TableColumn(FD_EnvData env) {
		initializeItemList();
	}

	/**
	 * コンストラクター<br>
	 * インスタンス時にアイテムを初期化し、引数の情報IDを持つテーブル項目情報を取得する。<br>
	 * Initialize the item at the time of instance and get the table item information with the information ID of the argument.<br>
	 * @author officina-hide.com
	 * @since 1.30 2021/01/25
	 * @param env 環境情報
	 * @param tableColumnID テーブル項目情報ID
	 */
	public X_FD_TableColumn(FD_EnvData env, int tableColumnID) {
		initializeItemList();
		load(env, tableColumnID);
	}

	/**
	 * 情報抽出[Information extraction]
	 * @author officina-hide.com
	 * @since 1.30 2021/01/25
	 * @param env 環境情報
	 * @param tableColumnID テーブル項目情報ID
	 */
	public void load(FD_EnvData env, int tableColumnID) {
		load(env, Table_Name, tableColumnID);
	}

	/**
	 * 項目初期化<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/22
	 */
	private void initializeItemList() {
		itemList.clear();
		itemList.add(COLUMNNAME_FD_TableColumn_ID, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_FD_Table_ID, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_TableColumn_Name, null, COLUMNTYPE_FD_Text);
		itemList.add(COLUMNNAME_FD_Name, null, COLUMNTYPE_FD_Text);
		itemList.add(COLUMNNAME_FD_Comment, null, COLUMNTYPE_FD_Field_Text);
		itemList.add(COLUMNNAME_TableColumn_Type_ID, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_Defualt_Data, null, COLUMNTYPE_FD_Text);
		itemList.add(COLUMNNAME_TableColumn_Size, null, COLUMNTYPE_FD_Number);
		itemList.add(COLUMNNAME_Column_Sort_Order, null, COLUMNTYPE_FD_Number);
		itemList.add(COLUMNNAME_Is_Null, null, COLUMNTYPE_FD_YesNo);
		itemList.add(COLUMNNAME_Is_Primary, null, COLUMNTYPE_FD_YesNo);
		itemList.add(COLUMNNAME_FD_Reference_ID, null, COLUMNTYPE_FD_Information_ID);
		
		itemList.add(COLUMNNAME_FD_Process_ID, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_FD_CREATE, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_CREATED, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_FD_UPDATE, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_UPDATED, null, COLUMNTYPE_FD_Information_ID);
	}

	/**
	 * 情報保存[Information storage]<br>
	 * @author officine-hide.com
	 * @since 1.30 2020/12/16
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

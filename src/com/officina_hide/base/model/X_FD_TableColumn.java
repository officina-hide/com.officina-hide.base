package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * テーブル項目情報I/Oクラス<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/10/22
 */
public class X_FD_TableColumn extends FD_DB implements I_FD_TableColumn {

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、項目の初期化を行う。</p>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/22
	 * @param env 環境情報
	 */
	public X_FD_TableColumn(FD_EnvData env) {
		//項目初期化
		initializeItemList();
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
		
		itemList.add(COLUMNNAME_FD_Process_ID, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_FD_CREATE, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_CREATED, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_FD_UPDATE, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_UPDATED, null, COLUMNTYPE_FD_Information_ID);
	}

	/**
	 * 情報保存<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/22
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

package com.officina_hide.base.model;

import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;

/**
 * 採番情報I/Oクラス<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/10/13
 */
public class X_FD_Numbering extends FD_DB implements I_FD_Numbering {

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、項目の初期化を行う。</p>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/13
	 * @param env 環境情報
	 */
	public X_FD_Numbering(FD_EnvData env) {
		initializeItemList();
	}

	/**
	 * 項目リスト初期化<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/13
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
	 * 項目リストセット<br>
	 * <p>項目リストの指定された項目名を持つ情報に対して、指定された項目情報をセットする。</p>
	 * @param env 環境情報
	 * @param columnName テーブル項目名
	 * @param columnData テーブル項目情報
	 */
	public void setValueByName(FD_EnvData env, String columnName, Object columnData) {
		if(itemList.setData(columnName, columnData) == false) {
			System.out.println(new Date()+" : "+"Column Name Not Found!! ["+columnName+"]");
			new Exception();
		}
	}

	/**
	 * 情報保存<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/13
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

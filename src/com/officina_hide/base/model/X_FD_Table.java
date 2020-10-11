package com.officina_hide.base.model;

import java.util.Date;

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
		itemList.add(new FD_Item(COLUMNNAME_FD_Table_ID, null, COLUMNTYPE_FD_Information_ID));
		itemList.add(new FD_Item(COLUMNNAME_Table_Name, null, COLUMNTYPE_FD_Text));
		itemList.add(new FD_Item(COLUMNNAME_FD_Name, null, COLUMNTYPE_FD_Text));
		itemList.add(new FD_Item(COLUMNNAME_FD_Comment, null, COLUMNTYPE_FD_Field_Text));
		itemList.add(new FD_Item(COLUMNNAME_FD_CREATE, null, COLUMNTYPE_FD_Date));
		itemList.add(new FD_Item(COLUMNNAME_FD_CREATED, null, COLUMNTYPE_FD_Information_ID));
		itemList.add(new FD_Item(COLUMNNAME_FD_UPDATE, null, COLUMNTYPE_FD_Date));
		itemList.add(new FD_Item(COLUMNNAME_FD_UPDATED, null, COLUMNTYPE_FD_Information_ID));
	}

	/**
	 * 項目リストセット<br>
	 * <p>項目リストの指定された項目名を持つ情報に対して、指定された項目情報をセットする。</p>
	 * @param env 環境情報
	 * @param columnName テーブル項目名
	 * @param columnData テーブル項目情報
	 */
	public void setValueByName(FD_EnvData env, String columnName, Object columnData) {
		//項目名チェック
		boolean chk = false;
		for(FD_Item item : itemList) {
			if(item.getItemName().equals(columnName)) {
				item.setItemData(columnData);
				chk = true;
				break;
			}
		}
		
		if(chk == false) {
			System.out.println(new Date()+" : "+"Column Name Not Found!! ["+columnName+"]");
			new Exception();
		}
	}

}

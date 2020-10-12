package com.officina_hide.base.model;

import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;

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
		itemList.add(COLUMNNAME_FD_Table_ID, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_Table_Name, null, COLUMNTYPE_FD_Text);
		itemList.add(COLUMNNAME_FD_Name, null, COLUMNTYPE_FD_Text);
		itemList.add(COLUMNNAME_FD_Comment, null, COLUMNTYPE_FD_Field_Text);
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
//		//項目名チェック
//		boolean chk = false;
//		for(FD_Item item : itemList) {
//			if(item.getItemName().equals(columnName)) {
//				item.setItemData(columnData);
//				chk = true;
//				break;
//			}
//		}
//		
//		if(chk == false) {
			System.out.println(new Date()+" : "+"Column Name Not Found!! ["+columnName+"]");
			new Exception();
		}
	}

	/**
	 * 情報保存<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/12
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		StringBuffer setItem = new StringBuffer();
		
		sql.append("INSERT INTO ").append(Table_Name).append(" SET ");
		
		for(String columnName : itemList.getNameList()) {
			if(setItem.length() > 0) {
				setItem.append(",");
			}
			if(itemList.getValueOfItem(columnName) != null) {
				
			} else {
				
			}
		}
		
		
//		for(FD_Item item : itemList) {
//			if(setItem.length() > 0) {
//				setItem.append(",");
//			}
//			if(item.getItemData() != null) {
//				switch(item.getItemType()) {
//				case COLUMNTYPE_FD_Information_ID:
//				case COLUMNTYPE_FD_Number:
//					setItem.append(item.getItemName()).append(" = ").append(getIntOfValue(item.getItemName()));
//					break;
//				case COLUMNTYPE_FD_Text:
//				case COLUMNTYPE_FD_Field_Text:
//					setItem.append(item.getItemName()).append(" = ").append(FD_SQ).append(getStringOfValue(item.getItemName())).append(FD_SQ);
//					break;
//				case COLUMNNAME_FD_UPDATE:
//					setItem.append(item.getItemName()).append(" = ")
//						.append(FD_SQ).append(dateFormat.format(getDateOfValue(item.getItemName()).getTime())).append(FD_SQ);
//					break;
//				}
//				}
//			}
//		}
	}

}

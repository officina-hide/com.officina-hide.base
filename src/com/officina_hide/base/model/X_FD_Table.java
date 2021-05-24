package com.officina_hide.base.model;

import org.w3c.dom.Element;

import com.officina_hide.base.common.FD_Items;

/**
 * テーブルI/Oクラス[Table I / O class]
 * @author officina-hide.net
 * @version 1.00
 * @since 2021/05/22
 */
public class X_FD_Table {
	/** 項目リスト[item list] */
	private FD_Items items = new FD_Items();

	/**
	 * インスタンス時に、XMLデータからテーブル情報をセットする。<br>
	 * Set table information from XML data at instance time.<br>
	 * @author officina-hide.net
	 * @since 1.00 2021/05/22
	 * @param xmlData XMLデータ[XML data]
	 */
	public X_FD_Table(Element xmlData) {
		if(xmlData.getTagName().equals("Table")) {
			items.add(I_FD_Table.COLUMNNAME_FD_Table_ID, xmlData.getAttribute(I_FD_Table.COLUMNNAME_FD_Table_ID));
			items.add(I_FD_Table.COLUMNNAME_FD_Table_Name, xmlData.getAttribute(I_FD_Table.COLUMNNAME_FD_Table_Name));
			items.add(I_FD_Table.COLUMNNAME_FD_DisplayName, xmlData.getAttribute(I_FD_Table.COLUMNNAME_FD_DisplayName));
			items.add(I_FD_Table.COLUMNNAME_FD_Description, xmlData.getAttribute(I_FD_Table.COLUMNNAME_FD_Description));
		}
	}

	/**
	 * テーブル情報の項目リストを返す。<br>
	 * Returns a list of table information items.<br>
	 * @author officine-hide.com
	 * @since 1.00 2021/05/24
	 * @return テーブル項目リスト[Table item list]
	 */
	public FD_Items getItems() {
		return items;
	}

}

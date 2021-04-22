package com.officina_hide.base.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 汎用項目リスト[General item list]<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2021/04/23
 */
public class FD_ItemCollection {
	private List<FD_Item> list = new ArrayList<>();

	/**
	 * 項目登録[item entry]<br>
	 * @author officine-hide.com
	 * @since 1.00 2021/04/23
	 * @param name 項目名[item name]
	 * @param value 項目値[item value]
	 * @param valueType 項目種別名[item type name]
	 */
	public void add(String name, Object value, String valueType) {
		FD_Item item = new FD_Item();
		item.setName(name);
		item.setValue(value);
		item.setValueType(valueType);
		list.add(item);
	}
}

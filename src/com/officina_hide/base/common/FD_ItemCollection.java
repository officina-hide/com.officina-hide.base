package com.officina_hide.base.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.model.FD_DB;

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
		item.setData(value);
		item.setType(valueType);
		list.add(item);
	}

	/**
	 * 検索結果から項目一覧をセットする。
	 * @param rs
	 * @throws SQLException 
	 */
	public void setItem(ResultSet rs) throws SQLException {
		for(FD_Item item : list) {
			switch(item.getType()) {
			case FD_DB.Item_Value_Type_ID:
				item.setData(rs.getInt(item.getName()));
				break;
			case FD_DB.Item_Value_Type_String:
				item.setData(rs.getString(item.getName()));
				break;
			}
		}
	}

	/**
	 * 指定されたインデックスの項目を取得する。[Gets the item at the specified index.]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/26
	 * @param index 索引番号[Index number]
	 * @return 項目情報[item information]
	 */
	public FD_Item getItem(int index) {
		return list.get(index);
	}
}

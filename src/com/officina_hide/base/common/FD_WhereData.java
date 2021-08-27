package com.officina_hide.base.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 条件句クラス[Where clause class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/08/23
 */
public class FD_WhereData {
	
	/** 条件一覧 */
	List<WhereItemData> whereList = new ArrayList<>();
	
	/**
	 * コンストラクタ[Constructor]
	 * @param itemName
	 * @param itemData
	 */
	public FD_WhereData(String itemName, int itemData) {
		whereList.add(new WhereItemData(itemName, itemData));
	}
	
	@Override
	public String toString() {
		StringBuffer where = new StringBuffer();
		WhereItemData item = whereList.get(0);
		where.append("Where ").append(item.getItemName()).append(" = ");
		if(item.getItemData().getClass().getSimpleName().equals("Integer")) {
			where.append(item.getItemData().toString()).append(" ");
		}
		return where.toString();
	}
	
	/**
	 * 条件句情報[Where clause Information]<br>
	 * @author officine-hide.net
	 * @version 1.00
	 * @since 2021/08/23
	 */
	private class WhereItemData {
		public WhereItemData(String name, int data) {
			setItemName(name);
			setItemData(data);
		}

		/** 項目名 */
		private String itemName;
		/** 項目データ(数値) */
		private Object itemData;
		public String getItemName() {
			return itemName;
		}
		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
		public Object getItemData() {
			return itemData;
		}
		public void setItemData(Object itemData) {
			this.itemData = itemData;
		}
	}
}

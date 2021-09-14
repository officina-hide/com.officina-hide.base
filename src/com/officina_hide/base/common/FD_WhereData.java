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
	
	/** 論理演算子 */
	public static final String AND = "and";
	public static final String OR = "or";
	
	/** 条件一覧 */
	List<WhereItemData> whereList = new ArrayList<>();
	
	/**
	 * コンストラクタ[Constructor]
	 * @param itemName
	 * @param itemData
	 */
	public FD_WhereData(String itemName, int itemData) {
		whereList.add(new WhereItemData(null, itemName, itemData));
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

	public void add(String connectType, String itemName, int itemData) {
		
	}
	
	/**
	 * 条件句情報[Where clause Information]<br>
	 * @author officine-hide.net
	 * @version 1.00
	 * @since 2021/08/23
	 */
	private class WhereItemData {
		public WhereItemData(String connect, String name, int data) {
			setConnect(connect);
			setItemName(name);
			setItemData(data);
		}

		/** 論理接続子 */
		private String connect;
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
		public void setConnect(String connect) {
			this.connect = connect;
		}
	}
}

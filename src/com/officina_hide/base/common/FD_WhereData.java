package com.officina_hide.base.common;

import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.model.I_FD_DB;

/**
 * 条件句クラス[Where clause class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/08/23
 */
public class FD_WhereData implements I_FD_DB {
	
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
	
	/**
	 * コンストラクタ[constructor]
	 * long属性の項目値の条件情報を追加する。<br>
	 * Add the condition information of the item value of long attribute.<br>
	 * @param itemName 項目名[item name]
	 * @param itemData 項目値[item value]
	 */
	public FD_WhereData(String itemName, long itemData) {
		whereList.add(new WhereItemData(null, itemName, itemData));
	}

	/**
	 * コンストラクタ[constructor]
	 * String属性の項目値の条件情報を追加する。<br>
	 * Add the condition information of the item value of String attribute.<br>
	 * @author officina-hide.net
	 * @since 1.00 2021/09/23
	 * @param itemName 項目名[item name]
	 * @param itemData 項目値[item value]
	 */
	public FD_WhereData(String itemName, String itemData) {
		whereList.add(new WhereItemData(null, itemName, itemData));
	}

	@Override
	public String toString() {
		StringBuffer where = new StringBuffer();
		WhereItemData item = whereList.get(0);
		where.append("Where ").append(item.getItemName()).append(" = ");
		switch(item.getItemData().getClass().getSimpleName()) {
		case "Integer":
		case "Long":
			where.append(item.getItemData().toString()).append(" ");
			break;
		case "String":
			where.append(FD_SQ).append(item.getItemData().toString()).append(FD_SQ).append(" ");
			break;
		}
		if(whereList.size() == 2) {
			WhereItemData item2 = whereList.get(1);
			where.append(" ").append(item2.getConnect()).append(" ");
			where.append(item2.getItemName()).append(" = ");
			switch(item2.getItemData().getClass().getSimpleName()) {
			case "Integer":
			case "Long":
				where.append(item2.getItemData().toString()).append(" ");
				break;
			case "String":
				where.append(FD_SQ).append(item2.getItemData().toString()).append(FD_SQ).append(" ");
				break;
			}
		}
		
		
		
		return where.toString();
	}

	public void add(String connectType, String itemName, int itemData) {
		
	}
	
	public void add(String connectType, String itemName, String itemData) {
		whereList.add(new WhereItemData(connectType, itemName, itemData));
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

		public WhereItemData(String connect, String name, long data) {
			setConnect(connect);
			setItemName(name);
			setItemData(data);
		}

		public WhereItemData(String connect, String name, String data) {
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
		@SuppressWarnings("unused")
		public String getConnect() {
			return connect;
		}
	}
}

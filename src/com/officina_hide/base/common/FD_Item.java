package com.officina_hide.base.common;

import java.util.Date;

/**
 * 項目情報クラス[Item information class]<br>
 * @author officine-hide.com
 * @version 1.30
 * @since 2020/12/10
 */
public class FD_Item {

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、必要な情報を設定する。</p>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/12
	 * @param itemName
	 * @param itemData
	 * @param itemType
	 */
	public FD_Item(String itemName, Object itemData, String itemType) {
		setItemName(itemName);
		setItemData(itemData);
		setItemType(itemType);
	}

	/** 項目名 */
	private String ItemName;
	/** 項目情報 */
	private Object itemData;
	/** 項目属性名 */
	private String itemType;
	
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public Object getItemData() {
		return itemData;
	}
	public void setItemData(Object itemData) {
		this.itemData = itemData;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
	/**
	 * 項目情報をintで返す<br>
	 * @author officina-hide.com
	 * @since 1.00 2020/10/12
	 * @return 項目情報
	 */
	public int getIntOfValue() {
		try {
			int data = 0;
			if(getItemData() != null) {
				data = (int) getItemData();
			}
			return data;
		} catch (ClassCastException e) {
			try {
				int data = Integer.parseInt((String) getItemData());
				return data;
			} catch (NumberFormatException | ClassCastException e1) {
				return 0;
			}
		}
	}
	
	/**
	 * 項目情報を文字列で返す。<br>
	 * @author officina-hide.com
	 * @since 1.00 2020/10/12
	 * @return 項目情報
	 */
	public String getStringOfValue() {
		return (String) itemData;
	}
	
	/**
	 * 項目情報を日付で返す。<br>
	 * @author officina-hide.com
	 * @since 1.00 2020/10/12
	 * @return 項目情報
	 */
	public Date getDateOfValue() {
		return (Date) getItemData();
	}

	/**
	 * nullチェック[check of null]<br>
	 * <p>項目情報がnullかどうかをチェックする。<br>
	 * Check if the item information is null.</p>
	 * @return true - is null、false - is not null
	 */
	public boolean isNullData() {
		if(itemData == null) {
			return true;
		} else {
			return false;
		}
	}
}

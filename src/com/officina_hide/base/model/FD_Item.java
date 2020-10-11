package com.officina_hide.base.model;

/**
 * 項目情報<br>
 * @author officina-hide.com
 * @version 1.00
 * @since 2020/10/12
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

}

package com.officina_hide.base.common;

/**
 * 汎用項目クラス[Generic item class]<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2021/04/23
 */
public class FD_Item {

	/** 項目名[item name] */
	private String name;
	/** 項目種別[item type] */
	private String type;
	/** 項目サイズ[item size] */
	private int size;
	/** 項目設定値[item setting value] */
	private Object data;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
//	/** 項目値（検索用項目）[]item vlue (search item) */
//	private Object value;
//	/** 項目種別 */
//	private String valueType;
//	
//	/**
//	 * @return name
//	 */
//	public String getName() {
//		return name;
//	}
//	/**
//	 * @param name セットする name
//	 */
//	public void setName(String name) {
//		this.name = name;
//	}
//	/**
//	 * @return value
//	 */
//	public Object getValue() {
//		return value;
//	}
//	/**
//	 * @param value セットする value
//	 */
//	public void setValue(Object value) {
//		this.value = value;
//	}
//	/**
//	 * @return valueType
//	 */
//	public String getValueType() {
//		return valueType;
//	}
//	/**
//	 * @param valueType セットする valueType
//	 */
//	public void setValueType(String valueType) {
//		this.valueType = valueType;
//	}
}

package com.officina_hide.base.common;

/**
 * 情報集約クラス[Data collection class]
 * @author officina-hide.net
 * @version 新規作成
 * @since 2022/03/03 Ver. 1.00
 */
public class FD_Collection {

	/** 情報名 */
	private String name;
	/** 情報値 */
	private Object value;
	/** 初期値 */
	private Object initialValue;
	/** 情報型名 */
	private String typeName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Object getInitialValue() {
		return initialValue;
	}
	public void setInitialValue(Object initialValue) {
		this.initialValue = initialValue;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public void setFD_Item_ID(long id) {
		this.value = id;
	}
}

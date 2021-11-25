package com.officina_hide.fx.base;

import com.officina_hide.fx.model.X_FX_Field;

/**
 * 画面項目情報[Screen item information]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/11/25 Ver. 1.00
 */
public class FX_FieldItem {

	/** 画面項目情報 */
	private X_FX_Field field;
	/** 項目オブジェクト */
	private Object fieldItem;
	/** 項目属性名 */
	private String fieldTypeName;

	public X_FX_Field getField() {
		return field;
	}
	public void setField(X_FX_Field field) {
		this.field = field;
	}
	public Object getFieldItem() {
		return fieldItem;
	}
	public void setFieldItem(Object fieldItem) {
		this.fieldItem = fieldItem;
	}
	public String getFieldTypeName() {
		return fieldTypeName;
	}
	public void setFieldTypeName(String fieldTypeName) {
		this.fieldTypeName = fieldTypeName;
	}
}

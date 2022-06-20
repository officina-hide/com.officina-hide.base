package com.officina_hide.base.common;

import com.officina_hide.ui.model.X_FX_Field;

import javafx.scene.control.Label;

/**
 * 画面項目情報[Screen item information]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/06/17 Ver. 1.00
 */
public class FD_FIeldData {

	/** 項目 : 画面項目情報 */
	private X_FX_Field fieldData;
	/** 項目 : ラベル */
	private Label fieldLabel;

	public X_FX_Field getFieldData() {
		return fieldData;
	}
	public void setFieldData(X_FX_Field fieldData) {
		this.fieldData = fieldData;
	}
	public Label getFieldLabel() {
		return fieldLabel;
	}
	public void setFieldLabel(Label fieldLabel) {
		this.fieldLabel = fieldLabel;
	}
}

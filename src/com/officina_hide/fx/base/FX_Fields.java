package com.officina_hide.fx.base;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * 画面項目一覧[Screen item list]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/11/25 Ver. 1.00
 */
public class FX_Fields {

	/** 表示項目情報 */
	private List<FX_FieldItem> fields;
	/** タブに対する対象情報件数 */
	private long recordCount;
	/** ヘッダー領域 */
	private HBox headBox;

	public List<FX_FieldItem> getFields() {
		if(fields == null) {
			fields = new ArrayList<>();
		}
		return fields;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public HBox getHeadBox() {
		if(headBox == null) {
			headBox = new HBox(5);
			headBox.setAlignment(Pos.CENTER_LEFT);
			HBox nameBox = new HBox(5);
			HBox countBox = new HBox(5);
			countBox.setAlignment(Pos.CENTER_RIGHT);
			countBox.setSpacing(100);
			headBox.getChildren().addAll(nameBox, countBox);
			DecimalFormat countFmt = new DecimalFormat("#,##0");
			Label recordCount = new Label(countFmt.format(getRecordCount()));
			countBox.getChildren().add(recordCount);
		}
		return headBox;
	}

}

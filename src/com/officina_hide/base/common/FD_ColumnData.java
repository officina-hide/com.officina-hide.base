package com.officina_hide.base.common;

/**
 * テーブル項目情報[Table item information]
 * @author officina-hide.net
 * @version 1.50 新規作成
 * @since 2022/03/26 Ver. 1.50
 */
public class FD_ColumnData {

	/** 項目 : テーブル項目名 */
	private String columnName;
	/** 項目 : テーブル項目属性名 */
	private String columnType;
	/** 項目 : テーブル項目値 */
	private Object columnData;

	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public Object getColumnData() {
		return columnData;
	}
	public void setColumnData(Object columnData) {
		this.columnData = columnData;
	}
}

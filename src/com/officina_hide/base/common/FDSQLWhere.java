package com.officina_hide.base.common;

import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.model.I_FD_DB;

/**
 * SQL用whereクラス[Where class for SQL]<br>
 * @author officine-hide.com
 * @version 1.31
 * @since 2021/02/09
 */
public class FDSQLWhere implements I_FD_DB {

	/** Whre句リスト */
	List<whereData> list = new ArrayList<>();
	
	/**
	 * コンストラクター<br>
	 * 実体化時に、指定された項目に対して文字情報のwhere句を生成する。<br>
	 * At the time of instance, the where clause of character information is generated for the specified item.
	 * @param columnName 項目名
	 * @param value 文字情報
	 */
	public FDSQLWhere(String columnName, String value) {
		whereData data = new whereData();
		data.setColumnName(columnName);
		data.setColumnData(value);
		data.setColumnType(COLUMNTYPE_ID_FD_Text);
		list.add(data);
	}
	
	@Override
	public String toString() {
		/*
		 * SQLWhere句を出力する。
		 */
		StringBuffer where = new StringBuffer();
		for(whereData data : list) {
			if(where.length() > 0) {
				// TODO 当面はANDによる直列型のWhere句のみを扱う（2021/02/11）
				where.append("AND").append(" ");
			}
			switch(data.getColumnType()) {
			case COLUMNTYPE_ID_FD_Text:
				where.append(data.getColumnName()).append(" = ").append(FD_SQ).append(data.getColumnData()).append(FD_SQ);
				break;
			}
		}
		return where.toString();
	}



	/**
	 * Where句情報プライベートクラスWhere clause information private class[]<br>
	 * @author officina-hide.com
	 * @version 1.31
	 * @since 2021/02/11
	 */
	private class whereData {
		private String columnName;
		private Object columnData;
		private int columnType;
		public String getColumnName() {
			return columnName;
		}
		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}
		public Object getColumnData() {
			return columnData;
		}
		public void setColumnData(Object columnData) {
			this.columnData = columnData;
		}
		public int getColumnType() {
			return columnType;
		}
		public void setColumnType(int columnType) {
			this.columnType = columnType;
		}
	}
}

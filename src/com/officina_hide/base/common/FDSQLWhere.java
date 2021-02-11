package com.officina_hide.base.common;

import com.officina_hide.base.model.I_FD_DB;

/**
 * SQL用whereクラス[Where class for SQL]<br>
 * @author officine-hide.com
 * @version 1.31
 * @since 2021/02/09
 */
public class FDSQLWhere implements I_FD_DB {

	/** Whre句リスト */
	
	private StringBuffer where;
	
	/**
	 * コンストラクター<br>
	 * 実体化時に、指定された項目に対して文字情報のwhere句を生成する。<br>
	 * At the time of instance, the where clause of character information is generated for the specified item.
	 * @param columnName 項目名
	 * @param value 文字情報
	 */
	public FDSQLWhere(String columnName, String value) {
		where.append(columnName).append(" = ").append(FD_SQ).append(value).append(FD_SQ);
	}
	
	private class whereData {
		private String columnName;
		private Object columnData;
		private int columnType;
	}
}

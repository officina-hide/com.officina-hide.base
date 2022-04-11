package com.officina_hide.base.common;

/**
 * 条件情報クラス[Condition information class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[Create new]
 * @since 2022/04/11 Ver. 1.00
 */
public class FD_WhereData {

	/** 条件用一覧 */
	FD_Collections columnList = new FD_Collections();
	
	public FD_WhereData(String columnName, String columnData) {
		FD_Collect collect = new FD_Collect();
		collect.setName(columnName);
		collect.setValue(columnData);
	}

}

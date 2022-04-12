package com.officina_hide.base.common;

import com.officina_hide.base.model.I_FD_DB;

/**
 * 条件情報クラス[Condition information class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[Create new]
 * @since 2022/04/11 Ver. 1.00
 */
public class FD_WhereData {

	/** 条件用一覧 */
	FD_Collections columnList = new FD_Collections();
	
	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/04/12 Ver. 1.00
	 * @param columnName テーブル項目名[Table column name]
	 * @param columnData テーブル項目値[Table column value]
	 */
	public FD_WhereData(String columnName, String columnData) {
		FD_Collect collect = new FD_Collect();
		collect.setName(columnName);
		collect.setValue(columnData);
		collect.setType(I_FD_DB.FD_Item_String);
		columnList.getList().add(collect);
	}

	@Override
	public String toString() {
		FD_Collect fc = columnList.getList().get(0);
		return fc.getWhereString();
	}

}

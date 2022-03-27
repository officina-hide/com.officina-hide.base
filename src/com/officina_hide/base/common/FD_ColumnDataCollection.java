package com.officina_hide.base.common;

import java.util.ArrayList;
import java.util.List;

/**
 * テーブル項目コレクション[Table item collection]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成
 * @since 2022/03/24 Ver. 1.50
 */
public class FD_ColumnDataCollection {

	/** 情報リスト */
	List<FD_ColumnData> list = new ArrayList<>();
	
	/**
	 * テーブル項目追加[Table item entry]
	 * @param columnName
	 * @param typeName 
	 */
	public void add(String columnName, String typeName) {
		FD_ColumnData cd = new FD_ColumnData();
		cd.setColumnName(columnName);
		cd.setColumnType(typeName);
		list.add(cd);
	}

}

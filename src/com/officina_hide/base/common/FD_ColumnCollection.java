package com.officina_hide.base.common;

import java.util.ArrayList;
import java.util.List;

/**
 * テーブル項目集約情報[Table item collection]
 * @author officina-hide.net
 * @version 新規作成
 * @since 2022/03/03 Ver. 1.00
 */
public class FD_ColumnCollection {

	/** テーブル項目情報リスト */
	private List<FD_Collection> list;

	/**
	 * コンストラクター[Constructor]
	 * @author officina-hide.net
	 * @since 2022/03/03 Ver. 1.00
	 * @param tableName テーブル名[Table name] 
	 * @param dataList 登録データリスト[Entry data list]
	 */
	public FD_ColumnCollection(String tableName, String dataList) {
		createList(tableName);
	}

	/**
	 * テーブル項目情報リスト初期化[Table item information list initialization]
	 * @author officina-hide.net
	 * @since 2022/03/04 Ver. 1.00
	 * @param tableName テーブル名
	 */
	private void createList(String tableName) {
		
	}

	/**
	 * テーブル項目情報リスト取得[Get table item collection list]
	 * @author officina-hide.net
	 * @since 2022/03/03 Ver. 1.00
	 * @return テーブル項目情報リスト[Table item collection list]
	 */
	public List<FD_Collection> getCollectionList() {
		if(list == null) {
			list = new ArrayList<>();
		}
		return list;
	}
}

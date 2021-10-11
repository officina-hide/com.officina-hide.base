package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Items;

/**
 * テーブルI/Oクラス[Table I / O class]
 * @author officina-hide.net
 * @version 1.00
 * @since 2021/05/22
 */
public class X_FD_Table extends FD_DB implements I_FD_Table {	
//	/** 項目リスト[item list] */
//	private FD_Items items; 
	
	//	private FD_Items items = new FD_Items();
	/** テーブル項目 */
	private long FD_Table_ID;
	private String FD_Table_Name;
//
//	/**
//	 * インスタンス時に、XMLデータからテーブル情報をセットする。<br>
//	 * Set table information from XML data at instance time.<br>
//	 * @author officina-hide.net
//	 * @since 1.00 2021/05/22
//	 * @param xmlData XMLデータ[XML data]
//	 */
//	public X_FD_Table(Element xmlData) {
//		if(xmlData.getTagName().equals("Table")) {
////			items.add(I_FD_Table.COLUMNNAME_FD_Table_ID, xmlData.getAttribute(I_FD_Table.COLUMNNAME_FD_Table_ID));
////			items.add(I_FD_Table.COLUMNNAME_FD_Table_Name, xmlData.getAttribute(I_FD_Table.COLUMNNAME_FD_Table_Name));
////			items.add(I_FD_Table.COLUMNNAME_FD_DisplayName, xmlData.getAttribute(I_FD_Table.COLUMNNAME_FD_DisplayName));
////			items.add(I_FD_Table.COLUMNNAME_FD_Description, xmlData.getAttribute(I_FD_Table.COLUMNNAME_FD_Description));
//		}
//	}

	/**
	 * インスタンス時に、指定されたIDを持つ情報を取得する。<br>
	 * At the time instance, to get the information with the specified ID.<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/07/13
	 * @param env 環境情報[Environment Information]
	 * @param tableID 情報ID[Information ID]
	 */
	public X_FD_Table(FD_EnvData env, long tableID) {
		createItemList();
		if(tableID > 0) {
			load(env, Table_Name, tableID, items);
		}
	}

	/**
	 * テーブル項目リスト生成[Table item list generation]<br>
	 * TODO 汎用化予定　2021/08/26
	 * @author officine-hide.net
	 * @since 1.00 2021/04/23
	 * @return テーブル項目リスト[Table item list]
	 */
	private void createItemList() {
		items = new FD_Items();
		items.setTableName(Table_Name);
		items.setTableId(Table_ID);
		items.add(COLUMNNAME_FD_Table_ID, null, FD_ITEM_ID);
		items.add(COLUMNNAME_FD_Table_Name, null, FD_ITEM_String);
		items.add(COLUMNNAME_FD_Name, null, FD_ITEM_String);
		items.add(COLUMNNAME_FD_Description, null, FD_ITEM_Text);
		baseItemSet(items);
	}

	/**
	 * 情報登録[Information registration]
	 * @param env 環境情報[Enfironment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
	}
//
//	/**
//	 * テーブル情報テーブル生成[Table information table generation]<br>
//	 * @author officine-hide.net
//	 * @since 1.00 2021/06/03
//	 * @param env 環境情報[Environment Information]
//	 */
//	public void createTable(FD_EnvData env) {
//		//既構築テーブルの削除[Delete existing table]
//		deleteTable(env, items.getStringData(COLUMNNAME_FD_Table_Name));
//		//テーブル生成[Table generation]
////		createTable(env, items.getStringData(COLUMNNAME_FD_Table_Name),
////				items.getStringData(COLUMNNAME_FD_), getItems());
//	}

	public long getFD_Table_ID() {
		FD_Table_ID = items.getlongData(COLUMNNAME_FD_Table_ID);
		return FD_Table_ID;
	}
	public void setFD_Table_ID(long tableID) {
		items.setValue(COLUMNNAME_FD_Table_ID, tableID);
	}
	public String getFD_Table_Name() {
		FD_Table_Name = items.getStringData(COLUMNNAME_FD_Table_Name);
		return FD_Table_Name;
	}
	public void setFD_Table_Name(String tableName) {
		items.setValue(COLUMNNAME_FD_Table_Name, tableName);
	}
//
//	public FD_Items getItems() {
//		return items;
//	}

}

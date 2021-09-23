package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Items;
import com.officina_hide.base.common.FD_WhereData;

/**
 * 属性項目情報I/Oクラス[Type item information I/O class]<br>
 * @author officina-hide.net
 * @version 1.00
 * @since 2021/09/23
 */
public class X_FD_TypeItem extends FD_DB implements I_FD_TypeItem {

	/** 項目 : 属性項目情報ID */
	private long FD_TypeItem_ID;
	/** 項目 : 属性項目識別名 */
	private String FD_TypeItem_Name;
	/** 項目 : 属性情報ID */
	private long FD_Type_ID;
	
	/**
	 * コンストラクタ[Constructor]
	 * @param env 環境情報[Enfironment information]
	 * @param typeItemID 属性項目情報ID
	 */
	public X_FD_TypeItem(FD_EnvData env, int typeItemID) {
		createItemList();
	}

	/**
	 * コンストラクタ[Constructor]
	 * @author officina-hide.net
	 * @since 1.00 2021/09/23
	 * @param env 環境情報[Enfironment information]
	 * @param where 抽出条件[Extraction condition]
	 */
	public X_FD_TypeItem(FD_EnvData env, FD_WhereData where) {
		createItemList();
		if(where != null) {
			load(env, items, where);
		}
	}

	/**
	 * テーブル項目リスト生成[Table item list generation]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/23
	 * @return テーブル項目リスト[Table item list]
	 */
	private void createItemList() {
		items = new FD_Items();
		items.add(COLUMNNAME_FD_TypeItem_ID, null, FD_Item_ID);
		items.add(COLUMNNAME_FD_TypeItem_Name, null, FD_Item_String);
		items.add(COLUMNNAME_FD_Type_ID, null, FD_Item_ID);
		items.add(COLUMNNAME_FD_Name, null, FD_Item_String);
		items.add(COLUMNNAME_FD_Description, null, FD_Item_Text);
		baseItemSet(items);
		items.setTableId(Table_ID);
		items.setTableName(Table_Name);
	}

	/**
	 * 情報保存
	 * @param env
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
	}

	public long getFD_TypeItem_ID() {
		FD_TypeItem_ID = items.getlongData(COLUMNNAME_FD_TypeItem_ID);
		return FD_TypeItem_ID;
	}
	public void setFD_TypeItem_ID(long typeItemID) {
		items.setValue(COLUMNNAME_FD_TypeItem_ID, typeItemID);
	}
	public String getFD_TypeItem_Name() {
		FD_TypeItem_Name = items.getStringData(COLUMNNAME_FD_TypeItem_Name);
		return FD_TypeItem_Name;
	}
	public void setFD_TypeItem_Name(String typeItemName) {
		items.setValue(COLUMNNAME_FD_TypeItem_Name, typeItemName);
	}
	public long getFD_Type_ID() {
		FD_Type_ID = items.getlongData(COLUMNNAME_FD_Type_ID);
		return FD_Type_ID;
	}
	public void setFD_Type_ID(long typeID) {
		items.setValue(COLUMNNAME_FD_Type_ID, typeID);
	}
	
}

package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Items;

/**
 * 種別情報I/Oクラス[Type information I / O class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/04 
 */
public class X_FD_Type extends FD_DB implements I_FD_Type {
//	
//	/** 項目情報 */
//	private FD_Items items;
	
	/** 属性情報ID */
	private long FD_Type_ID;
	/** 属性名 */
	private String FD_Type_Name;
	
	public X_FD_Type() {	
	}
	
	public X_FD_Type(FD_EnvData env, int typeId) {
		createItemList();
	}

	/**
	 * テーブル項目リスト生成[Table item list generation]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/22
	 * @return テーブル項目リスト[Table item list]
	 */
	private void createItemList() {
		items = new FD_Items();
		items.add(COLUMNNAME_FD_Type_ID, null, FD_Item_ID);
		items.add(COLUMNNAME_FD_Type_Name, null, FD_Item_String);
		items.add(COLUMNNAME_FD_Name, null, FD_Item_String);
		items.add(COLUMNNAME_FD_Description, null, FD_Item_Text);
		baseItemSet(items);
		items.setTableId(Table_ID);
		items.setTableName(Table_Name);
	}

	/**
	 * 情報登録[Information registration]
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
	}

	public long getFD_Type_ID() {
		FD_Type_ID = items.getlongData(COLUMNNAME_FD_Type_ID);
		return FD_Type_ID;
	}
	public void setFD_Type_ID(long typeID) {
		items.setValue(COLUMNNAME_FD_Type_ID, typeID);
	}
	public String getFD_Type_Name() {
		FD_Type_Name = items.getStringData(COLUMNNAME_FD_Type_Name);
		return FD_Type_Name;
	}
	public void setFD_Type_Name(String typeName) {
		items.setValue(COLUMNNAME_FD_Type_Name, typeName);
		FD_Type_Name = typeName;
	}
	
}

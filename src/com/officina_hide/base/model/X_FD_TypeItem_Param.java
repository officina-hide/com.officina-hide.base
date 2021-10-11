package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Items;

/**
 * 属性項目設定情報I/Oクラス[Attribute item setting information I/O class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/27
 */
public class X_FD_TypeItem_Param extends FD_DB implements I_FD_TypeITem_Param {

	/** 項目 : 属性項目設定情報ID */
	private long FD_TypeItemParamID;
	/** 項目 : 属性項目設定名 */
	private String FD_TypeItemParamName;
	/** 項目 : 属性項目情報ID */
	private long FD_TypeItemID;
	/** 項目 : 設定値 */
	private String FD_Value;
	
	/**
	 * コンストラクタ[Constructor]
	 * @author officine-hide.net
	 * @since 1.00 2021/09/27
	 * @param env 環境情報[Environment information]
	 * @param typeItemParamID 属性項目設定情報ID[Attribute item setting information ID]
	 */
	public X_FD_TypeItem_Param(FD_EnvData env, long typeItemParamID) {
		//項目一覧設定
		createItemList();
		if(typeItemParamID > 0) {
			load(env, Table_Name, typeItemParamID, items);
		}
	}

	/**
	 * 項目一覧生成[Item list generation]
	 * @author officine-hide.net
	 * @since 1.00 2021/09/27
	 */
	private void createItemList() {
		items = new FD_Items();
		items.add(COLUMNNAME_FD_TypeItem_Param_ID, null, FD_ITEM_ID);
		items.add(COLUMNNAME_FD_TypeItem_Param_Name, null, FD_ITEM_String);
		items.add(COLUMNNAME_FD_TypeItem_ID, null, FD_ITEM_ID);
		items.add(COLUMNNAME_FD_Value, null, FD_ITEM_Text);
		items.add(COLUMNNAME_FD_Name, null, FD_ITEM_String);
		items.add(COLUMNNAME_FD_Description, null, FD_ITEM_Text);
		baseItemSet(items);
		items.setTableId(Table_ID);
		items.setTableName(Table_Name);
	}

	/**
	 * 情報保存[Save Information]
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
	}

	public long getFD_TypeItemParamID() {
		FD_TypeItemParamID = items.getlongData(COLUMNNAME_FD_TypeItem_Param_ID);
		return FD_TypeItemParamID;
	}
	public void setFD_TypeItemParamID(long typeItemParamID) {
		items.setValue(COLUMNNAME_FD_TypeItem_Param_ID, typeItemParamID);
	}
	public String getFD_TypeItemParamName() {
		FD_TypeItemParamName = items.getStringData(COLUMNNAME_FD_TypeItem_Param_Name);
		return FD_TypeItemParamName;
	}
	public void setFD_TypeItemParamName(String typeItemParamName) {
		items.setValue(COLUMNNAME_FD_TypeItem_Param_Name, typeItemParamName);
	}
	public long getFD_TypeItemID() {
		FD_TypeItemID = items.getlongData(COLUMNNAME_FD_TypeItem_ID);
		return FD_TypeItemID;
	}
	public void setFD_TypeItemID(long typeItemID) {
		items.setValue(COLUMNNAME_FD_TypeItem_ID, typeItemID);
	}
	public String getFD_Value() {
		FD_Value = items.getStringData(COLUMNNAME_FD_Value);
		return FD_Value;
	}
	public void setFD_Value(String value) {
		items.setValue(COLUMNNAME_FD_Value, value);
	}

}

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

	/** 環境情報 */
	private FD_EnvData env = new FD_EnvData();
	
	/** 項目情報 */
	private FD_Items items;
	private int FD_Type_ID;
	
	public X_FD_Type(FD_EnvData env, int typeId) {
		this.env = env;
		
		items = new FD_Items();
		items.add(COLUMNNAME_FD_Type_ID, null, Item_Value_Type_ID);
		items.add(COLUMNNAME_FD_Type_Name, null, Item_Value_Type_String);
		load(env, Table_Name, typeId, items);
	}

	public int getFD_Type_ID() {
		return items.getintData(COLUMNNAME_FD_Type_ID);
	}
	public void setFD_Type_ID(int fD_Type_ID) {
		FD_Type_ID = fD_Type_ID;
	}
	
}

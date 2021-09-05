package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Items;

/**
 * テーブル項目I/Oクラス[Table item I / O class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/04
 */
public class X_FD_Column extends FD_DB implements I_FD_Column {
	
	/** 環境情報 */
	FD_EnvData env = new FD_EnvData();
	
	/** 項目 */
	private FD_Items items;
	private int FD_Column_ID;
	private int FD_Table_ID;
	private int FD_Type_ID;
	private X_FD_Type FD_Type;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/05
	 * @param env 環境情報[Environment Information]
	 * @param columnId テーブル項目情報ID[Table Item Information ID]
	 */
	public X_FD_Column(FD_EnvData env, int columnId) {
		this.env = env;
		
		items = new FD_Items();
		items.add(COLUMNNAME_FD_Column_ID, null, Item_Value_Type_ID);
		items.add(COLUMNNAME_FD_Column_Name, null, Item_Value_Type_String);
		items.add(COLUMNNAME_FD_DataDictionnary_ID, null, Item_Value_Type_ID);
		items.add(COLUMNNAME_FD_Table_ID, null, Item_Value_Type_ID);
		items.add(COLUMNNAME_FD_Type_ID, null, Item_Value_Type_ID);
		load(env, Table_Name, columnId, items);
	}

	public X_FD_Type getFD_Type() {
		System.out.println(getFD_Type_ID());
		if(FD_Type == null) {
			FD_Type = new X_FD_Type();
		}
		return FD_Type;
	}
	public int getFD_Type_ID() {
		return items.getintData(Item_Value_Type_ID);
	}
	public void setFD_Type_ID(int fD_Type_ID) {
		FD_Type_ID = fD_Type_ID;
	}
}

package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Items;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.model.X_FD_Column;

/**
 * Fx画面項目情報[Fx screen item information]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/08/26
 */
public class X_Fx_Field extends FD_DB implements I_Fx_Field {

	/** 環境情報 */
	FD_EnvData env = new FD_EnvData();
	
	/** 画面項目 */
	private int FX_Fields_ID;
	private String FX_Field_Name;
	private String FD_Name;
	private int Fx_View_ID;
	private X_Fx_View Fx_View;
	private int FD_Column_ID;
	private X_FD_Column FD_Column;
	
	private FD_Items items;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * インスタンス時に指定されたIDを持つ情報を抽出する。<br>
	 * Extract the information with the ID specified at the time of instance.<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/28
	 * @param env 環境情報[Environment Information]
	 * @param id [Information ID]
	 */
	public X_Fx_Field(FD_EnvData env, int id) {
		this.env = env;
		
		items = new FD_Items();
		items.add(COLUMNNAME_Fx_Field_ID, env, Item_Value_Type_ID);
		items.add(COLUMNNAME_Fx_Field_Name, env, Item_Value_Type_String);
		items.add(COLUMNNAME_Fx_View_ID, env, Item_Value_Type_ID);
		items.add(COLUMNNAME_FD_Column_ID, env, Item_Value_Type_ID);
		items.add(COLUMNNAME_FD_Name, null, Item_Value_Type_String);
		load(env, Table_Name, id, items);
	}

	/**
	 * 項目名でデータを返す。[Returns data by column name.]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/30
	 * @param columnName 項目名[column name]
	 * @return 情報[data]
	 */
	public String getValue(String columnName) {
		String data = null;
		data = items.getStringData(columnName);
		return data;
	}

	public int getFX_Fields_ID() {
		return FX_Fields_ID;
	}
	public void setFX_Fields_ID(int fX_Fields_ID) {
		FX_Fields_ID = fX_Fields_ID;
	}
	public String getFX_Field_Name() {
		return items.getStringData(COLUMNNAME_Fx_Field_Name);
	}
	public void setFX_Field_Name(String fX_Field_Name) {
		FX_Field_Name = fX_Field_Name;
	}
	public int getFx_View_ID() {
		return Fx_View_ID;
	}
	public void setFx_View_ID(int fx_View_ID) {
		Fx_View_ID = fx_View_ID;
	}
	public X_Fx_View getFx_View() {
		return Fx_View;
	}
	public int getFD_Column_ID() {
		return items.getintData(COLUMNNAME_FD_Column_ID);
	}
	public void setFD_Column_ID(int fD_Column_ID) {
		FD_Column_ID = fD_Column_ID;
	}
	public X_FD_Column getFD_Column() {
		if(FD_Column == null) {
			FD_Column = new X_FD_Column(env, getFD_Column_ID());
		}
		return FD_Column;
	}
//	public String getFD_Name() {
//		FD_Name = items.getStringData(COLUMNNAME_FD_Name);
//		return FD_Name;
//	}
//	public void setFD_Name(String fD_Name) {
//		FD_Name = fD_Name;
//	}
}

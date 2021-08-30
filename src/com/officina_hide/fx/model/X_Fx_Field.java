package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Items;
import com.officina_hide.base.model.FD_DB;

/**
 * Fx画面項目情報[Fx screen item information]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/08/26
 */
public class X_Fx_Field extends FD_DB implements I_Fx_Fields {

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
		items = new FD_Items();
		items.add(COLUMNNAME_Fx_Fields_ID, env, Item_Value_Type_ID);
		items.add(COLUMNNAME_Fx_Fields_Name, env, Item_Value_Type_String);
		items.add(COLUMNNAME_Fx_View_ID, env, Item_Value_Type_ID);
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

}

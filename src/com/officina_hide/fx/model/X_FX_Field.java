package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * Fx画面項目情報[Fx screen item information]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/08/26 新規作成
 * @since 2021/10/07 機能改訂
 */
public class X_FX_Field extends FD_DB implements I_FX_Field {

	/** 項目 : 画面項目情報iD */
	private long FX_Field_ID;
	/** 項目 : 画面項目名 */
	private String Fx_Field_Name;
	/** 項目 : タブ情報ID */
	private long FX_Tab_ID;
	/** 項目 : タブ情報 */
	private X_FX_Tab FX_Tab;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * インスタンス時に指定されたIDを持つ情報を抽出する。<br>
	 * Extract the information with the ID specified at the time of instance.<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/28
	 * @since 2021/10/07 Ver. 1.00 機能全面改訂
	 * @param env 環境情報[Environment Information]
	 * @param fieldID 画面項目情報ID [Screen item information ID]
	 */
	public X_FX_Field(FD_EnvData env, long fieldID) {
		createItemList(env, Table_Name);
		if(fieldID > 0) {
			load(env, Table_Name, fieldID, items);
		}
	}

	/**
	 * 
	 * @param env
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
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

	public long getFX_Field_ID() {
		FX_Field_ID = items.getlongData(COLUMNNAME_FX_Field_ID);
		return FX_Field_ID;
	}
	public void setFX_Field_ID(long fieldID) {
		items.setValue(COLUMNNAME_FX_Field_ID, fieldID);
	}
	public String getFx_Field_Name() {
		Fx_Field_Name = items.getStringData(COLUMNNAME_FX_Field_Name);
		return Fx_Field_Name;
	}
	public void setFx_Field_Name(String fieldName) {
		items.setValue(COLUMNNAME_FX_Field_Name, fieldName);
	}
	public long getFX_Tab_ID() {
		FX_Tab_ID = items.getlongData(COLUMNNAME_FX_Tab_ID);
		return FX_Tab_ID;
	}
	public void setFX_Tab_ID(long tabID) {
		items.setValue(COLUMNNAME_FX_Tab_ID, tabID);
	}
	public X_FX_Tab getFX_Tab(FD_EnvData env) {
		if(FX_Tab == null) {
			if(getFX_Tab_ID() > 0) {
				FX_Tab = new X_FX_Tab(env, getFX_Tab_ID());
			} else {
				return null;
			}
		} else {
			if(getFX_Tab_ID() != FX_Tab.getFX_Tab_ID()) {
				FX_Tab = new X_FX_Tab(env, getFX_Tab_ID());
			}
		}
		return FX_Tab;
	}

}

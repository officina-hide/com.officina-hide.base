package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

public class X_FX_Tab extends FD_DB implements I_FX_Tab {

	/** 項目 : FXタブ情報ID */
	private long FX_Tab_ID;
	/** 項目 : FXタブ識別名 */
	private String FX_Tab_Name;
	/** 項目 : FX画面情報ID */
	private long FX_View_ID;
	
	/**
	 * コンストラクタ[Constructor]
	 * @param env 環境情報[Enfironment information]
	 * @param tabID FXタブ情報ID
	 */
	public X_FX_Tab(FD_EnvData env, int tabID) {
		createItemList(env, Table_Name);
		if(tabID > 0) {
			load(env, Table_Name, tabID, items);
		}
	}

	/**
	 * 情報登録[Save data]
	 * @author officina-hide.net
	 * @since 2021/10/03 Ver. 1.00
	 * @param env 環境情報[Enfironment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
	}

	public long getFX_Tab_ID() {
		FX_Tab_ID = items.getlongData(COLUMNNAME_FX_Tab_ID);
		return FX_Tab_ID;
	}
	public void setFX_Tab_ID(long tabID) {
		items.setValue(COLUMNNAME_FX_Tab_ID, tabID);
	}
	public String getFX_Tab_Name() {
		FX_Tab_Name = items.getStringData(COLUMNNAME_FX_Tab_Name);
		return FX_Tab_Name;
	}
	public void setFX_Tab_Name(String tabName) {
		items.setValue(COLUMNNAME_FX_Tab_Name, tabName);
	}
	public long getFX_View_ID() {
		FX_View_ID = items.getlongData(COLUMNNAME_FX_View_ID);
		return FX_View_ID;
	}
	public void setFX_View_ID(long viewId) {
		items.setValue(COLUMNNAME_FX_View_ID, viewId);
	}

}

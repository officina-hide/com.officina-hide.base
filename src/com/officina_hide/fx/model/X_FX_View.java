package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.FD_DB;

/**
 * Fx画面基本情報I/Oクラス[Fx screen basic information I / O class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/08/23
 */
public class X_FX_View extends FD_DB implements I_FX_View {

//	/** 項目リスト[item list] */
//	private FD_Items items = new FD_Items();
	
	/** Fx画面基本情報ID */
	private long FX_View_ID;
	/** Fx画面識別名 */
	private String FX_View_Name;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/23
	 * @param env 環境情報[Environment Information]
	 * @param viewId 条件情報[Where clouse Information]
	 */
	public X_FX_View(FD_EnvData env, int viewId) {
		/** 項目リスト作成 */
		createItemList(env, Table_Name);
		if(viewId > 0) {
			load(env, Table_Name, viewId, items);
		}
	}

	/**
	 * コンストラクタ[Constructor]
	 * @param env 環境情報[Environment information]
	 * @param where 抽出条件[Extraction condition]
	 */
	public X_FX_View(FD_EnvData env, FD_WhereData where) {
		/** 項目リスト作成 */
		createItemList(env, Table_Name);
		if(where != null) {
			load(env, items, where);
		}
	}

	/**
	 * 情報登録[Save data]<br>
	 * @author officina-hide.net
	 * @since 2021/10/03 Ver. 1.00
	 * @param env 環境情報[Enfironment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
	}

	public long getFX_View_ID() {
		FX_View_ID = items.getlongData(COLUMNNAME_FX_View_ID);
		return FX_View_ID;
	}
	public void setFX_View_ID(long viewID) {
		items.setValue(COLUMNNAME_FX_View_ID, viewID);
	}
	public String getFX_View_Name() {
		FX_View_Name = items.getStringData(COLUMNNAME_FX_View_Name);
		return FX_View_Name;
	}
	public void setFX_View_Name(String viewName) {
		items.setValue(COLUMNNAME_FX_View_Name, viewName);
	}

}

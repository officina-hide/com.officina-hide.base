package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.X_FD_Table;

public class X_FX_Tab extends FD_DB implements I_FX_Tab {

	/** 項目 : FXタブ情報ID */
	private long FX_Tab_ID;
	/** 項目 : FXタブ識別名 */
	private String FX_Tab_Name;
	/** 項目 : FX画面情報ID */
	private long FX_View_ID;
	/** 項目 : テーブル情報ID */
	private long FD_Table_ID;
	/** 情報 : テーブル情報 */
	private X_FD_Table FD_Table;
	/** 項目 : タブレベル */
	private int FX_Tab_Level;
	
	/**
	 * コンストラクタ[Constructor]
	 * @param env 環境情報[Environment information]
	 * @param tabId FXタブ情報ID
	 */
	public X_FX_Tab(FD_EnvData env, long tabId) {
		createItemList(env, Table_Name);
		if(tabId > 0) {
			load(env, Table_Name, tabId, items);
		}
	}

	/**
	 * コンストラクタ[Constructor]
	 * @author officina-hide.net
	 * @since 2021/10/06 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param where 抽出条件[Extraction condition]
	 */
	public X_FX_Tab(FD_EnvData env, FD_WhereData where) {
		createItemList(env, Table_Name);
		if(where != null) {
			load(env, items, where);
		}
	}

	/**
	 * 情報登録[Save data]
	 * @author officina-hide.net
	 * @since 2021/10/03 Ver. 1.00
	 * @param env 環境情報[Environment information]
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
	public long getFD_Table_ID() {
		FD_Table_ID = items.getlongData(COLUMNNAME_FD_Table_ID);
		return FD_Table_ID;
	}
	public void setFD_Table_ID(long tableID) {
		items.setValue(COLUMNNAME_FD_Table_ID, tableID);
	}
	public int getFX_Tab_Level() {
		FX_Tab_Level = items.getintData(COLUMNNAME_FX_Tab_Level);
		return FX_Tab_Level;
	}
	public void setFX_Tab_Level(int tabLevel) {
		items.setValue(COLUMNNAME_FX_Tab_Level, tabLevel);
	}
	public X_FD_Table getFD_Table(FD_EnvData env) {
		if(FD_Table == null) {
			if(getFD_Table_ID() == 0) {
				return null;
			} else {
				FD_Table = new X_FD_Table(env, getFD_Table_ID());
			}
		} else {
			if(getFD_Table_ID() != FD_Table.getFD_Table_ID()) {
				FD_Table = new X_FD_Table(env, getFD_Table_ID());
			}
		}
		return FD_Table;
	}

}

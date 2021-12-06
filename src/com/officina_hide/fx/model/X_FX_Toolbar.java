package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * ツールバー情報I/Oクラス[Toolbar information I/O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/12/06 Ver. 1.00
 */
public class X_FX_Toolbar extends FD_DB implements I_FX_ToolBar {

	/** 項目 : ツールバー情報ID */
	private long FX_ToolBar_ID;
	/** 項目 : ツールバー名  */
	private String FX_ToolBar_Name;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @param env 環境情報[Environment information]
	 * @param toolBarID ツールバー情報ID[ToolBar information ID]
	 */
	public X_FX_Toolbar(FD_EnvData env, int toolBarID) {
		createItemList(env, Table_Name);
		if(toolBarID > 0) {
			load(env, Table_Name, toolBarID, items);
		}
	}

	/**
	 * 情報保存[Save data]<br>
	 * @author officina-hide.net
	 * @since 2021/12/06 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
	}

	public long getFX_ToolBar_ID() {
		FX_ToolBar_ID = items.getlongData(COLUMNNAME_FX_ToolBar_ID);
		return FX_ToolBar_ID;
	}
	public void setFX_ToolBar_ID(long toolBarId) {
		items.setValue(COLUMNNAME_FX_ToolBar_ID, toolBarId);
	}
	public String getFX_ToolBar_Name() {
		FX_ToolBar_Name = items.getStringData(COLUMNNAME_FX_ToolBar_Name);
		return FX_ToolBar_Name;
	}
	public void setFX_ToolBar_Name(String toolBarName) {
		items.setValue(COLUMNNAME_FX_ToolBar_Name, toolBarName);
	}

}

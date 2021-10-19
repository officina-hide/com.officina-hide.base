package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.X_FD_TypeItem;

/**
 * メニュー情報I/Oクラス[Menu information I/O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/19 Ver. 1.00
 */
public class X_FX_Menu extends FD_DB implements I_FX_Menu {

	/** 項目 : メニュー情報ID */
	private long FX_Menu_ID;
	/** 項目 : メニュー名 */
	private String FX_Menu_Name;
	/** 項目 : 遷移先情報ID */
	private long FX_Target_ID;
	/** 項目 : 属性項目情報ID */
	private long FD_TypeItem_ID;
	/** 情報 : 属性項目情報 */
	private X_FD_TypeItem FD_TypeItem;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2021/10/19 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param menuId メニュー情報ID[Menu information ID]
	 */
	public X_FX_Menu(FD_EnvData env, long menuId) {
		createItemList(env, Table_Name);
		if(menuId > 0) {
			load(env, Table_Name, menuId, items);
		}
	}

	/**
	 * 情報登録[Save data]<br>
	 * @author officina-hide.net
	 * @since 2021/10/19 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
	}

	public long getFX_Menu_ID() {
		FX_Menu_ID = items.getlongData(COLUMNNAME_FX_Menu_ID);
		return FX_Menu_ID;
	}
	public void setFX_Menu_ID(long menuId) {
		items.setValue(COLUMNNAME_FX_Menu_ID, menuId);
	}
	public String getFX_Menu_Name() {
		FX_Menu_Name = items.getStringData(COLUMNNAME_FX_Menu_Name);
		return FX_Menu_Name;
	}
	public void setFX_Menu_Name(String menuName) {
		items.setValue(COLUMNNAME_FX_Menu_Name, menuName);
	}
	public long getFX_Target_ID() {
		FX_Target_ID = items.getlongData(COLUMNNAME_FX_Target_ID);
		return FX_Target_ID;
	}
	public void setFX_Target_ID(long targetId) {
		items.setValue(COLUMNNAME_FX_Target_ID, targetId);
	}
	public long getFD_TypeItem_ID() {
		FD_TypeItem_ID = items.getlongData(COLUMNNAME_FD_TypeItem_ID);
		return FD_TypeItem_ID;
	}
	public void setFD_TypeItem_ID(long typeItemId) {
		items.setValue(COLUMNNAME_FD_TypeItem_ID, typeItemId);
	}
	public X_FD_TypeItem getFD_TypeItem(FD_EnvData env) {
		if(FD_TypeItem == null) {
			if(getFD_TypeItem_ID() > 0) {
				FD_TypeItem = new X_FD_TypeItem(env, getFD_TypeItem_ID());
			}
		} else {
			if(FD_TypeItem.getFD_TypeItem_ID() != getFD_TypeItem_ID()) {
				FD_TypeItem = new X_FD_TypeItem(env, getFD_TypeItem_ID());
			}
		}
		return FD_TypeItem;
	}

}

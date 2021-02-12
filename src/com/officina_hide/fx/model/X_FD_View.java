package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * Fx画面情報I/Oクラス[Fx screen information I / O class]<br>
 * @author officina-hide.com
 * @version 1.31
 * @since 2021/02/08
 */
public class X_FD_View extends FD_DB implements I_FD_View {

	/**
	 * コンストラクター<br>
	 * インスタンス化時にアイテムを初期化します。<br>
	 * Initialize the items at the time of instancing.<br>
	 * @author officina-hide.com
	 * @since 1.31 2021/02/08
	 * @param env 環境情報
	 */
	public X_FD_View(FD_EnvData env) {
		FD_DB_Utility dbUtil = new FD_DB_Utility();
		dbUtil.initializeItemList(env, TABLE_ID, itemList);
	}

	/**
	 * コンストラクター<br>
	 * インスタンス化時にアイテムを初期化し、指定されたFx画面情報IDを扱う持つ情報を抽出する。<br>
	 * Initialize the item at the time of instance and extract the information that handles the specified Fx screen information ID.
	 * @param env 環境情報
	 * @param fxViewID Fx画面情報ID
	 */
	public X_FD_View(FD_EnvData env, int fxViewID) {
		FD_DB_Utility dbUtil = new FD_DB_Utility();
		dbUtil.initializeItemList(env, TABLE_ID, itemList);
		load(env, Table_Name, fxViewID);
	}

	/**
	 * 情報保存[Information storage]<br>
	 * @author officina-hide.com
	 * @since 1.31 2021/02/08
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

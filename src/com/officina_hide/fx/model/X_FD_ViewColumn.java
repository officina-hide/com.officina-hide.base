package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * Fx画面項目情報I/OクラスFx screen item information I/O class]<br>
 * @author officina-hide.com
 * @version 1.31
 * @since 2021/02/20
 */
public class X_FD_ViewColumn extends FD_DB implements I_FD_ViewColumn {

	/**
	 * コンストラクター<br>
	 * インスタンス化時にアイテムを初期化します。<br>
	 * Initialize the items at the time of instancing.<br>
	 * @author officina-hide.com
	 * @since 1.31 2021/02/20
	 * @param env 環境情報
	 */
	public X_FD_ViewColumn(FD_EnvData env) {
		FD_DB_Utility dbUtil = new FD_DB_Utility();
		dbUtil.initializeItemList(env, TABLE_ID, itemList);
	}

	/**
	 * 情報保存[Information storage]<br>
	 * @author officina-hide.com
	 * @since 1.31 2021/02/20
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

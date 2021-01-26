package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;

/**
 * リファレンスリスト情報I/Oクラス[Reference list information I / O class]
 * @author officina-hide.com
 * @version 1.30
 * @since 2021/01/25
 */
public class X_FD_ReferenceList extends FD_DB implements I_FD_ReferenceList {

	/**
	 * コンストラクタ<br>
	 * インスタンス化時にアイテムを初期化します。<br>
	 * Initialize the items at the time of instancing.<br>
	 * @author officina-hide.com
	 * @since 1.30 2021/01/25
	 * @param env 環境情報
	 */
	public X_FD_ReferenceList(FD_EnvData env) {
		FD_DB_Utility dbUtil = new FD_DB_Utility();
		dbUtil.initializeItemList(env, TABLE_ID, itemList);
	}

	/**
	 * 情報保存[Information storage]<br>
	 * @author officine-hide.com
	 * @since 1.30 2021/01/25
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

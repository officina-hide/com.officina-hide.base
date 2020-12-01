package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * リファレンスリスト情報I/Oクラス<br>
 * @author officine-hide.com
 * @version 1.21
 * @since 2020/11/26
 */
public class X_FD_ReferenceList extends FD_DB implements I_FD_ReferenceList {

	/**
	 * コンストラクター<br>
	 * @author officine-hide.com
	 * @since 1.21 2020/11/26
	 * @param env 環境情報
	 */
	public X_FD_ReferenceList(FD_EnvData env) {
		//実体化時に、項目を初期化する。
		initializeItemList(env, TABLE_ID);
	}

	/**
	 * リファレンスリスト情報登録<br>
	 * @author officine-hide.com
	 * @since 1.21 2020/11/26
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

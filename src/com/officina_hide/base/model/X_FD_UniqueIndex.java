package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * ユニーク制約インデックス情報I/Oクラス<br>
 * @author officine-hide.com
 * @version 1.20
 * @since 2020/11/13
 */
public class X_FD_UniqueIndex extends FD_DB implements I_FD_UniqueIndex {

	/**
	 * コンストラクター<br>
	 * @author officine-hide.com
	 * @since 1.20 2020/11/13
	 */
	public X_FD_UniqueIndex(FD_EnvData env) {
		//実体化時に、項目を初期化する。
		initializeItemList(env, TABLE_ID);
	}

	/**
	 * 情報保存<br>
	 * @author officine-hide.com
	 * @since 1.20 2020/11/13
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

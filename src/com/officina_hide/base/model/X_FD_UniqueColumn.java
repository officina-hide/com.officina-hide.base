package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * ユニーク制約項目情報I/Oクラス<br>
 * @author officina-hide.com
 * @version 1.20
 * @since 2020/11/14
 */
public class X_FD_UniqueColumn extends FD_DB implements I_FD_UniqueColumn {

	/**
	 * コンストラクター<br>
	 * @param env 環境情報
	 */
	public X_FD_UniqueColumn(FD_EnvData env) {
		//実体化時に、項目を初期化する。
		initializeItemList(env, TABLE_ID);
	}

	/**
	 * 情報登録<br>
	 * @author officina-hide.com
	 * @since 1.20 2020/11/14
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

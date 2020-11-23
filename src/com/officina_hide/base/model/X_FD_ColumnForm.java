package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * 項目書式情報I/Oクラス<br>
 * @author officina-hide.com
 * @version 1.21 新規作成
 * @since 2020/11/22
 */
public class X_FD_ColumnForm extends FD_DB implements I_FD_ColumnForm {

	/**
	 * コンストラクター<br>
	 * @author officina-hide.com
	 * @since 1.21 2020/11/22
	 * @param env 環境情報
	 */
	public X_FD_ColumnForm(FD_EnvData env) {
		//実体化時に、項目を紐付ける初期化する。
		initializeItemList(env, TABLE_ID);
	}

	/**
	 * 情報保存<br>
	 * @author officina-hide.com
	 * @since 1.21 2020/11/22
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_ColumnFormArray;

/**
 * 書式配列情報I/Oクラス<br>
 * @author officina-hide.com
 * @version 1.21
 * @since 2020/11/23
 */
public class X_FD_ColumnFormArray extends FD_DB implements I_FD_ColumnFormArray {

	/**
	 * コンストラクター<br>
	 * @author officina-hide.com
	 * @since 1.21 2020/11/23
	 * @param env 環境情報
	 */
	public X_FD_ColumnFormArray(FD_EnvData env) {
		//実体化時に、項目を初期化する。
		initializeItemList(env, TABLE_ID);
	}

	/**
	 * 情報登録<br>
	 * @author officina-hide.com
	 * @since 1.21 2020/11/23
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

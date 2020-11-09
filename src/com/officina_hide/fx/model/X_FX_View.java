package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * 画面情報I/Oクラス<br>
 * @author officine-hide.com
 * @version 1.20
 * @since 2020/11/10
 */
public class X_FX_View extends FD_DB implements I_FX_View {

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、項目を初期化する。</p>
	 * @author officine-hide.com
	 * @since 1.20 2020/11/10
	 * @param env 環境情報
	 */
	public X_FX_View(FD_EnvData env) {
		//項目初期化
		initializeItemList(env, TABLE_ID);
	}

	/**
	 * 情報登録<br>
	 * @author officine-hide.com
	 * @since 1.20 2020/11/10
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

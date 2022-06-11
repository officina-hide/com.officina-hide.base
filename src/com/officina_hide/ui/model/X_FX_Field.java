package com.officina_hide.ui.model;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * 画面項目I/Oクラス[Screen item I/O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/06/09 Ver. 1.00
 */
public class X_FX_Field extends FD_DB implements I_FX_Field {

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/06/09 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param entry 登録情報
	 */
	public X_FX_Field(FD_EnvData env, FD_Collections entry) {
		createColumnList(env, Table_Name);
		columnCollection.setData(entry);
	}

	/**
	 * 情報登録[Data entry]<br>
	 * @author officina-hide.net
	 * @since 2022/06/09 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

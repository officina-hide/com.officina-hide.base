package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;

/**
 * 参照情報I/Oクラス[Reference information I/O class]<br>
 * @author officina-hide.net
 * @since 2022/05/12 Ver. 1.50
 */
public class X_FD_Reference extends FD_DB implements I_FD_Reference {

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/05/13 Ver. 1.50
	 * @param env
	 * @param entry
	 */
	public X_FD_Reference(FD_EnvData env, FD_Collections entry) {
		createColumnList();
	}

	/**
	 * 参照情報テーブル項目リスト生成[Reference information table Item list generation]<br>
	 * @author officina-hide.net
	 * @since 2022/05/13 Ver. 1.50
	 */
	private void createColumnList() {
		columnCollection.clear();
		
	}

}

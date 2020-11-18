package com.officina_hide.project.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * タスク情報I/Oクラス<br>
 * @author officine-hide.com
 * @version 1.20
 * @since 2020/11/18
 */
public class X_FD_Task extends FD_DB implements I_FD_Task {

	/**
	 * コンストラクター<br>
	 * @author officine-hide.com
	 * @since 1.20 2020/11/18
	 * @param env 環境情報
	 */
	public X_FD_Task(FD_EnvData env) {
		//実体化時に、項目を初期化する。
		initializeItemList(env, TABLE_ID);
	}

}

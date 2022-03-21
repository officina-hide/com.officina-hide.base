package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * 採番情報クラス[Numbering information class]
 * @author officina-hide.com
 * @version 1.50 新規作成
 * @since 2022/03/19 Ver. 1.50
 */
public class FD_Numbering extends FD_DB {

	/**
	 * 採番情報テーブル構築[Numbering information table construction]<br>
	 * 採番情報テーブルは初期構築の為、生成は標準外で行う。<br>
	 * Since the numbering information table is initially constructed, it is generated outside the standard.<br>
	 * @author officina-hide.net
	 * @since 2022/03/19 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		connection(env);
	}

}

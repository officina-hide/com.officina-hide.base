package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * 参照変数情報クラス[Reference variable information class]<br>
 * @author officina-hide.net
 * @version 新規作成
 * @since 2021/12/13 Ver. 1.00
 */
public class FD_Reference_Param extends FD_DB implements I_FD_Reference_Param {

	/**
	 * 参照変数情報テーブル構築[Reference variable information Table Generate]<br>
	 * @author officina-hide.net
	 * @since 2021/12/13 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
	}

}

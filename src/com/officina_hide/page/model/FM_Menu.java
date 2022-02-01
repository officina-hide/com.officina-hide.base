package com.officina_hide.page.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_Table;

/**
 * メニュー情報クラス[Menu information class]<br>
 * HTML用メニュー情報[HTML menu information]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2022/02/01 Ver. 1.00
 */
public class FM_Menu extends FD_DB implements I_FM_Menu {

	/**
	 * テーブル情報構築[Table information construction]
	 * @author officina-hide.net
	 * @since 2022/02/01 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		long tableId = table.add(env, 0, Table_Name, Table_Disp_Name, Table_Comment);
	}

}

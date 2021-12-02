package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_Table;

/**
 * ツールバー情報クラス[Toolbar information class]
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/12/02 Ver. 1.00
 */
public class FX_ToolBar extends FD_DB implements I_FX_ToolBar  {

	/**
	 * ツールバー情報テーブル構築[Toolbar information table construction]<br>
	 * @author officina-hide.net
	 * @since 2021/12/02 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
	}

}

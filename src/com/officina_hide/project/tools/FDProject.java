package com.officina_hide.project.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.tools.FDTable;
import com.officina_hide.project.model.I_FD_Project;

/**
 * プロジェクト情報クラス<br>
 * @author officine-hide.com
 * @version 1.20
 * @since 2020/11/06
 */
public class FDProject extends FD_DB implements I_FD_Project {

	/**
	 * プロジェクト情報テーブル構築<br>
	 * @author officine-hide.com
	 * @since 1.20 2020/11/06
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, TABLE_ID, Table_Name, NAME, COMMENT);
	}

}

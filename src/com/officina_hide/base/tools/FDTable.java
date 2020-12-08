package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.I_FD_Table;

/**
 * テーブル情報クラス[Table information class]<br>
 * @author officina-hide.com
 * @version 1.30
 * @since 2020/12/07
 */
public class FDTable extends FD_DB implements I_FD_Table {

	/**
	 * テーブル生成[table creation]<br>
	 * <p>テーブル情報は導入フェーズとなるので、ダイレクトにSQL文を作成してテーブルを作成する。<br>
	 * Since table information is in the introduction phase, create a table by directly creating an SQL statement.</p>
	 * @param env
	 */
	public void createTable(FD_EnvData env) {
		StringBuffer sqlDrop = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		//既に登録されているテーフル情報を削除する。
		sqlDrop.append("DROP TABLE IF EXISTS ").append(Table_Name);
		DBUpdateExecution(env, sqlDrop.toString());

	}

}

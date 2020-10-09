package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Table;

/**
 * テーブル情報クラス<br>
 * @author officine-hide.com
 *@version 1.00
 *@since 2020/10/08
 */
public class FDTable extends FD_DB implements I_FD_Table {

	/**
	 * テーブル情報テーブル生成<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/08
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		//テーブル生成
		createDBTable(env);
	}

	/**
	 * テーブル情報テーブル構築<br>
	 * @author officine-hide.com
	 * @since 2020/10/08
	 * @param env 環境情報
	 */
	private void createDBTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されているテーフル情報を削除する。
		sql.append("DROP TABLE IF EXISTS ").append(Table_Name);
		DBexecute(env, sql.toString());
	}

	/**
	 * DB更新<br>
	 * @author officine-hide.com
	 * @since 2020/10/08
	 * @param env 環境情報
	 * @param sql SQL文
	 */
	private void DBexecute(FD_EnvData env, String sql) {
		connection(env);
	}

}

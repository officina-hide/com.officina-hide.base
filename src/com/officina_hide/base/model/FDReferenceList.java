package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * リファレンスリスト情報クラス[Reference list information class.]<br>
 * @author officine-hide.com
 * @version 1.30
 * @since 2021/01/14
 */
public class FDReferenceList extends FD_DB implements I_FD_ReferenceList {
	/** ログ情報 */
	private FDLog log = new FDLog();

	/**
	 * リファレンスリスト情報テーブル構築[Reference list information table construction.]<br>
	 * @author officine-hide.com
	 * @since 1.30 2021/01/14
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築開始");

		StringBuffer sqlDrop = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		//既に登録されているテーフル情報を削除する。
		sqlDrop.append("DROP TABLE IF EXISTS ").append(Table_Name);
		DBUpdateExecution(env, sqlDrop.toString());
		
	}

}

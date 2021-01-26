package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_DB_Utility;
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

		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, TABLE_ID, Table_Name, NAME, COMMENT);
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_ReferenceList_ID, NAME_FD_ReferenceList_ID, COMMENT_FD_ReferenceList_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 10, "Y", "Y");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Reference_ID, NAME_FD_Reference_ID, COMMENT_FD_Process_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 20, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Sequence, NAME_FD_Sequence, COMMENT_FD_Sequence
				, COLUMNTYPE_ID_FD_Number, null, 0, 30, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Code, NAME_FD_Code, COMMENT_FD_Code
				, COLUMNTYPE_ID_FD_Text, null, 100, 40, "N", "N");
		column.addBaseTableColumnData(env, TABLE_ID);
		//テーブル生成
		FD_DB_Utility dbUtil = new FD_DB_Utility();
		dbUtil.createDBTable(env, TABLE_ID);
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.addData(env, TABLE_ID, TABLE_ID, 0, 1000001);
		
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築完了");
	}

	/**
	 * 情報登録[Entry of Information.]
	 * @author officina-hide.com
	 * @since 1.30 2021/01/25
	 * @param env 環境情報
	 * @param referenceListId リファレンスリスト情報ID
	 * @param referenceId リファレンス情報ID
	 * @param listSeq リファレンス並び順
	 * @param code リストコード
	 */
	public void addData(FD_EnvData env, int referenceListId, int referenceId, int listSeq, String code) {
		X_FD_ReferenceList refList = new X_FD_ReferenceList(env);
		refList.setValueByName(env, COLUMNNAME_FD_Reference_ID, referenceId);
		refList.setValueByName(env, COLUMNNAME_FD_Sequence, listSeq);
		refList.setValueByName(env, COLUMNNAME_FD_Code, code);
		refList.save(env);
	}

}

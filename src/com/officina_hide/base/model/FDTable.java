package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;

/**
 * テーブル情報クラス[Table information class]<br>
 * @author officina-hide.com
 * @version 1.30
 * @since 2020/12/07
 */
public class FDTable extends FD_DB implements I_FD_Table {
	//ログ情報クラス
	private FDLog log = new FDLog();

	/**
	 * テーブル生成[table creation]<br>
	 * <p>テーブル情報は導入フェーズとなるので、ダイレクトにSQL文を作成してテーブルを作成する。<br>
	 * Since table information is in the introduction phase, create a table by directly creating an SQL statement.</p>
	 * @param env
	 */
	public void createTable(FD_EnvData env) {
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築開始");
		
		StringBuffer sqlDrop = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		//既に登録されているテーフル情報を削除する。
		sqlDrop.append("DROP TABLE IF EXISTS ").append(Table_Name);
		DBUpdateExecution(env, sqlDrop.toString());
	
		//テーブル情報の再構築
		sql.append("CREATE TABLE IF NOT EXISTS ").append(Table_Name).append(" (");
		sql.append(COLUMNNAME_FD_Table_ID).append(" INT UNSIGNED NOT NULL PRIMARY KEY COMMENT ")
			.append(FD_SQ).append(NAME_FD_Table_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Table_Name).append(" Varchar(100) COMMENT ")
			.append(FD_SQ).append(NAME_Table_Name).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_Name).append(" Varchar(100) COMMENT ")
			.append(FD_SQ).append(NAME_FD_Name).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_Comment).append(" Text COMMENT ")
			.append(FD_SQ).append(NAME_FD_Comment).append(FD_SQ).append(",");
		
		FD_DB_Utility dutil = new FD_DB_Utility();
		sql.append(dutil.getBaseSQLStrings(NAME));		
		DBUpdateExecution(env, sql.toString());
		
		//ログ情報の構築をログ情報に登録する。
		log.addLog(env, I_FD_Log.LOGTYPE_Table_Drop_ID, changeEscape(sqlDrop.toString()));
		log.addLog(env, I_FD_Log.LOGTYPE_Table_Create_ID, changeEscape(sql.toString()));
		
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築完了");
	}

	/**
	 * テーブル情報登録<br>
	 * @author officina-hide.com
	 * @since 1.00 2020/10/10
	 * @param env 環境情報
	 * @param tableId テーブル情報ID
	 * @param tableName テーブル名
	 * @param name テーブル論理名
	 * @param comment テーブル説明
	 * @return テーブル情報ID
	 */
	public int addData(FD_EnvData env, int tableId, String tableName, String name, String comment) {
		X_FD_Table table = new X_FD_Table(env);
		table.setValueByName(env, COLUMNNAME_FD_Table_ID, tableId);
		table.setValueByName(env, COLUMNNAME_Table_Name, tableName);
		table.setValueByName(env, COLUMNNAME_FD_Name, name);
		table.setValueByName(env, COLUMNNAME_FD_Comment, comment);
		table.save(env);

		return table.getintOfValue(COLUMNNAME_FD_Table_ID);
	}
	
	/**
	 * テーブル項目情報登録
	 * @author officine-hide.com
	 * @since 1.00 2020/10/22
	 * @param env 環境情報
	 */
	public void addTableColumn(FD_EnvData env) {
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"のテーブル項目情報登録開始");

		FDTableColumn column = new FDTableColumn();
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Table_ID, NAME_FD_Table_ID, COMMENT_FD_Table_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 10, "Y", "Y");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_Table_Name, NAME_Table_Name, COMMENT_Table_Name
				, COLUMNTYPE_ID_FD_Text, null, 100, 20, "Y", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Name, NAME_FD_Name, COMMENT_FD_Name
				, COLUMNTYPE_ID_FD_Text, null, 100, 30, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Comment, NAME_FD_Comment, COMMENT_FD_Comment
				, COLUMNTYPE_ID_FD_Field_Text, null, 0, 40, "N", "N");

		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Process_ID, NAME_FD_Process_ID, COMMENT_FD_Process_ID
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 900, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_CREATE, NAME_FD_CREATE, COMMENT_FD_CREATE
				, COLUMNTYPE_ID_FD_Date, null, 0, 910, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_CREATED, NAME_FD_CREATED, COMMENT_FD_CREATED
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 920, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_UPDATE, NAME_FD_UPDATE, COMMENT_FD_UPDATE
				, COLUMNTYPE_ID_FD_Date, null, 0, 930, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_UPDATED, NAME_FD_UPDATED, COMMENT_FD_UPDATED
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 940, "N", "N");
		
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"のテーブル項目情報登録終了");

	}

}

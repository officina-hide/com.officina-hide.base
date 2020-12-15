package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;

/**
 * テーブル項目情報クラス[Table item information class]<br>
 * @author officina-hide.com
 * @version 1.30
 * @since 2020/12/14
 */
public class FDTableColumn extends FD_DB implements I_FD_TableColumn {

	/**
	 * テーブル項目情報テーブル構築[Table item information table construction]<br>
	 * @author officina-hide.com
	 * @since 1.30 2020/12/14
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		FDLog log = new FDLog();
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築開始");
		
		StringBuffer sqlDrop = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		
		//既に登録されているテーフル情報を削除する。
		sqlDrop.append("DROP TABLE IF EXISTS ").append(Table_Name);
		DBUpdateExecution(env, sqlDrop.toString());

		//テーブル情報の再構築
		sql.append("CREATE TABLE IF NOT EXISTS ").append(Table_Name).append(" (");
		sql.append(COLUMNNAME_FD_TableColumn_ID).append(" INT UNSIGNED NOT NULL PRIMARY KEY COMMENT ")
			.append(FD_SQ).append(NAME_FD_TableColumn_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_Table_ID).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_FD_Table_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_TableColumn_Name).append(" VARCHAR(100) COMMENT ")
			.append(FD_SQ).append(NAME_TableColumn_Name).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_Name).append(" VARCHAR(100) ")
			.append(" COMMENT ").append(FD_SQ).append(NAME_FD_Name).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_Comment).append(" TEXT ")
			.append(" COMMENT ").append(FD_SQ).append(NAME_FD_Comment).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_TableColumn_Type_ID).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_TableColumn_Type_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_TableColumn_Size).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_TableColumn_Size).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Defualt_Data).append(" VARCHAR(100) COMMENT ")
			.append(FD_SQ).append(NAME_Defualt_Data).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Column_Sort_Order).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_Column_Sort_Order).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Is_Null).append(" ENUM (")
			.append(FD_SQ).append("Y").append(FD_SQ).append(",")
			.append(FD_SQ).append("N").append(FD_SQ).append(")  ")
			.append("COMMENT ").append(FD_SQ).append(NAME_Is_Null).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Is_Primary).append(" ENUM(")
			.append(FD_SQ).append("Y").append(FD_SQ).append(",")
			.append(FD_SQ).append("N").append(FD_SQ).append(")  ")
			.append("COMMENT ").append(FD_SQ).append(NAME_Is_Primary).append(FD_SQ).append(",");
		
		FD_DB_Utility dutil = new FD_DB_Utility();
		sql.append(dutil.getBaseSQLStrings(NAME));		
		DBUpdateExecution(env, sql.toString());
		
		//ログ情報の構築をログ情報に登録する。
		log.addLog(env, I_FD_Log.LOGTYPE_Table_Drop_ID, changeEscape(sqlDrop.toString()));
		log.addLog(env, I_FD_Log.LOGTYPE_Table_Create_ID, changeEscape(sql.toString()));

		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築完了");
	}

}

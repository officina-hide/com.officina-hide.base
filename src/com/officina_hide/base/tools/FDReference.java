package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_Referecne;
import com.officina_hide.base.model.X_FD_Reference;

/**
 * リファレンス情報クラス<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/10/15
 */
public class FDReference extends FD_DB implements I_FD_Referecne {

	/**
	 * リファレンス情報テーブル構築
	 * @author officine-hide.com
	 * @since 1.00 2020/10/15
	 * @param env 環境情報
	 */
	public void createDBTable(FD_EnvData env) {
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築開始");

		StringBuffer sqlDrop = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		//既に登録されているテーフル情報を削除する。
		sqlDrop.append("DROP TABLE IF EXISTS ").append(Table_Name);
		DBexecute(env, sqlDrop.toString());
		//テーブル情報の再構築
		sql.append("CREATE TABLE IF NOT EXISTS ").append(Table_Name).append(" (");

		sql.append(COLUMNNAME_FD_Reference_ID).append(" INT UNSIGNED NOT NULL PRIMARY KEY COMMENT ")
			.append(FD_SQ).append(NAME_FD_Reference_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Reference_Name).append(" Varchar(100) COMMENT ")
			.append(FD_SQ).append(NAME_Reference_Name).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_Name).append(" Varchar(100) COMMENT ")
			.append(FD_SQ).append(NAME_FD_Name).append(FD_SQ).append(",");
		
		sql.append(COLUMNNAME_FD_CREATE).append(" DATETIME COMMENT ")
			.append(FD_SQ).append(NAME_FD_CREATE).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_CREATED).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_FD_CREATED).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATE).append(" DATETIME COMMENT ")
			.append(FD_SQ).append(NAME_FD_UPDATE).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATED).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_FD_UPDATED).append(FD_SQ).append(" ");
		
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=").append(FD_SQ).append(NAME).append(FD_SQ);
		
		DBexecute(env, sql.toString());
		
		addLog(env, I_FD_Log.LOGTYPE_Table_Drop_ID, changeEscape(sqlDrop.toString()));
		addLog(env, I_FD_Log.LOGTYPE_Table_Create_ID, changeEscape(sql.toString()));

		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, TABLE_ID, Table_Name, NAME, COMMENT);
		
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.addData(env, TABLE_ID, TABLE_ID, 0, 1000001);
		
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築開始");
	}

	/**
	 * テーブル項目情報用リファレンス情報登録<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/15
	 * @param env 環境情報
	 */
	public void addColumnTypeReference(FD_EnvData env) {
		addData(env, COLUMNTYPE_ID_FD_Information_ID, COLUMNTYPE_FD_Information_ID, COLUMNTYPENAME_FD_Information_ID);
		addData(env, COLUMNTYPE_ID_FD_Text, COLUMNTYPE_FD_Text, COLUMNTYPENAME_FD_Field_Text);
		addData(env, COLUMNTYPE_ID_FD_Field_Text, COLUMNTYPE_FD_Field_Text, COLUMNTYPENAME_FD_Field_Text);
		addData(env, COLUMNTYPE_ID_FD_Date, COLUMNTYPE_FD_Date, COLUMNTYPENAME_FD_Date);
		addData(env, COLUMNTYPE_ID_FD_Number, COLUMNTYPE_FD_Number, COLUMNTYPE_FD_Number);
		addData(env, COLUMNTYPE_ID_FD_YesNo, COLUMNTYPE_FD_YesNo, COLUMNTYPENAME_FD_YesNo);
		addData(env, COLUMNTYPE_ID_FD_List, COLUMNTYPE_FD_List, COLUMNTYPENAME_FD_List);
	}

	/**
	 * リファレンス情報登録<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/15
	 * @param env 環境情報
	 * @param referenceId リファレンスId
	 * @param referenceName リファレンス名
	 * @param name リファレンス表示名
	 */
	public void addData(FD_EnvData env, int referenceId, String referenceName, String name) {
		X_FD_Reference ref = new X_FD_Reference(env);
		ref.setValueByName(env, COLUMNNAME_FD_Reference_ID, referenceId);
		ref.setValueByName(env, COLUMNNAME_Reference_Name, referenceName);
		ref.setValueByName(env, COLUMNNAME_FD_Name, name);
		ref.save(env);
	}

	/**
	 * リファレンス情報の項目についてテーブル項目情報に登録する。<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/26
	 * @param env 環境情報
	 */
	public void addTableColumn(FD_EnvData env) {
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"のテーブル項目情報登録開始");
		
		FDTableColumn column = new FDTableColumn();
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Reference_ID, NAME_FD_Reference_ID, COMMENT_FD_Reference_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 10, "Y", "Y");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_Reference_Name, NAME_Reference_Name, COMMENT_Reference_Name
				, COLUMNTYPE_ID_FD_Text, null, 100, 20, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Name, NAME_FD_Name, COMMENT_FD_Name
				, COLUMNTYPE_ID_FD_Text, null, 100, 30, "N", "N");
		
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_CREATE, NAME_FD_CREATE, COMMENT_FD_CREATE
				, COLUMNTYPE_ID_FD_Date, null, 0, 900, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_CREATED, NAME_FD_CREATED, COMMENT_FD_CREATED
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 910, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_UPDATE, NAME_FD_UPDATE, COMMENT_FD_UPDATE
				, COLUMNTYPE_ID_FD_Date, null, 0, 920, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_UPDATED, NAME_FD_UPDATED, COMMENT_FD_UPDATED
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 930, "N", "N");

		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"のテーブル項目情報登録終了");
	}

}

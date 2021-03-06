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
	/** ログ情報 */
	private FDLog log = new FDLog();

	/**
	 * テーブル項目情報テーブル構築[Table item information table construction]<br>
	 * @author officina-hide.com
	 * @since 1.30 2020/12/14
	 * @param env 環境情報
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
		sql.append(COLUMNNAME_FD_Reference_ID).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_FD_Reference_ID).append(FD_SQ).append(",");
		
		FD_DB_Utility dutil = new FD_DB_Utility();
		sql.append(dutil.getBaseSQLStrings(NAME));		
		DBUpdateExecution(env, sql.toString());
		
		//ログ情報の構築をログ情報に登録する。
		log.addLog(env, I_FD_Log.LOGTYPE_Table_Drop_ID, changeEscape(sqlDrop.toString()));
		log.addLog(env, I_FD_Log.LOGTYPE_Table_Create_ID, changeEscape(sql.toString()));

		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築完了");
	}

	/**
	 * テーブル項目関連リファレンス情報登録[Registration of reference information related to table items]<br>
	 * @author officine-hide.com
	 * @since 1.30 2020/12/15
	 * @param env 環境情報
	 */
	public void addRefData(FD_EnvData env) {	
		FDReference ref = new FDReference();
		ref.addData(env, COLUMNTYPE_ID_FD_Information_ID, COLUMNTYPE_FD_Information_ID, COLUMNTYPENAME_FD_Information_ID);
		ref.addData(env, COLUMNTYPE_ID_FD_Text, COLUMNTYPE_FD_Text, COLUMNTYPENAME_FD_Field_Text);
		ref.addData(env, COLUMNTYPE_ID_FD_Field_Text, COLUMNTYPE_FD_Field_Text, COLUMNTYPENAME_FD_Field_Text);
		ref.addData(env, COLUMNTYPE_ID_FD_Date, COLUMNTYPE_FD_Date, COLUMNTYPENAME_FD_Date);
		ref.addData(env, COLUMNTYPE_ID_FD_Number, COLUMNTYPE_FD_Number, COLUMNTYPE_FD_Number);
		ref.addData(env, COLUMNTYPE_ID_FD_YesNo, COLUMNTYPE_FD_YesNo, COLUMNTYPENAME_FD_YesNo);
		ref.addData(env, COLUMNTYPE_ID_FD_List, COLUMNTYPE_FD_List, COLUMNTYPENAME_FD_List);
		ref.addData(env, COLUMNTYPE_ID_FD_Numbering, COLUMNTYPE_FD_Numbering, COLUMNTYPENAME_FD_Numbering);
	}

	/**
	 * テーブル項目情報登録<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/22
	 * @param env 環境情報
	 * @param columnId テーブル項目情報ID
	 * @param tableId テーブル情報ID
	 * @param columnName テーブル項目名
	 * @param name テーブル項目表示名
	 * @param comment テーブル項目説明
	 * @param typeId テーブル項目属性ID
	 * @param defaultData 初期値
	 * @param size 桁数
	 * @param sortOrder 並び順
	 * @param idNull notNull判定
	 * @param isPrimary PrimaryKey判定
	 * @return 
	 */
	public int addData(FD_EnvData env, int columnId, int tableId, String columnName, String name,
			String comment, int typeId, String defaultData, int size, int sortOrder, String isNull, String isPrimary) {
		X_FD_TableColumn column = new X_FD_TableColumn(env);
		column.setValueByName(env, COLUMNNAME_FD_TableColumn_ID, columnId);
		column.setValueByName(env, COLUMNNAME_FD_Table_ID, tableId);
		column.setValueByName(env, COLUMNNAME_TableColumn_Name, columnName);
		column.setValueByName(env, COLUMNNAME_FD_Name, name);
		column.setValueByName(env, COLUMNNAME_FD_Comment, comment);
		column.setValueByName(env, COLUMNNAME_TableColumn_Type_ID, typeId);
		column.setValueByName(env, COLUMNNAME_Defualt_Data, defaultData);
		column.setValueByName(env, COLUMNNAME_TableColumn_Size, size);
		column.setValueByName(env, COLUMNNAME_Column_Sort_Order, sortOrder);
		column.setValueByName(env, COLUMNNAME_Is_Null, isNull);
		column.setValueByName(env, COLUMNNAME_Is_Primary, isPrimary);
		column.save(env);
		return column.getintOfValue(COLUMNNAME_FD_TableColumn_ID);
	}

	/**
	 * テーブル項目のテーブル項目情報登録<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/24
	 * @param env 環境情報
	 */
	public void addTableColumn(FD_EnvData env) {
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"のテーブル項目情報登録開始");

		addData(env, 0, TABLE_ID, COLUMNNAME_FD_TableColumn_ID, NAME_FD_TableColumn_ID, COMMENT_FD_TableColumn_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 10, "Y", "Y");
		addData(env, 0, TABLE_ID, COLUMNNAME_FD_Table_ID, NAME_FD_Table_ID, COMMENT_FD_Table_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 20, "Y", "N");
		addData(env, 0, TABLE_ID, COLUMNNAME_TableColumn_Name, NAME_TableColumn_Name, COMMENT_TableColumn_Name
				, COLUMNTYPE_ID_FD_Text, null, 100, 30, "N", "N");
		addData(env, 0, TABLE_ID, COLUMNNAME_FD_Name, NAME_FD_Name, COMMENT_FD_Name
				, COLUMNTYPE_ID_FD_Text, null, 100, 40, "N", "N");
		addData(env, 0, TABLE_ID, COLUMNNAME_FD_Comment, NAME_FD_Comment, COMMENT_FD_Comment
				, COLUMNTYPE_ID_FD_Field_Text, null, 0, 50, "N", "N");
		addData(env, 0, TABLE_ID, COLUMNNAME_TableColumn_Type_ID, NAME_TableColumn_Type_ID, COMMENT_TableColumn_Type_ID
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 60, "N", "N");
		addData(env, 0, TABLE_ID, COLUMNNAME_Defualt_Data, NAME_Defualt_Data, COMMENT_Defualt_Data
				, COLUMNTYPE_ID_FD_Text, null, 100, 70, "N", "N");
		addData(env, 0, TABLE_ID, COLUMNNAME_TableColumn_Size, NAME_TableColumn_Size, COMMENT_TableColumn_Size
				, COLUMNTYPE_ID_FD_Number, "0", 0, 80, "N", "N");
		addData(env, 0, TABLE_ID, COLUMNNAME_Column_Sort_Order, NAME_Column_Sort_Order, COMMENT_Column_Sort_Order
				, COLUMNTYPE_ID_FD_Number, "0", 0, 90, "N", "N");
		addData(env, 0, TABLE_ID, COLUMNNAME_Is_Null, NAME_Is_Null, COMMENT_Is_Null
				, COLUMNTYPE_ID_FD_YesNo, "N", 0, 100, "N", "N");
		addData(env, 0, TABLE_ID, COLUMNNAME_Is_Primary, NAME_Is_Primary, COMMENT_Is_Primary
				, COLUMNTYPE_ID_FD_YesNo, "N", 0, 110, "N", "N");
		addData(env, 0, TABLE_ID, COLUMNNAME_FD_Reference_ID, NAME_FD_Reference_ID, COMMENT_FD_Reference_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 120, "N", "N");
		
		addBaseTableColumnData(env, TABLE_ID);
		
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"のテーブル項目情報登録開始");

	}

	/**
	 * 共通テーブル項目情報登録[Register the information of common table items.]<br>
	 * @author officina-hide.com
	 * @since 1.30 2021/01/04
	 * @param env 環境情報
	 * @param tableId テーブル情報ID[Table information ID]
	 */
	public void addBaseTableColumnData(FD_EnvData env, int tableId) {
		addData(env, 0, tableId, COLUMNNAME_FD_Process_ID, NAME_FD_Process_ID, COMMENT_FD_Process_ID
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 900, "N", "N");
		addData(env, 0, tableId, COLUMNNAME_FD_CREATE, NAME_FD_CREATE, COMMENT_FD_CREATE
				, COLUMNTYPE_ID_FD_Date, null, 0, 910, "N", "N");
		addData(env, 0, tableId, COLUMNNAME_FD_CREATED, NAME_FD_CREATED, COMMENT_FD_CREATED
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 920, "N", "N");
		addData(env, 0, tableId, COLUMNNAME_FD_UPDATE, NAME_FD_UPDATE, COMMENT_FD_UPDATE
				, COLUMNTYPE_ID_FD_Date, null, 0, 930, "N", "N");
		addData(env, 0, tableId, COLUMNNAME_FD_UPDATED, NAME_FD_UPDATED, COMMENT_FD_UPDATED
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 940, "N", "N");
	}

	/**
	 * 項目名による情報登録[Information registration by item name.]
	 * @author officina-hide.com
	 * @since 1.30 2021/01/25
	 * @param env 環境情報
	 * @param referenceId リファレンス情報ID
	 * @param columnName 項目名
	 * @param data 項目情報
	 */
	public void addDataByName(FD_EnvData env, int tableColumnID, String columnName, Object data) {
		X_FD_TableColumn ref = new X_FD_TableColumn(env, tableColumnID);
		ref.setValueByName(env, columnName, data);
		ref.save(env);
	}

}

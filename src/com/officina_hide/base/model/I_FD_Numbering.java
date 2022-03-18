package com.officina_hide.base.model;

/**
 * 採番情報インターフェースクラス[Numbering information interface class]<br>
 * @author officina-hide.net
 * @version 1.00
 * @since 2021/09/15
 */
public interface I_FD_Numbering extends I_FD_DB {

	/** テーブル識別名 */
	public static final String Table_Name = "FD_Numbering";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "採番情報";
	/** テーブル説明 */
	public static final String Table_Comment = "パッケージで使用する各種番号の採番を管理するテーブル";
	public static final String Table_Comment_Eng = "A table that manages the numbering of various numbers used in the package.";
	/** テーブル情報ID */
	public static final int Table_ID = 101;
	/** システム初期番号 */
	public static final String Initial_Number = "100001";
	
	/** 項目 : 採番情報ID */
	public static final String COLUMNNAME_FD_Numbering_ID = Table_Name + "_ID";
	public static final String NAME_FD_Numbering_ID = Table_Disp_Name+"ID";
	/** 項目 : 採番情報ID */
	public static final String COLUMNNAME_FD_Table_ID = I_FD_Table.COLUMNNAME_FD_Table_ID;
	public static final String NAME_FD_Table_ID = I_FD_Table.NAME_FD_Table_ID;
	public static final String COMMENT_FD_Table_ID = I_FD_Table.COMMENT_FD_Table_ID;
	/** 項目 : 採番初期値 */
	public static final String COLUMNNAME_FD_InitialNumber = "FD_InitialNumber";
	public static final String NAME_FD_InitialNumber = "採番初期値";
	/** 項目 : 採番現在値 */
	public static final String COLUMNNAME_FD_CurrentNumber = "FD_CurrentNumber";
	public static final String NAME_FD_CurrentNumber = "採番現在値";
	
	/** 採番情報登録用 */
	public static final String SQL_NUMBER_DATA = 
			   COLUMNNAME_FD_Numbering_ID + ":0,"
			+ COLUMNNAME_FD_Table_ID + ":101,"
			+ COLUMNNAME_FD_InitialNumber + ":" + Initial_Number + ","
			+ COLUMNNAME_FD_CurrentNumber + ":0,";
	
	/** テーブル削除用SQL */
	public static final String Table_Drop_SQL =
			"DROP TABLE IF EXISTS " + Table_Name;
	/** テーブル生成用SQL */
	public static final String Table_Create_SQL = 
			"CREATE TABLE IF NOT EXISTS " + Table_Name
			+" ("
				+ COLUMNNAME_FD_Numbering_ID + ID_KEY_TYPE 
					+ COMMENT + FD_SQ + NAME_FD_Numbering_ID + FD_SQ + ","
				+ COLUMNNAME_FD_Table_ID + ID_TYPE 
					+ COMMENT + FD_SQ + NAME_FD_Table_ID + FD_SQ + ","
				+ COLUMNNAME_FD_InitialNumber + UNSIGNED_BIGINT
					+ COMMENT + FD_SQ + NAME_FD_InitialNumber + FD_SQ + ","
				+ COLUMNNAME_FD_CurrentNumber + UNSIGNED_BIGINT
					+ COMMENT + FD_SQ + NAME_FD_CurrentNumber + FD_SQ + ","
				+ COMMON_ITEM_CREATE_SQL
			+") "
			+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;
}

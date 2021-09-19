package com.officina_hide.base.model;

/**
 * テーブル情報インターフェースクラス[Table Information Interfaces class]<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2021/04/08
 */
public interface I_FD_Table extends I_FD_DB {

	/** テーブル名 */
	public static final String Table_Name = "FD_Table";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "テーブル情報";
	/** テーブルID */
	public static final int Table_ID = 103;

	/** 項目 : テーブル情報ID */
	public static final String COLUMNNAME_FD_Table_ID = Table_Name+"_ID";
	public static final String NAME_FD_Table_ID = Table_Disp_Name+"ID";
	public static final String COMMENT_FD_Table_ID = "テーブルを識別する為の情報ID";
	/** 項目 : テーブル識別名 */
	public static final String COLUMNNAME_FD_Table_Name = Table_Name + "_Name";
	public static final String NAME_FD_Table_Name = "テーブル識別名";
	public static final String COMMENT_FD_Table_Name = "テーブルを識別する為の名称";
		
	/** テーブル削除用SQL */
	public static final String Table_Drop_SQL =
			"DROP TABLE IF EXISTS " + Table_Name;
	/** テーブル生成用SQL */
	public static final String Table_Create_SQL = 
			"CREATE TABLE IF NOT EXISTS " + Table_Name
			+" ("
				+ COLUMNNAME_FD_Table_ID + ID_KEY_TYPE
					+ COMMENT + FD_SQ + NAME_FD_Table_ID + FD_SQ + ","
				+ COLUMNNAME_FD_Table_Name + VARCHAR_100
					+ COMMENT + FD_SQ + NAME_FD_Table_Name + FD_SQ + ","
				+ COLUMNNAME_FD_Name + VARCHAR_100
					+ COMMENT + FD_SQ + NAME_FD_Name + FD_SQ + ","
				+ COLUMNNAME_FD_Description + TEXT
					+ COMMENT + FD_SQ + NAME_FD_Description + FD_SQ + ","
				+ COMMON_ITEM_CREATE_SQL
			+") "
			+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;
	
 }

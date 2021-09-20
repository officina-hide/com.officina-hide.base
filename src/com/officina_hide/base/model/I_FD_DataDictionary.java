package com.officina_hide.base.model;

/**
 * 辞書情報インターフェースクラス[Dictionary information interface class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/17
 */
public interface I_FD_DataDictionary extends I_FD_DB {

	/** テーブル識別名 */
	public static final String Table_Name = "FD_DataDictionary";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "辞書情報";
	/** テーブル説明 */
	public static final String Table_Comment = "パッケージで使用する項目の整合性を維持するために、項目を一元管理する。";
	public static final String Table_Comment_Eng = "Centrally manage items to ensure the integrity of the items used in the package..";
	/** テーブル情報ID */
	public static final int Table_ID = 102;

	/** 項目 : 辞書情報ID */
	public static final String COLUMNNAME_FD_DataDictionary_ID = Table_Name + "_ID";
	public static final String NAME_FD_DataDictionary_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FD_DataDictionary_ID = Table_Disp_Name+"を識別する為の情報ID";
	/** 項目 : 辞書項目識別名 */
	public static final String COLUMNNAME_FD_DataDictionary_Name = "FD_DataDictionary_Name";
	public static final String NAME_FD_DataDictionary_Name = "辞書項目識別名";
	public static final String COMMENT_FD_DataDictionary_Name = "各辞書情報を識別する為のユニークな名称";
	
	/** テーブル削除用SQL */
	public static final String Table_Drop_SQL =
			"DROP TABLE IF EXISTS " + Table_Name;
	/** テーブル生成用SQL */
	public static final String Table_Create_SQL = 
		"CREATE TABLE IF NOT EXISTS " + Table_Name
			+" ("
			+ COLUMNNAME_FD_DataDictionary_ID + ID_KEY_TYPE
				+ COMMENT + FD_SQ + NAME_FD_DataDictionary_ID + FD_SQ + ","
			+ COLUMNNAME_FD_DataDictionary_Name + VARCHAR_100
				+ COMMENT + FD_SQ + NAME_FD_DataDictionary_Name + FD_SQ + ","
			+ COLUMNNAME_FD_Name + VARCHAR_100
				+ COMMENT + FD_SQ + NAME_FD_Name + FD_SQ + ","
			+ COLUMNNAME_FD_Description + TEXT
				+ COMMENT + FD_SQ + NAME_FD_Description + FD_SQ + ","
			+ COMMON_ITEM_CREATE_SQL
			+") "
			+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;
}

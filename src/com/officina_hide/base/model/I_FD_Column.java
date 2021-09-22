package com.officina_hide.base.model;

/**
 * テーブル項目インターフェースクラス[Table item interface class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/02
 */
public interface I_FD_Column extends I_FD_DB {

	/** テーブル名 */
	public final static String Table_Name = "FD_Column";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "テーブル項目情報";
	/** テーブル情報ID */
	public final static int Table_ID = 106;
	
	/** 項目 : テーブル項目情報ID */
	public final static String COLUMNNAME_FD_Column_ID = Table_Name + "_ID";
	public final static String NAME_FD_Column_ID = Table_Disp_Name + "ID";
	public final static String COMMENT_FD_Column_ID = "テーブル項目を識別する為の情報ID";
	/** 項目 : テーブル情報ID */
	public static final String COLUMNNAME_FD_Table_ID = I_FD_Table.COLUMNNAME_FD_Table_ID;
	public static final String NAME_FD_Table_ID = I_FD_Table.NAME_FD_Table_ID;
	public static final String COMMENT_FD_Table_ID = I_FD_Table.COMMENT_FD_Table_ID;
	/** 項目 : 辞書情報ID */
	public static final String COLUMNNAME_FD_DataDictionary_ID = I_FD_DataDictionary.COLUMNNAME_FD_DataDictionary_ID;
	public static final String NAME_FD_DataDictionary_ID = I_FD_DataDictionary.NAME_FD_DataDictionary_ID;
	public static final String COMMENT_FD_DataDictionary_ID = I_FD_DataDictionary.COMMENT_FD_DataDictionary_ID;
	/** 項目 : 属性項目情報ID */
	
	
	/** テーブル項目識別名 */
	public final static String COLUMNNAME_FD_Column_Name = "FD_Column_Name";
	/** テーブル項目属性 */
	public final static String COLUMNNAME_FD_Type_ID = "FD_Type_ID";
	
	/** テーブル削除用SQL */
	public static final String Table_Drop_SQL =
			"DROP TABLE IF EXISTS " + Table_Name;
	/** テーブル生成用SQL */
	public static final String Table_Create_SQL = 
			"CREATE TABLE IF NOT EXISTS " + Table_Name
			+" ("
			+ COLUMNNAME_FD_Column_ID + ID_KEY_TYPE
				+ COMMENT + FD_SQ + NAME_FD_Column_ID + FD_SQ + ","
			+ COLUMNNAME_FD_Table_ID + ID_TYPE
				+ COMMENT + FD_SQ + NAME_FD_Table_ID + FD_SQ + ","
			+ COLUMNNAME_FD_DataDictionary_ID + ID_TYPE
				+ COMMENT + FD_SQ + NAME_FD_DataDictionary_ID + FD_SQ + ","
			+ COMMON_ITEM_CREATE_SQL
		+") "
		+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;
}

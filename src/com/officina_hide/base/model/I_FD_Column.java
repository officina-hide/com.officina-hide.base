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
	/** テーブル説明 */
	public static final String Table_Comment = "パッケージで使用するテーブルの項目情報を管理する。";
	public static final String Table_Comment_Eng = "Manage the item information of the table used in the package.";
	/** テーブル情報ID */
	public final static int Table_ID = 104;
	
	/** 項目 : テーブル項目情報ID */
	public final static String COLUMNNAME_FD_Column_ID = Table_Name + "_ID";
	public final static String NAME_FD_Column_ID = Table_Disp_Name + "ID";
	public final static String COMMENT_FD_Column_ID = "テーブル項目を識別する為の情報ID";
	/** 項目 : テーブル情報ID */
	public static final String COLUMNNAME_FD_Table_ID = I_FD_Table.COLUMNNAME_FD_Table_ID;
	public static final String NAME_FD_Table_ID = I_FD_Table.NAME_FD_Table_ID;
	/** 項目 : 辞書情報ID */
	public static final String COLUMNNAME_FD_DataDictionary_ID = I_FD_DataDictionary.COLUMNNAME_FD_DataDictionary_ID;
	public static final String NAME_FD_DataDictionary_ID = I_FD_DataDictionary.NAME_FD_DataDictionary_ID;
	/** 項目 : 属性項目情報ID */
	public static final String COLUMNNAME_FD_TypeItem_ID = I_FD_TypeItem.COLUMNNAME_FD_TypeItem_ID;
	public static final String NAME_FD_TypeItem_ID = I_FD_TypeItem.NAME_FD_TypeItem_ID;
	/** 項目 : 項目桁数 */
	public static final String COLUMNNAME_FD_Column_Size = "FD_Column_Size";
	public static final String NAME_FD_Column_Size = "項目桁数";
	public final static String COMMENT_FD_Column_Size = "テーブル項目の桁数";
	/** 項目 : Null判定 */
	public static final String COLUMNNAME_FD_IS_Null = "FD_IS_Null";
	public static final String NAME_FD_IS_Null = "Null判定";
	public final static String COMMENT_FD_IS_Null = "null値を許可するかどうかを判定するフラグ。\nYの時は許可、Nの時は非許可";
	public static final String FD_IS_NULL_YES = "Y";
	public static final String FD_IS_NULL_NO = "N";
	/** 項目 : プライマリーKey判定 */
	public static final String COLUMNNAME_FD_IS_Key = "FD_IS_Key";
	public static final String NAME_FD_IS_Key = "プライマリーKey判定";
	public final static String COMMENT_FD_IS_Key = "プライマリーKeyかどうかを判定するフラグ。\nYの時は許可、Nの時は非許可";
	public static final String FD_IS_KEY_YES = "Y";
	public static final String FD_IS_KEY_NO = "N";
	/** 項目 : 初期値 */
	public static final String COLUMNNAME_FD_Default = "FD_Default";
	public static final String NAME_FD_Default = "初期値";
	public final static String COMMENT_FD_Default = "入力が無い時にセットする初期値";
	
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
			+ COLUMNNAME_FD_TypeItem_ID + ID_TYPE
				+ COMMENT + FD_SQ + NAME_FD_TypeItem_ID + FD_SQ + ","
			+ COLUMNNAME_FD_Column_Size + UNSIGNED_INT
				+ COMMENT + FD_SQ + NAME_FD_Column_Size + FD_SQ + ","
			+ COLUMNNAME_FD_IS_Null + YES_NO + " default 'Y' "
				+ COMMENT + FD_SQ + NAME_FD_IS_Null + FD_SQ + ","
			+ COLUMNNAME_FD_IS_Key + YES_NO + DEFAULT + FD_SQ + FD_IS_KEY_NO + FD_SQ
				+ COMMENT + FD_SQ + NAME_FD_IS_Key + FD_SQ + ","
			+ COLUMNNAME_FD_Default + VARCHAR_100
				+ COMMENT + FD_SQ + NAME_FD_Default + FD_SQ + ","
			+ COLUMNNAME_FD_Name + VARCHAR_100
				+ COMMENT + FD_SQ + NAME_FD_Name + FD_SQ + ","
			+ COLUMNNAME_FD_Description + TEXT
				+ COMMENT + FD_SQ + NAME_FD_Description + FD_SQ + ","
			+ COMMON_ITEM_CREATE_SQL
		+") "
		+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;
}

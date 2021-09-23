package com.officina_hide.base.model;

/**
 * 属性項目情報インターフェースクラス[Type item information interface class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/22
 */
public interface I_FD_TypeItem extends I_FD_DB {

	/** テーブル名 */
	public final static String Table_Name = "FD_TypeItem";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "属性項目情報";
	/** テーブル説明 */
	public static final String Table_Comment = "パッケージで使用する個々の属性情報を管理する。"
			+ "\n" + "本情報はFD_Type情報を親としてまとめられている。";
	public static final String Table_Comment_Eng = "Manage individual attribute information used in the package."
			+ "\n" + "This information is organized with FD_Type information as the parent.";	
	/** テーブル情報ID */
	public final static int Table_ID = 105;

	/** 項目 : 属性項目情報ID */
	public static final String COLUMNNAME_FD_TypeItem_ID = Table_Name + "_ID";
	public static final String NAME_FD_TypeItem_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FD_TypeItem_ID = "属性項目を識別する為の情報ID";
	/** 項目 : 属性項目名 */
	public static final String COLUMNNAME_FD_TypeItem_Name = "FD_TypeItem_Name";
	public static final String NAME_FD_TypeItem_Name = "属性項目識別名";
	public static final String COMMENT_FD_TypeItem_Name = "属性項目を識別する為の名称";
	/** 項目 : 属性情報ID */
	public static final String COLUMNNAME_FD_Type_ID = I_FD_Type.COLUMNNAME_FD_Type_ID;
	public static final String NAME_FD_Type_ID = I_FD_Type.NAME_FD_Type_ID;
	
	/** テーブル削除用SQL */
	public static final String Table_Drop_SQL =
			"DROP TABLE IF EXISTS " + Table_Name;
	/** テーブル生成用SQL */
	public static final String Table_Create_SQL = 
		"CREATE TABLE IF NOT EXISTS " + Table_Name
		+" ("
		+ COLUMNNAME_FD_TypeItem_ID + ID_KEY_TYPE
			+ COMMENT + FD_SQ + NAME_FD_TypeItem_ID + FD_SQ + ","
		+ COLUMNNAME_FD_TypeItem_Name + VARCHAR_100
			+ COMMENT + FD_SQ + NAME_FD_TypeItem_Name + FD_SQ + ","
		+ COLUMNNAME_FD_Type_ID + ID_TYPE
			+ COMMENT + FD_SQ + NAME_FD_Type_ID + FD_SQ + ","
		+ COLUMNNAME_FD_Name + VARCHAR_100
			+ COMMENT + FD_SQ + NAME_FD_Name + FD_SQ + ","
		+ COLUMNNAME_FD_Description + TEXT
			+ COMMENT + FD_SQ + NAME_FD_Description + FD_SQ + ","
		+ COMMON_ITEM_CREATE_SQL
		+") "
		+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;
}

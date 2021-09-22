package com.officina_hide.base.model;

/**
 * 種別情報インターフェースクラス[Type information interface class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/06
 */
public interface I_FD_Type extends I_FD_DB {

	/** テーブル名 */
	public final static String Table_Name = "FD_Type";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "属性情報";
	/** テーブル説明 */
	public static final String Table_Comment = "パッケージで使用する属性を一元管理するためのヘッダー情報"
			+ "\n" + "属性の詳細はFD_TypeItemで管理する。";
	public static final String Table_Comment_Eng = "Header information for centrally managing the attributes used in the package."
			+ "\n" + "Attribute details are managed by FD_TypeItem.";
	/** テーブル情報ID */
	public final static int Table_ID = 104;
	
	/** 項目 */
	/** 種別情報ID */
	public final static String COLUMNNAME_FD_Type_ID = Table_Name + "_ID";
	public final static String NAME_FD_Type_ID = Table_Disp_Name + "ID";
	public final static String COMMENT_FD_Type_ID = "属性情報を識別する情報ID";
	/** 属性識別名 */
	public final static String COLUMNNAME_FD_Type_Name = "FD_Type_Name";
	public final static String NAME_FD_Type_Name = "属性識別名";
	public final static String COMMENT_FD_Type_Name = "属性を識別する為の名称。";
	
	/** テーブル削除用SQL */
	public static final String Table_Drop_SQL =
			"DROP TABLE IF EXISTS " + Table_Name;
	/** テーブル生成用SQL */
	public static final String Table_Create_SQL = 
			"CREATE TABLE IF NOT EXISTS " + Table_Name
			+" ("
			+ COLUMNNAME_FD_Type_ID + ID_KEY_TYPE 
				+ COMMENT + FD_SQ + NAME_FD_Type_ID + FD_SQ + ","
			+ COLUMNNAME_FD_Type_Name + VARCHAR_100
				+ COMMENT + FD_SQ + NAME_FD_Type_Name + FD_SQ + ","
			+ COLUMNNAME_FD_Name + VARCHAR_100
				+ COMMENT + FD_SQ + NAME_FD_Name + FD_SQ + ","
			+ COLUMNNAME_FD_Description + TEXT
				+ COMMENT + FD_SQ + NAME_FD_Description + FD_SQ + ","
			+ COMMON_ITEM_CREATE_SQL
		+") "
		+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;
}

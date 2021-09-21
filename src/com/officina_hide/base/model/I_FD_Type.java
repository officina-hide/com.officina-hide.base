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
	public static final String Table_Comment = "パッケージで使用する各種属性情報を管理する。";
	public static final String Table_Comment_Eng = "Manage various type information used in the package.";
	/** テーブル情報ID */
	public final static int Table_ID = 104;
	
	/** 項目 */
	/** 種別情報ID */
	public final static String COLUMNNAME_FD_Type_ID = Table_Name + "_ID";
	public final static String NAME_FD_Type_ID = Table_Disp_Name + "ID";
	public final static String COMMENT_FD_Type_ID = "属性情報を識別する情報ID";
	/** 種別情報識別名 */
	public final static String COLUMNNAME_FD_Type_Name = "FD_Type_Name";
	/** 名前 */
	public final static String COLUMNNAME_FD_Name = "FD_Name";
	
	/** テーブル削除用SQL */
	public static final String Table_Drop_SQL =
			"DROP TABLE IF EXISTS " + Table_Name;
	/** テーブル生成用SQL */
	public static final String Table_Create_SQL = 
			"CREATE TABLE IF NOT EXISTS " + Table_Name
			+" ("
			+ COMMON_ITEM_CREATE_SQL
		+") "
		+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;
}

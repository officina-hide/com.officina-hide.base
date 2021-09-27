package com.officina_hide.base.model;

/**
 * 属性項目設定情報インターフェースクラス[Attribute item setting information interface class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/27
 */
public interface I_FD_TypeITem_Param extends I_FD_DB {
	
	/** テーブル名 */
	public static final String Table_Name = "FD_TypeITem_Param";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "属性項目設定情報";
	/** テーブル説明 */
	public static final String Table_Comment = "属性項目情報に紐づく設定値を管理する。";
	public static final String Table_Comment_Eng = "Manage the setting value associated with the attribute item information.";
	/** テーブルID */
	public static final int Table_ID = 107;
	
	/** 項目 : 属性項目設定情報ID */
	public final static String COLUMNNAME_FD_TypeItem_Param_ID = Table_Name + "_ID";
	public final static String NAME_FD_TypeItem_Param_ID = Table_Disp_Name + "ID";
	public final static String COMMENT_FD_TypeItem_Param_ID = "属性項目で使用する設定情報を識別する為の情報ID";
	/** 項目 : 属性項目設定名 */
	public final static String COLUMNNAME_FD_TypeItem_Param_Name = "FD_TypeITem_Param_Name";
	public final static String NAME_FD_TypeItem_Param_Name = "属性項目設定名";
	public final static String COMMENT_FD_TypeItem_Param_Name = "属性項目で使用する設定情報を識別する為の名称";
	/** 項目 : 属性項目情報ID */
	public final static String COLUMNNAME_FD_TypeItem_ID = I_FD_TypeItem.COLUMNNAME_FD_TypeItem_ID;
	public final static String NAME_FD_TypeItem_ID = I_FD_TypeItem.NAME_FD_TypeItem_ID;
	
	/** テーブル削除用SQL */
	public static final String Table_Drop_SQL =
			"DROP TABLE IF EXISTS " + Table_Name;
	
	/** テーブル生成用SQL */
	public static final String Table_Create_SQL = 
			"CREATE TABLE IF NOT EXISTS " + Table_Name
			+" ("
			+ COLUMNNAME_FD_TypeItem_Param_ID + ID_KEY_TYPE
				+ COMMENT + FD_SQ + NAME_FD_TypeItem_Param_ID + FD_SQ + ","
			+ COLUMNNAME_FD_TypeItem_Param_Name + VARCHAR_100
				+ COMMENT + FD_SQ + NAME_FD_TypeItem_Param_Name + FD_SQ + ","
			+ COLUMNNAME_FD_TypeItem_ID + ID_TYPE
				+ COMMENT + FD_SQ + NAME_FD_TypeItem_ID + FD_SQ + ","
			+ COLUMNNAME_FD_Name + VARCHAR_100
				+ COMMENT + FD_SQ + NAME_FD_Name + FD_SQ + ","
			+ COLUMNNAME_FD_Value + TEXT
				+ COMMENT + FD_SQ + NAME_FD_Value + FD_SQ + ","
			+ COLUMNNAME_FD_Description + TEXT
				+ COMMENT + FD_SQ + NAME_FD_Description + FD_SQ + ","
			+ COMMON_ITEM_CREATE_SQL
		+") "
		+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;
}

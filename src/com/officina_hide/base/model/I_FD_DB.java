package com.officina_hide.base.model;

/**
 * データベース操作インターフェースクラス[Database operation interface class]<br>
 * @author officina-hide.com
 * @version 1.00
 * @since 2021/04/11
 */
public interface I_FD_DB {

	/** SQL文補助 */
	public final static String FD_SQ = "'";	//シングルコーテーション
	
	/** テーブル項目種別 : ID */
	public static final String Item_Value_Type_ID = "ID";
	/** テーブル項目種別 : 文字列 */
	public static final String Item_Value_Type_String = "String";
	/** テーブル項目種別 : テキスト */
	public static final String Item_Value_Type_Text = "Text";
	/** テーブル項目種別 : 日付 */
	public static final String Item_Value_Type_Date = "Date";
	/** テーブル項目種別 : 拡大整数値 */
	public static final String Item_Value_Type_Bigint = "Bigint";
	
	/** システムグループ情報ID */
	public static final int SYSTEM_GROUP_ID = 100;
	/** システムユーザー情報ID */
	public static final int SYSTEM_USER_ID = 100;
	
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_COMMENT = "comment";
	public static final String COLUMN_LENGTH = "length";
	public static final String DATETIME = " datetime ";
	public static final String DATE = " date ";
	
	/** SQL用共通定数 */
	public static final String ID_KEY_TYPE = " int unsigned NOT NULL PRIMARY KEY ";
	public static final String ID_TYPE = " int unsigned ";
	public static final String UNSIGNED_BIGINT = " bigint unsigned ";
	public static final String COMMENT = " COMMENT ";
	
	/** 共通項目 : グルーブ情報ID */
	public static final String COLUMNNAME_FD_Group_ID = I_FD_Group.COLUMNNAME_FD_Group_ID;
	public static final String NAME_FD_Group_ID = I_FD_Group.NAME_FD_Group_ID;
	/** 共通項目 : 登録日時 */
	public static final String COLUMNNAME_FD_Created = "FD_Created";
	public static final String NAME_FD_Created = "登録日次";
	/** 共通項目 : 登録者情報ID */
	public static final String COLUMNNAME_FD_CreatedBy = "FD_CreatedBy";
	public static final String NAME_FD_CreatedBy = "登録者情報ID";
	/** 共通項目 : 更新日時 */
	public static final String COLUMNNAME_FD_Updated = "FD_Updated";
	public static final String NAME_FD_Updated = "更新日時";
	/** 共通項目 : 更新者情報ID */
	public static final String COLUMNNAME_FD_UpdatedBy = "FD_UpdatedBy";
	public static final String NAME_FD_UpdatedBy = "更新者情報ID";
	
	/** 共通項目生成用SQL */
	public static final String COMMON_ITEM_CREATE_SQL = 
			   COLUMNNAME_FD_Group_ID + ID_TYPE + COMMENT + FD_SQ + NAME_FD_Group_ID + FD_SQ + ","
			+ COLUMNNAME_FD_Created + DATETIME + COMMENT + FD_SQ + NAME_FD_Created + FD_SQ + ","
			+ COLUMNNAME_FD_CreatedBy + ID_TYPE + COMMENT + FD_SQ + NAME_FD_CreatedBy + FD_SQ + ","
			+ COLUMNNAME_FD_Updated + DATETIME + COMMENT + FD_SQ + NAME_FD_Updated + FD_SQ + ","
			+ COLUMNNAME_FD_UpdatedBy + ID_TYPE + COMMENT + FD_SQ + NAME_FD_UpdatedBy + FD_SQ;
}

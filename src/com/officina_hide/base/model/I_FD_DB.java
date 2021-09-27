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
	public static final String FD_Item_ID = "FD_Item_ID";
	/** テーブル項目種別 : 文字列 */
	public static final String FD_Item_String = "FD_Item_String";
	/** テーブル項目種別 : テキスト */
	public static final String FD_Item_Text = "FD_Item_Text";
	/** テーブル項目種別 : 日付 */
	public static final String FD_ITEM_Date = "FD_ITEM_Date";
	/** テーブル項目種別 : 整数値 */
	public static final String FD_ITEM_Int = "FD_ITEM_Int";
	public static final String FD_ITEM_Unsigned_Int = "FD_ITEM_Unsigned_Int";
	/** テーブル項目種別 : 拡大整数値 */
	public static final String FD_ITEM_BigInt = "FD_ITEM_BigInt";
	public static final String FD_ITEM_Unsugned_BigInt = "FD_ITEM_Unsigned_BigInt";
	
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
	public static final String VARCHAR_100 = " varchar(100) ";
	public static final String TEXT = " text ";
	
	/** SQL用共通定数 */
	public static final String ID_KEY_TYPE = " bigint unsigned NOT NULL PRIMARY KEY ";
	public static final String ID_TYPE = " bigint unsigned ";
	public static final String UNSIGNED_BIGINT = " bigint unsigned ";
	public static final String UNSIGNED_INT = " int unsigned ";
	public static final String COMMENT = " COMMENT ";
	
	/** 共通項目 : グルーブ情報ID */
	public static final String COLUMNNAME_FD_Group_ID = I_FD_Group.COLUMNNAME_FD_Group_ID;
	public static final String NAME_FD_Group_ID = I_FD_Group.NAME_FD_Group_ID;
	public static final String COMMENT_FD_Group_ID = I_FD_Group.COMMENT_FD_Group_ID;
	/** 共通項目 : 登録日時 */
	public static final String COLUMNNAME_FD_Created = "FD_Created";
	public static final String NAME_FD_Created = "登録日時";
	public static final String COMMENT_FD_Created = "情報の新規登録日時（登録時点のローカルシステム日付がセットされる。）";
	/** 共通項目 : 登録者情報ID */
	public static final String COLUMNNAME_FD_CreatedBy = "FD_CreatedBy";
	public static final String NAME_FD_CreatedBy = "登録者情報ID";
	public static final String COMMENT_FD_CreatedBy = "情報の新規登録者の情報ID（環境情報の処理者情報IDがセットされる。）";
	/** 共通項目 : 更新日時 */
	public static final String COLUMNNAME_FD_Updated = "FD_Updated";
	public static final String NAME_FD_Updated = "更新日時";
	public static final String COMMENT_FD_Updated = "情報の最終更新日時（更新時点のローカルシステム日付がセットされる。）";
	/** 共通項目 : 更新者情報ID */
	public static final String COLUMNNAME_FD_UpdatedBy = "FD_UpdatedBy";
	public static final String NAME_FD_UpdatedBy = "更新者情報ID";
	public static final String COMMENT_FD_UpdatedBy = "情報の最終更新者の情報ID（環境情報の処理者情報IDがセットされる。）";
	/** 項目 : 表示名 */
	public static final String COLUMNNAME_FD_Name = "FD_Name";
	public static final String NAME_FD_Name = "表示名";
	public static final String COMMENT_FD_Name = "情報の表示用の名称";
	/** 項目 : 説明 */
	public static final String COLUMNNAME_FD_Description = "FD_Description";
	public static final String NAME_FD_Description = "説明";
	public static final String COMMENT_FD_Description = "情報に関する詳細の説明";
	/** 項目 : 値 */
	public static final String COLUMNNAME_FD_Value = "FD_Value";
	public static final String NAME_FD_Value = "値";
	public static final String COMMENT_FD_Value = "情報が保有する値";
	
	/** 共通項目生成用SQL */
	public static final String COMMON_ITEM_CREATE_SQL = 
			   COLUMNNAME_FD_Group_ID + ID_TYPE + COMMENT + FD_SQ + NAME_FD_Group_ID + FD_SQ + ","
			+ COLUMNNAME_FD_Created + DATETIME + COMMENT + FD_SQ + NAME_FD_Created + FD_SQ + ","
			+ COLUMNNAME_FD_CreatedBy + ID_TYPE + COMMENT + FD_SQ + NAME_FD_CreatedBy + FD_SQ + ","
			+ COLUMNNAME_FD_Updated + DATETIME + COMMENT + FD_SQ + NAME_FD_Updated + FD_SQ + ","
			+ COLUMNNAME_FD_UpdatedBy + ID_TYPE + COMMENT + FD_SQ + NAME_FD_UpdatedBy + FD_SQ;

	/** 属性情報名 : テーブル項目情報用属性情報名 */
	public static final String FD_Column_Type = "FD_Column_Type";

}

package com.officina_hide.base.model;

/**
 * データベース操作インターフェースクラス[Database operation interface class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成
 * @since 2022/03/22 Ver. 1.50
 */
public interface I_FD_DB {
	
	/** 汎用定数 */
	public final long ID_ZERO = 0;
	public final int INT_ZERO = 0;
	
	/** SQL文補助 */
	public final static String FD_SQ = "'";	//シングルコーテーション

	/** SQL用共通定数 */
	public static final String ID_KEY_TYPE = " bigint unsigned NOT NULL PRIMARY KEY ";
	public static final String ID_TYPE = " bigint unsigned ";
	public static final String UNSIGNED_BIGINT = " bigint unsigned ";
	public static final String UNSIGNED_INT = " int unsigned ";
	public static final String YES_NO = " enum('Y','N') ";
	public static final String COMMENT = " COMMENT ";	
	public static final String DEFAULT = " DEFAULT ";	
	public static final String DATETIME = " datetime ";
	public final String DETAULT_CURRENT_DATE = " default (current_date) ";
	public static final String VARCHAR = " varchar(n) ";
	public final String LONGBLOB = " longblob ";
	public static final String TEXT = " text ";
	public static final String UNIQUE = " unique ";
	public static final String NOT_NULL = " not null ";
	
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

	/** 汎用項目 : 名前 */
	public static final String COLUMNNAME_FD_Name = "FD_Name";
	public static final String NAME_FD_Name = "名前";
	public static final String COMMENT_FD_Name = "対象の情報を表示する為の文字列情報<br>"
			+ "名前は翻訳機能と連携して言語により変換される。";
	public static final int SIZE_FD_Name = 100;
	/** 汎用項目 : 説明 */
	public static final String COLUMNNAME_FD_Description = "FD_Description";
	public static final String NAME_FD_Description = "説明";
	public static final String COMMENT_FD_Description = "対象の情報の内容を説明する<br>"
			+ "説明は翻訳機能と連携して言語により変換される。";
	
	/** テーブル型 : ID */
	public final String FD_Item_ID = "FD_Item_ID"; 
	/** テーブル型 : 文字列 */
	public final String FD_Item_String = "FD_Item_String";
	/** テーブル型 : Long列 */
	public final String FD_Item_Long = "FD_Item_Long";
	/** テーブル型 : int列 */
	public final String FD_Item_Int = "FD_Item_Int";
	/** テーブル型 : テキスト列 */
	public final String FD_Item_Text = "FD_Item_Text";
	/** テーブル型 : 大容量項目 */
	public final String FD_Item_LongBlob = "FD_Item_LongBlob";
	/** テーブル型 : 日付 */
	public final String FD_Item_Date = "FD_Item_Date";
	
	/** 参照グループ情報（テーブル項目） */
	public final String FD_REFGROUP_COLUMN = "FD_RefGroup_Column";
	
	/** 参照グルーブ : 画面項目属性 */
	public static final String FD_Reference_Group_Field = "View_Field";
	
	/** 共通項目生成用SQL */
	public static final String COMMON_ITEM_CREATE_SQL = 
			   COLUMNNAME_FD_Group_ID + ID_TYPE + COMMENT + FD_SQ + NAME_FD_Group_ID + FD_SQ + ","
			+ COLUMNNAME_FD_Created + DATETIME + COMMENT + FD_SQ + NAME_FD_Created + FD_SQ + ","
			+ COLUMNNAME_FD_CreatedBy + ID_TYPE + COMMENT + FD_SQ + NAME_FD_CreatedBy + FD_SQ + ","
			+ COLUMNNAME_FD_Updated + DATETIME + COMMENT + FD_SQ + NAME_FD_Updated + FD_SQ + ","
			+ COLUMNNAME_FD_UpdatedBy + ID_TYPE + COMMENT + FD_SQ + NAME_FD_UpdatedBy + FD_SQ;

	/** テーブル削除用SQL */
	public static final String SQL_Table_Drop =
			"DROP TABLE IF EXISTS ? ";
	
	/** テーブル項目情報抽出用SQL文 */
	public final String SQL_Get_ColumnData =
			"SELECT * FROM " + I_FD_Column.Table_Name + " c "
			+ "LEFT JOIN " + I_FD_Table.Table_Name + " t ON t." + I_FD_Table.COLUMNNAME_FD_Table_ID + " = "
				+ "c." + I_FD_Column.COLUMNNAME_FD_Table_ID + " "
			+ "LEFT JOIN " + I_FD_Reference.Table_Name + " r ON r." + I_FD_Reference.COLUMNNAME_FD_Reference_ID + " = "
				+ "c." + I_FD_Column.COLUMNNAME_FD_ColumnType_ID + " "
			+ "WHERE t." + I_FD_Table.COLUMNNAME_FD_Table_Code + " = ? ";
}

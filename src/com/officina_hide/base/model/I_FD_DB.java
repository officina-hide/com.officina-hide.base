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
	public final static String FD_LR = "\n";	//改行
	public final static String FD_SC = ";";	//セミコロン
	public final static String FD_TAB = "\t";		//タブ
	
	/** クラス設定用 */
	public final static String FD_DB_ImportUri = "com.officina_hide.base.model.FD_DB";
	
	/** テーブル項目種別 : ID */
	public static final String FD_ITEM_ID = "FD_Item_ID";
	/** テーブル項目種別 : 文字列 */
	public static final String FD_ITEM_String = "FD_Item_String";
	/** テーブル項目種別 : テキスト */
	public static final String FD_ITEM_Text = "FD_Item_Text";
	/** テーブル項目種別 : 日付 */
	public static final String FD_ITEM_Date = "FD_ITEM_Date";
	/** テーブル項目種別 : 整数値 */
	public static final String FD_ITEM_Int = "FD_ITEM_Int";
	public static final String FD_ITEM_Unsigned_Int = "FD_ITEM_Unsigned_Int";
	/** テーブル項目種別 : 拡大整数値 */
	public static final String FD_ITEM_BigInt = "FD_ITEM_BigInt";
	public static final String FD_ITEM_Unsigned_BigInt = "FD_ITEM_Unsigned_BigInt";
	/** テーブル項目種別 : 判定 */
	public static final String FD_ITEM_YES_NO = "FD_ITEM_YES_NO";
	/** テーブル項目種別 : 金額 */
	public static final String FD_ITEM_Amount = "FD_ITEM_Amount";
	
	/** 画面項目種別 : 1行テキスト */
	public static final String FD_Field_SimpleText = "FD_Field_SimpleText";
	/** 画面項目種別 : パスワード */
	public static final String FD_Field_Password = "FD_Field_Password";
	/** 画面項目種別 : 複数行テキスト */
	public static final String FD_Field_Text = "FD_Field_Text";
	/** 画面項目種別 : 日付 */
	public static final String FD_Field_Date = "FD_Field_Date";
	/** 画面項目種別 : リスト */
	public static final String FD_Field_List = "FD_Field_List";
	/** 画面項目種別 : 金額 */
	public static final String FD_Field_Amount = "FD_Field_Amount";
	
	/** メニュー種別 : 画面 */
	public static final String FD_Menu_View = "FD_Menu_View";
	/** メニュー種別 : 処理 */
	public static final String FD_Menu_Process = "FD_Menu_Process";
	
	/** 参照種別 : テーブル */
	public static final String FD_Reference_Table = "FD_Reference_Table";
	public static final String NAME_FD_Reference_Table = "テーブル参照";
	public static final String COMMENT_FD_Reference_Table = "指定されたテーブルからリストを作成し選択できるようにする。";
	
	/** テーブル参照変数 : テーブル情報ID */
	public static final String FD_RefParam_Table = "FD_RefParam_Table";
	
	/** 処理変数種別 */
	public static final String FD_Param_Type = "FD_Param_Type";
	public static final String NAME_FD_Param_Type = "処理変数属性";
	public static final String COMMENT_FD_Param_Type = "処理変数で使用する属性を管理する。";
	/** 処理変数種別 : オブジェクト */
	public static final String FD_Param_Object = "FD_Param_Object";
	public static final String NAME_FD_Param_Object = "オブジェクト";
	public static final String COMMENT_FD_Param_Object = "クラスなどのオブジェクト情報";
	
	/** システムグループ情報ID */
	public static final int SYSTEM_GROUP_ID = 100;
	/** システムユーザー情報ID */
	public static final int SYSTEM_USER_ID = 100;
	public static final String SYSTEM_USER = "SystemUser";
	public static final String SYSTEM_USER_PASSWORD = "System";
	public static final String SYSTEM_USER_NAME = "システム管理者";
	public static final String SYSTEM_USER_DESCRIPTION = "システム初期設定時の管理者（稼働時には無効化される。）";
	
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
	public static final String YES_NO = " enum('Y','N') ";
	public static final String COMMENT = " COMMENT ";	
	public static final String DEFAULT = " DEFAULT ";	
	
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
	public static final String FD_Column_Type = "FD_Column_Type";	//テーブル項目
	public static final String FD_Field_Type = "FD_Field_Type";				//画面項目
	public static final String FD_MENU_Type = "FD_Menu_Type";			//メニュー項目
	public static final String FD_Reference_Type = "FD_Reference_Type";			//参照項目

	/** テーブル項目一覧用情報抽出SQL文 */
	public static final String ITEM_LIST_SQL_Name = "name";
	public static final String ITEM_LIST_SQL_TypeName = "typename";
	public static final String ITEM_LIST_SQL_TableID = "tableID";
	public static final String ITEM_LIST_SQL = 
			"SELECT "
				+ "d."+I_FD_DataDictionary.COLUMNNAME_FD_DataDictionary_Name + " " + ITEM_LIST_SQL_Name+ ", "
				+ "ty." + I_FD_TypeItem.COLUMNNAME_FD_TypeItem_Name + " " + ITEM_LIST_SQL_TypeName + ", "
				+ "t." + I_FD_Table.COLUMNNAME_FD_Table_ID + " " + ITEM_LIST_SQL_TableID + " "
			+ "FROM " + I_FD_Column.Table_Name + " c "
			+ "LEFT JOIN " + I_FD_Table.Table_Name + " t "
				+ " ON " + "t." + I_FD_Table.COLUMNNAME_FD_Table_ID + " = "
				+ "c." + I_FD_Column.COLUMNNAME_FD_Table_ID + " "
			+ "LEFT JOIN " + I_FD_DataDictionary.Table_Name + " d "
				+ " ON " + "d." + I_FD_DataDictionary.COLUMNNAME_FD_DataDictionary_ID + " = "
				+ "c." + I_FD_Column.COLUMNNAME_FD_DataDictionary_ID + " "
			+ "LEFT JOIN " + I_FD_TypeItem.Table_Name + " ty "
				+ " ON " + "ty." + I_FD_TypeItem.COLUMNNAME_FD_TypeItem_ID + " = "
				+ "c." + I_FD_Column.COLUMNNAME_FD_TypeItem_ID + " "
			+ "WHERE " + "t." + I_FD_Table.COLUMNNAME_FD_Table_Name + " = ?";

}

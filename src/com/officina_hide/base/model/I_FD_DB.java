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
	
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_COMMENT = "comment";
	public static final String COLUMN_LENGTH = "length";

}

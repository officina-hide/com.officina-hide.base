package com.officina_hide.base.model;

/**
 * ファイル拡張子インターフェースクラス[File Extension information interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[Create new]
 * @since 2022/05/07 Ver. 1.00
 */
public interface I_FD_ExtensionType extends I_FD_DB {
	
	/** テーブル名 */
	public static final String Table_Name = "FD_ExtentionType";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "ファイル拡張子情報";
	
	/** 項目 : ファイル拡張子情報ID */
	public final String COLUMNNAME_FD_ExtensionType_ID = Table_Name + "_ID";
	public final String NAME_FD_ExtentionType_ID = Table_Disp_Name + "ID";
	/** 項目 : ファイル拡張子文字列 */
	public final String COLUMNNAME_FD_Extension_Code = "FD_Extension_Code";
	public final String NAME_FD_Extension_Code = "拡張子コード";
	public final int SIZE_FD_Extension_Code = 4;

	/** テーブル削除用SQL */
	public static final String Table_Drop_SQL =
			"DROP TABLE IF EXISTS " + Table_Name;

	/** テーブル生成用SQL */
	public static final String Table_Create_SQL = 
			"CREATE TABLE IF NOT EXISTS " + Table_Name
			+" ("
			+") "
			+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;
}

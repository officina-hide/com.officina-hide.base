package com.officina_hide.base.model;

/**
 * ファイル情報クラス[File information class]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 */
public interface I_FD_File extends I_FD_DB {
	
	/** テーブル名 */
	public static final String Table_Name = "FD_File";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "ファイル情報";

	/** 項目 : ファイル情報ID */
	public static final String COLUMNNAME_FD_File_ID = Table_Name + "_ID";
	public static final String NAME_FD_File_ID = Table_Disp_Name + "ID";
	/** 項目 : ファイル管理コード */
	public static final String COLUMNNAME_FD_File_Code = "FD_File_Code";
	public static final String NAME_FD_File_Code = "ファイル管理コード";
	public static final int SIZE_FD_File_Code = 10;
	/** 項目 : 名前(FD_Name) */
	
	/** テーブル削除用SQL */
	public static final String Table_Drop_SQL =
			"DROP TABLE IF EXISTS " + Table_Name;

	/** テーブル生成用SQL */
	public static final String Table_Create_SQL = 
			"CREATE TABLE IF NOT EXISTS " + Table_Name
			+" ("
				+ COLUMNNAME_FD_File_ID + ID_KEY_TYPE
					+ COMMENT + FD_SQ + NAME_FD_File_ID + FD_SQ + ","
				+ COLUMNNAME_FD_File_Code + VARCHAR.replaceAll("n", Integer.toString(SIZE_FD_File_Code))
					+ UNIQUE + NOT_NULL
					+ COMMENT + FD_SQ + NAME_FD_File_Code + FD_SQ + ","
				+ COLUMNNAME_FD_Name + VARCHAR.replaceAll("n", Integer.toString(SIZE_FD_Name))
					+ COMMENT + FD_SQ + NAME_FD_Name + FD_SQ + ""
			+") "
			+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;
	
	/** 登録用情報 : テーブル情報 */ 
	public final String Entry_FD_Table = 
			I_FD_Table.COLUMNNAME_FD_Table_Code+":"+Table_Name+","
			+ I_FD_Table.COLUMNNAME_FD_Name+":"+Table_Disp_Name;
	/** 登録用情報 : 採番情報 */
	public final String Entry_FD_Number =
			I_FD_Numbering.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name + ","
			+ I_FD_Numbering.COLUMNNAME_FD_InitialNumber+":"+"1000001";			
	/** 登録用情報 : テーブル項目情報 : ファイル情報ID */
	public final String Entry_FD_Column_FD_File_ID =
			I_FD_Column.COLUMNNAME_FD_Column_Code+":"+COLUMNNAME_FD_File_ID+","
			+ I_FD_Column.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Name+":"+NAME_FD_File_ID;
	/** 登録用情報 : テーブル項目情報 : ファイル管理コード  */
	public final String Entry_FD_Column_FD_File_Code =
			I_FD_Column.COLUMNNAME_FD_Column_Code+":"+COLUMNNAME_FD_File_Code+","
			+ I_FD_Column.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Name+":"+NAME_FD_File_Code;
	/** 登録用情報 : 採番情報 : ファイル管理コード  */
	public final String Entry_FD_Number_FD_File_Code =
			I_FD_Numbering.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Numbering.COLUMNNAME_FD_Column_ID+":@getColumnID:"+Table_Name+":"+COLUMNNAME_FD_File_Code+","
			+ I_FD_Numbering.COLUMNNAME_FD_NumberFormat+":FILE_@n05"+","
			+ I_FD_Numbering.COLUMNNAME_FD_InitialNumber+":"+"10001";
	
}

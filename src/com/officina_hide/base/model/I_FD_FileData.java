package com.officina_hide.base.model;

/**
 * ファイルデータ情報インターフェースクラス[File data information interface class]<br?
 * @author officina-hide.net
 * @version 1.00 新規作成[Create new]
 * @since 2022/05/03 Ver. 1.00
 */
public interface I_FD_FileData extends I_FD_DB {
	
	/** テーブル名 */
	public static final String Table_Name = "FD_FileData";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "ファイルデータ情報";
	
	/** 項目 : ファイルデータ情報ID */
	public final String COLUMNNAME_FD_FileData_ID = Table_Name + "_ID";
	public final String NAME_FD_FileData_ID = Table_Disp_Name + "ID";
	/** 項目 : ファイル情報ID */
	public final String COLUMNNAME_FD_File_ID = I_FD_File.COLUMNNAME_FD_File_ID;
	public final String NAME_FD_File_ID = I_FD_File.NAME_FD_File_ID;
	/** 項目 : ファイルデータ */
	public final String COLUMNNAME_FD_File_Data = "FD_File_Data";
	public final String Name_FD_File_Data = "ファイルデータ";
	
	/** テーブル削除用SQL */
	public static final String Table_Drop_SQL =
			"DROP TABLE IF EXISTS " + Table_Name;

	/** テーブル生成用SQL */
	public static final String Table_Create_SQL = 
			"CREATE TABLE IF NOT EXISTS " + Table_Name
			+" ("
				+ COLUMNNAME_FD_FileData_ID + ID_KEY_TYPE
					+ COMMENT + FD_SQ + NAME_FD_FileData_ID + FD_SQ + ","
				+ COLUMNNAME_FD_File_ID + ID_TYPE
					+ UNIQUE + NOT_NULL
					+ COMMENT + FD_SQ + NAME_FD_File_ID + FD_SQ + ","
				+ COLUMNNAME_FD_File_Data + LONGBLOB
					+ COMMENT + FD_SQ + Name_FD_File_Data + FD_SQ + ""
			+") "
			+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;

}

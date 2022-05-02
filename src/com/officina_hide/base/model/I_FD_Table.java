package com.officina_hide.base.model;

/**
 * テーブル情報インターフェースクラス[Table information interface class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成
 * @since 2022/03/22 Ver. 1.50
 */
public interface I_FD_Table extends I_FD_DB {
	
	/** テーブル名 */
	public static final String Table_Name = "FD_Table";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "テーブル情報";
	/** テーブル説明 */
	public static final String Table_Comment = "パッケージで使用するテーブルを管理する。";
	public static final String Table_Comment_Eng = "Manage the tables used in the package.";
	/** テーブルID */
	public static final int Table_ID = 101;

	/** 項目 : テーブル情報ID */
	public static final String COLUMNNAME_FD_Table_ID = Table_Name+"_ID";
	public static final String NAME_FD_Table_ID = Table_Disp_Name+"ID";
	public static final String COMMENT_FD_Table_ID = "テーブルを識別する為の情報ID";
	/** 項目 : テーブル情報コード */
	public static final String COLUMNNAME_FD_Table_Code = "FD_Table_Code";
	public static final String NAME_FD_Table_Code = "テーブル情報コード";
	public static final String COMMENT_FD_Table_Code = "テーブルを識別する手目のコード";
	public static final int SIZE_FD_Table_Code = 100;
	
	/** テーブル削除用SQL */
	public static final String Table_Drop_SQL =
		"DROP TABLE IF EXISTS " + Table_Name;

	/** テーブル生成用SQL */
	public static final String Table_Create_SQL = 
		"CREATE TABLE IF NOT EXISTS " + Table_Name
		+" ("
			+ COLUMNNAME_FD_Table_ID + ID_KEY_TYPE
				+ COMMENT + FD_SQ + NAME_FD_Table_ID + FD_SQ + ","
			+ COLUMNNAME_FD_Table_Code + VARCHAR.replaceAll("n", Integer.toString(SIZE_FD_Table_Code))
				+ UNIQUE + NOT_NULL
				+ COMMENT + FD_SQ + NAME_FD_Table_Code + FD_SQ + ","
			+ COLUMNNAME_FD_Name + VARCHAR.replaceAll("n", Integer.toString(SIZE_FD_Name))
				+ COMMENT + FD_SQ + NAME_FD_Name + FD_SQ + ","
			+ COLUMNNAME_FD_Description + TEXT
				+ COMMENT + FD_SQ + NAME_FD_Description + FD_SQ + ""
		+") "
		+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;
	
	/** 登録用情報 : 採番情報 */
	public final String Entry_FD_Number = 
			I_FD_Numbering.COLUMNNAME_FD_Numbering_ID+":"+Table_ID+","
			+ I_FD_Numbering.COLUMNNAME_FD_Table_ID+":"+Table_ID + ","
			+ I_FD_Numbering.COLUMNNAME_FD_InitialNumber+":"+"10001";
	/** 登録用情報 : テーブル情報 */
	public final String Entry_FD_Table = 
			COLUMNNAME_FD_Table_ID+":"+Table_ID+","
			+ COLUMNNAME_FD_Table_Code+":"+Table_Name+","
			+ COLUMNNAME_FD_Name+":"+Table_Disp_Name+","
			+ COLUMNNAME_FD_Description+":"+Table_Comment+"";
}

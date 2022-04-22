package com.officina_hide.base.model;

/**
 * テーブル項目情報インターフェースクラス[Table column information interface class]<br>
 * @author officina-hide.com
 * @version 1.50 新規作成[New create]
 * @since 2022/04/19 Ver. 1.50
 */
public interface I_FD_Column extends I_FD_DB {
	
	/** テーブル名 */
	public final String Table_Name = "FD_Column";
	/** テーブル表示名 */
	public final String Table_Disp_Name = "テーブル項目情報";
	/** テーブルID */
	public final int Table_ID = 102;
	
	/** 項目 : テーブル項目情報ID */
	public static final String COLUMNNAME_FD_Column_ID = Table_Name + "_ID";
	public static final String NAME_FD_Column_ID = Table_Disp_Name + "ID";
	/** 項目 : テーブル情報ID */
	public static final String COLUMNNAME_FD_Table_ID = I_FD_Table.COLUMNNAME_FD_Table_ID;
	public static final String NAME_FD_Table_ID = I_FD_Table.NAME_FD_Table_ID;
	/** 項目 : テーブル項目コード */
	public static final String COLUMNNAME_FD_Column_Code = "FD_Column_Code";
	public static final String NAME_FD_Column_Code = "テーブル項目コード";
	public static final int SIZE_FD_Column_Code = 100;
	
	/** テーブル削除用SQL */
	public static final String Table_Drop_SQL =
		"DROP TABLE IF EXISTS " + Table_Name;

	/** テーブル生成用SQL */
	public static final String Table_Create_SQL = 
		"CREATE TABLE IF NOT EXISTS " + Table_Name
		+" ("
			+ COLUMNNAME_FD_Column_ID + ID_KEY_TYPE
				+ COMMENT + FD_SQ + NAME_FD_Column_ID + FD_SQ + ","
			+ COLUMNNAME_FD_Table_ID + ID_TYPE
				+ COMMENT + FD_SQ + NAME_FD_Table_ID + FD_SQ + ","
			+ COLUMNNAME_FD_Column_Code + VARCHAR.replaceAll("n", Integer.toString(SIZE_FD_Column_Code))
				+ COMMENT + FD_SQ + NAME_FD_Column_Code + FD_SQ + ","
			+ COLUMNNAME_FD_Name + VARCHAR.replaceAll("n", Integer.toString(SIZE_FD_Name))
				+ COMMENT + FD_SQ + NAME_FD_Name + FD_SQ + ""
		+") "
		+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;
	
	/** 登録用情報 : 採番情報 */
	public final String Entry_FD_Number = 
			I_FD_Numbering.COLUMNNAME_FD_Numbering_ID+":"+Table_ID+","
			+ I_FD_Numbering.COLUMNNAME_FD_Table_ID+":"+Table_ID + ","
			+ I_FD_Numbering.COLUMNNAME_FD_InitialNumber+":"+"1000001";
}

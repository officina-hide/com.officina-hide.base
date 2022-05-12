package com.officina_hide.base.model;

/**
 * 参照グループ情報インターフェースクラス[Reference group information interface class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成[New create]
 * @since 2022/05/10 Ver. 1.50
 */
public interface I_FD_ReferenceGroup extends I_FD_DB {
	
	/** テーブル名 */
	public final String Table_Name = "FD_ReferenceGroup";
	/** テーブル表示名 */
	public final String Table_Disp_Name = "参照グループ情報";
	/** テーブルID */
	public final long Table_ID = 105;

	/** 項目 : 参照グルーブ情報ID */
	public final String COLUMNNAME_FD_ReferenceGroup_ID = Table_Name + "_ID";
	public final String NAME_FD_ReferenceGroup_ID = Table_Disp_Name + "ID";
	/** 項目 : 参照グループコード */
	public final String COLUMNNAME_FD_ReferenceGroup_Code = "FD_ReferenceGroup_Code";
	public final String NAME_FD_ReferenceGroup_Code = "参照グループコード";
	public final int SIZE_FD_ReferenceGroup_Code = 100;
	
	/** テーブル生成用SQL */
	public final String Table_Create_SQL = 
		"CREATE TABLE IF NOT EXISTS " + Table_Name
		+" ("
			+ COLUMNNAME_FD_ReferenceGroup_ID + ID_KEY_TYPE
				+ COMMENT + FD_SQ + NAME_FD_ReferenceGroup_ID + FD_SQ + ","
			+ COLUMNNAME_FD_ReferenceGroup_Code + VARCHAR.replaceAll("n", Integer.toString(SIZE_FD_ReferenceGroup_Code))
				+ NOT_NULL
				+ COMMENT + FD_SQ + NAME_FD_ReferenceGroup_Code + FD_SQ + ","
			+ COLUMNNAME_FD_Name + VARCHAR.replaceAll("n", Integer.toString(SIZE_FD_Name)) + ""
		+") "
		+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;
	
	/** 登録用情報 : テーブル情報 */ 
	public final String Entry_FD_Table = 
			I_FD_Table.COLUMNNAME_FD_Table_Code+":"+Table_Name+","
			+ I_FD_Table.COLUMNNAME_FD_Name+":"+Table_Disp_Name+","
			+ I_FD_Table.COLUMNNAME_FD_Table_ID+":"+Long.toString(Table_ID);
	
	/** 登録用情報 : 採番情報 */
	public final String Entry_FD_Number = 
			I_FD_Numbering.COLUMNNAME_FD_Numbering_ID+":"+Table_ID+","
			+ I_FD_Numbering.COLUMNNAME_FD_Table_ID+":"+Table_ID + ","
			+ I_FD_Numbering.COLUMNNAME_FD_InitialNumber+":"+"100001";

	/** 登録用情報 : 参照グループ情報（共通）: テーブル項目 */
	public final String Entry_FD_ReferenceGroup_ColumnType =
			COLUMNNAME_FD_ReferenceGroup_Code+":"+FD_REFGROUP_COLUMN+","
			+ COLUMNNAME_FD_Name+":"+"テーブル項目";
}

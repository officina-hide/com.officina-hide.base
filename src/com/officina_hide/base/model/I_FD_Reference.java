package com.officina_hide.base.model;

/**
 * 参照情報インターフェースクラス[Reference information interface class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成[New create]
 * @since 2022/05/10 Ver. 1.50
 */
public interface I_FD_Reference extends I_FD_DB {
	
	/** テーブル名 */
	public final String Table_Name = "FD_Reference";
	/** テーブル表示名 */
	public final String Table_Disp_Name = "参照情報";
	/** テーブルID */
	public final long Table_ID = 104;
	
	/** 項目 : 参照情報ID */
	public final String COLUMNNAME_FD_Reference_ID = Table_Name + "_ID";
	public final String NAME_FD_Reference_ID = Table_Disp_Name + "ID";
	/** 項目 : 参照グルーブID */
	public final String COLUMNNAME_FD_ReferenceGroup_ID = I_FD_ReferenceGroup.COLUMNNAME_FD_ReferenceGroup_ID;
	public final String NAME_FD_ReferenceGroup_ID = I_FD_ReferenceGroup.NAME_FD_ReferenceGroup_ID;
	/** 項目 : 参照コード */
	public final String COLUMNNAME_FD_Reference_Code = "FD_Reference_Code";
	public final String NAME_FD_Reference_Code = "参照コード";
	public final int SIZE_FD_Reference_Code = 100;

	/** テーブル生成用SQL */
	public final String Table_Create_SQL = 
		"CREATE TABLE IF NOT EXISTS " + Table_Name
		+" ("
			+ COLUMNNAME_FD_Reference_ID + ID_KEY_TYPE
				+ COMMENT + FD_SQ + NAME_FD_Reference_ID + FD_SQ + ","
			+ COLUMNNAME_FD_ReferenceGroup_ID + ID_TYPE
				+ NOT_NULL
				+ COMMENT  + FD_SQ + NAME_FD_ReferenceGroup_ID + FD_SQ + ","
			+ COLUMNNAME_FD_Reference_Code + VARCHAR.replaceAll("n", Integer.toString(SIZE_FD_Reference_Code))
				+ NOT_NULL
				+ COMMENT + FD_SQ + NAME_FD_Reference_Code + FD_SQ + ","
			+ COLUMNNAME_FD_Name + VARCHAR.replaceAll("n", Integer.toString(SIZE_FD_Name))
				+ COMMENT + FD_SQ + NAME_FD_Name + FD_SQ + ""
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
	
	/** 登録用情報 : 参照情報（共通）: FD_Item_ID */
	public final String Entry_FD_Reference_FD_Item_ID =
			COLUMNNAME_FD_ReferenceGroup_ID+":"+"@getRefGroupId:"+FD_REFGROUP_COLUMN+","
			+ COLUMNNAME_FD_Reference_Code+":"+FD_Item_ID+","
			+ COLUMNNAME_FD_Name+":"+"情報ID";
	/** 登録用情報 : 参照情報（共通）: FD_Item_String */
	public final String Entry_FD_Reference_FD_Item_String =
			COLUMNNAME_FD_ReferenceGroup_ID+":"+"@getRefGroupId:"+FD_REFGROUP_COLUMN+","
			+ COLUMNNAME_FD_Reference_Code+":"+FD_Item_String+","
			+ COLUMNNAME_FD_Name+":"+"文字列";
	
}

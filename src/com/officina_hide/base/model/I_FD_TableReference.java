package com.officina_hide.base.model;

/**
 * テーブル参照情報インターフェースクラス[Table reference information interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/12/21 Ver. 1.00
 */
public interface I_FD_TableReference extends I_FD_DB {
	
	/** テーブル名 */
	public static final String Table_Name = "FD_TableReference";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "テーブル参照情報";
	/** テーブル説明 */
	public static final String Table_Comment = "参照情報でテーブル参照とした時の参照先の情報を管理する。";
	public static final String Table_Comment_Eng = "Manage the information of the reference destination when the table is referenced in the reference information.";
	/** テーブルID */
	public static final int Table_ID = 116;

	/** 項目 : テーブル参照情報ID */
	public static final String COLUMNNAME_FD_TableReference_ID = Table_Name + "_ID";
	public static final String NAME_FD_TableReference_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FD_TableReference_ID = "テーブル参照に関する情報を識別する為の情報ID";
	/** 項目 : 参照情報ID */
	public static final String COLUMNNAME_FD_Reference_ID = I_FD_Reference.COLUMNNAME_FD_Reference_ID;
	public static final String NAME_FD_Reference_ID = I_FD_Reference.NAME_FD_Reference_ID;
	/** 項目 : テーブル情報ID */
	public static final String COLUMNNAME_FD_Table_ID = I_FD_Table.COLUMNNAME_FD_Table_ID;
	public static final String NAME_FD_Table_ID = I_FD_Table.NAME_FD_Table_ID;
}

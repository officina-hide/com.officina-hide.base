package com.officina_hide.base.model;

/**
 * 採番情報インターフェースクラス[Numbering informatioin interface class]
 * @author officine-hide.com
 * @version 1.30
 * @since 2020/12/13
 */
public interface I_FD_Numbering {

	/** 採番情報 : テーブル名 */
	public final String Table_Name = "FD_Numbering";
	/** 採番情報 : テーブル表示名 */
	public final String NAME = "採番情報";
	/** 採番情報 : テーブル説明 */
	public final String COMMENT = "テーブル毎に情報IDの採番を管理する為の情報";
	/** 採番情報 : テーブル情報ID */
	public final int TABLE_ID = 110;

	//テーブル項目情報
	/** 採番情報ID */
	public final String COLUMNNAME_FD_Numbering_ID = Table_Name + "_ID";
	public final String NAME_FD_Numbering_ID = "採番情報ID";
	public final String COMMENT_FD_Numbering_ID = "採番情報を識別する為の情報ID";
	/** テーブル情報ID */
	public final String COLUMNNAME_FD_Table_ID = I_FD_Table.Table_Name + "_ID";
	public final String NAME_FD_Table_ID = "テーブル情報ID";
	public final String COMMENT_FD_Table_ID = "採番の対象となるテーブル情報の情報ID";
	/** 現在値 */
	public final String COLUMNNAME_Current_Number = "Current_Number";
	public final String NAME_Current_Number = "現在値";
	public final String COMMENT_Current_Number = "使用されている最大の値";
	/** 初期値 */
	public final String COLUMNNAME_Initial_Number = "Initial_Number";
	public final String NAME_Initial_Number = "初期値";
	public final String COMMENT_Initial_Number = "最初に付与される値";
	/** 採番Key */
	public final String COLUMNNAME_Numbering_Key = "Numbering_Key";
	public final String NAME_Numbering_Key = "採番Key";
	public final String COMMENT_Numbering_Key = "採番の対象となるKey情報";
}

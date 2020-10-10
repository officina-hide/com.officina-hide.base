package com.officina_hide.base.model;

public interface I_FD_Table {

	/** テーブル情報 : テーブル名 */
	public final String Table_Name = "FD_Table";
	/** テーブル情報 : テーブル表示名 */
	public final String NAME = "テーブル情報";
	/** テーブル情報 : テーブル説明 */
	public final String COMMENT = "テーブルを管理する為の情報";
	/** テーブル情報 : テーブル情報ID */
	public final int FD_TABLE_ID = 101;
	
	//テーブル項目情報
	/** テーブル情報ID */
	public final String COLUMNNAME_FD_Table_ID = "FD_Table_ID";
	public final String NAME_FD_Table_ID = "テーブル情報ID";
	public final String COMMENT_FD_Table_ID = "テーブル情報を設定識別するための情報ID";
	/** テーブル名 */
	public final String COLUMNNAME_Table_Name = "Table_Name";
	public final String NAME_Table_Name = "テーブル名";
	public final String COMMENT_Table_Name = "テーブルの物理名称";
	/** テーブル論理名 */
	public final String COLUMNNAME_FD_Name = "FD_Name";
	public final String NAME_FD_Name = "テーブル論理名";
	public final String COMMENT_FD_Name = "テーブルの論理名（コメント）";
	/** テーブル説明 */
	public final String COLUMNNAME_FD_Comment = "FD_Comment";
	public final String NAME_FD_Comment = "テーブル説明";
	public final String COMMENT_FD_Comment = "テーブル情報の説明";
}

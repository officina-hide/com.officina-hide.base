package com.officina_hide.base.model;

/**
 * テーブル項目情報インターフェース
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/10/20
 */
public interface I_FD_TableColumn {
	/** テーブル項目情報 : テーブル名 */
	public final String Table_Name = "FD_TableColumn";
	/** テーブル項目情報 : テーブル表示名 */
	public final String NAME = "テーブル項目情報";
	/** テーブル項目情報 : テーブル説明 */
	public final String COMMENT = "テーブル項目を管理する為の情報";
	/** テーブル項目情報 : テーブル情報ID */
	public final int TABLE_ID = 102;
	
	//テーブル項目情報
	/** テーブル項目情報ID */
	public final String COLUMNNAME_FD_TableColumn_ID = Table_Name + "_ID";
	public final String NAME_FD_TableColumn_ID = "テーブル項目情報ID";
	public final String COMMENT_FD_TableColumn_ID = "テーブル項目情報を識別する為の情報ID";
	/** テーブル情報ID */
	public final String COLUMNNAME_FD_Table_ID = "FD_Table_ID";
	public final String NAME_FD_Table_ID = "テーブル情報ID";
	public final String COMMENT_FD_Table_ID = "テーブル項目を管理するテーブルの情報ID";
	/** テーブル項目名 */
	public final String COLUMNNAME_TableColumn_Name = "Talbe_Name";
	public final String NAME_TableColumn_Name = "テーブル項目名";
	public final String COMMENT_TableColumn_Name = "テーブル項目を識別する際に使用する名称";
}

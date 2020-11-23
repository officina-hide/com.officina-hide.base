/**
 * 
 */
package com.officina_hide.base.model;

/**
 * 項目書式情報インタフェース<br>
 * @author officina-hide.com
 * @version 1.21 新規作成
 * @since 2020/11/21
 */
public interface I_FD_ColumnForm {

	/** 項目書式情報 : テーブル名 */
	public final String Table_Name = "FD_ColumnForm";
	/** 項目書式情報 : テーブル表示名 */
	public final String NAME = "項目書式情報";
	/** 項目書式情報 : テーブル説明 */
	public final String COMMENT = "項目書式を管理する為の情報";
	/** 項目書式情報 : テーブル情報ID */
	public final int TABLE_ID = 113;
	
	/** 項目書式情報ID */
	public final String COLUMNNAME_FD_ColumnForm_ID = Table_Name + "_ID";
	public final String NAME_FD_ColumnForm_ID = "項目書式情報ID";
	public final String COMMENT_FD_ColumnForm_ID = "項目書式を識別するための情報ID";
	/** 項目書式名 */
	public final String COLUMNNAME_ColumnForm_Name = "ColumnForm_Name";
	public final String NAME_ColumnForm_Name = "項目書式名";
	public final String COMMENT_ColumnForm_Name = "項目書式の識別名";
	/** 項目書式表示名 */
	public final String COLUMNNAME_FD_Name = "FD_Name";
	public final String NAME_FD_Name = "項目書式表示名";
	public final String COMMENT_FD_Name = "項目書式の論理名称";
}

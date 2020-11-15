package com.officina_hide.base.model;

/**
 * ユニーク制約インデックス情報インターフェース<br>
 * @author officine-hide.com
 * @version 1.20
 * @since 2020/11/13
 */
public interface I_FD_UniqueIndex {

	/** ユニーク制約インデックス情報 : テーブル名 */
	public final String Table_Name = "FD_UniqueIndex";
	/** ユニーク制約インデックス情報 : テーブル表示名 */
	public final String NAME = "ユニーク制約インデックス情報";
	/** ユニーク制約インデックス情報 : テーブル説明 */
	public final String COMMENT = "ユニーク制約インデックスを管理する為の情報";
	/** ユニーク制約インデックス情報 : テーブル情報ID */
	public final int TABLE_ID = 103;
	
	/** ユニーク制約インデックス情報ID */
	public final String COLUMNNAME_FD_UniqueIndex_ID = Table_Name + "_ID";
	public final String NAME_FD_UniqueIndex_ID = "ユニーク制約インデックス情報ID";
	public final String COMMENT_FD_UniqueIndex_ID = "ユニーク制約インデックス情報を識別する為の情報ID";
	/** テーブル情報ID */
	public final String COLUMNNAME_FD_Table_ID = I_FD_Table.Table_Name + "_ID";
	public final String NAME_FD_Table_ID = I_FD_Table.NAME_FD_Table_ID;
	public final String COMMENT_FD_Table_ID = "ユニーク制約を設定するテーブルの情報ID";
	/** インデックス名 */
	public final String COLUMNNAME_Index_Name = "Index_Name";
	public final String NAME_Index_Name = "インデックス名";
	public final String COMMENT_Index_Name = "ユニーク制約を識別する為のインデックス名";
	/** インデックス表示名 */
	public final String COLUMNNAME_FD_Name = "FD_Name";
	public final String NAME_FD_Name = "インデックス表示名";
	public final String COMMENT_FD_Name = "ユニーク制約のインデックスの論理名称";
}

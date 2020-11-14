package com.officina_hide.base.model;

/**
 * ユニーク制約項目情報インターフェースクラス<br>
 * @author officine-hide.com
 * @version 1.20
 * @since 2020/11/13
 */
public interface I_FD_UniqueColumn {

	/** ユニーク制約項目情報 : テーブル名 */
	public final String Table_Name = "FD_UniqueColumn";
	/** ユニーク制約項目情報 : テーブル表示名 */
	public final String NAME = "ユニーク制約項目情報";
	/** ユニーク制約項目情報 : テーブル説明 */
	public final String COMMENT = "ユニーク制約項目を管理する為の情報";
	/** ユニーク制約項目情報 : テーブル情報ID */
	public final int TABLE_ID = 105;
	
	/** ユニーク制約項目情報ID */
	public final String COLUMNNAME_FD_UniqueColumn_ID = Table_Name + "_ID";
	public final String NAME_FD_UniqueColumn_ID = "ユニーク制約項目情報ID";
	public final String COMMENT_FD_UniqueColumn_ID = "ユニーク制約項目を識別する為の情報ID";
	/** ユニーク制約インデックス情報ID */
	public final String COLUMNNAME_FD_UniqueIndex_ID = I_FD_UniqueIndex.Table_Name + "_ID";
	public final String NAME_FD_UniqueIndex_ID = I_FD_UniqueIndex.NAME_FD_UniqueIndex_ID;
	public final String COMMENT_FD_UniqueIndex_ID = "ユニーク制約項目が紐づくユニーク制約インデックスの情報ID";
	/** テーブル項目情報ID */
	public final String COLUMNNAME_FD_TableColumn_ID = I_FD_TableColumn.Table_Name + "_ID";
	public final String NAME_FD_TableColumn_ID = I_FD_TableColumn.NAME_FD_TableColumn_ID;
	public final String COMMENT_FD_TableColumn_ID = "ユニーク制約の対象となるテーブル項目の情報ID";
}

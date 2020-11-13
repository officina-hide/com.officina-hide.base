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

}

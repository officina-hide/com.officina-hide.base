package com.officina_hide.base.model;

/**
 * リファレンス情報インターフェース<br>
 * @author officine-hide.com
 * @version 1.00 新規作成
 * @since 2020/10/15
 */
public interface I_FD_Reference {

	/** リファレンス情報 : テーブル名 */
	public final String Table_Name = "FD_Reference";
	/** リファレンス情報 : テーブル表示名 */
	public final String NAME = "リファレンス情報";
	/** リファレンス情報 : テーブル説明 */
	public final String COMMENT = "リファレンスを管理する為の情報";
	/**リファレンス情報 : テーブル情報ID */
	public final int TABLE_ID = 104;
	
	//テーブル項目情報
	/** リファレンス情報ID */
	public final String COLUMNNAME_FD_Reference_ID = Table_Name + "_ID";
	public final String NAME_FD_Reference_ID = "リファレンス情報ID";
	public final String COMMENT_FD_Reference_ID = "リファレンス情報を識別する為の情報ID";
	/** リファレンス名 */
	public final String COLUMNNAME_Reference_Name = "Reference_Name";
	public final String NAME_Reference_Name = "リファレンス名";
	public final String COMMENT_Reference_Name = "リファレンスを使用する時の名称";
	/** リファレンス表示名 */
	public final String COLUMNNAME_FD_Name = "FD_Name";
	public final String NAME_FD_Name = "リファレンス表示名";
	public final String COMMENT_FD_Name = "リファレンスを表す名称";
}

package com.officina_hide.base.model;

/**
 * リファレンスリスト情報インターフェースクラス[Interface class for reference list information.]<br>
 * @author officine-hide.com
 * @version 1.30
 * @since 2021/01/14
 */
public interface I_FD_ReferenceList {
	
	/** リファレンスリスト情報 : テーブル名 */
	public final String Table_Name = "FD_ReferenceList";
	/** リファレンスリスト情報 : テーブル表示名 */
	public final String NAME = "リファレンスリスト情報";
	/** リファレンスリスト情報 : テーブル説明 */
	public final String COMMENT = "リファレンスリストを管理する為の情報";
	/** リファレンスリスト情報 : テーブル情報ID */
	public final int TABLE_ID = 107;

	/** リファレンスリスト情報ID */
	public final String COLUMNNAME_FD_ReferenceList_ID = Table_Name + "_ID";
	public final String NAME_FD_ReferenceList_ID = NAME+"ID";
	public final String COMMENT_FD_ReferenceList_ID = "リファレンスリストを識別するための情報ID";
	/** リファレンス情報ID */
	public final String COLUMNNAME_FD_Reference_ID = I_FD_Reference.Table_Name + "_ID";
	public final String NAME_FD_Reference_ID = I_FD_Reference.NAME+"ID";
	public final String COMMENT_FD_Reference_ID = "リファレンスリスト情報の親となるリファレンスの情報ID";
	/** リスト並び順 */
	public final String COLUMNNAME_FD_Sequence = "FD_Sequence";
	public final String NAME_FD_Sequence = "リスト並び順";
	public final String COMMENT_FD_Sequence = "リスト表示の並び順";
	/** リストコード */
	public final String COLUMNNAME_FD_Code = "FD_Code";
	public final String NAME_FD_Code = "リストコード";
	public final String COMMENT_FD_Code = "リファレンスで使用する情報";
	/** リスト名 */
	public final String COLUMNNAME_FD_Name = "FD_Name";
	public final String NAME_FD_Name = "リスト名";
	public final String COMMENT_FD_Name = "リストコードに対する表示名";
	}

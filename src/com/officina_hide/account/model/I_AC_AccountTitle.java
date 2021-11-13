package com.officina_hide.account.model;

/**
 * 勘定科目情報インターフェースクラス[Account title information interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/11/13 Ver. 1.00
 */
public interface I_AC_AccountTitle {
	
	/** テーブル名 */
	public final static String Table_Name = "AC_AccountTitle";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "勘定科目情報";
	/** テーブル説明 */
	public static final String Table_Comment = "会計機能で使用する勘定科目を管理する。";
	public static final String Table_Comment_Eng = "Manage accounts used in accounting functions.";

	/** 項目 : 勘定科目情報ID */
	public static final String COLUMNNAME_AC_AccountTitle_ID = Table_Name + "_ID";
	public static final String NAME_AC_AccountTitle_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_AC_AccountTitle_ID = "勘定科目を識別する為の情報ID";
	/** 項目 : 勘定科目コード */
	public static final String COLUMNNAME_AC_AccountTitle_Code = "AC_AccountTitle_Code";
	public static final String NAME_AC_AccountTitle_Code = "勘定科目コード";
	public static final String COMMENT_AC_AccountTitle_Code = "勘定科目を識別する為のコード";
}

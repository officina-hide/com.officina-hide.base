package com.officina_hide.account.model;

import com.officina_hide.base.model.I_FD_DB;

/**
 * 仕訳伝票情報インターフェースクラス[Journal slip information interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/12/09 Ver. 1.00
 */
public interface I_AC_JournalSlip extends I_FD_DB {
	
	/** テーブル名 */
	public final static String Table_Name = "AC_JournalSlip";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "仕訳伝票情報";
	/** テーブル説明 */
	public static final String Table_Comment = "会計機能で使用する仕訳伝票を管理する。";
	public static final String Table_Comment_Eng = "Manage journal slips used in accounting functions.";

	/** 項目 : 仕訳伝票情報 */
	public static final String COLUMNNAME_AC_JournalSlip_ID = Table_Name + "_ID";
	public static final String NAME_AC_JournalSlip_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_AC_JournalSlip_ID = "仕訳伝票情報を識別する為の情報ID";
	/** 項目 : 発行日 */
	public static final String COLUMNNAME_AC_IssueDate = "AC_IssueDate";
	public static final String NAME_AC_IssueDate = "発行日";
	public static final String COMMENT_AC_IssueDate = "仕訳伝票の発行日";
	/** 項目 : 貸方勘定科目 */
	public static final String COLUMNNAME_AC_Credit_AccountTitle_ID = "AC_Credit_AccountTitle_ID";
	public static final String NAME_AC_Credit_AccountTitle_ID = "貸方勘定科目情報ID";
	public static final String COMMENT_AC_Credit_AccountTitle_ID = "貸方の勘定科目を識別する為の情報ID（AC_AccountTitle_ID）";
	/** 項目 : 貸方金額 */
	public static final String COLUMNNAME_AC_Credit_Amount = "AC_Credit_Amount";
	public static final String NAME_AC_Credit_Amount = "貸方金額";
	public static final String COMMENT_AC_Credit_Amount = "貸方の金額";
	/** 項目 : 貸方適用 */
	public static final String COLUMNNAME_AC_Credit_Memo = "AC_Credit_Memo";
	public static final String NAME_AC_Credit_Memo = "貸方適用";
	public static final String COMMENT_AC_Credit_Memo = "貸方の内容を記載";
}

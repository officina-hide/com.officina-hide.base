package com.officina_hide.account.model;

import com.officina_hide.base.model.I_FD_DB;

/**
 * 現金出納インターフェースクラス[Cash balance interface class]
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2022/02/10 Ver. 1.00
 */
public interface I_AC_CashBalance extends I_FD_DB {
	
	/** クラスパス */
	public final static String classPath = "./src/com/officina_hide/account/model/";
	public final static String packageUri = "com.officina_hide.account.model";
	
	/** テーブル名 */
	public final static String Table_Name = "AC_CashBalance";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "現金出納情報";
	/** テーブル説明 */
	public static final String Table_Comment = "会計機能で使用する現金出納を管理する。";
	public static final String Table_Comment_Eng = "Manage cash balances used in accounting functions.";
	
	/** 項目 : 現金出納情報ID */
	public static final String COLUMNNAME_AC_CashBalance_ID = Table_Name + "_ID";
	public static final String NAME_AC_CashBalance_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_AC_CashBalance_ID = "現金出納情報を識別する為の情報ID";
	/** 項目 : 発行日 */
	public static final String COLUMNNAME_AC_CashBalance_Date = "AC_CashBalance_Date";
	public static final String NAME_AC_CashBalance_Date = "現金出納日";
	public static final String COMMENT_AC_CashBalance_Date = "現金出納の発生日";
	/** 項目 : 勘定科目 */
	public static final String COLUMNNAME_AC_AccountTitle_ID = "AC_AccountTitle_ID";
	public static final String NAME_AC_AccountTitle_ID = "勘定科目情報ID";
	public static final String COMMENT_AC_AccountTitle_ID = "出納情報の勘定科目を識別する為の情報ID";
}

package com.officina_hide.page.model;

import com.officina_hide.base.model.I_FD_DB;

/**
 * ホームページ基盤情報インターフェースクラス[Homepage base information interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/02 Ver. 1.00
 */
public interface I_FM_Base extends I_FD_DB {

	/** テーブル名 */
	public static final String Table_Name = "FM_Base";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "ホームページ基盤情報";
	/** テーブル説明 */
	public static final String Table_Comment = "ホームページ全体に関する情報を管理する。";
	public static final String Table_Comment_Eng = "Manage information about the entire home page.";

	/** 項目 : ホームページ基盤情報ID */
	public static final String COLUMNNAME_FM_Base_ID = Table_Name + "_ID";
	public static final String NAME_FM_Base_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FM_Base_ID = "ホームページ基盤情報を識別する為の情報ID";
	/** 項目 : メインタイトル */
	public static final String COLUMNNAME_FM_Title = "FM_Title";
	public static final String NAME_FM_Title = "メインタイトル";
	public static final String COMMENT_FM_Title = "ホームページの共通タイトル";
}

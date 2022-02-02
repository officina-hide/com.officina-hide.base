package com.officina_hide.page.model;

import com.officina_hide.base.model.I_FD_DB;

/**
 * HPメニュー情報インターフェースクラス[Menu information interface class]
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2022/02/01 Ver. 1.00
 */
public interface I_FM_Menu extends I_FD_DB {

	/** テーブル名 */
	public static final String Table_Name = "FM_Menu";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "ホームページメニュー情報";
	/** テーブル説明 */
	public static final String Table_Comment = "ホームページで表示するメニューの情報を管理する。";
	public static final String Table_Comment_Eng = "Manage the menu information displayed on the home page.";

	/** 項目 : HPメニュー情報ID */
	public static final String COLUMNNAME_FM_Menu_ID = Table_Name +"_ID";
	public static final String NAME_FM_Menu_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FM_Menu_ID = "ホームページメニュー情報を識別する為のID";
	/** 項目 : HPメニューグループ */
	public static final String COLUMNNAME_FM_MenuGroup_ID = "FM_MenuGroup_ID";
	public static final String NAME_FM_MenuGroup_ID = "ホームページメニューグルーブ情報ID";
	public static final String COMMENT_FM_MenuGroup_ID = "ホームページで使用するメニューの種別をグルーブとしてまとめる為の情報ID<br>"
			+ "属性情報(FM_Menu_Type)で属性を作成する。";
}

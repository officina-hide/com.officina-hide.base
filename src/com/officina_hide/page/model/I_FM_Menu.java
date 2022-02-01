package com.officina_hide.page.model;

import com.officina_hide.base.model.I_FD_DB;

/**
 * メニュー情報インターフェースクラス[Menu information interface class]
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

}

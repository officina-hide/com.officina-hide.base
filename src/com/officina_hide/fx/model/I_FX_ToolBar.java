package com.officina_hide.fx.model;

import com.officina_hide.base.model.I_FD_DB;

/**
 * ツールバー情報インターフェースクラス[Toolbar information interface class]
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/12/02 Ver. 1.00
 */
public interface I_FX_ToolBar extends I_FD_DB {
	
	/** テーブル名 */
	public static final String Table_Name = "FX_ToolBar";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "ツールバー情報";
	/** テーブル説明 */
	public static final String Table_Comment = "画面のツールバーで使用するボタンを管理する。";
	public static final String Table_Comment_Eng = "Manage the buttons used on the screen toolbar.";
	/** テーブル情報ID */
	public final static long Table_ID = 206;

}

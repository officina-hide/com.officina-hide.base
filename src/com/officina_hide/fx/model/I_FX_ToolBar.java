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
	
	/** 項目 : ツールバー情報 */
	public final static String COLUMNNAME_FX_ToolBar_ID = Table_Name + "_ID";
	public final static String NAME_FX_ToolBar_ID = Table_Disp_Name + "ID";
	public final static String COMMENT_FX_ToolBar_ID = "テーブル情報を識別する為の情報ID";
	/** 項目 : ツールバー名 */
	public final static String COLUMNNAME_FX_ToolBar_Name = Table_Name + "_Name";
	public final static String NAME_FX_ToolBar_Name = "ツールバー名称";
	public final static String COMMENT_FX_ToolBar_Name = "ツールバーを識別する為の名称";

	/** ボタン名 : 保存 */
	public final static String TB_Save = "TB_Save";
	public final static String NAME_TB_Save = "保存";
	/** ボタン名 : 新規 */
	public final static String TB_New = "TB_New";
	public final static String NAME_TB_New = "新規";
}
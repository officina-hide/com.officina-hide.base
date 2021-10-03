package com.officina_hide.fx.model;

/**
 * 画面タブ情報インターフェースクラス[Screen Tab Information Interface Class]<br>
 * @author officina-hide.net
 * @version 1.00
 * @since 2021/09/13
 */
public interface I_FX_Tab {

	/** テーブル名 */
	public static final String Table_Name = "FX_Tab";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "FXタブ情報";
	/** テーブル説明 */
	public static final String Table_Comment = "パッケージで使用するFX画面でテーブルに紐づく情報を照会・更新する画面。";
	public static final String Table_Comment_Eng = "A screen to inquire and update the information "
			+ "associated with the table on the FX screen used in the package.";
	/** テーブル情報ID */
	public final static long Table_ID = 202;

	/** 項目 : FXタブ情報ID */
	public static final String COLUMNNAME_FX_Tab_ID = Table_Name + "_ID";
	public static final String NAME_FX_Tab_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FX_Tab_ID = "FXタブ情報をテーブル識別するための情報ID";
	/** 項目 : FXタブ情報識別名 */
	public static final String COLUMNNAME_FX_Tab_Name = "FX_Tab_Name";
	public static final String NAME_FX_Tab_Name = "FXタブ情報識別名";
	public static final String COMMENT_FX_Tab_Name = "FXタブ情報を識別する為の名称";
	/** 項目 : FX画面基盤情報ID */
	public static final String COLUMNNAME_FX_View_ID = I_FX_View.COLUMNNAME_FX_View_ID;
	public static final String NAME_FX_View_ID = I_FX_View.NAME_FX_View_ID;
}

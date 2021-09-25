package com.officina_hide.fx.model;

/**
 * Fx画面基本情報インターフェースクラス[Fx screen basic information interface class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/08/23
 */
public interface I_FX_View {
	/** 基本定数 */
	/** テーブル名 */
	public static final String Table_Name = "FX_View";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "FX_画面情報";
	/** テーブル説明 */
	public static final String Table_Comment = "パッケージで使用するFX画面のヘッダー情報を管理する。";
	public static final String Table_Comment_Eng = "Manage the header information of the FX screen used in the package.";
	/** テーブル情報ID */
	public final static long Table_ID = 201;
	
	/** 項目 : FX画面情報ID */
	public static final String COLUMNNAME_FX_View_ID = Table_Name + "_ID";
	public static final String NAME_FX_View_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FX_View_ID = "FX画面情報を識別するための情報ID";
	/** 項目 : FX画面識別名 */
	public static final String COLUMNNAME_FX_View_Name = "FX_View_Name";
	public static final String NAME_FX_View_Name = "FX画面識別名";
	public static final String COMMENT_FX_View_Name = "FX画面情報を識別する為の名称";
}

package com.officina_hide.fx.model;

import com.officina_hide.base.model.I_FD_TypeItem;

/**
 * メニュー情報インターフェースクラス[Menu information interfaces class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/16 Ver. 1.00
 */
public interface I_FX_Menu {
	
	/** テーブル名 */
	public static final String Table_Name = "FX_Menu";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "メニュー情報";
	/** テーブル説明 */
	public static final String Table_Comment = "パッケージで処理する機能をメニュー情報として管理する。";
	public static final String Table_Comment_Eng = "Manage the functions processed by the package as menu information.";
	/** テーブル情報ID */
	public final static long Table_ID = 205;
	
	/** 項目 : メニュー情報ID */
	public static final String COLUMNNAME_FX_Menu_ID = Table_Name + "_ID";
	public static final String NAME_FX_Menu_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FX_Menu_ID = "メニュー情報を識別するためのID";
	/** 項目 : メニュー名 */
	public static final String COLUMNNAME_FX_Menu_Name = "FX_Menu_Name";
	public static final String NAME_FX_Menu_Name = "メニュー識別名";
	public static final String COMMENT_FX_Menu_Name = "メニューを識別する為の名称";
	/** 項目 : 遷移対象情報ID */
	public static final String COLUMNNAME_FX_Target_ID = "FX_Target_ID";
	public static final String NAME_FX_Target_ID = "遷移対象情報ID";
	public static final String COMMENT_FX_Target_ID = "メニュー選択時に遷移する先の情報ID。"
			+ "遷移する対象はメニュー種別で指定する。";
	/** 項目 : メニュー種別(属性項目情報ID) */
	public static final String COLUMNNAME_FD_TypeItem_ID = I_FD_TypeItem.COLUMNNAME_FD_TypeItem_ID;
	public static final String NAME_FD_TypeItem_ID = I_FD_TypeItem.NAME_FD_TypeItem_ID;
	
}

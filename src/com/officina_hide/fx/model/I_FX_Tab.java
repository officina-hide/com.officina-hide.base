package com.officina_hide.fx.model;

/**
 * 画面タブ情報インターフェースクラス[Screen Tab Information Interface Class]<br>
 * @author officina-hide.net
 * @version 1.00
 * @since 2021/09/13
 */
public interface I_FX_Tab {

	/** テーブル識別名 */
	public final static String Table_Name = "FX_Tab";
	/** テーフルID */
	public final static int Table_ID = 202;

	/** 項目 */
	/** タブ情報ID */
	public final static String COLUMNNAME_FX_Tab_ID = Table_Name + "_ID";
	/** タブ階層番号 */
	public final static String COLUMNNAME_FX_Tab_Level = "FX_Tab_Level";
}

package com.officina_hide.fx.model;

/**
 * Fx画面項目インターフェースクラス[Fx screen item interface class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/08/28
 */
public interface I_Fx_Fields {

	/** テーブル識別名 */
	public final static String Table_Name = "FX_Field";
	/** テーフルID */
	public final static int Table_ID = 202;
	
	/** 項目情報 */
	/** 画面項目情報ID */
	public final static String COLUMNNAME_Fx_Fields_ID = Table_Name + "_ID";
	/** 画面項目識別名 */
	public final static String COLUMNNAME_Fx_Fields_Name = Table_Name + "_Name";
	/** 画面基本情報ID */
	public final static String COLUMNNAME_Fx_View_ID = I_Fx_View.Table_Name + "_ID";
}
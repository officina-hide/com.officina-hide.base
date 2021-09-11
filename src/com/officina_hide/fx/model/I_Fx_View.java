package com.officina_hide.fx.model;

import com.officina_hide.base.model.I_FD_Table;

/**
 * Fx画面基本情報インターフェースクラス[Fx screen basic information interface class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/08/23
 */
public interface I_Fx_View {
	/** 基本定数 */
	/** テーブル名 */
	public static final String Table_Name = "FX_View";
	/** テーブル情報ID */
	public static final int Table_Id = 201;
	/** 項目関連定数 */
	public static final String COLUMNNAME_Fx_View_ID = Table_Name + "_ID";
	public static final String COLUMNNAME_Fx_View_Name = "FX_View_Name";
	public static final String COLUMNNAME_FD_Table_ID = I_FD_Table.COLUMNNAME_FD_Table_ID;
	public static final String COLUMNNAME_FD_Name = "FD_Name";
}

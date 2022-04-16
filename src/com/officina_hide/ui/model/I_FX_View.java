package com.officina_hide.ui.model;

import com.officina_hide.base.model.I_FD_DB;

/**
 * 画面情報インターフェースクラス[View information interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/04/07 Ver. 1.00
 */
public interface I_FX_View extends I_FD_DB {
	
	/** テーブル名 */
	public static final String Table_Name = "FX_View";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "画面情報";
	
	/** 項目 : 画面情報ID */
	public static final String COLUMNNAME_FX_View_ID = Table_Name + "_ID";
	public static final String NAME_FX_View_ID = Table_Disp_Name + "ID";
	/** 項目 : 画面コード */
	public static final String COLUMNNAME_FX_View_Code = "FX_View_Code";
	public static final String NAME_FX_View_Code = "画面コード";
	public static final String COMMENT_FX_View_Code = "画面を識別する為のコード";
	public static final int SIZE_FX_View_Name = 100;
	
	/** テーブル削除用SQL */
	public static final String Table_Drop_SQL =
			"DROP TABLE IF EXISTS " + Table_Name;

	/** テーブル生成用SQL */
	public static final String Table_Create_SQL = 
			"CREATE TABLE IF NOT EXISTS " + Table_Name
			+" ("
				+ COLUMNNAME_FX_View_ID + ID_KEY_TYPE
					+ COMMENT + FD_SQ + NAME_FX_View_ID + FD_SQ + ","
				+ COLUMNNAME_FX_View_Code + VARCHAR.replaceAll("n", Integer.toString(SIZE_FX_View_Name))
					+ UNIQUE + NOT_NULL
					+ COMMENT + FD_SQ + NAME_FX_View_Code + FD_SQ + ","
				+ COLUMNNAME_FD_Name + VARCHAR.replaceAll("n", Integer.toString(SIZE_FD_Name))
					+ COMMENT + FD_SQ + NAME_FD_Name + FD_SQ + ""
			+") "
			+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;

}
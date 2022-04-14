package com.officina_hide.ui.model;

import com.officina_hide.base.model.I_FD_DB;

/**
 * 画面項目インターフェースクラス[Screen item interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/04/13 Ver. 1.00
 */
public interface I_FX_Field extends I_FD_DB {
	
	/** テーブル名 */
	public static final String Table_Name = "FX_Field";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "画面項目情報";
	
	/** 項目 : 画面項目情報ID */
	public static final String COLUMNNAME_FX_Field_ID = Table_Name + "_ID";
	public static final String NAME_FX_Field_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FX_Field_ID = "画面項目を識別する為の情報ID";
	/** 項目 : 画面項目コード */
	public static final String COLUMNNAME_FX_Field_Code = "FX_Field_Code";
	public static final String NAME_FX_Field_Code = "画面項目コード";
	public static final String COMMENT_FX_Field_Code = "画面項目を一意に扱う為のコード";
	public static final int SIZE_FX_Field_Code = 100;
	/** 項目 : 画面情報ID */
	public static final String COLUMNNAME_FX_View_ID = I_FX_View.COLUMNNAME_FX_View_ID;
	/** 項目 : 名前(共通) */
	/** 項目 : 画面項目種別(ID) */
	public static final String COLUMNNAME_FX_Field_Type_ID = "FX_Field_Type_ID";
	public static final String NAME_FX_Field_Type_ID = "画面項目種別(ID)";
	public static final String COMMENT_FX_Field_Type_ID = "画面項目の種類を識別する為の情報ID<br>"
			+ "リファレンス情報のグループ("+FD_Reference_Group_Field+")で設定する。";
	/** 項目 : 画面項目行番号 */
	public static final String COLUMNNAME_FX_Field_RowNo = "FX_Field_RowNo";
	public static final String NAME_FX_Field_RowNo = "画面項目行番号";
	public static final String COMMENT_FX_Field_RowNo = "画面項目の画面上の行位置";

	/** テーブル削除用SQL */
	public static final String Table_Drop_SQL =
			"DROP TABLE IF EXISTS " + Table_Name;

	/** テーブル生成用SQL */
	public static final String Table_Create_SQL = 
			"CREATE TABLE IF NOT EXISTS " + Table_Name
			+" ("
				+ COLUMNNAME_FX_Field_ID + ID_KEY_TYPE
					+ COMMENT + FD_SQ + NAME_FX_Field_ID + FD_SQ + ","
				+ COLUMNNAME_FX_Field_Code + VARCHAR.replaceFirst("n", Integer.toString(SIZE_FX_Field_Code))
					+ UNIQUE + NOT_NULL
					+ COMMENT + FD_SQ + NAME_FX_Field_Code + FD_SQ + ","
				+ COLUMNNAME_FX_View_ID + ID_TYPE
					+ COMMENT + FD_SQ + I_FX_View.NAME_FX_View_ID + FD_SQ + ","
				+ COLUMNNAME_FD_Name + VARCHAR.replaceAll("n", Integer.toString(SIZE_FD_Name))
					+ COMMENT + FD_SQ + NAME_FD_Name + FD_SQ + ","
				+ COLUMNNAME_FX_Field_Type_ID + ID_TYPE
					+ COMMENT + FD_SQ + NAME_FX_Field_Type_ID + FD_SQ + ","
				+ COLUMNNAME_FX_Field_RowNo + UNSIGNED_INT
					+ COMMENT + FD_SQ + NAME_FX_Field_RowNo + FD_SQ + ""
			+") "
			+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;
}

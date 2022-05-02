package com.officina_hide.medical.model;

import com.officina_hide.base.model.I_FD_DB;

/**
 * 検査情報インターフェースクラス[Inspection information interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/05/02 Ver. 1.00
 */
public interface I_FM_InspectionData extends I_FD_DB {
	
	/** テーブル名 */
	public static final String Table_Name = "FM_InspectionData";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "検査情報";

	/** 項目 : 検査情報ID */
	public final String COLUMNNAME_FM_InspectionData_ID = Table_Name + "_ID";
	public final String NAME_FM_InspectionData_ID = Table_Disp_Name + "ID";
	/** 項目 : 検査日時 */
	public final String COLUMNNAME_FM_Inspection_Date = "FM_Inspection_Date";
	public final String NAME_FM_Inspection_Date = "検査日時";
	/** 項目 : 検査タイトル */
	public final String COLUMNNAME_FM_Inspection_Title = "FM_Inspection_Title";
	public final String NAME_FM_Inspection_Title = "検査タイトル";
	public final int SIZE_FM_Inspection_Title = 200;

	/** テーブル削除用SQL */
	public static final String Table_Drop_SQL =
			"DROP TABLE IF EXISTS " + Table_Name;

	/** テーブル生成用SQL */
	public static final String Table_Create_SQL = 
			"CREATE TABLE IF NOT EXISTS " + Table_Name
			+" ("
				+ COLUMNNAME_FM_InspectionData_ID + ID_KEY_TYPE
					+ COMMENT + FD_SQ + NAME_FM_InspectionData_ID + FD_SQ + ","
				+ COLUMNNAME_FM_Inspection_Date + DATETIME
					+ DETAULT_CURRENT_DATE
					+ COMMENT + FD_SQ + NAME_FM_Inspection_Date + FD_SQ + ","
				+ COLUMNNAME_FM_Inspection_Title + VARCHAR.replaceAll("n", Integer.toString(SIZE_FM_Inspection_Title))
					+ COMMENT + FD_SQ + NAME_FM_Inspection_Title + FD_SQ + ""
			+") "
			+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;

}

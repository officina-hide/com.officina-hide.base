package com.officina_hide.project_ad.model;

import com.officina_hide.base.model.I_FD_DB;

/**
 * プロジェクト情報インターフェースクラス[Project information interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[Create new]
 * @since 2022/04/05 Ver. 1.00
 */
public interface I_FD_Project extends I_FD_DB {
	
	/** テーブル名 */
	public static final String Table_Name = "FD_Project";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "プロジェクト情報";
	
	/** 項目 : プロジェクト情報ID */
	public static final String COLUMNNAME_FD_Project_ID = Table_Name + "_ID";
	public static final String NAME_FD_Project_ID = Table_Disp_Name + "ID";
	
	/** テーブル削除用SQL */
	public static final String Table_Drop_SQL =
			"DROP TABLE IF EXISTS " + Table_Name;

	/** テーブル生成用SQL */
	public static final String Table_Create_SQL = 
			"CREATE TABLE IF NOT EXISTS " + Table_Name
			+" ("
				+ COLUMNNAME_FD_Project_ID + ID_KEY_TYPE 
					+ COMMENT + FD_SQ + NAME_FD_Project_ID + FD_SQ
			+") "
			+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;
}

package com.officina_hide.project.model;

import com.officina_hide.base.model.I_FD_Column;
import com.officina_hide.base.model.I_FD_DataDictionary;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.I_FD_TypeItem;

/**
 * プロジェクト情報インターフェースクラス[Project information interface class]
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2022/02/24 Ver. 1.00
 */
public interface I_FD_Project {
	/** クラスパス */
	public final static String classPath = "./src/com/officina_hide/project/model/";
	public final static String packageUri = "com.officina_hide.project.model";

	/** テーブル名 */
	public final static String Table_Name = "FD_Project";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "プロジェクト情報";
	/** テーブル説明 */
	public static final String Table_Comment = "プロジェクトに関する情報を管理する。";
	public static final String Table_Comment_Eng = "Manage information about the project.";

	/** 項目 : プロジェクト情報ID */
	public static final String COLUMNNAME_FD_Project_ID = Table_Name + "_ID";
	public static final String NAME_FD_Project_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FD_Project_ID = "プロジェクト情報を識別する為の情報ID";
	public static final String COLUMNDATA_FD_Project_ID = 
			I_FD_Column.COLUMNNAME_FD_DataDictionary_ID 
				+ ":@getId:" + I_FD_DataDictionary.Table_Name + ":" 
					+ I_FD_DataDictionary.COLUMNNAME_FD_DataDictionary_Name + ":" + COLUMNNAME_FD_Project_ID
			+ "," + I_FD_Column.COLUMNNAME_FD_TypeItem_ID
				+ ":@getId:" + I_FD_TypeItem.Table_Name + ":"
					+ I_FD_TypeItem.COLUMNNAME_FD_TypeItem_Name + ":" + I_FD_TypeItem.FD_ITEM_ID
			+ "," + I_FD_Column.COLUMNNAME_FD_Table_ID
				+ ":@getId:" + I_FD_Table.Table_Name + ":"
					+ I_FD_Table.COLUMNNAME_FD_Table_Name + ":" + Table_Name
			+ "," + I_FD_Column.COLUMNNAME_FD_IS_Null + ":" + "N"
			+ "," + I_FD_Column.COLUMNNAME_FD_IS_Key + ":" + "Y"
			+ "," + I_FD_Column.COLUMNNAME_FD_Default + ":" + "0";
	/** 項目 : プロジェクト名 */
	public static final String COLUMNNAME_FD_Project_Name = Table_Name + "_Name";
	public static final String NAME_FD_Project_Name = "プロジェクト名";
	public static final String COMMENT_FD_Project_Name = "プロジェクトを識別する為の名称";
//	public static final String COLUMNDATA_FD_Project_Name = 
	
}

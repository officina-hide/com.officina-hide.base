package com.officina_hide.ui.model;

import com.officina_hide.base.model.I_FD_Column;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_Table;

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
	/** 項目 : 名前（FD_Nameを使用） */
	
	/** 登録用SQL : テーブル情報 */
	public final String Entry_FD_Table = 
			I_FD_Table.COLUMNNAME_FD_Table_Code+":"+Table_Name+","
			+ I_FD_Table.COLUMNNAME_FD_Name+":"+Table_Disp_Name;
	/** 登録用SQL : 採番情報 */
	public final String Entry_FD_Number = 
			I_FD_Numbering.COLUMNNAME_FD_Numbering_ID+":@getID:"+Table_Name+","
			+ I_FD_Numbering.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Numbering.COLUMNNAME_FD_InitialNumber+":"+"100001";
	
	/** 登録用情報 : テーブル項目情報 : 画面情報ID */
	public final String Entry_FD_Column_FV_View_ID =
			I_FD_Column.COLUMNNAME_FD_Column_Code+":"+COLUMNNAME_FX_View_ID+","
			+ I_FD_Column.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Name+":"+NAME_FX_View_ID+","
			+ I_FD_Column.COLUMNNAME_FD_ColumnType_ID+":@getItemID:"+FD_Item_ID+","
			+ I_FD_Column.COLUMNNAME_FD_Column_DefaultValue+":"+ID_ZERO+"";
	/** 登録用情報 : テーブル項目情報 : 画面コード */
	public final String Entry_FD_Column_FV_View_Code =
			I_FD_Column.COLUMNNAME_FD_Column_Code+":"+COLUMNNAME_FX_View_Code+","
			+ I_FD_Column.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Name+":"+NAME_FX_View_Code+","
			+ I_FD_Column.COLUMNNAME_FD_ColumnType_ID+":@getItemID:"+FD_Item_String+","
			+ I_FD_Column.COLUMNNAME_FD_Column_Size+":"+SIZE_FX_View_Name+","
			+ I_FD_Column.COLUMNNAME_FD_IS_Unoque+":"+FD_YES+"";
	/** 登録用情報 : テーブル項目情報 : 名前 */
	public final String Entry_FD_Column_FD_Name =
			I_FD_Column.COLUMNNAME_FD_Column_Code+":"+COLUMNNAME_FD_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Name+":"+NAME_FD_Name+","
			+ I_FD_Column.COLUMNNAME_FD_ColumnType_ID+":@getItemID:"+FD_Item_String+","
			+ I_FD_Column.COLUMNNAME_FD_Column_Size+":"+ SIZE_FD_Name+"";
	
//	
//	/** テーブル削除用SQL */
//	public static final String Table_Drop_SQL =
//			"DROP TABLE IF EXISTS " + Table_Name;
//
//	/** テーブル生成用SQL */
//	public static final String Table_Create_SQL = 
//			"CREATE TABLE IF NOT EXISTS " + Table_Name
//			+" ("
//				+ COLUMNNAME_FX_View_ID + ID_KEY_TYPE
//					+ COMMENT + FD_SQ + NAME_FX_View_ID + FD_SQ + ","
//				+ COLUMNNAME_FX_View_Code + VARCHAR.replaceAll("n", Integer.toString(SIZE_FX_View_Name))
//					+ UNIQUE + NOT_NULL
//					+ COMMENT + FD_SQ + NAME_FX_View_Code + FD_SQ + ","
//				+ COLUMNNAME_FD_Name + VARCHAR.replaceAll("n", Integer.toString(SIZE_FD_Name))
//					+ COMMENT + FD_SQ + NAME_FD_Name + FD_SQ + ""
//			+") "
//			+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;

}

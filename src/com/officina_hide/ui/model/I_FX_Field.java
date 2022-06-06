package com.officina_hide.ui.model;

import com.officina_hide.base.model.I_FD_Column;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_Table;

/**
 * 画面項目インターフェースクラス[Screen item interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/04/13 Ver. 1.00
 */
public interface I_FX_Field extends I_FD_DB {
	
	/** テーブル名 */
	public final String Table_Name = "FX_Field";
	/** テーブル表示名 */
	public final String Table_Disp_Name = "画面項目情報";
	
	/** 項目 : 画面項目情報ID */
	public static final String COLUMNNAME_FX_Field_ID = Table_Name + "_ID";
	public static final String NAME_FX_Field_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FX_Field_ID = "画面項目を識別する為の情報ID";
	/** 項目 : 画面項目コード */
	public static final String COLUMNNAME_FX_Field_Code = "FX_Field_Code";
	public static final String NAME_FX_Field_Code = "画面項目コード";
	public static final String COMMENT_FX_Field_Code = "画面項目を一意に扱う為のコード";
	public static final int SIZE_FX_Field_Code = 20;
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
	
	/** 登録用SQL : テーブル情報 */
	public final String Entry_FD_Table = 
			I_FD_Table.COLUMNNAME_FD_Table_Code+":"+Table_Name+","
			+ I_FD_Table.COLUMNNAME_FD_Name+":"+Table_Disp_Name;
	/** 登録用SQL : 採番情報 */
	public final String Entry_FD_Number = 
			I_FD_Numbering.COLUMNNAME_FD_Numbering_ID+":@getID:"+Table_Name+","
			+ I_FD_Numbering.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Numbering.COLUMNNAME_FD_InitialNumber+":"+"100001";
	
	/** 登録用情報 : テーブル項目情報 : 画面項目情報ID */
	public final String Entry_FD_Column_FX_Field_ID =
			I_FD_Column.COLUMNNAME_FD_Column_Code+":"+COLUMNNAME_FX_Field_ID+","
			+ I_FD_Column.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Name+":"+NAME_FX_Field_ID+","
			+ I_FD_Column.COLUMNNAME_FD_ColumnType_ID+":@getItemID:"+FD_Item_ID+","
			+ I_FD_Column.COLUMNNAME_FD_Column_DefaultValue+":"+ID_ZERO+"";
	/** 登録用情報 : テーブル項目情報 : 画面項目コード */
	public final String Entry_FD_Column_FX_Field_Code =
			I_FD_Column.COLUMNNAME_FD_Column_Code+":"+COLUMNNAME_FX_Field_Code+","
			+ I_FD_Column.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Name+":"+NAME_FX_Field_Code+","
			+ I_FD_Column.COLUMNNAME_FD_ColumnType_ID+":@getItemID:"+FD_Item_String+","
			+ I_FD_Column.COLUMNNAME_FD_Column_Size + ":" + SIZE_FX_Field_Code + ","
			+ I_FD_Column.COLUMNNAME_FD_IS_Unoque + ":" + FD_YES + "";
	/** 登録用情報 : テーブル項目情報 : 画面情報ID */
	public final String Entry_FD_Column_FX_View_ID =
			I_FD_Column.COLUMNNAME_FD_Column_Code+":"+I_FX_View.COLUMNNAME_FX_View_ID+","
			+ I_FD_Column.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Name+":"+ I_FX_View.NAME_FX_View_ID+","
			+ I_FD_Column.COLUMNNAME_FD_ColumnType_ID+":@getItemID:"+FD_Item_ID+","
			+ I_FD_Column.COLUMNNAME_FD_Column_DefaultValue+":"+ID_ZERO+"";
	
//
//	/** テーブル生成用SQL */
//	public static final String Table_Create_SQL = 
//			"CREATE TABLE IF NOT EXISTS " + Table_Name
//			+" ("
//				+ COLUMNNAME_FX_Field_ID + ID_KEY_TYPE
//					+ COMMENT + FD_SQ + NAME_FX_Field_ID + FD_SQ + ","
//				+ COLUMNNAME_FX_Field_Code + VARCHAR.replaceFirst("n", Integer.toString(SIZE_FX_Field_Code))
//					+ UNIQUE + NOT_NULL
//					+ COMMENT + FD_SQ + NAME_FX_Field_Code + FD_SQ + ","
//				+ COLUMNNAME_FX_View_ID + ID_TYPE
//					+ COMMENT + FD_SQ + I_FX_View.NAME_FX_View_ID + FD_SQ + ","
//				+ COLUMNNAME_FD_Name + VARCHAR.replaceAll("n", Integer.toString(SIZE_FD_Name))
//					+ COMMENT + FD_SQ + NAME_FD_Name + FD_SQ + ","
//				+ COLUMNNAME_FX_Field_Type_ID + ID_TYPE
//					+ COMMENT + FD_SQ + NAME_FX_Field_Type_ID + FD_SQ + ","
//				+ COLUMNNAME_FX_Field_RowNo + UNSIGNED_INT
//					+ COMMENT + FD_SQ + NAME_FX_Field_RowNo + FD_SQ + ""
//			+") "
//			+"ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=" + FD_SQ + Table_Disp_Name + FD_SQ;
} 

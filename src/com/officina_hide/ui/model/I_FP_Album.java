package com.officina_hide.ui.model;

import com.officina_hide.base.model.I_FD_Column;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_Table;

/**
 * アルバム情報インターフェースクラス[Album information interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/06/01 Ver. 1.00
 */
public interface I_FP_Album extends I_FD_DB {
	
	/** テーブル名 */
	public static final String Table_Name = "FP_Album";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "アルバム情報";

	/** 項目 : アルバム情報ID */
	public final String COLUMNNAME_FP_Album_ID = Table_Name + "_ID";
	public final String NAME_FP_Album_ID = Table_Disp_Name+"ID";
	/** 項目 : アルバムコード */
	public final String COLUMNNAME_FP_Album_Code = "FP_Album_Code"; 
	public final String NAME_FP_Album_Code = "アルバムコード";
	public final String COMMENT_FP_Album_Code = "アルバムを識別する為のコード";
	public final int SIZE_FP_Album_Code = 20;
	/** 項目 : 名前(FD_Name使用) */
	/** 項目 : 登録日(FD_Created使用) */
	
	/** 登録用SQL : テーブル情報 */
	public final String Entry_FD_Table = 
			I_FD_Table.COLUMNNAME_FD_Table_Code+":"+Table_Name+","
			+ I_FD_Table.COLUMNNAME_FD_Name+":"+Table_Disp_Name;
	/** 登録用SQL : 採番情報 */
	public final String Entry_FD_Number = 
			I_FD_Numbering.COLUMNNAME_FD_Numbering_ID+":@getID:"+Table_Name+","
			+ I_FD_Numbering.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Numbering.COLUMNNAME_FD_InitialNumber+":"+"100001";
	
	/** 登録用情報 : テーブル項目情報 : アルバム情報ID */
	public final String Entry_FD_Column_FP_Album_ID =
			I_FD_Column.COLUMNNAME_FD_Column_Code+":"+COLUMNNAME_FP_Album_ID+","
			+ I_FD_Column.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Name+":"+NAME_FP_Album_ID+","
			+ I_FD_Column.COLUMNNAME_FD_ColumnType_ID+":@getItemID:"+FD_Item_ID+","
			+ I_FD_Column.COLUMNNAME_FD_Column_DefaultValue+":"+ID_ZERO+"";
	/** 登録用情報 : テーブル項目情報 : アルバムコード */
	public final String Entry_FD_Column_FP_Album_Code =
			I_FD_Column.COLUMNNAME_FD_Column_Code+":"+COLUMNNAME_FP_Album_Code+","
			+ I_FD_Column.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Name+":"+NAME_FP_Album_Code+","
			+ I_FD_Column.COLUMNNAME_FD_ColumnType_ID+":@getItemID:"+FD_Item_String+","
			+ I_FD_Column.COLUMNNAME_FD_Column_Size+":"+SIZE_FP_Album_Code+"";
	/** 登録用情報 : テーブル項目情報 : 名前 */
	public final String Entry_FD_Column_FD_Name =
			I_FD_Column.COLUMNNAME_FD_Column_Code+":"+COLUMNNAME_FD_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Name+":"+NAME_FD_Name+","
			+ I_FD_Column.COLUMNNAME_FD_ColumnType_ID+":@getItemID:"+FD_Item_String+","
			+ I_FD_Column.COLUMNNAME_FD_Column_Size+":"+ SIZE_FD_Name+"";
	/** 登録用情報 : テーブル項目情報 : 登録日 */
	public final String Entry_FD_Column_FD_Created =
			I_FD_Column.COLUMNNAME_FD_Column_Code+":"+COLUMNNAME_FD_Created+","
			+ I_FD_Column.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Name+":"+NAME_FD_Created+","
			+ I_FD_Column.COLUMNNAME_FD_ColumnType_ID+":@getItemID:"+FD_Item_Date+"";
	
	/** 画面用情報 : FV_Album_Entry 画面情報 */
	public final String Entry_FX_View_FV_Album_Entry = 
			I_FX_View.COLUMNNAME_FX_View_Code+":FV_Album_Entry"+","
			+ I_FX_View.COLUMNNAME_FD_Name+":アルバム情報画面"+","
			+ I_FX_View.COLUMNNAME_FD_Table_ID+":@getID:"+I_FP_Album.Table_Name+" ";
}

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
	public final String COLUMNNAME_FX_Field_ID = Table_Name + "_ID";
	public final String NAME_FX_Field_ID = Table_Disp_Name + "ID";
	public final String COMMENT_FX_Field_ID = "画面項目を識別する為の情報ID";
	/** 項目 : 画面項目コード */
	public final String COLUMNNAME_FX_Field_Code = "FX_Field_Code";
	public final String NAME_FX_Field_Code = "画面項目コード";
	public final String COMMENT_FX_Field_Code = "画面項目を一意に扱う為のコード";
	public final int SIZE_FX_Field_Code = 20;
	/** 項目 : 画面情報ID */
	public final String COLUMNNAME_FX_View_ID = I_FX_View.COLUMNNAME_FX_View_ID;
	/** 項目 : 名前(共通) */
	/** 項目 : 画面項目種別(ID) */
	public final String COLUMNNAME_FX_Field_Type_ID = "FX_Field_Type_ID";
	public final String NAME_FX_Field_Type_ID = "画面項目種別(ID)";
	public final String COMMENT_FX_Field_Type_ID = "画面項目の種類を識別する為の情報ID<br>"
			+ "リファレンス情報のグループ("+FD_REFGROUP_FIELD+")で設定する。";
	/** 項目 : 画面項目行番号 */
	public final String COLUMNNAME_FX_Field_RowNo = "FX_Field_RowNo";
	public final String NAME_FX_Field_RowNo = "画面項目行番号";
	public final String COMMENT_FX_Field_RowNo = "画面項目の画面上の行位置";
	/** 項目 : テーブル項目情報 */
	public final String COLUMNNAME_FD_Column_ID = I_FD_Column.COLUMNNAME_FD_Column_ID;
	public final String NAME_FD_Column_ID = I_FD_Column.NAME_FD_Column_ID;
	
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
	/** 登録用情報 : テーブル項目情報 : 名前 */
	public final String Entry_FD_Column_FD_Name =
			I_FD_Column.COLUMNNAME_FD_Column_Code+":"+COLUMNNAME_FD_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Name+":"+NAME_FD_Name+","
			+ I_FD_Column.COLUMNNAME_FD_ColumnType_ID+":@getItemID:"+FD_Item_String+","
			+ I_FD_Column.COLUMNNAME_FD_Column_Size + ":" + SIZE_FD_Name + "";
	/** 登録用情報 : テーブル項目情報 : 画面項目種別(ID) */
	public final String Entry_FD_Column_FX_Field_Type_ID =
			I_FD_Column.COLUMNNAME_FD_Column_Code+":"+ COLUMNNAME_FX_Field_Type_ID+","
			+ I_FD_Column.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Name+":"+ NAME_FX_Field_Type_ID +","
			+ I_FD_Column.COLUMNNAME_FD_ColumnType_ID+":@getItemID:"+FD_Item_ID+","
			+ I_FD_Column.COLUMNNAME_FD_Column_DefaultValue+":"+ID_ZERO+"";
	/** 登録用情報 : テーブル項目情報 : テーブル項目情報ID */
	public final String Entry_FD_Column_FD_Column_ID =
			I_FD_Column.COLUMNNAME_FD_Column_Code+":"+ COLUMNNAME_FD_Column_ID+","
			+ I_FD_Column.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Name+":"+ NAME_FD_Column_ID +","
			+ I_FD_Column.COLUMNNAME_FD_ColumnType_ID+":@getItemID:"+FD_Item_ID+","
			+ I_FD_Column.COLUMNNAME_FD_Column_DefaultValue+":"+ID_ZERO+"";
	
	/** 画面項目リスト抽出用SQL文 */
	public final String SQL_GET_FIELD_LIST =
			"SELECT * FROM " + Table_Name + " "
			+ "WHERE " + COLUMNNAME_FX_View_ID + " = ? ";
	
} 

package com.officina_hide.medical.model;

import com.officina_hide.base.model.I_FD_Column;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.model.I_FD_Table;

/**
 * 検査項目情報インターフェースクラス[Inspection item information interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/05/09 Ver. 1.00
 */
public interface I_FM_InspectionItem extends I_FD_DB {
	
	/** テーブル名 */
	public static final String Table_Name = "FM_InspectionItem";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "検査項目情報";

	/** 項目 : 検査項目情報ID */
	public final String COLUMNNAME_FM_InspectionItem_ID = Table_Name + "_ID";
	public final String NAME_FM_InspectionItem_ID = Table_Disp_Name + "ID";
	public final String COMMENT_FM_InspectionItem_ID = "検査項目を識別する為の情報ID";
	/** 項目 : 検査項目コード */
	public final String COLUMNNAME_FM_InspectionItem_Code = "FM_InspectionItem_Code";
	public final String NAME_FM_InspectionItem_Code = "検査項目コード";
	public final String COMMENT_FM_InspectionItem_Code = "検査項目を識別する為のコード";
	public final int SIZE_FM_InspectionItem_Code = 100;
	/** 項目 : 検査項目名(FD_Name使用) */
	
	/** 登録用SQL : テーブル情報 */
	public final String Entry_FD_Table = 
			I_FD_Table.COLUMNNAME_FD_Table_Code+":"+Table_Name+","
			+ I_FD_Table.COLUMNNAME_FD_Name+":"+Table_Disp_Name;
	/** 登録用情報 : テーブル項目情報 : 検査項目情報ID */
	public final String Entry_FD_Column_FM_InspectionItem_ID =
			I_FD_Column.COLUMNNAME_FD_Column_Code+":"+COLUMNNAME_FM_InspectionItem_ID+","
			+ I_FD_Column.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Name+":"+NAME_FM_InspectionItem_ID+","
			+ I_FD_Column.COLUMNNAME_FD_ColumnType_ID+":@getItemID:"+FD_Item_ID;
	/** 登録用情報 : テーブル項目情報 : 検査項目コード */
	public final String Entry_FD_Column_FM_InspectionItem_Code =
			I_FD_Column.COLUMNNAME_FD_Column_Code+":"+COLUMNNAME_FM_InspectionItem_Code+","
			+ I_FD_Column.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Name+":"+NAME_FM_InspectionItem_Code+","
			+ I_FD_Column.COLUMNNAME_FD_ColumnType_ID+":@getItemID:"+FD_Item_String+","
			+ I_FD_Column.COLUMNNAME_FD_Column_Size+":"+I_FM_InspectionItem.SIZE_FM_InspectionItem_Code+"";
	/** 登録用情報 : テーブル項目情報 : 検査項目名 */
	public final String Entry_FD_Column_FD_Name =
			I_FD_Column.COLUMNNAME_FD_Column_Code+":"+COLUMNNAME_FD_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Table_ID+":@getID:"+Table_Name+","
			+ I_FD_Column.COLUMNNAME_FD_Name+":"+NAME_FD_Name+","
			+ I_FD_Column.COLUMNNAME_FD_ColumnType_ID+":@getItemID:"+FD_Item_String+","
			+ I_FD_Column.COLUMNNAME_FD_Column_Size+":"+I_FM_InspectionItem.SIZE_FD_Name+"";
	
	/** 検査項目 : 最高血圧 */
	public final String Entry_MaxBloodPressure =
			COLUMNNAME_FM_InspectionItem_Code+":"+"9A751000000099501"
			+ COLUMNNAME_FD_Name+":"+"収縮期血圧";
}

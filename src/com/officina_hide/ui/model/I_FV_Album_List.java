package com.officina_hide.ui.model;

import com.officina_hide.base.model.I_FD_DB;

/**
 * アルバム情報リスト画面インターフェースクラス[Album information list screen interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/07/23 Ver. 1.00
 */
public interface I_FV_Album_List extends I_FD_DB {
	
	/** 画面コード */
	public final String VIEW_CODE= "FV_Album_List";

	/** 画面用情報 : FV_Album_List 画面情報 */
	public final String Entry_Fx_View = 
			I_FX_View.COLUMNNAME_FX_View_Code+":FV_Album_List"+","
			+ I_FX_View.COLUMNNAME_FD_Name+":アルバム情報リスト画面"+","
			+ I_FX_View.COLUMNNAME_FD_Table_ID+":@getID:"+I_FP_Album.Table_Name+" ";

	/** 画面項目登録 : アルバムコード */
	public final String Entry_Field_FP_Album_Code = 
			I_FX_Field.COLUMNNAME_FX_Field_Code + ":" + I_FP_Album.COLUMNNAME_FP_Album_Code + ","
				+ I_FX_Field.COLUMNNAME_FX_View_ID + ":@getViewId:" + VIEW_CODE + ","
				+ I_FX_Field.COLUMNNAME_FD_Name + ":" + I_FP_Album.NAME_FP_Album_Code + ","
				+ I_FX_Field.COLUMNNAME_FX_Field_Type_ID + ":@getFieldID:"  + FD_Field_SingleText + ","
				+ I_FX_Field.COLUMNNAME_FD_Column_ID 
					+ ":@getColumnID:" + FP_Album.Table_Name + ":" + FP_Album.COLUMNNAME_FP_Album_Code + " ";
	/** 画面項目登録 : アルバム名 */
	public final String Entry_Field_FD_Name = 
			I_FX_Field.COLUMNNAME_FX_Field_Code + ":" + I_FP_Album.COLUMNNAME_FD_Name + ","
			+ I_FX_Field.COLUMNNAME_FX_View_ID + ":@getViewId:" + VIEW_CODE + ","
			+ I_FX_Field.COLUMNNAME_FD_Name + ":" + I_FP_Album.NAME_FD_Name + ","
			+ I_FX_Field.COLUMNNAME_FX_Field_Type_ID + ":@getFieldID:"  + FD_Field_SingleText + ","
			+ I_FX_Field.COLUMNNAME_FD_Column_ID 
				+ ":@getColumnID:" + FP_Album.Table_Name + ":" + FP_Album.COLUMNNAME_FD_Name + " ";
		
}

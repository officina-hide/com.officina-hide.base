package com.officina_hide.ui.view;

import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.ui.model.I_FP_Album;
import com.officina_hide.ui.model.I_FX_Field;

/**
 * アルバム情報単票画面インターフェースクラス[Album information single sheet screen interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[Create new]
 * @since 2022/06/05 Ver. 1.00
 */
public interface I_FV_Album_Entry extends I_FD_DB {

	/** 画面コード */
	public final String VIEW_CODE= "FV_Album_Entry";
	/** 画面名 */
	public final String VIEW_NAME = "アルバム情報単票画面";
	
	/** 画面項目登録 : アルバムコード */
	public final String Entry_Field_FP_Album_Code = 
			I_FX_Field.COLUMNNAME_FX_Field_Code + ":" + I_FP_Album.COLUMNNAME_FP_Album_Code + ","
			+ I_FX_Field.COLUMNNAME_FX_View_ID + ":@getViewId:" + VIEW_CODE + ","
			+ I_FX_Field.COLUMNNAME_FD_Name + ":" + I_FP_Album.NAME_FP_Album_Code + ","
			+ I_FX_Field.COLUMNNAME_FX_Field_Type_ID 
				+ ":@getItemID:"  + FD_Item_String + "";
	/** 画面項目登録 : アルバム名 */
	public final String Entry_Field_FD_Name = 
			I_FX_Field.COLUMNNAME_FX_Field_Code + ":" + I_FP_Album.COLUMNNAME_FD_Name + ","
			+ I_FX_Field.COLUMNNAME_FX_View_ID + ":@getViewId:" + VIEW_CODE + ","
			+ I_FX_Field.COLUMNNAME_FD_Name + ":" + I_FP_Album.NAME_FD_Name + ","
			+ I_FX_Field.COLUMNNAME_FX_Field_Type_ID 
				+ ":@getItemID:"  + FD_Item_String + "";
}

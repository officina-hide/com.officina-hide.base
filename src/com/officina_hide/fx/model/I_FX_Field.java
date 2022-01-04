package com.officina_hide.fx.model;

import com.officina_hide.base.model.I_FD_Column;
import com.officina_hide.base.model.I_FD_Reference;
import com.officina_hide.base.model.I_FD_TypeItem;

/**
 * Fx画面項目インターフェースクラス[Fx screen item interface class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/08/28
 */
public interface I_FX_Field {

	/** テーブル名 */
	public static final String Table_Name = "FX_Field";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "FX画面項目情報";
	/** テーブル説明 */
	public static final String Table_Comment = "タブ上で表示する項目に関する情報を管理する。";
	public static final String Table_Comment_Eng = "Manage information about items displayed on tabs.";
	/** テーブル情報ID */
	public final static long Table_ID = 203;
	
	/** 項目 : FX画面項目情報ID */
	public static final String COLUMNNAME_FX_Field_ID = Table_Name + "_ID";
	public static final String NAME_FX_Field_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FX_Field_ID = "FX画面項目を識別する為の情報ID";
	/** 項目 : FX画面項目名 */
	public static final String COLUMNNAME_FX_Field_Name = "FX_Field_Name";
	public static final String NAME_FX_Field_Name = "FX画面項目識別名";
	public static final String COMMENT_FX_Field_Name = "FX画面項目を識別する為の名称";
	/** 項目 : FXタブ情報ID */
	public static final String COLUMNNAME_FX_Tab_ID = I_FX_Tab.COLUMNNAME_FX_Tab_ID;
	public static final String NAME_FX_Tab_ID = I_FX_Tab.NAME_FX_Tab_ID;
	/** 項目 : FX画面項目属性情報ID */
	public static final String COLUMNNAME_FD_TypeItem_ID = I_FD_TypeItem.COLUMNNAME_FD_TypeItem_ID;
	public static final String NAME_FD_TypeItem_ID = I_FD_TypeItem.NAME_FD_TypeItem_ID;
	/** 項目 : 参照情報ID */
	public static final String COLUMNNAME_FD_Reference_ID = I_FD_Reference.COLUMNNAME_FD_Reference_ID;
	public static final String NAME_FD_Reference_ID = I_FD_Reference.NAME_FD_Reference_ID;	
	/** テーブル項目情報ID */
	public final static String COLUMNNAME_FD_Column_ID = I_FD_Column.COLUMNNAME_FD_Column_ID;
	public static final String NAME_FD_Column_ID = I_FD_Column.NAME_FD_Column_ID;
	
	/** リスト用項目判定 */
	public final static String COLUMNNAME_Fx_isListField = "FX_isListField";
	public final static String Fx_isListField_YES = "Y";
	public final static String Fx_isListField_NO = "N";
}

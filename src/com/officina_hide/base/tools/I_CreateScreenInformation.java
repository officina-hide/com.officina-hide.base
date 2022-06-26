package com.officina_hide.base.tools;

import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.model.I_FD_Reference;
import com.officina_hide.base.model.I_FD_ReferenceGroup;

/**
 * 画面情報用インターフェースクラス[Interface class for screen information]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/06/22 Ver. 1.00
 */
public interface I_CreateScreenInformation extends I_FD_DB{

	/** 登録用情報 : 参照グループ情報（共通）: 画面項目 */
	public final String Entry_FD_ReferenceGroup_FieldType =
			I_FD_ReferenceGroup.COLUMNNAME_FD_ReferenceGroup_Code+":"+FD_REFGROUP_FIELD+","
			+ COLUMNNAME_FD_Name+":"+"画面項目";
	
	/** 登録用情報 : 参照情報（共通）: FD_Item_Date */
	public final String Entry_FD_Reference_FD_Field_SingleText =
			I_FD_Reference.COLUMNNAME_FD_ReferenceGroup_ID+":"+"@getRefGroupId:"+FD_REFGROUP_FIELD+","
			+ I_FD_Reference.COLUMNNAME_FD_Reference_Code+":"+FD_Field_SingleText+","
			+ COLUMNNAME_FD_Name+":"+"単一行テキスト";

}

package com.officina_hide.base.model;

/**
 * データベース操作インタフェース<br>
 * @author officina-hide.com
 * @version 1.00
 * @since 2020/10/10
 */
public interface I_DB {
	
	/** シングルクォーテーション */
	public final String FD_SQ = "'";

	//テーブル共通項目
	/** 登録日 */
	public final String COLUMNNAME_FD_CREATE = "FD_Create";
	public final String NAME_FD_CREATE = "登録日";
	public final String COMMENT_FD_CREATE = "情報の登録日";

	/** 登録者ID	 */
	public final String COLUMNNAME_FD_CREATED = "FD_Created";
	public final String NAME_FD_CREATED = "登録者";
	public final String COMMENT_FD_CREATED = "情報の登録者ID";

	/** 更新日 */
	public final String COLUMNNAME_FD_UPDATE = "FD_Update";
	public final String NAME_FD_UPDATE = "更新日";
	public final String COMMENT_FD_UPDATE = "情報の更新日";

	/** 更新者ID	 */
	public final String COLUMNNAME_FD_UPDATED = "FD_Updated";
	public final String NAME_FD_UPDATED = "更新者";
	public final String COMMENT_FD_UPDATED = "情報の更新者ID";
	
	/** 項目種別 : 情報ID */
	public static final String COLUMNTYPE_FD_Information_ID = "FD_Information_ID";
	/** 項目種別 : テキスト */
	public static final String COLUMNTYPE_FD_Text = "FD_Text";
	/** 項目種別 : 複数行テキスト */
	public static final String COLUMNTYPE_FD_Field_Text = "FD_Field_Text";
	/** 項目種別 : 日付 */
	public static final String COLUMNTYPE_FD_Date = "FD_Date";
	/** 項目種別 : 自然数 */
	public static final String COLUMNTYPE_FD_Number = "FD_Number";
	/** 項目種別 : YESNO */
	public static final String COLUMNTYPE_FD_YesNo = "FD_YesNo";
	/** 項目種別 : リスト */
	public static final String COLUMNTYPE_FD_Lis = "FD_List";
	public static final String COLUMNYPENAME_FD_List = "リスト";

}

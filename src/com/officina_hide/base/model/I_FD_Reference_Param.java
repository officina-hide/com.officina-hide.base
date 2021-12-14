package com.officina_hide.base.model;

/**
 * 参照変数情報インターフェースクラス[Reference variable information interface class]<br>
 * @author officina-hide.net
 * @since 2021/12/13 Ver. 1.00
 * @author ueno 環境情報[Environment information]
 *
 */
public interface I_FD_Reference_Param extends I_FD_DB {
	
	/** テーブル名 */
	public static final String Table_Name = "FD_Reference_Param";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "参照変数情報";
	/** テーブル説明 */
	public static final String Table_Comment = "参照情報で必要となる各種変数情報を管理する。";
	public static final String Table_Comment_Eng = "Manage various variable information required for reference information.";
	/** テーブルID */
	public static final int Table_ID = 116;

	/** 項目 : 参照変数情報ID */
	public static final String COLUMNNAME_FD_Reference_Param_ID = Table_Name + "_ID";
	public static final String NAME_FD_Reference_Param_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FD_Reference_Param_ID = "参照変数を識別しる為の情報ID";
	/** 項目 : 参照変数名 */
	public static final String COLUMNNAME_FD_Reference_Param_Name = Table_Name + "_Name";
	public static final String NAME_FD_Reference_Param_Name = "参照変数名";
	public static final String COMMENT_FD_Reference_Param_Name = "参照変数を識別しる為の名称";
	/** 項目 : 参照情報ID */
	public static final String COLUMNNAME_FD_Reference_ID = I_FD_Reference.COLUMNNAME_FD_Reference_ID;
	public static final String NAME_FD_Reference_ID = I_FD_Reference.NAME_FD_Reference_ID;
	/** 項目 : 属性項目情報ID */
	public static final String COLUMNNAME_FD_TypeItem_ID = I_FD_TypeItem.COLUMNNAME_FD_TypeItem_ID;
	public static final String NAME_FD_TypeItem_ID = I_FD_TypeItem.NAME_FD_TypeItem_ID;
	/** 項目 : 数値情報 */
	public static final String COLUMNNAME_FD_Reference_Param_Numeric = "FD_Reference_Param_Numeric";
	public static final String NAME_FD_Reference_Param_Numeric = "数値情報";
	public static final String COMMENT_FD_Reference_Param_Numeric = "数値変数として保管する為の項目";
}

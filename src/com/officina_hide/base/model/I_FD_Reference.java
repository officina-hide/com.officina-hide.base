package com.officina_hide.base.model;

/**
 * 参照情報インターフェースクラス[Reference information interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/12/10 Ver. 1.00
 */
public interface I_FD_Reference {
	
	/** テーブル名 */
	public static final String Table_Name = "FD_Reference";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "参照情報";
	/** テーブル説明 */
	public static final String Table_Comment = "項目使用時に情報を参照する為の設定を管理する。";
	public static final String Table_Comment_Eng = "Manage settings for referencing information when using items.";
	/** テーブルID */
	public static final int Table_ID = 115;

	/** 項目 : 参照情報ID */
	public static final String COLUMNNAME_FD_Reference_ID = Table_Name + "_ID";
	public static final String NAME_FD_Reference_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FD_Reference_ID = "参照情報を識別する為の情報ID";
	/** 項目 : 参照名 */
	public static final String COLUMNNAME_FD_Reference_Name = Table_Name + "_Name";
	public static final String NAME_FD_Reference_Name = "参照名";
	public static final String COMMENT_FD_Reference_Name = "参照情報を識別する為の名称";
	/** 項目 : 参照タイプ */
	public static final String COLUMNNAME_FD_ReferenceType_ID = "FD_ReferenceType_ID";
	public static final String NAME_FD_ReferenceType_ID = "参照種別ID";
	public static final String COMMENT_FD_ReferenceType_ID = "参照方法の種類を識別する為の情報ID（FD_TypeItemを参照）";
}

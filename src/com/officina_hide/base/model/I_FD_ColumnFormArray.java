package com.officina_hide.base.model;

/**
 * 書式配列情報インタフェース
 * @author officina-hide.com
 * @version 1.21
 * @since 2020/11/22
 */
public interface I_FD_ColumnFormArray {

	/** 書式配列情報 : テーブル名 */
	public final String Table_Name = "FD_ColumnFormArray";
	/** 書式配列情報 : テーブル表示名 */
	public final String NAME = "書式配列情報";
	/** 書式配列情報 : テーブル説明 */
	public final String COMMENT = "書式配列を管理する為の情報";
	/** 書式配列情報 : テーブル情報ID */
	public final int TABLE_ID = 114;
	
	/** 書式配列情報ID */
	public final String COLUMNNAME_FD_ColumnFormArray_ID = Table_Name + "_ID";
	public final String NAME_FD_ColumnFormArray_ID = "書式配列情報ID";
	public final String COMMENT_FD_ColumnFormArray_ID = "書式配列を識別するための情報ID";
	/** 項目書式情報ID */
	public final String COLUMNNAME_FD_ColumnForm_ID = I_FD_ColumnForm.Table_Name + "_ID";
	public final String NAME_FD_ColumnForm_ID = "項目書式情報ID";
	public final String COMMENT_FD_ColumnForm_ID = "書式配列を紐付ける項目書式の情報ID";
	/** 書式配列番号 */
	public final String COLUMNNAME_Array_Number = "Array_Number";
	public final String NAME_Array_Number = "書式配列番号";
	public final String COMMENT_Array_Number = "書式配列の順番";
	/** 書式種別ID */
	public final String COLUMNNAME_FormType_ID = "FormType_ID";
	public final String NAME_FormType_ID = "書式種別ID";
	public final String COMMENT_FormType_ID = "書式の種類を識別するID（リファレンスID）";
	/** 書式設定値 */
	public final String COLUMNNAME_FD_Value = "FD_Value";
	public final String NAME_FD_Value = "書式設定値";
	public final String COMMENT_FD_Value = "書式として設定された値";
	/** 書式コード */
	public final String COLUMNNAME_FD_Code = "FD_Code";
	public final String NAME_FD_Code = "書式コード";
	public final String COMMENT_FD_Code = "接続書式のリファレンスリストに該当する情報ID";
	
	/** 書式種別 : 固定文字列 */
	public final String FORMTYPE_REF_NAME_FixText = "FixText";
	public final int FORMTYPE_ID_FixText_ID = 121;
	public final String FORMTYPE_NAME_FixText = "固定文字列";
	/** 書式種別 : 接続文字列 */
	public final String FORMTYPE_REF_NAME_ConnextText = "ConnextText";
	public final int FORMTYPE_ID_ConnextText_ID = 122;
	public final String FORMTYPE_NAME_ConnextText = "接続文字列";
	/** 書式種別 : 採番数値 */
	public final String FORMTYPE_REF_NAME_Numbering = "Numbering";
	public final int FORMTYPE_ID_Numbering_ID = 123;
	public final String FORMTYPE_NAME_Numbering = "採番数値";
	
}

package com.officina_hide.base.model;

/**
 * 処理変数情報インターフェースクラス[Processing variable information interface class]<br>
 * @author officine-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/11 Ver. 1.00
 */
public interface I_FD_ProcessParam extends I_FD_DB {

	/** テーブル名 */
	public static final String Table_Name = "FD_ProcessParam";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "処理変数情報";
	/** テーブル説明 */
	public static final String Table_Comment = "処理情報で使用する変数に関する情報を管理する。";
	public static final String Table_Comment_Eng = "Manage information about variables used in processing information.";
	/** テーブル情報ID */
	public final static long Table_ID = 114;

	/** 項目 : 処理変数情報ID */
	public static final String COLUMNNAME_FD_ProcessParam_ID = Table_Name + "_ID";
	public static final String NAME_FD_ProcessParam_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FD_ProcessParam_ID = "処理変数を識別するための情報ID";
	/** 項目 : 処理変数名 */
	public static final String COLUMNNAME_FD_ProcessParam_Name = Table_Name + "_Name";
	public static final String NAME_FD_ProcessParam_Name = "処理変数名";
	public static final String COMMENT_FD_ProcessParam_Name = "処理変数を識別するための名称";
	/** 項目 : 属性項目情報ID */
	public static final String COLUMNNAME_FD_TypeItem_ID = I_FD_TypeItem.COLUMNNAME_FD_TypeItem_ID;
	public static final String NAME_FD_TypeItem_ID = I_FD_TypeItem.NAME_FD_TypeItem_ID;
	/** 項目 : 処理情報ID */
	public static final String COLUMNNAME_FD_Process_ID = I_FD_Process.COLUMNNAME_FD_Process_ID;
	public static final String NAME_FD_Process_ID = I_FD_Process.NAME_FD_Process_ID;
}

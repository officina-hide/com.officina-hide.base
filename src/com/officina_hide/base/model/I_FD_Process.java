package com.officina_hide.base.model;

/**
 * 処理情報インターフェースクラス[Process information interface clas]<br>
 * @author officine-hide.com
 * @version 1.00 新規作成
 * @since 2021/10/09 Ver. 1.00
 */
public interface I_FD_Process extends I_FD_DB {
	
	/** テーブル名 */
	public final static String Table_Name = "FD_Process";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "処理情報";
	/** テーブル説明 */
	public static final String Table_Comment = "パッケージで使用する処理に関する情報を管理する。";
	public static final String Table_Comment_Eng = "Manage information about the processes used in the package.";
	/** テーブル情報ID */
	public final static int Table_ID = 113;

	/** 項目 : 処理情報ID */
	public static final String COLUMNNAME_FD_Process_ID = Table_Name + "_ID";
	public static final String NAME_FD_Process_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FD_Process_ID = "処理を識別する為の情報ID";
	/** 項目 : 処理識別名 */
	public static final String COLUMNNAME_FD_Process_Name = "FD_Process_Name";
	public static final String NAME_FD_Process_Name = "処理名";
	public static final String COMMENT_FD_Process_Name = "処理を識別する為の名称";
}

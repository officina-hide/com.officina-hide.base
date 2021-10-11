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

}

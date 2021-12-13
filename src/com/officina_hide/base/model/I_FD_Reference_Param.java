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

}

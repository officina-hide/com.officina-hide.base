package com.officina_hide.base.model;

/**
 * テーブル情報インターフェースクラス[Table information interface class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成
 * @since 2022/03/22 Ver. 1.50
 */
public record I_FD_Table() {
	
	/** テーブル名 */
	public static final String Table_Name = "FD_Table";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "テーブル情報";
	/** テーブル説明 */
	public static final String Table_Comment = "パッケージで使用するテーブルを管理する。";
	public static final String Table_Comment_Eng = "Manage the tables used in the package.";
	/** テーブルID */
	public static final int Table_ID = 103;

	/** 項目 : テーブル情報ID */
	public static final String COLUMNNAME_FD_Table_ID = Table_Name+"_ID";
	public static final String NAME_FD_Table_ID = Table_Disp_Name+"ID";
	public static final String COMMENT_FD_Table_ID = "テーブルを識別する為の情報ID";
}

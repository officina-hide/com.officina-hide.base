package com.officina_hide.base.model;

/**
 * ログイン情報インターフェースクラス[Login information interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規登録
 * @since 2021/10/05 Ver. 1.00
 */
public interface I_FD_Login extends I_FD_DB {
	
	/** テーブル名 */
	public static final String Table_Name = "FD_Login";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "ログイン情報";
	/** テーブル説明 */
	public static final String Table_Comment = "パッケージの認証時に使用する情報を管理する。現在は検証用に構築";
	public static final String Table_Comment_Eng = "Manage the information used when authenticating packages."
			+ "Currently built for verification";
	/** テーブルID */
	public static final int Table_ID = 112;

	/** 項目 : ログイン情報ID */
	public static final String COLUMNNAME_FD_Login_ID = Table_Name + "_ID";
	public static final String NAME_FD_Login_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FD_Login_ID = "ログイン情報を識別する為の情報ID";
	/** 項目 : ユーザー識別名 */
	public static final String COLUMNNAME_FD_User_Name = I_FD_User.COLUMNNAME_FD_User_Name;
	public static final String NAME_FD_User_Name = I_FD_User.COLUMNNAME_FD_User_Name;
	/** 項目 : ログインパスワード */
	public static final String COLUMNNAME_FD_Login_Password = I_FD_User.COLUMNNAME_FD_Login_Password;
	public static final String NAME_FD_Login_Password = I_FD_User.NAME_FD_Login_Password;
}

package com.officina_hide.base.model;

/**
 * ユーザー情報インターフェースクラス[User information interface class]<br>
 * @author officine-hide.net
 * @version 1.00 新規登録
 * @since 2021/10/04 Ver. 1.00
 */
public interface I_FD_User extends I_FD_DB {

	/** テーブル名 */
	public static final String Table_Name = "FD_User";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "ユーザー情報";
	/** テーブル説明 */
	public static final String Table_Comment = "パッケージを使用するユーザーに関する情報を管理する。";
	public static final String Table_Comment_Eng = "Manage information about users who use the package..";
	/** テーブルID */
	public static final int Table_ID = 111;
	
	/** 項目 : ユーザー情報ID */
	public static final String COLUMNNAME_FD_User_ID = Table_Name + "_ID";
	public static final String NAME_FD_User_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FD_User_ID = "ユーザー情報を識別する為の情報ID";
	/** 項目 : ユーザー識別名 */
	public static final String COLUMNNAME_FD_User_Name = "FD_User_Name";
	public static final String NAME_FD_User_Name = "ユーザー識別名";
	public static final String COMMENT_FD_User_Name = "ユーザー情報を識別する為の名称。ログイン時のユーザーIDとして使用する。";
	/** 項目 : ログインパスワード */
	public static final String COLUMNNAME_FD_Login_Password = "FD_Login_Password";
	public static final String NAME_FD_Login_Password = "ログインパスワード";
	public static final String COMMENT_FD_Login_Password = "ログイン時に認証に使用するパスワード。暗号化されて保管される。";	
}

package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * ログイン情報I/Oクラス[Login information I/O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/14 Ver. 1.00
 */
public class X_FD_Login extends FD_DB implements I_FD_Login {

	/** 項目 : ログイン情報ID */
	private long FD_Login_ID;
	/** 項目 : ユーザー名 */
	private String FD_User_Name;
	/** 項目 : ログインパスワード */
	private String FD_Login_Password;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2021/10/14 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param loginId ログイン情報ID[Login information ID]
	 */
	public X_FD_Login(FD_EnvData env, int loginId) {
		createItemList(env, Table_Name);
		if(loginId > 0) {
			load(env, Table_Name, loginId, items);
		}
	}

	/**
	 * テーブル項目名による情報セット[Information set by table column name]<br>
	 * @author officina-hide.net
	 * @since 2021/10/14 Ver. 1.00
	 * @param columnName テーブル項目名[Table column name]
	 * @param data 設定値[Setting value]
	 */
	public void setValue(String columnName, Object data) {
		items.setValue(columnName, data);
	}

	public long getFD_Login_ID() {
		FD_Login_ID = items.getlongData(COLUMNNAME_FD_Login_ID);
		return FD_Login_ID;
	}
	public void setFD_Login_ID(long loginId) {
		items.setValue(COLUMNNAME_FD_Login_ID, loginId);
	}
	public String getFD_User_Name() {
		FD_User_Name = items.getStringData(COLUMNNAME_FD_User_Name);
		return FD_User_Name;
	}
	public void setFD_User_Name(String userName) {
		items.setValue(COLUMNNAME_FD_User_Name, userName);
	}
	public String getFD_Login_Password() {
		FD_Login_Password = items.getStringData(COLUMNNAME_FD_Login_Password);
		return FD_Login_Password;
	}
	public void setFD_Login_Password(String loginPassword) {
		items.setValue(COLUMNNAME_FD_Login_Password, loginPassword);
	}

}

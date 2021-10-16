package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;

/**
 * ユーザー情報I/Oクラス[User information I/O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/04 Ver. 1.00
 */
public class X_FD_User extends FD_DB implements I_FD_User {

	/** 項目 : ユーザー情報ID */
	private long FD_User_ID;
	private String FD_User_Name;
	private String FD_Login_Password;
	
	/**
	 * コンストラクタ[Constructor]
	 * @param env 環境情報[Environment information]
	 * @param userID ユーザー情報ID
	 */
	public X_FD_User(FD_EnvData env, long userID) {
		createItemList(env, Table_Name);
		if(userID > 0) {
			load(env, Table_Name, userID, items);
		}
	}

	/**
	 * コンストラクタ[Constructor]
	 * @param env 環境情報[Environment information]
	 * @param where 抽出条件[Extraction condition]
	 */
	public X_FD_User(FD_EnvData env, FD_WhereData where) {
		createItemList(env, Table_Name);
		if(where.toString().length() > 0) {
			load(env, items, where);
		}
	}

	/**
	 * 情報登録[Save data]
	 * @author officina-hide.net
	 * @since 2021/10/05 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
	}

	public long getFD_User_ID() {
		FD_User_ID = items.getlongData(COLUMNNAME_FD_User_ID);
		return FD_User_ID;
	}
	public void setFD_User_ID(long userID) {
		items.setValue(COLUMNNAME_FD_User_ID, userID);
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
	public void setFD_Login_Password(String password) {
		items.setValue(COLUMNNAME_FD_Login_Password, password);
	}

}

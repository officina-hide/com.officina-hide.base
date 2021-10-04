package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * ユーザー情報I/Oクラス[User information I/O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/04 Ver. 1.00
 */
public class X_FD_User extends FD_DB implements I_FD_User {

	/**
	 * コンストラクタ[Constructor]
	 * @param env 環境情報[Enfironment information]
	 * @param userID ユーザー情報ID
	 */
	public X_FD_User(FD_EnvData env, long userID) {
		createItemList(env, Table_Name);
		if(userID > 0) {
			load(env, Table_Name, userID, items);
		}
	}

}

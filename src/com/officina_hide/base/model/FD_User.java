package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * ユーザー情報クラス[User infromation class]<br>
 * @author officine-hide.com
 * @version 1.00 新規登録
 * @since 2021/10/04 Ver. 1.00
 */
public class FD_User extends FD_DB implements I_FD_User {

	/**
	 * ユーザー情報テーブル構築[User infoermation table construction]<br>
	 * @author officine-hide.net
	 * @since 2021/10/04 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
		//採番情報
		FD_Numbering num = new FD_Numbering();
		num.add(env, Table_ID, Table_ID, 100001, 0);
		//辞書情報
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FD_User_ID, NAME_FD_User_ID, COMMENT_FD_User_ID);
		dd.add(env, 0, COLUMNNAME_FD_User_Name, NAME_FD_User_Name, COMMENT_FD_User_Name);
		dd.add(env, 0, COLUMNNAME_FD_Login_Password, NAME_FD_Login_Password, COMMENT_FD_Login_Password);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, Table_ID, COLUMNNAME_FD_User_ID, FD_Item_ID, 0, false, true, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_User_Name, FD_Item_String, 100, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Name, FD_Item_String, 100, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Description, FD_Item_Text, 0, true, false, null);
		addCommonColumn(env, Table_ID);
	}

}

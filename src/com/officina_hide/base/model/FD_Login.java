package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * ログイン情報クラス[Login information class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/10/05 Ver. 1.00
 */
public class FD_Login extends FD_DB implements I_FD_Login {

	/**
	 * ログイン情報テーブル構築[Login information table construction]<br>
	 * @author officine-hide.net
	 * @since 2021/10/05 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
		//採番情報登録
		FD_Numbering num = new FD_Numbering();
		num.add(env, Table_ID, Table_ID, 100001, 0);
		//辞書情報登録
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FD_Login_ID, NAME_FD_Login_ID, COMMENT_FD_Login_ID);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Login_ID, FD_Item_ID, 0, false, true, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_User_Name, FD_Item_String, 100, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Login_Password, FD_Item_String, 100, true, false, null);
		addCommonColumn(env, Table_ID);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

}

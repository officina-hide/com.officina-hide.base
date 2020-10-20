package com.officina_hide.base.model;

import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;

/**
 * ログ情報I/Oクラス<br>
 * @author officine-hide.com
 * @version 1.00 新規作成
 * @since 2020/10/20
 */
public class X_FD_Log extends FD_DB implements I_FD_Log {

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、項目の初期化を行う。</p>
	 * @param env 環境情報
	 */
	public X_FD_Log(FD_EnvData env) {
		initializeItemList();
	}

	/**
	 * 項目初期化<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/20
	 */
	private void initializeItemList() {
		itemList.clear();
		itemList.add(COLUMNNAME_FD_Log_ID, 0, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_Log_Type_ID, 0, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_Log_Data, null, COLUMNTYPE_FD_Field_Text);
		itemList.add(COLUMNNAME_FD_CREATE, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_CREATED, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_FD_UPDATE, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_UPDATED, null, COLUMNTYPE_FD_Information_ID);
}

	/**
	 * 情報保存<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/20
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
		System.out.println(new Date()+" : "+NAME+"新規追加");
	}

}

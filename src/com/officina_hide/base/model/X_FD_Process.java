package com.officina_hide.base.model;

import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;

/**
 * プロセス情報I/Oクラス<br>
 * @author officine-hide.com
 * @version 1.10
 * @since 2020/10/31
 */
public class X_FD_Process extends FD_DB implements I_FD_Process {

	/**
	 * コンストラクター<br>
	 * 実体化時に、項目の初期化を行う。<br>
	 * @param env 環境情報
	 */
	public X_FD_Process(FD_EnvData env) {
		//項目の初期化
		initializeItemList();
	}

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、項目の初期化と指定されたプロセス情報IDを持つプロセス情報の抽出を行う。</p>
	 * @author officine-hide.com
	 * @since 1.10 2020/11/02
	 * @param env 環境情報
	 * @param processId プロセス情報ID
	 */
	public X_FD_Process(FD_EnvData env, int processId) {
		//項目の初期化
		initializeItemList();
		//情報読み取り
		load(env, Table_Name, processId);
	}

	/**
	 * 項目初期化<br>
	 * @author officine-hide.com
	 * @since 1.10 2020/10/31
	 */
	private void initializeItemList() {
		itemList.clear();
		itemList.add(I_FD_Process.COLUMNNAME_FD_Process_ID, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_Process_Name, null, COLUMNTYPE_FD_Text);
		itemList.add(COLUMNNAME_Process_StartTime, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_Process_EndTime, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_CREATE, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_CREATED, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_FD_UPDATE, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_UPDATED, null, COLUMNTYPE_FD_Information_ID);
	}

	/**
	 * 情報保存<br>
	 * @author officine-hide.com
	 * @since 1.10 2020/10/31
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

	/**
	 * 項目の文字列情報を返す<br>
	 * @author officine-hide.com
	 * @since 1.10 2020/11/02
	 * @param columnName 項目名
	 * @return 文字列情報
	 */
	public String getValueOfString(String columnName) {
		FD_Item item = itemList.getItemByName(columnName);
		return item.getStringOfValue();
	}

	/**
	 * 項目の日付情報を返す<br>
	 * @author officine-hide.com
	 * @since 1.10 2020/11/02
	 * @param columnName 項目名
	 * @return 日付情報
	 */
	public Date getDateOfValue(String columnName) {
		FD_Item item = itemList.getItemByName(columnName);
		return item.getDateOfValue();
	}

}

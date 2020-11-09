package com.officina_hide.project.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * プロジェクト情報I/Oクラス<br>
 * @author officina-hide.com
 * @version 1.20
 * @since 2020/11/08
 */
public class X_FD_Project extends FD_DB implements I_FD_Project {

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、項目の初期化を行う。<br></p>
	 * @author officina-hide.com
	 * @since 1.20 2020/11/08
	 * @param env 環境情報
	 */
	public X_FD_Project(FD_EnvData env) {
		//項目初期化
		initializeItemList();
	}
	
	/**
	 * 項目リスト初期化<br>
	 * @author officina-hide.com
	 * @since 1.20 2020/11/08
	 */
	private void initializeItemList() {
		itemList.clear();
		itemList.add(COLUMNNAME_FD_Project_ID, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_Project_Name, null, COLUMNTYPE_FD_Text);
		itemList.add(COLUMNNAME_FD_Name, null, COLUMNTYPE_FD_Text);
		itemList.add(COLUMNNAME_Task_Number_Form, null, COLUMNTYPE_FD_Text);
		
		itemList.add(COLUMNNAME_FD_Process_ID, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_FD_CREATE, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_CREATED, null, COLUMNTYPE_FD_Information_ID);
		itemList.add(COLUMNNAME_FD_UPDATE, null, COLUMNTYPE_FD_Date);
		itemList.add(COLUMNNAME_FD_UPDATED, null, COLUMNTYPE_FD_Information_ID);
		initializeItemList(env, TABLE_ID);
	}

	/**
	 * 情報保存<br>
	 * @author officina-hide.com
	 * @since 1.20 2020/11/08
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * 処理情報クラス[Process information class]<br>
 * @author officine-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/09 Ver. 1.00
 */
public class FD_Process extends FD_DB implements I_FD_Process {

	/**
	 * 処理情報テーブル構築[Process information table construction]<br>
	 * @author officine-hide.net
	 * @since 2021/10/09 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
		//採番情報登録
		FD_Numbering num = new FD_Numbering();
		num.add(env, Table_ID, Table_ID, 101, 0);
		//辞書情報登録
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FD_Process_ID, NAME_FD_Process_ID, COMMENT_FD_Process_ID);
		dd.add(env, 0, COLUMNNAME_FD_Process_Name, NAME_FD_Process_Name, COMMENT_FD_Process_Name);
		dd.add(env, 0, COLUMNNAME_FD_CallProcess_Name, NAME_FD_CallProcess_Name, NAME_FD_CallProcess_Name);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Process_ID, FD_ITEM_ID, 0, false, true, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Process_Name, FD_ITEM_String, 100, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_CallProcess_Name, FD_ITEM_String, 256, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Name, FD_ITEM_String, 100, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Description, FD_ITEM_Text, 0, true, false, null);
		addCommonColumn(env, Table_ID);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

	/**
	 * 情報登録[Save data]<br>
	 * @author officina-hide.net
	 * @since 2021/10/09 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param processId 処理情報ID[Process information ID]
	 * @param processName 処理名[Process name]
	 * @param processClass 
	 * @return 処理情報ID[Process information ID]
	 */
	public long add(FD_EnvData env, int processId, String processName, String processClass) {
		X_FD_Process process = new X_FD_Process(env, 0);
		process.setFD_Process_ID(0);
		process.setFD_Process_Name(processName);
		process.setFD_CallProcess_Name(processClass);
		process.setFD_Group_ID(env.getActionUserID());
		process.save(env);
		return process.getFD_Process_ID();
	}

}

package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * 処理情報I/Oクラス[Process information I/O class]<br>
 * @author officine-hide.com
 * @version 1.00 新規作成
 * @since 2021/10/09 Ver. 1.00
 */
public class X_FD_Process extends FD_DB implements I_FD_Process {

	/** 項目 : 処理情報ID */
	private long FD_Process_ID;
	/** 項目 : 処理情報名 */
	private String FD_Process_Name;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officine-hide.net
	 * @since 2021/10/09 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param processID 処理情報ID[Process information ID]
	 */
	public X_FD_Process(FD_EnvData env, int processID) {
		createItemList(env, Table_Name);
		if(processID > 0) {
			load(env, Table_Name, processID, items);
		}
	}

	/**
	 * 情報登録[Save data]<br>
	 * @author officine-hide.net
	 * @since 2021/10/09 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
	}

	public long getFD_Process_ID() {
		FD_Process_ID = items.getlongData(COLUMNNAME_FD_Process_ID);
		return FD_Process_ID;
	}
	public void setFD_Process_ID(long processID) {
		items.setValue(COLUMNNAME_FD_Process_ID, processID);
	}
	public String getFD_Process_Name() {
		FD_Process_Name = items.getStringData(COLUMNNAME_FD_Process_Name);
		return FD_Process_Name;
	}
	public void setFD_Process_Name(String processName) {
		items.setValue(COLUMNNAME_FD_Process_Name, processName);
	}
	
}

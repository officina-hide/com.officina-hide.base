package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * 処理変数情報I/Oクラス[Processing variable information I / O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/12 Ver. 1.00
 */
public class X_FD_ProcessParam extends FD_DB implements I_FD_ProcessParam {

	/** 項目 : 処理変数情報ID */
	private long FD_ProcessParam_ID;
	/** 項目 : 処理変数名 */
	private String FD_ProcessParam_Name;
	/** 項目 : 属性項目情報ID */
	private long FD_TypeItem_ID;
	/** 項目 : 処理情報ID */
	private long FD_Process_ID;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2021/10/12 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param processParamId 処理変数情報ID[Process variable information ID]
	 */
	public X_FD_ProcessParam(FD_EnvData env, int processParamId) {
		createItemList(env, Table_Name);
		if(processParamId > 0) {
			load(env, Table_Name, processParamId, items);
		}
	}

	/**
	 * 情報登録[Save data]<br>
	 * @author officina-hide.net
	 * @since 2021/10/12 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
	}

	public long getFD_ProcessParam_ID() {
		FD_ProcessParam_ID = items.getlongData(COLUMNNAME_FD_ProcessParam_ID);
		return FD_ProcessParam_ID;
	}
	public void setFD_ProcessParam_ID(long processParamId) {
		items.setValue(COLUMNNAME_FD_ProcessParam_ID, processParamId);
	}
	public String getFD_ProcessParam_Name() {
		FD_ProcessParam_Name = items.getStringData(COLUMNNAME_FD_ProcessParam_Name);
		return FD_ProcessParam_Name;
	}
	public void setFD_ProcessParam_Name(String processParamName) {
		items.setValue(COLUMNNAME_FD_ProcessParam_Name, processParamName);
	}
	public long getFD_TypeItem_ID() {
		FD_TypeItem_ID = items.getlongData(COLUMNNAME_FD_TypeItem_ID);
		return FD_TypeItem_ID;
	}
	public void setFD_TypeItem_ID(long typeItemId) {
		items.setValue(COLUMNNAME_FD_TypeItem_ID, typeItemId);
	}
	public long getFD_Process_ID() {
		FD_Process_ID = items.getlongData(COLUMNNAME_FD_Process_ID);
		return FD_Process_ID;
	}
	public void setFD_Process_ID(long processId) {
		items.setValue(COLUMNNAME_FD_Process_ID, processId);
	}

}

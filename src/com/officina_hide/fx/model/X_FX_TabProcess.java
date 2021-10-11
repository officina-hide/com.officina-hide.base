package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.X_FD_Process;

/**
 * タブ処理情報I/Oクラス[Tab process information I/O class]<br>
 * @author officine-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/11 Ver. 1.00
 */
public class X_FX_TabProcess extends FD_DB implements I_FX_TabProcess {

	/** 項目 : タブ処理情報ID */
	private long FX_TabProcess_ID;
	/** 項目 : タブ情報ID */
	private long FX_Tab_ID;
	/** 情報 : タブ情報 */
	private X_FX_Tab FX_Tab;
	/** 項目 : 処理情報ID */
	private long FD_Process_ID;
	/** 情報 : 処理情報 */
	private X_FD_Process FD_Process;
	
	/**
	 * コンストラクタ[Constructor]
	 * @param env 環境情報[Environment information]
	 * @param tabProcessId タブ処理情報ID[Tab process information ID]
	 */
	public X_FX_TabProcess(FD_EnvData env, long tabProcessId) {
		createItemList(env, Table_Name);
		if(tabProcessId > 0) {
			load(env, Table_Name, tabProcessId, items);
		}
	}

	/**
	 * 情報登録[Save data]<br>
	 * @author officine-hide.net
	 * @since 2021/10/11 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
	}

	public long getFX_TabProcess_ID() {
		FX_TabProcess_ID = items.getlongData(COLUMNNAME_FX_TabProcess_ID);
		return FX_TabProcess_ID;
	}
	public void setFX_TabProcess_ID(long tabProcessID) {
		items.setValue(COLUMNNAME_FX_TabProcess_ID, tabProcessID);
	}
	public long getFX_Tab_ID() {
		FX_Tab_ID = items.getlongData(COLUMNNAME_FX_Tab_ID);
		return FX_Tab_ID;
	}
	public void setFX_Tab_ID(long tabId) {
		items.setValue(COLUMNNAME_FX_Tab_ID, tabId);
	}
	public X_FX_Tab getFX_tab(FD_EnvData env) {
		if(FX_Tab == null) {
			if(getFX_Tab_ID() > 0) {
				FX_Tab = new X_FX_Tab(env, getFX_Tab_ID());
			} else {
				return null;
			}
		} else {
			if(FX_Tab.getFX_Tab_ID() != getFX_Tab_ID()) {
				FX_Tab = new X_FX_Tab(env, getFX_Tab_ID());
			}
		}
		return FX_Tab;
	}
	public long getFD_Process_ID() {
		FD_Process_ID = items.getlongData(COLUMNNAME_FD_Process_ID);
		return FD_Process_ID;
	}
	public void setFD_Process_ID(long processId) {
		items.setValue(COLUMNNAME_FD_Process_ID, processId);
	}
	public X_FD_Process getFD_Process(FD_EnvData env) {
		if(FD_Process == null) {
			if(getFD_Process_ID() > 0) {
				FD_Process = new X_FD_Process(env, getFD_Process_ID());
			} else {
				return null;
			}
		} else {
			if(FD_Process.getFD_Process_ID() != getFD_Process_ID()) {
				FD_Process = new X_FD_Process(env, getFD_Process_ID());
			}
		}
		return FD_Process;
	}

}

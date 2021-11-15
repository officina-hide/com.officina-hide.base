package com.officina_hide.account.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * 勘定科目情報I/Oクラス[Account title information I/O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/11/15 Ver. 1.00
 */
public class X_AC_AccountTitle extends FD_DB implements I_AC_AccountTitle {

	/** 勘定科目情報ID */
	private long AC_AccountTitle_ID;
	/** 勘定科目コード */
	private String AC_AccountTitle_Code;

	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2021/11/15 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param accountTitleId 勘定科目情報ID[Account title information ID]
	 */
	public X_AC_AccountTitle(FD_EnvData env, int accountTitleId) {
		createItemList(env, Table_Name);
		if(accountTitleId > 0) {
			load(env, Table_Name, accountTitleId, items);
		}
	}
	
	/**
	 * 情報保存[Save data]<br>
	 * @author officina-hide.net
	 * @since 2021/11/15 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
	}

	public long getAC_AccountTitle_ID() {
		AC_AccountTitle_ID = items.getlongData(COLUMNNAME_AC_AccountTitle_ID);
		return AC_AccountTitle_ID;
	}
	public void setAC_AccountTitle_ID(long accountTitleId) {
		items.setValue(COLUMNNAME_AC_AccountTitle_ID, accountTitleId);
	}
	public String getAC_AccountTitle_Code() {
		AC_AccountTitle_Code = items.getStringData(COLUMNNAME_AC_AccountTitle_Code);
		return AC_AccountTitle_Code;
	}
	public void setAC_AccountTitle_Code(String accountTitleCode) {
		items.setValue(COLUMNNAME_AC_AccountTitle_Code, accountTitleCode);
	} 
}

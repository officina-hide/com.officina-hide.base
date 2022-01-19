package com.officina_hide.account.model;

import java.util.Calendar;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * 仕訳伝票情報I/Oクラス[Journal slip information I/O Class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2022/01/17 Ver. 1.00
 */
public class X_AC_JournalSlip extends FD_DB implements I_AC_JournalSlip {

	/** 項目 : 作成日 */
	private Calendar AC_IssueDate;
	/** 項目 : 貸方勘定科目 */
	private long AC_Credit_AccountTitle_ID;
	
	/**
	 * 
	 * @param env
	 * @param journalSlipId
	 */
	public X_AC_JournalSlip(FD_EnvData env, long journalSlipId) {
		createItemList(env, Table_Name);
		if(journalSlipId > 0) {
			load(env, Table_Name, journalSlipId, items);
		}
	}
	
	/**
	 * 情報保存[Save data]<br>
	 * @author officina-hide.net
	 * @since 2022/01/17 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
	}

	public Calendar getAC_IssueDate() {
		AC_IssueDate = items.getDateData(COLUMNNAME_AC_IssueDate);
		return AC_IssueDate;
	}
	public void setAC_IssueDate(Calendar issueDate) {
		items.setValue(COLUMNNAME_AC_IssueDate, issueDate);
	}
	public long getAC_Credit_AccountTitle_ID() {
		AC_Credit_AccountTitle_ID = items.getlongData(COLUMNNAME_AC_Credit_AccountTitle_ID);
		return AC_Credit_AccountTitle_ID;
	}
	public void setAC_Credit_AccountTitle_ID(long creditAccountTitleId) {
		items.setValue(COLUMNNAME_AC_Credit_AccountTitle_ID, creditAccountTitleId);
	}

}

package com.officina_hide.account.tools;

import com.officina_hide.account.model.AC_AccountTitle;
import com.officina_hide.base.common.FD_EnvData;

/**
 * 会計機能基盤生成[Accounting function infrastructure generation]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/11/13 Ver. 1.00
 */
public class CreateAccountBase {
	
	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2021/11/13 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public CreateAccountBase(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 生成実行[Run generation]<br>
	 * @author officina-hide.net
	 * @since 2021/11/13 Ver. 1.00
	 */
	public void execute() {
		/*
		 * 1. 勘定科目情報生成[Generate account information]
		 */
		//1.
		AC_AccountTitle aat = new AC_AccountTitle();
		aat.createTable(env);
		aat.addTitle(env);
	}

}

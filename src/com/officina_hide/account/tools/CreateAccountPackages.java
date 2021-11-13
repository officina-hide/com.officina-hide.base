package com.officina_hide.account.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.tools.CreateBaseInformation;
import com.officina_hide.base.tools.CreateFxInformation;

/**
 * 会計機能に関するパッケージを生成する。<br>
 * Generate a package for accounting functions.<br>
 * CREATE DATABASE ACCOUNT CHARACTER SET utf8;<br>
 * GRANT ALL ON ACCOUNT.* TO 'fdadmin'@'%' WITH GRANT OPTION;<br></p>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/11/13 Ver. 1.00
 */
public class CreateAccountPackages {

	public static void main(String[] args) {
		/*
		 * 1. プロジェクト共通環境設定
		 * 2. 会計機能ベース構築
		 */
		//1.
		FD_EnvData env = new FD_EnvData();
		env.setRunLevel(0);
		env.setDbName("ACCOUNT");
		env.setActionUserID(I_FD_DB.SYSTEM_USER_ID);
		if(env.getRunLevel() == 0) {
			CreateBaseInformation cbi = new CreateBaseInformation(env);
			cbi.execute();
			CreateFxInformation cfi = new CreateFxInformation(env);
			cfi.execute();
			//採番再セット
			cbi.reNumber(env);
		}
		if(env.getRunLevel() <= 1) {
			CreateAccountBase cab = new CreateAccountBase(env);
			cab.execute();
		}

	}

}

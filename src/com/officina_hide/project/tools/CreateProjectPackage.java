package com.officina_hide.project.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.tools.CreateBaseInformation;
import com.officina_hide.base.tools.CreateFxInformation;

/**
 * プロジェクト機能に関するパッケージを生成する。<br>
 * Generate a package for the project function.<br>
 * CREATE DATABASE PROJECT CHARACTER SET utf8;<br>
 * GRANT ALL ON PROJECT.* TO 'fdadmin'@'%' WITH GRANT OPTION;<br></p>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2022/02/22 Ver. 1.00
 */
public class CreateProjectPackage {

	public static void main(String[] args) {
		/*
		 * 1. プロジェクト共通環境設定
		 * 2. プロジェクト管理ベース構築
		 */
		//1.
		FD_EnvData env = new FD_EnvData();
		env.setRunLevel(0);
		env.setDbName("PROJECT");
		env.setActionUserID(I_FD_DB.SYSTEM_USER_ID);
		CreateBaseInformation cbi = new CreateBaseInformation(env);
		cbi.execute();
		CreateFxInformation cfi = new CreateFxInformation(env);
		cfi.execute();
		cbi.reNumber(env);
		//2.
		CreateProjectBase cpb = new CreateProjectBase(env);
		cpb.execute();
	}
}

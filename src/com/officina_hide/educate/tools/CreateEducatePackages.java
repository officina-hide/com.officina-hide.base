package com.officina_hide.educate.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.tools.CreateBaseInformation;

/**
 * 教育用パッケージ生成[Educational package generation]<br>
 * <p>データベース生成関連SQL<br>
 * CREATE DATABASE EDUCATE CHARACTER SET utf8;<br>
 * GRANT ALL ON EDUCATE.* TO 'fdadmin'@'%' WITH GRANT OPTION;<br></p>

 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/11/08 Ver. 1.00
 */
public class CreateEducatePackages {

	public static void main(String[] args) {
		/*
		 * 1. プロジェクト用環境セット
		 * 2. プロジェクト基盤構築
		 */
		//1.
		FD_EnvData env = new FD_EnvData();
		env.setRunLevel(0);
		env.setDbName("EDUCATE");
		env.setActionUserID(I_FD_DB.SYSTEM_USER_ID);
		//2.
		if(env.getRunLevel() == 0) {
			CreateBaseInformation cbi = new CreateBaseInformation(env);
			cbi.execute();
		}
	}

}

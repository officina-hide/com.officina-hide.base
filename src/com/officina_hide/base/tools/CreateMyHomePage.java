package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.I_FD_DB;

/**
 * officina-hide.netのホームページ用環境設定<br>
 * officina-hide.net homepage environment settings.
 * <p>データベース生成関連SQL<br>
 * CEATE DATABASE MYPAGE CHARACTER SET utf8;<br>
 * GRANT ALL ON MYPAGE.* TO 'fdadmin'@'%' WITH GRANT OPTION;<br></p>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/02 Ver.1.00
 */
public class CreateMyHomePage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * 1.プロジェクト用環境セット(0)
		 * 1-1. 基盤テーブル構築
		 * 1-2. 採番修正
		 * 2.ホームページ用環境セット(1)
		 * ※()内はRunLevel
		 * RunLevelが0の時、基盤ベースの初期設定を行う。
		 * RunLevelが1の時、ホームページ用の環境セット行う。
		 */
		FD_EnvData env = new FD_EnvData();
		env.setRunLevel(0);
		env.setDbName("MYPAGE");
		env.setActionUserID(I_FD_DB.SYSTEM_USER_ID);
		
		//RunLevelが0の時、基盤ベースの初期設定を行う。
		if(env.getRunLevel() == 0) {
			//1-1.
			CreateBaseInformation cbi = new CreateBaseInformation(env);
			cbi.execute();
			//1-2.
			cbi.reNumber(env);
		}
		if(env.getRunLevel() <= 1) {
			//2.
			CreateHomeInformation chi = new CreateHomeInformation();
			chi.execute(env);
		}
	}

}

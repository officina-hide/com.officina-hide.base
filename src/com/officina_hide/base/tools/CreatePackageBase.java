package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;

/**
 * プロジェクトの基盤情報を構築する。<br>
 * Build basic information for the project.<br>
 * 引数 : 1-処理レベル<br>
 * @author officina-hide.net
 * @version 1.50 新規作成
 * @since 2022/03/19 Ver. 1.50
 */
public class CreatePackageBase {

	/** 環境情報[Environment information] */
	private static FD_EnvData env = new FD_EnvData();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//処理レベルを引数から取得する。
		env.setRunLevel(args[0]);
		
		//基盤情報生成
		CreateBaseInformation cbi = new CreateBaseInformation(env);
		cbi.execute();
	}

}

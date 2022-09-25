package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * プロジェクトクラス[Project class]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/08/13 Ver. 1.00
 */
public class FD_Project {

	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクタ[Constructor]
	 * @param env 環境情報[Environment information]
	 */
	public FD_Project(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * コマンド取得[Getting command]
	 * @param command コマンド[Command]
	 */
	public void getCommand(String command) {
		
	}

}

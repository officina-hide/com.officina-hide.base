package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;

/**
 * 基盤情報生成クラス[Infrastructure information generation class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成
 * @since 2022/03/19 Ver. 1.50
 */
public class CreateBaseInformation {

	/** 項目 : 環境情報[Environment information] */
	private FD_EnvData env = new FD_EnvData();
	
	/**
	 * コンストラクタ[Constructor]
	 * @author officina-hide.net
	 * @since 2022/03/19 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 */
	public CreateBaseInformation(FD_EnvData env) {
		this.env = env;
	}

}

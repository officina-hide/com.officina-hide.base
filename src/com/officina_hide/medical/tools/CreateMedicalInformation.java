package com.officina_hide.medical.tools;

import com.officina_hide.base.common.FD_EnvData;

/**
 * 医療情報生成処理[Medical information generation processing]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/05/02 Ver. 1.00
 */
public class CreateMedicalInformation {

	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクター[Constructor]
	 * @author officina-hide.net
	 * @since 2022/05/02 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public CreateMedicalInformation(FD_EnvData env) {
		this.env = env;
	}

}

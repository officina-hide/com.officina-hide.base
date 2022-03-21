package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Numbering;

/**
 * 基盤情報生成クラス[Infrastructure information generation class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成
 * @since 2022/03/19 Ver. 1.50
 */
public class CreateBaseInformation {

	/** 項目 : 環境情報[Environment information] */
	private FD_EnvData env;
	
	/**
	 * コンストラクタ[Constructor]
	 * @author officina-hide.net
	 * @since 2022/03/19 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 */
	public CreateBaseInformation(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 生成実行[Generation execution].<br>
	 * <p>1. ID採番用テーブル構築、情報登録[ID numbering table construction, information registration]</p>
	 * @author officina-hide.com
	 * @since 2022/03/19 Ver. 1.50
	 */
	public void execute() {
		//1.
		FD_Numbering num = new FD_Numbering();
		num.createTable(env);
	}

}

package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.page.model.FM_Base;

/**
 * ホームページ用環境設定クラス[Homepage preference class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/02 Ver 1.00
 */
public class CreateHomeInformation {

	/**
	 * 構築実行[Build execution]
	 * @author officina-hide.net
	 * @since 2021/10/02 1.00
	 * @param env 環境情報[Enfironment information]
	 */
	public void execute(FD_EnvData env) {
		/*
		 * 1. ページ基本情報構築
		 */
		FM_Base base = new FM_Base();
		base.createTable(env);
		base.add(env, 0, "秀さんの情報工房");
	}

}

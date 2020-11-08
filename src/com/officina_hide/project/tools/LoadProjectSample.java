package com.officina_hide.project.tools;

import com.officina_hide.base.common.FD_EnvData;

/**
 * プロジェクト管理用情報クラス<br>
 * <p>プロジェクト管理に関係する情報の登録・保管を行う。<br>
 * 本クラスの目的は、枠組み構築に際して登録した情報の維持にある。</p>
 * @author officina-hide.com
 * @version 1.20
 * @since 2020/11/08
 */
public class LoadProjectSample {

	/**
	 * 情報を生成する<br>
	 * TODO 本メソッドは変更される予定
	 * @param env 環境情報
	 */
	public void createData(FD_EnvData env) {
		FDProject project = new FDProject();
		project.addData(env, "SDSS", "システム開発支援プロジェクト");
	}

}

package com.officina_hide.project.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * プロジェクト情報I/Oクラス<br>
 * @author officina-hide.com
 * @version 1.20
 * @since 2020/11/08
 */
public class X_FD_Project extends FD_DB implements I_FD_Project {

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、項目の初期化を行う。<br></p>
	 * @author officina-hide.com
	 * @since 1.20 2020/11/08
	 * @param env 環境情報
	 */
	public X_FD_Project(FD_EnvData env) {
		//項目初期化
		initializeItemList(env, TABLE_ID);
	}

	/**
	 * コンストラクター<br>
	 * <p>実体化時に、項目の初期化を行い、指定された情報IDを持つプロジェクト情報を抽出する。</p>
	 * @author officine-hide.com
	 * @since 1.21 2020/11/26
	 * @param env 環境情報
	 * @param projectId プロジェクト情報ID
	 */
	public X_FD_Project(FD_EnvData env, int projectId) {
		//項目初期化
		initializeItemList(env, TABLE_ID);
		//情報取得
		load(env, Table_Name, projectId);
	}

	/**
	 * 情報保存<br>
	 * @author officina-hide.com
	 * @since 1.20 2020/11/08
	 * @param env 環境情報
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

}

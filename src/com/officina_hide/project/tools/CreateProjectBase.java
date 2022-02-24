package com.officina_hide.project.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.project.model.FD_Project;

/**
 * プロジェクト機能基盤生成[Project function infrastructure generation]
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2022/02/24 Ver. 1.00
 */
public class CreateProjectBase {
	
	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクター[Constructor]
	 * @author officina-hide.net
	 * @since 2022/02/24 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public CreateProjectBase(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 構築実行[Build execution]
	 * @author officina-hide.net
	 * @since 2022/02/24 Ver. 1.00
	 */
	public void execute() {
		/*
		 * 1. プロジェクト情報テーブル生成
		 */
		//1.
		FD_Project pj = new FD_Project();
		pj.createTable(env);
	}

}

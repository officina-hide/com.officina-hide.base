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

	/*
	 * プロジェクト情報を生成する登録し、関連するタスク情報を登録する。<br>
	 * Fx画面では、プロジェクトでタスク一覧を表示する。ことができるようにする。<br>
	 * すべて登録後に、登録した情報をバックアップする。
	 */
	
	
	/**
	 * 情報を生成する<br>
	 * TODO 本メソッドは変更される予定
	 * @param env 環境情報
	 */
	public void createData(FD_EnvData env) {
		// TODO 書式設定方法の変更中(2020/11/20)
//		//プロジェクト情報登録
//		FDProject project = new FDProject();
//		int projectId = project.addData(env, "SDSS", "システム開発支援プロジェクト", "SDSS_0000");
//		//タスク情報登録
//		FDTask task = new FDTask();
//		task.addData(env, projectId);	
	}

}

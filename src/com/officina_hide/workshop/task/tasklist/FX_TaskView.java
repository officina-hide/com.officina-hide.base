package com.officina_hide.workshop.task.tasklist;

import com.officina_hide.base.common.FD_EnvData;

import javafx.application.Application;
import javafx.stage.Stage;

public class FX_TaskView extends Application {

	/** 環境情報 */
	private FD_EnvData env;
	/** Fx画面情報ID */
	private int FxViewID;
	
	@Override
	public void start(Stage stage) throws Exception {
		/*
		 * 本画面が呼び出される際に対象テーブルの情報IDがセットされていた場合は、既登録の情報を抽出し画面にセットする。
		 * 画面にセットする項目は、画面情報・画面項目情報から取得する。
		 */
		stage.showAndWait();
	}

	public void setEnv(FD_EnvData env) {
		this.env = env;
	}
	public void setFxViewID(int fxViewID) {
		FxViewID = fxViewID;
	}

}

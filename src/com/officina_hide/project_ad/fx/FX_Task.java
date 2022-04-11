package com.officina_hide.project_ad.fx;

import com.officina_hide.base.common.FD_EnvData;

import javafx.application.Application;
import javafx.stage.Stage;

public class FX_Task extends Application {
	/** 環境情報[Environment information] */
	private FD_EnvData env;

	@Override
	public void start(Stage stage) throws Exception {
		//環境情報取得
		env = new FD_EnvData("Project_AD.prop");
		
		
//		X_FX_View view = new X_FX_View(env, where);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

package com.officina_hide.fx.view;

import com.officina_hide.base.common.FD_EnvData;

import javafx.application.Application;
import javafx.stage.Stage;

public class V_Main_View extends Application {

	/** 環境情報 */
	private FD_EnvData env;
	
	public V_Main_View(FD_EnvData env) {
		this.env = env;
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		stage.show();
	}

}

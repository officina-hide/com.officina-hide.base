package com.officina_hide.workshop.task.tasklist;

import com.officina_hide.base.common.FD_EnvData;

import javafx.application.Application;
import javafx.stage.Stage;

public class FX_TaskView extends Application {

	private FD_EnvData env;
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.showAndWait();
	}

	public void setEnv(FD_EnvData env) {
		this.env = env;
	}

}

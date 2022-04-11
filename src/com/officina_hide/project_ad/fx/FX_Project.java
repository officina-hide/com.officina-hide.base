package com.officina_hide.project_ad.fx;

import com.officina_hide.base.common.FD_EnvData;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FX_Project extends Application {
	/** 環境情報[Environment information] */
	private  FD_EnvData env;

	@Override
	public void start(Stage stage) throws Exception {
		//環境情報取得
		env = new FD_EnvData("Project_AD.prop");
		
		VBox root = new VBox(5);
		setItem(root);
		Scene scene = new Scene(root, 600, 400);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * 画面項目セット
	 * @param root
	 */
	private void setItem(VBox root) {
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}

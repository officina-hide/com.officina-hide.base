package com.officina_hide.project_ad.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Fx_Project extends Application {

	@Override
	public void start(Stage stage) throws Exception {
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

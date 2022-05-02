package com.officina_hide.medical.ui;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FX_InspectionResult_View extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox(5);
		
		//画面項目設定
		DisplayInitializing(root);
		
		Scene scene = new Scene(root, 400, 300);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * 画面初期化[Screen initializing]<br>
	 * @author officina-hide.net
	 * @since 2022/05/02 Ver. 1.00
	 * @param root ルート[root]
	 */
	private void DisplayInitializing(VBox root) {
		//検査日時
		HBox dateBox = new HBox(5);
		root.getChildren().add(dateBox);
		DatePicker insDate = new DatePicker();
		insDate.setValue(LocalDate.now());
		ComboBox<Integer> hourList = new ComboBox<>();
		for(int ix = 0; ix <= 23; ix++) {
			hourList.getItems().add(ix);
		}
		dateBox.getChildren().addAll(insDate, hourList);
	}

	public static void main(String[] args) {
		launch(args);
	}

}

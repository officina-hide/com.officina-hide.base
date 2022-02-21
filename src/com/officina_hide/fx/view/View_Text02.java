package com.officina_hide.fx.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View_Text02 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox(5);
		root.setPadding(new Insets(5, 5, 5, 5));

		Pane pane = new Pane();
		root.getChildren().add(pane);
		
		Text text = new Text(10, 100, "Text");
		text.setFont(new Font("Meiryo UI", 12));
		pane.getChildren().add(text);
		pane.getChildren().add(new Line(10, 10, 100, 100));
		
		//		Canvas can = new Canvas();
//		root.getChildren().add(can);
//		GraphicsContext con = can.getGraphicsContext2D();
//		//		Group group = new Group();
//
		
		Scene scene = new Scene(root, 300, 300);
		stage.setScene(scene);
		stage.show();
		
	}

}

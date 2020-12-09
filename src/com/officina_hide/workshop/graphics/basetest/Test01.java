package com.officina_hide.workshop.graphics.basetest;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Test01 extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Group root = new Group();
		
		Canvas canvas = new Canvas(100, 100);
		root.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.GRAY);
		gc.strokeRect(0, 0, 100, 100);
		Rectangle rect = new Rectangle(10, 10, 30, 30);
		root.getChildren().add(rect);
		rect.setOnMouseClicked(event->{
			System.out.println("cliscked");
		});
		
		Scene scene = new Scene(root, 400, 200);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

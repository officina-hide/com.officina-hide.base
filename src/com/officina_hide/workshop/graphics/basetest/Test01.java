package com.officina_hide.workshop.graphics.basetest;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * [Test01]四角のフレームを画面の大きさに合わせて表示する。<br>
 * Display a square frame according to the size of the screen.<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/12/09
 */
public class Test01 extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Group root  = new Group();
		System.out.println(root.getLayoutBounds());
		
		int preWidth = 400;
		int preHeight = 200;
		
		Canvas canvas = new Canvas(preWidth, preHeight);
		root.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.GRAY);
		gc.strokeRect(10, 10, preWidth - 20, preHeight - 20);
		Rectangle rect = new Rectangle(10, 10, 30, 30);
		root.getChildren().add(rect);
	
		Scene scene = new Scene(root, preWidth, preHeight);
		System.out.println(root.getLayoutBounds());
		scene.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				gc.clearRect(10, 10, oldValue.intValue(), preHeight - 20);
				gc.strokeRect(10, 10, newValue.intValue() - 20, preHeight - 20);
				canvas.setWidth(newValue.doubleValue());
			}
		});

		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

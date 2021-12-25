package com.officina_hide.fx.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *グラフィックテスト
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/12/21 Ver. 1.00
 */
public class View_Test01 extends Application {

	protected static final int CELL_SIZE = 8;
	protected static final int MAP_CELL_COUNT = 32;
	
	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox(5);
		root.setPadding(new Insets(5, 5, 5, 5));

		int width = CELL_SIZE * MAP_CELL_COUNT;
		Canvas can = new Canvas();
		root.getChildren().add(can);
		can.setHeight(width + 1);
		can.setWidth(width + 1);
		
		GraphicsContext con = can.getGraphicsContext2D();
		con.setStroke(Color.GRAY);
		con.setLineWidth(0.5);
		con.strokeRect(0, 0, width, width);
		con.strokeLine(7, 1, 7, 256);
		con.strokeLine(1, 7, 256, 7);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}

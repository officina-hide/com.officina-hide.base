package com.officina_hide.fx.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View_Text02 extends Application {

	/** 標準フォント */
	private Font font12 = new Font("Meiryo UI", 16);
	/** 表サイズ */
	private long OUTER_WIDTH = 400;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox(5);
		root.setPadding(new Insets(5, 5, 5, 5));

		Pane pane = new Pane();
		root.getChildren().add(pane);
		
		Rectangle outer = new Rectangle(10, 10, OUTER_WIDTH, 200);
		outer.setFill(null);
		outer.setStroke(Color.GREY);
		outer.setStrokeWidth(2);
		pane.getChildren().add(outer);
		pane.getChildren().add(getText(24, 32, "項目Test", font12));
		pane.getChildren().add(new Line(24, 10, 24, 40));
		int x = 65;
		pane.getChildren().add(new Line(24 + x, 10, 24 + x, 40));
		pane.getChildren().add(new Line(10, 40, OUTER_WIDTH + 10, 40));
		pane.getChildren().add(new Line(200, 10, 200, 210));
		
		Scene scene = new Scene(root, 500, 400);
		stage.setScene(scene);
		stage.show();
		
	}

	/**
	 * テキスト設定[Text settings]
	 * @param x 表示x位置[Display x position]
	 * @param y 表示y位置[Display y position]
	 * @param string
	 * @param font
	 * @return
	 */
	private Node getText(int x, int y, String string, Font font) {
		Text text = new Text(x, y, string);
		text.setFont(font);
		System.out.println(text.getLayoutBounds().getWidth());
		return text;
	}

}

package com.officina_hide.fx.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class View_Text02 extends Application {

	/** プロジェクト名 */
	private String projectName;
	/** 標準フォント */
	private Font font12 = new Font("Meiryo UI", 14);
	/** 表サイズ */
	private long OUTER_WIDTH = 400;
	/** 項目幅 */
	private long ITEM_WIDTH = 200;

	@Override
	public void start(Stage stage) throws Exception {
		/** 環境設定 */
		projectName = "テストプロジェクト";
		
		VBox root = new VBox(5);
		root.setPadding(new Insets(5, 5, 5, 5));

		Pane pane = new Pane();
		root.getChildren().add(pane);
		
		Rectangle outer = new Rectangle(0, 0, OUTER_WIDTH, 200);
		outer.setFill(null);
		outer.setStroke(Color.GREY);
		outer.setStrokeWidth(2);
		pane.getChildren().add(outer);
		pane.getChildren().add(setText(0, 0, ITEM_WIDTH, TextAlignment.CENTER, "項　目", font12));
//		pane.getChildren().add(new Line(24, 10, 24, 40));
		int x = 65;
//		pane.getChildren().add(new Line(24 + x, 10, 24 + x, 40));
		pane.getChildren().add(new Line(0, 40, OUTER_WIDTH, 40));
		pane.getChildren().add(new Line(ITEM_WIDTH, 0, ITEM_WIDTH, 200));
//		pane.getChildren().add(new Line(216, 10, 216, 210));
		
		Scene scene = new Scene(root, 500, 400);
		stage.setScene(scene);
		stage.show();
		
	}

	/**
	 * テキスト設定[Text settings]
	 * @param x 表示x位置[Display x position]
	 * @param y 表示y位置[Display y position]
	 * @param itemWidth 項目幅[Item width] 
	 * @param alignment 表示位置[Display alignment]
	 * @param text 表示文字列[Display string]
	 * @param font 表示文字[Display font]
	 * @return テキストノード[Text node]
	 */
	private Node setText(int x, int y, long itemWidth, TextAlignment alignment, String textStrng, Font font) {
		Text text = new Text(textStrng);
		text.setFont(font);
		text.setTextOrigin(VPos.TOP);
		System.out.println(text.getLayoutBounds().getHeight());
		text.setY(y + 2);
		text.setX((itemWidth - text.getLayoutBounds().getWidth()) / 2);
		return text;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

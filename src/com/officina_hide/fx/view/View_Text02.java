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

	private Font font12 = new Font("Meiryo UI", 16);
	
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
		text.setOnMouseClicked(event->{
			System.out.println("text");
		});
		Rectangle outer = new Rectangle(10, 10, 200, 200);
		outer.setFill(null);
		outer.setStroke(Color.GREY);
		outer.setStrokeWidth(2);
		pane.getChildren().add(outer);
//		pane.getChildren().add(text);
		pane.getChildren().add(getText(24, 32, "項目", font12));
		pane.getChildren().add(new Line(10, 40, 210, 40));
		
		Scene scene = new Scene(root, 300, 300);
		stage.setScene(scene);
		stage.show();
		
	}

	private Node getText(int x, int y, String string, Font font) {
		Text text = new Text(x, y, string);
		text.setFont(font);
		return text;
	}

}

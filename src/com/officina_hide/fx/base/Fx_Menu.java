package com.officina_hide.fx.base;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 基本メニュー[Basic menu]<br>
 * @author officina-hide.com
 * @version 1.00
 * @since 2021/05/22
 */
public class Fx_Menu extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		HBox root = new HBox();
		root.setPadding(new Insets(10, 10, 10, 10));
		
		MenuItem sysCreateTableItem = new MenuItem("テーブル生成");
		sysCreateTableItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sysCreateTable();
			}
		});
		
		MenuButton systemButton = new MenuButton("システム管理", null, sysCreateTableItem);
		systemButton.setPrefWidth(100);
		systemButton.setAlignment(Pos.CENTER);
		MenuButton dataButton = new MenuButton("情報管理");
		dataButton.setPrefWidth(100);
		dataButton.setAlignment(Pos.CENTER);
		root.getChildren().add(systemButton);
		root.getChildren().add(dataButton);
		
		Scene scene = new Scene(root, 300, 100);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * テーブル生成画面遷移[Table generation screen transition]<br>
	 * @author officina-hide.com
	 * @since 1.00 2021/05/22
	 */
	protected void sysCreateTable() {
		try {
			FxCreateTable ct = new FxCreateTable();
			ct.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		};
	}

	public static void main(String[] args) {
		launch(args);
	}

}

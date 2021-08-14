package com.officina_hide.fx.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.officina_hide.base.common.FD_EnvData;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 基本メニュー[Basic menu]<br>
 * @author officina-hide.com
 * @version 1.00
 * @since 2021/05/22
 */
public class Fx_Menu extends Application {
	
	/** 環境情報 */
	FD_EnvData env = new FD_EnvData();
	
	@Override
	public void start(Stage stage) throws Exception {
		/*
		 * 環境情報取得
		 */
		env.initialize();
		
		HBox root = new HBox();
		root.setPadding(new Insets(10, 10, 10, 10));
		
		MenuItem sysTableInformation = new MenuItem("テーブル情報");
		sysTableInformation.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sysCreateTable();
			}
		});
		
		MenuButton systemButton = new MenuButton("システム管理", null, sysTableInformation);
		systemButton.setPrefWidth(120);
		systemButton.setAlignment(Pos.CENTER);
		systemButton.setFont(new Font("Meiryo UI", 12));
		MenuButton dataButton = new MenuButton("情報管理");
		dataButton.setPrefWidth(100);
		dataButton.setAlignment(Pos.CENTER);
		root.getChildren().add(systemButton);
		root.getChildren().add(dataButton);
		
		Scene scene = new Scene(root, 300, 100);
		stage.setScene(scene);
		stage.show();
	}
//
//	/**
//	 * テーブル生成画面遷移[Table generation screen transition]<br>
//	 * @author officina-hide.com
//	 * @since 1.00 2021/05/22
//	 */
//	protected void sysCreateTable() {
//		try {
//			FxCreateTable ct = new FxCreateTable(env);
//			ct.start(new Stage());
//		} catch (Exception e) {
//			e.printStackTrace();
//		};
//	}

	/**
	 * テーブル情報画面遷移[Table information screen transition]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/07/06
	 */
	protected void sysCreateTable() {
		try {
			Class<?> clazz = Class.forName("com.officina_hide.fx.base.Fx_FD_Table_List");
			Constructor<?> constructor = clazz.getConstructor(FD_EnvData.class);
			Object instance = constructor.newInstance(env);
			Method method = clazz.getMethod("start", Stage.class);
			method.invoke(instance, new Stage());
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}

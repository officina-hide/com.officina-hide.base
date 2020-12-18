package com.officina_hide.workshop.task.tasklist;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FX_TaskList extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox();
		root.setPadding(new Insets(5, 5, 5, 5));
		
		/*
		 * 日付項目については、表示の書式を設定する関係上、テーブル項目用のクラスが必要
		 */
		
		TableView<TaskTableData> table = new TableView<TaskTableData>();
		TableColumn<TaskTableData, String> titleCol = new TableColumn<TaskTableData, String>("タイトル");
		TableColumn<TaskTableData, Calendar> sdateCol = new TableColumn<TaskTableData, Calendar>("開始日");
		titleCol.setCellValueFactory(new PropertyValueFactory<TaskTableData, String>("title"));
		sdateCol.setCellValueFactory(new PropertyValueFactory<TaskTableData, Calendar>("startDate"));
		table.getColumns().add(titleCol);
		table.getColumns().add(sdateCol);
		root.getChildren().add(table);
		
		Calendar cal = new GregorianCalendar(new Locale("ja", "JP"));
		cal.setTime(new Date());
		TaskTableData data01 = new TaskTableData("AAAAA", cal);
		System.out.println(data01.getStartDate());
		table.getItems().add(data01);	
		Scene scene = new Scene(root, 400, 300);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

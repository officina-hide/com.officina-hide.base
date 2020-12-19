package com.officina_hide.workshop.task.tasklist;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.officina_hide.base.common.FD_Date;

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
		TableColumn<TaskTableData, FD_Date> sdateCol = new TableColumn<TaskTableData, FD_Date>("開始日");
		titleCol.setCellValueFactory(new PropertyValueFactory<TaskTableData, String>("title"));
		sdateCol.setCellValueFactory(new PropertyValueFactory<TaskTableData, FD_Date>("startDate"));
		table.getColumns().add(titleCol);
		table.getColumns().add(sdateCol);
		root.getChildren().add(table);
		
		Calendar cal = new GregorianCalendar(new Locale("ja", "JP"));
		cal.setTime(new Date());
		TaskTableData data01 = new TaskTableData("AAAAA", cal);
		table.getItems().add(data01);	
		Scene scene = new Scene(root, 400, 300);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

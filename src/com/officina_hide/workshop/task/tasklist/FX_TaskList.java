package com.officina_hide.workshop.task.tasklist;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.officina_hide.base.common.FD_Date;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FX_TaskList extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox(5);
		root.setPadding(new Insets(5, 5, 5, 5));
		root.setStyle("-fx-font-size:12.0;-fx-font-family:MeiryoUI");
		
		/*
		 * 日付項目については、表示の書式を設定する関係上、テーブル項目用のクラス(FD_Date)を作成。<br>
		 * 
		 */
		
		TableView<TaskTableData> table = new TableView<TaskTableData>();
		TableColumn<TaskTableData, Boolean> checkCol = new TableColumn<TaskTableData, Boolean>("check");
		TableColumn<TaskTableData, String> comboCol = new TableColumn<>("状況");
		TableColumn<TaskTableData, String> titleCol = new TableColumn<TaskTableData, String>("タイトル");
		TableColumn<TaskTableData, FD_Date> sdateCol = new TableColumn<TaskTableData, FD_Date>("開始日");
		
		ObservableList<String> list = FXCollections.observableArrayList("OK","ERROR");
		
		comboCol.setCellValueFactory(new PropertyValueFactory<TaskTableData, String>("status"));
		comboCol.setCellFactory(ComboBoxTableCell.forTableColumn(list));
		comboCol.setPrefWidth(100);

		
		
		checkCol.setCellValueFactory(new PropertyValueFactory<TaskTableData, Boolean>("check"));
		checkCol.setCellFactory(CheckBoxTableCell.forTableColumn(checkCol));	
		titleCol.setCellValueFactory(new PropertyValueFactory<TaskTableData, String>("title"));
		titleCol.setCellFactory(TextFieldTableCell.forTableColumn());
		titleCol.setPrefWidth(100);
		sdateCol.setCellValueFactory(new PropertyValueFactory<TaskTableData, FD_Date>("startDate"));
		table.getColumns().add(checkCol);
		table.getColumns().add(comboCol);
		table.getColumns().add(titleCol);
		table.getColumns().add(sdateCol);
		root.getChildren().add(table);
		table.setEditable(true);
		
		//ボタン追加
		Button button = new Button("登録");
		root.getChildren().add(button);
		button.setOnMouseClicked(event->{
		});
		
		//データ追加
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

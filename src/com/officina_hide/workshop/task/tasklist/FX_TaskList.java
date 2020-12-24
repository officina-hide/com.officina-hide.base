package com.officina_hide.workshop.task.tasklist;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.officina_hide.base.common.FD_Date;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

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
		checkCol.setStyle("-fx-alignment: center;");
		TableColumn<TaskTableData, String> comboCol = new TableColumn<>("状況");
		TableColumn<TaskTableData, String> titleCol = new TableColumn<TaskTableData, String>("タイトル");
		TableColumn<TaskTableData, FD_Date> sdateCol = new TableColumn<TaskTableData, FD_Date>("開始日");
		
		ObservableList<String> list = FXCollections.observableArrayList("OK","ERROR");
		
		checkCol.setCellValueFactory(new PropertyValueFactory<TaskTableData, Boolean>("check"));
		checkCol.setCellFactory(column -> new TableCell<TaskTableData, Boolean>() {
		        public void updateItem(Boolean check, boolean empty) {
		            super.updateItem(check, empty);
		            if (check == null || empty) {
		                setGraphic(null);
		            } else {
		                CheckBox box = new CheckBox();
		                box.setSelected(column.getCellData(getIndex()));
		                box.setOnAction(event->{
		                	System.out.println(box.isSelected());
		                	System.out.println(column.getCellData(getIndex()));
		                });
		                setGraphic(box);
		            }
		        }
		    });
       
		comboCol.setCellValueFactory(new PropertyValueFactory<TaskTableData, String>("status"));
		comboCol.setCellFactory(ComboBoxTableCell.forTableColumn(list));
		comboCol.setPrefWidth(100);
		comboCol.setOnEditCommit(event->{
			System.out.println(event.getNewValue());
			TaskTableData tdata = event.getTableView().getItems().get(1);
			tdata.setStatus(event.getNewValue());
		});
		
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
//			int idx = table.getSelectionModel().getSelectedIndex();
//			if(idx == -1) {
//				return;
//			}
		});
		
		//データ追加
		Calendar cal = new GregorianCalendar(new Locale("ja", "JP"));
		cal.setTime(new Date());
		TaskTableData data01 = new TaskTableData("AAAAA", cal);
		table.getItems().add(data01);	
		table.getItems().add(data01);	

		Scene scene = new Scene(root, 400, 300);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

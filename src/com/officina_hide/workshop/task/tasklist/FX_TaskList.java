package com.officina_hide.workshop.task.tasklist;

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
		
		TableView<TaskTableData> table = new TableView<TaskTableData>();
		TableColumn<TaskTableData, String> titleCol = new TableColumn<TaskTableData, String>("タイトル");
		titleCol.setCellValueFactory(new PropertyValueFactory<TaskTableData, String>("title"));
		table.getColumns().add(titleCol);
		root.getChildren().add(table);
		
		table.getItems().add(new TaskTableData("aaaaa", "2020/12/18"));		
		Scene scene = new Scene(root, 400, 300);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

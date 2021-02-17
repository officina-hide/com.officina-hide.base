package com.officina_hide.workshop.task.tasklist;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import com.officina_hide.base.common.FDSQLWhere;
import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_Date;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Task;
import com.officina_hide.base.model.X_FD_Task;
import com.officina_hide.fx.model.I_FD_View;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FX_TaskList extends Application {

	private static FD_EnvData env;
	
	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox(5);
		root.setPadding(new Insets(5, 5, 5, 5));
		root.setStyle("-fx-font-size:12.0;-fx-font-family:MeiryoUI");
		
		/*
		 * タスク情報取得
		 */
		List<X_FD_Task> tasklist = getDataList();
		
		/*
		 * 日付項目については、表示の書式を設定する関係上、テーブル項目用のクラス(FD_Date)を作成。<br>
		 * 
		 */
		
		TableView<TaskTableData> table = new TableView<TaskTableData>();
		table.setOnMouseClicked(event->{
			//左ボタンダブルクリックで単票画面へ遷移する。
			if(event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
				FD_DB_Utility dbUtil = new FD_DB_Utility();
				FDSQLWhere where = new FDSQLWhere(I_FD_View.COLUMNNAME_View_Name, "Task_View");
				/*
				 * クリックした情報のIDを取得する。
				 */
				int selectNo = table.getSelectionModel().getSelectedIndex();
				TaskTableData data = table.getItems().get(selectNo);
				System.out.println(data.getTitle());
				
				FX_TaskView taskview = new FX_TaskView();
				taskview.setEnv(env);
				taskview.setFxViewID(dbUtil.getId(env,I_FD_View.Table_Name, where));
				try {
					taskview.start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		TableColumn<TaskTableData, Boolean> checkCol = new TableColumn<TaskTableData, Boolean>("selRow");
		checkCol.setStyle("-fx-alignment: center;");
		TableColumn<TaskTableData, String> comboCol = new TableColumn<>("状況");
		TableColumn<TaskTableData, String> titleCol = new TableColumn<TaskTableData, String>("タイトル");
		TableColumn<TaskTableData, FD_Date> sdateCol = new TableColumn<TaskTableData, FD_Date>("開始日");
		
		ObservableList<String> list = FXCollections.observableArrayList("OK","ERROR");
		
		checkCol.setCellValueFactory(new PropertyValueFactory<TaskTableData, Boolean>("selRow"));
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
//		table.setEditable(true);
		
		//ボタン追加
		Button button = new Button("登録");
		root.getChildren().add(button);
		button.setOnMouseClicked(event->{
			System.out.println(table.getItems().size());
		});
		
		//データ追加
		Calendar cal = new GregorianCalendar(new Locale("ja", "JP"));
		cal.setTime(new Date());
		TaskTableData data01 = new TaskTableData("AAAAA", cal);
		TaskTableData data02 = new TaskTableData("AAABB", cal);
		table.getItems().add(data01);
		table.getItems().add(data02);	

		Scene scene = new Scene(root, 400, 300);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * タスク情報の抽出[Extraction of task information]<br>
	 * @author officine-hide.com
	 * @since 1.31 2021/02/17
	 * @return タスク情報リスト
	 */
	private List<X_FD_Task> getDataList() {
		FD_DB DB = new FD_DB();
		List<X_FD_Task> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ").append(I_FD_Task.Table_Name).append(" ");
		DB.connection(env);
		return list;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void main(FD_EnvData envData) {
		String[] args = null;
		env = envData;
		launch(args);
	}

}

package com.officina_hide.fx.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.X_FD_Table;
import com.officina_hide.fx.model.Fx_ToolButtonArea;
import com.officina_hide.fx.model.I_Fx_Fields;
import com.officina_hide.fx.model.X_Fx_Field;
import com.officina_hide.fx.model.X_Fx_View;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * テーブル情報一覧画面[Table information list screen]<br>
 * @author officina-hide.net 
 * @version 1.01
 * @since 2021/08/14
 */
public class Fx_FD_Table_List extends Application {

	/** DBクラス */
	private FD_DB DB = new FD_DB();
	/** 環境情報 */
	private FD_EnvData env = new FD_EnvData();
	/** 画面情報 */
	private X_Fx_View view;
	/** 一覧ビュー */
	@SuppressWarnings("rawtypes")
	private TableView<Map> table;
//	/** 「表示」ボタン */
//	private static final String Fx_Disp_Button = "表示";
//	/** 「新規ボタン」 */
//	private static final String Fx_New_Button = "新規";
	/** ボタン領域情報 */
	private Fx_ToolButtonArea tba;
	/** テーブル選択位置 */
	private int viewNo = -1;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * 実体化時に環境情報をセットする。[Set environment information at the time of class instance.]<br>
	 * @param env 環境情報 [Environment informatioin]
	 * @param viewId 画面情報ID[Screen Information ID]
	 */
	public Fx_FD_Table_List(FD_EnvData env, Integer viewId) {
		//環境情報セット[Environmental information set]
		this.env = env;
		//画面情報取得
		view = new X_Fx_View(env, viewId);
		//画面項目一覧取得
		FD_WhereData where = new FD_WhereData(I_Fx_Fields.COLUMNNAME_Fx_View_ID, viewId);
		List<X_Fx_Field> fields = X_Fx_Field.getList(env, where);
		//ツールバーボタン設定
		try {
			tba = new Fx_ToolButtonArea();
			tba.getButtonData(Fx_ToolButtonArea.Fx_Disp_Button).setMethod(this.getClass().getMethod("dispSelected", ActionEvent.class));
			tba.getButtonData(Fx_ToolButtonArea.Fx_Disp_Button).setClazz(this);
			tba.getButtonData(Fx_ToolButtonArea.Fx_New_Button).setMethod(this.getClass().getMethod("newSelected", ActionEvent.class));
			tba.getButtonData(Fx_ToolButtonArea.Fx_New_Button).setClazz(this);
			tba.getButtonData(Fx_ToolButtonArea.Fx_Save_Button).setActive(false);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		//テーブル情報取得
		List<X_FD_Table> list = getTableList();
		VBox root = new VBox(5);
		root.setPadding(new Insets(5, 5, 5, 5));
		
		//ボタン領域表示
		root.getChildren().add(tba.createNode());
		
		//スプリット領域生成（上下で親と子の一覧を表示する。)
		SplitPane mainSprit = new SplitPane();
		mainSprit.setOrientation(Orientation.VERTICAL);
		mainSprit.setDividerPositions(0.7, 0.3);
		root.getChildren().add(mainSprit);
		
		//初期表示では一覧を表示する。
		mainSprit.getItems().add(createTableView());
		
//		table = new TableView<>();
//		mainSprit.getItems().add(table);
//		TableColumn<Map, String> TableName = new TableColumn<>("テーブル物理名");
//		TableName.setCellValueFactory(new MapValueFactory<>(I_FD_Table.COLUMNNAME_FD_Table_Name));
//		TableColumn<Map, String> Name = new TableColumn<>("テーブル表示名");
//		Name.setCellValueFactory(new MapValueFactory<>(I_FD_Table.COLUMNNAME_FD_Name));
//		TableColumn<Map, String> TableDescription = new TableColumn<>("テーブル説明");
//		TableDescription.setCellValueFactory(new MapValueFactory<>(I_FD_Table.COLUMNNAME_FD_Description));
//		table.getColumns().add(TableName);
//		table.getColumns().add(Name);
//		table.getColumns().add(TableDescription);
//		
//		for(X_FD_Table data : list) {
//			Map<String, String> map = new HashMap<>();
//			map.put(I_FD_Table.COLUMNNAME_FD_Table_Name, data.getFD_Table_Name());
//			map.put(I_FD_Table.COLUMNNAME_FD_Name, data.getFD_Name());
//			map.put(I_FD_Table.COLUMNNAME_FD_Description, data.getFD_Description());
//			map.put(I_FD_Table.COLUMNNAME_FD_Table_ID, Integer.toString(data.getFD_Table_ID()));
//			table.getItems().add(map);
//		}
//		table.setOnMouseClicked(event ->{
//			tableclicked(event);
//		});
		
//		//タブ表示
//		SplitPane sp = new SplitPane();
//		sp.setOrientation(Orientation.VERTICAL);
//		sp.setDividerPositions(0.8, 0.2);
//		root.getChildren().add(sp);
		TabPane tabPane = new TabPane();
		Tab tab1 = new Tab("Column");
		tab1.setClosable(false);
		tabPane.getTabs().add(tab1);
		mainSprit.getItems().add(tabPane);
		
		Scene scene = new Scene(root, 700, 400);
		stage.setScene(scene);
		//タイトルセット[Title set]
		stage.setTitle(view.getFD_Name());
		//画面をユーザー対応待ちとして表示する。
		stage.show();
	}

	/**
	 * 一覧画面表示[List screen display]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/11
	 * @return 一覧画面ノード[List Screen Node]
	 */
	private Node createTableView() {
		table = new TableView<>();
		//テーブル項目名表示
		
		return table;
	}

	/**
	 * 表示ボタンクリック時処理[Processing when the display button is clicked]<br>
	 * @author officina-hide.net
	 * @since 1.00 2021/08/14
	 * @param event
	 */
	@SuppressWarnings("rawtypes")
	public void dispSelected(ActionEvent event) {
		//未選択時は処理を抜ける。[If not selected, the process is exited.]
		viewNo = table.getSelectionModel().getSelectedIndex();
		if(viewNo == -1) {
			return;
		}
		//選択されたオブジェクトを取得する。[Gets the selected object.]
		ObservableList<Map> items = table.getSelectionModel().getSelectedItems();
		System.out.println("selected"+": viewNo "+viewNo+":"+items);
		Map map = items.get(0);
		/*
		 * テーブル情報の照会画面に遷移する。<br>
		 * Move to the table information inquiry screen.
		 */
		StringBuffer clazzName = new StringBuffer();
		try {
			clazzName.append("com.officina_hide.fx.base").append(".").append("Fx_")
				.append(I_FD_Table.Table_Name).append("_View");
			Class<?> clazz = Class.forName(clazzName.toString());
			Constructor<?> constructor = clazz.getConstructor(FD_EnvData.class, Integer.class);
			Object instance = constructor.newInstance(env,
					Integer.parseInt(map.get(I_FD_Table.COLUMNNAME_FD_Table_ID).toString()));
			Method method = clazz.getMethod("start", Stage.class);
			method.invoke(instance, new Stage());
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 新規ボタンクリック時の処理<br>
	 * Processing when a new button is clicked.
	 * @author officine-hide.net
	 * @since 1.00 2021/08/16
	 * @param event イベント情報[event information]
	 */
	public void newSelected(ActionEvent event) {
		/*
		 * テーブル情報の照会画面に遷移する。<br>
		 * Move to the table information inquiry screen.
		 */
		StringBuffer clazzName = new StringBuffer();
		try {
			clazzName.append("com.officina_hide.fx.base").append(".").append("Fx_")
				.append(I_FD_Table.Table_Name).append("_View");
			Class<?> clazz = Class.forName(clazzName.toString());
			Constructor<?> constructor = clazz.getConstructor(FD_EnvData.class, Integer.class);
			Object instance = constructor.newInstance(env, 0);
			Method method = clazz.getMethod("start", Stage.class);
			method.invoke(instance, new Stage());
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
//	
//	/**
//	 * テーブルクリック時処理[Processing when clicking a table]<br>
//	 * @author officine-hide.net
//	 * @since 1.00 2021/08/05
//	 * @param event イベント情報
//	 */
//	private void tableclicked(MouseEvent event) {
//		System.out.println("Clicked!!");
////		//「表示」ボタン活性化
////		dispButton.setDisable(false);
//		//選択位置の保管
////		viewNo = table.getSelectionModel().getSelectedIndex();
//	}

	/**
	 * テーブル情報リスト取得[Get table information list]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/07/12
	 * @return テーブル情報リスト
	 */
	private List<X_FD_Table> getTableList() {
		List<X_FD_Table> list = new ArrayList<>();
		List<Integer> ids = DB.getAllId(I_FD_Table.Table_Name, "1 = 1", env);
		for(int id: ids) {
			X_FD_Table table = new X_FD_Table(env, id);
			list.add(table);
		}
		return list;
	}
}

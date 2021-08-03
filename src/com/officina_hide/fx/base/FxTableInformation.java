package com.officina_hide.fx.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.X_FD_Table;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * テーブル情報画面[Table Information Screen]<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2021/07/06
 */
public class FxTableInformation extends Application {
	/** DBクラス */
	private FD_DB DB = new FD_DB();
	/** 環境情報 */
	private FD_EnvData env = new FD_EnvData();

	/**
	 * インスタンス時に、環境情報をセットする。<br>
	 * Set environment information at the time of instance.<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/07/06
	 * @param env 環境情報[Environment Information]
	 */
	public FxTableInformation(FD_EnvData env) {
		this.env = env;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void start(Stage stage) throws Exception {
		//テーブル情報取得
		List<X_FD_Table> list = getTableList();
		VBox root = new VBox(5);
		TableView<Map> table = new TableView<>();
		root.getChildren().add(table);
		
		TableColumn<Map, String> TableName = new TableColumn<>("テーブル物理名");
		TableName.setCellValueFactory(new MapValueFactory<>(I_FD_Table.COLUMNNAME_FD_Table_Name));
		TableColumn<Map, String> Name = new TableColumn<>("テーブル表示名");
		Name.setCellValueFactory(new MapValueFactory<>(I_FD_Table.COLUMNNAME_FD_Name));
		TableColumn<Map, String> TableDescription = new TableColumn<>("テーブル説明");
		TableDescription.setCellValueFactory(new MapValueFactory<>(I_FD_Table.COLUMNNAME_FD_Description));
		table.getColumns().add(TableName);
		table.getColumns().add(Name);
		table.getColumns().add(TableDescription);
		
		for(X_FD_Table data : list) {
			Map<String, String> map = new HashMap<>();
//			System.out.println(list.get(0).getFD_Name());
			map.put(I_FD_Table.COLUMNNAME_FD_Table_Name, data.getFD_Table_Name());
			map.put(I_FD_Table.COLUMNNAME_FD_Name, data.getFD_Name());
			map.put(I_FD_Table.COLUMNNAME_FD_Description, data.getFD_Description());
			table.getItems().add(map);
		}
//		Map<String, String> map = new HashMap<>();
//		System.out.println(list.get(0).getFD_Name());
//		map.put(I_FD_Table.COLUMNNAME_FD_Table_Name, list.get(0).getFD_Table_Name());
//		map.put(I_FD_Table.COLUMNNAME_FD_Name, list.get(0).getFD_Name());
//		map.put(I_FD_Table.COLUMNNAME_FD_Description, list.get(0).getFD_Description());
//		table.getItems().add(map);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		//画面をユーザー対応待ちとして表示する。
		stage.show();
	}

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

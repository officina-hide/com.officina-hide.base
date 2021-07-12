package com.officina_hide.fx.base;

import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.X_FD_Table;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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

	@Override
	public void start(Stage stage) throws Exception {
		//テーブル情報取得
		List<X_FD_Table> list = getTableList();
		
		VBox root = new VBox(5);
		TableView<String> table = new TableView<>();
		root.getChildren().add(table);
		
		TableColumn<String, String> TableName = new TableColumn<>("テーブル物理名");
		TableColumn<String, String> TableDispName = new TableColumn<>("テーブル表示名");
		table.getColumns().add(TableName);
		table.getColumns().add(TableDispName);

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
//		List<Integer> ids = DB.getAllId(env);
		return list;
	}

}

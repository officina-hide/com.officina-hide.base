package com.officina_hide.fx.base;

import com.officina_hide.base.common.FD_EnvData;

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

}

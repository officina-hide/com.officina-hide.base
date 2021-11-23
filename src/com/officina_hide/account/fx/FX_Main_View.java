package com.officina_hide.account.fx;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.fx.base.FX_View_Common;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * メイン画面[Main Screen]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/11/16 Ver. 1.00
 */
public class FX_Main_View extends Application {
	
	/** 環境情報[Environment information] */
	private FD_EnvData env;
	
	FX_View_Common com = new FX_View_Common();

	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2021/11/17 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public FX_Main_View(FD_EnvData env) {
		this.env = env;
	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox(5);
		root.setPadding(new Insets(5, 5, 5, 5));
		//トップエリア設定[Top area setting]
		com.setTopArea(env, root);
		//セパレーター
		root.getChildren().add(new Separator());
		//メインエリア設定[Main area setting]
		com.setMainArea(env, root);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.showAndWait();
	}

}

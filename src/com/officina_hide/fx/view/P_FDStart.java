package com.officina_hide.fx.view;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * パッケージ開始処理クラス[Package start processing class]<br>
 * @author officine-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/05 Ver. 1.00
 */
public class P_FDStart extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		//ログイン画面を表示する。
		V_Login login = new  V_Login();
		login.start(new Stage());
	}

	public static void main(String[] args) {
		launch(args);
	}

}

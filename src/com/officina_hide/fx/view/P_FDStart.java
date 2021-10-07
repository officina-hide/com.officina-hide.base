package com.officina_hide.fx.view;

import com.officina_hide.base.common.FD_EnvData;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * パッケージ開始処理クラス[Package start processing class]<br>
 * @author officine-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/05 Ver. 1.00
 */
public class P_FDStart extends Application {
	//環境情報の取得
	private FD_EnvData env = new FD_EnvData();

	@Override
	public void start(Stage stage) throws Exception {
		//環境情報初期化
		env.setDbName("FDBASE");
		
		//ログイン画面を表示する。
		V_Login login = new  V_Login(env);
		//表示項目設定
		
		login.start(new Stage());
	}

	public static void main(String[] args) {
		launch(args);
	}

}

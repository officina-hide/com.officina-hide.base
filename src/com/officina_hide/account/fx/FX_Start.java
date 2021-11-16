package com.officina_hide.account.fx;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.I_FD_DB;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 会計機能画面起動クラス[Accounting function screen activation class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/11/16 Ver. 1.00
 */
public class FX_Start extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		//環境設定
		FD_EnvData env = new FD_EnvData();
		env.setRunLevel(0);
		env.setDbName("ACCOUNT");
		env.setActionUserID(I_FD_DB.SYSTEM_USER_ID);
		
		//メイン画面表示
		FX_Main_View fmv = new FX_Main_View();
		fmv.start(new Stage());
	}

	public static void main(String[] args) {
		launch(args);
	}

}

package com.officina_hide.ui.view;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.ui.model.FX_View;
import com.officina_hide.ui.model.X_FX_View;

import javafx.application.Application;
import javafx.stage.Stage;

public class FV_Album_Entry extends Application implements I_FV_Album_Entry {

	/** 環境情報[Environment information] */
	private FD_EnvData env;
	
	@Override
	public void start(Stage stage) throws Exception {
		//環境情報取得
		env = new FD_EnvData("Picture.prop");

		//情報取得
		FX_View view = new FX_View(env);
		X_FX_View xview = new X_FX_View(env, view.getIDbyCode(VIEW_CODE));
		
		/**
		 * @since 2022/06/06
		 * アルバム情報の項目を設定する。
		 */
		
		
		stage.setTitle(xview.getFD_Name());
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

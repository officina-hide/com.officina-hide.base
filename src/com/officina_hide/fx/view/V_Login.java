package com.officina_hide.fx.view;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.fx.model.I_FX_Tab;
import com.officina_hide.fx.model.I_FX_View;
import com.officina_hide.fx.model.V_FX_Login;
import com.officina_hide.fx.model.X_FX_Tab;
import com.officina_hide.fx.model.X_FX_View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * ログイン画面[Login screen]
 * @author officine-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/06 Ver. 1.00
 */
public class V_Login extends Application {

	/** 環境情報 */
	FD_EnvData env;
	
	/**
	 * コンストラクタ[Constructor]
	 * @param env 環境情報[Environment information]
	 */
	public V_Login(FD_EnvData env) {
		this.env = env;
	}

	@Override
	public void start(Stage stage) throws Exception {
		/*
		 * ログイン画面はView情報に1つのタブ情報が紐づく単一画面となる。
		 * 1. View情報を取得
		 * 2. タブ情報取得
		 */
		//1.
		FD_WhereData where = new FD_WhereData(I_FX_View.COLUMNNAME_FX_View_Name, V_FX_Login.FX_View_Name);
		X_FX_View view = new X_FX_View(env, where);
		//2.
		where = new FD_WhereData(I_FX_Tab.COLUMNNAME_FX_View_ID	, view.getFX_View_ID());
		X_FX_Tab tab = new X_FX_Tab(env, where);
		
		VBox root = new VBox();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle(view.getFD_Name());
		stage.showAndWait();
	}

}

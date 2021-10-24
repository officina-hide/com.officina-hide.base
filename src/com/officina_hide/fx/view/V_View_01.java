package com.officina_hide.fx.view;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.fx.model.I_FX_Tab;
import com.officina_hide.fx.model.X_FX_Tab;
import com.officina_hide.fx.model.X_FX_View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 標準画面01[Standard display 01]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/20 Ver. 1.00
 */
public class V_View_01 extends Application {

	/** 環境情報[Environment information] */
	private FD_EnvData env;
	/** 画面情報ID */
	private long viewId;
	/** 画面情報 */
	private X_FX_View view;
	/** 画面共通機能 */
	private V_Common vc;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @param env 環境情報[Environment information]
	 * @param viewId 画面情報ID[View information ID]
	 */
	public V_View_01(FD_EnvData env, long viewId) {
		this.env = env;
		this.viewId = viewId;
		//画面情報取得
		view = new X_FX_View(env, viewId);
	}

	@Override
	public void start(Stage stage) throws Exception {
		//メインタブ取得
		FD_WhereData where = new FD_WhereData(I_FX_Tab.COLUMNNAME_FX_View_ID, viewId);
		where.add("AND", I_FX_Tab.COLUMNNAME_FX_Tab_Level, 0);
		X_FX_Tab mainTab = new X_FX_Tab(env, where);
		//フィールド
		VBox root = new VBox(5);
		vc.setFIeld(env, root, mainTab.getFX_Tab_ID());
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle(view.getFD_Name());
		stage.showAndWait();
	}

}

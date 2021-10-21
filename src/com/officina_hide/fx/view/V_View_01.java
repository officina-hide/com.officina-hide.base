package com.officina_hide.fx.view;

import com.officina_hide.base.common.FD_EnvData;

import javafx.application.Application;
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
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @param env 環境情報[Environment information]
	 * @param viewId 画面情報ID[View information ID]
	 */
	public V_View_01(FD_EnvData env, long viewId) {
		this.env = env;
		this.viewId = viewId;
	}

	@Override
	public void start(Stage stage) throws Exception {

		stage.showAndWait();
	}

}

package com.officina_hide.workshop.task.tasklist;

import java.util.Date;
import java.util.List;

import com.officina_hide.base.common.FDSQLWhere;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.fx.model.I_FD_View;
import com.officina_hide.fx.model.I_FD_ViewColumn;
import com.officina_hide.fx.model.X_FD_View;
import com.officina_hide.fx.model.X_FD_ViewColumn;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FX_TaskView extends Application {

	/** 環境情報 */
	private FD_EnvData env;
	/** Fx画面情報ID */
	private int FxViewID;
	
	@Override
	public void start(Stage stage) throws Exception {
		/*
		 * 本画面が呼び出される際に対象テーブルの情報IDがセットされていた場合は、既登録の情報を抽出し画面にセットする。
		 * 画面にセットする項目は、画面情報・画面項目情報から取得する。
		 */
		VBox root = new VBox();
		
		/*
		 * 画面情報、画面項目情報を所得する。
		 */
		FD_DB DB = new FD_DB(); 
		X_FD_View view = new X_FD_View(env, FxViewID);
		FDSQLWhere where = new FDSQLWhere(I_FD_ViewColumn.COLUMNNAME_FD_View_ID, view.getintOfValue(I_FD_View.COLUMNNAME_FD_View_ID));
		List<X_FD_ViewColumn> vlist = DB.getDataList(env, I_FD_ViewColumn.Table_Name, where);
		System.out.println(view.getValueOfString(I_FD_View.COLUMNNAME_View_Name));
		Scene scene = new Scene(root, view.getintOfValue(I_FD_View.COLUMNNAME_Initial_Width	)
				, view.getintOfValue(I_FD_View.COLUMNNAME_Initial_Height));
		stage.setScene(scene);
		stage.showAndWait();
	}

	public void setEnv(FD_EnvData env) {
		this.env = env;
	}
	public void setFxViewID(int fxViewID) {
		FxViewID = fxViewID;
	}

}

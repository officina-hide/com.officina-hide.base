package com.officina_hide.project_ad.fx;

import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.ui.model.I_FX_Field;
import com.officina_hide.ui.model.I_FX_View;
import com.officina_hide.ui.model.X_FX_View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FX_Project extends Application implements I_FX_Project {
	
	/** 環境情報[Environment information] */
	private  FD_EnvData env;
	/** 画面情報 */
	private X_FX_View view;

	@Override
	public void start(Stage stage) throws Exception {
		//環境情報取得
		env = new FD_EnvData("Project_AD.prop");
		//画面情報取得
		FD_WhereData where = new FD_WhereData(I_FX_View.COLUMNNAME_FX_View_Code, FX_View_Code);
		view = new X_FX_View(env, where);
		
		VBox root = new VBox(5);
		setItem(root);
		Scene scene = new Scene(root, 600, 400);
		stage.setTitle(view.getStringValue(I_FX_View.COLUMNNAME_FD_Name));
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * 画面項目セット
	 * @param root
	 */
	private void setItem(VBox root) {
		FD_DB db = new FD_DB();
		FD_WhereData where = new FD_WhereData(I_FX_Field.COLUMNNAME_FX_View_ID,
				view.getLongValue(I_FX_View.COLUMNNAME_FX_View_ID));
		List<Integer> flist = db.getAllId(env, I_FX_Field.Table_Name, where);
	}

	public static void main(String[] args) {
		launch(args);
	}

}

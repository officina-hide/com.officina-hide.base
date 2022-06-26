package com.officina_hide.ui.view;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_FIeldData;
import com.officina_hide.base.common.FD_FieldDataCollection;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.ui.model.FX_View;
import com.officina_hide.ui.model.X_FX_View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FV_Album_Entry extends Application implements I_FV_Album_Entry, I_FD_DB {

	/** 環境情報[Environment information] */
	private FD_EnvData env;
	/** 画面情報 */
	private FX_View view;
	private X_FX_View xview;
	
	@Override
	public void start(Stage stage) throws Exception {
		//環境情報取得
		env = new FD_EnvData("Picture.prop");

		//情報取得
		view = new FX_View(env);
		xview = new X_FX_View(env, view.getIDbyCode(VIEW_CODE));
		
		/**
		 * @since 2022/06/06
		 * アルバム情報の項目を設定する。
		 */
		VBox root = new VBox(5);
		root.setPadding(new Insets(5, 5, 5, 5));
		//ツールバーセット
		setToolBar(root);
		//項目セット
		setItem(root);
		
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle(xview.getFD_Name());
		stage.show();
	}

	/**
	 * ツールバーセット[Setting toolbar]<br>
	 * @author officina-hide.net
	 * @since 2022/06/17 Ver. 1.00
	 * @param root ルート[root]
	 */
	private void setToolBar(VBox root) {
		ToolBar toolBar = new ToolBar();
		root.getChildren().add(toolBar);
		//保存ボタン
		Button saveButton = new Button("保存");
		saveButton.setFont(new Font("Meiryo UI", 12));
		saveButton.setOnAction(event->{
			AC_Save save = new AC_Save();
			save.execute(env, event);
		});
		toolBar.getItems().addAll(saveButton);
		
	}

	/**
	 * 項目設定[Setting item]<br>
	 * @author officina-hide.net
	 * @since 2022/06/11 Ver. 1.00
	 * ルートに画面項目情報の各項目をセットする。<br>
	 * Set each item of screen item information in the route.<br>
	 * @param root ルート[root]
	 */
	private void setItem(VBox root) {
		FD_FieldDataCollection fdc = new FD_FieldDataCollection();
		fdc.initialize(env, VIEW_CODE);
		for(FD_FIeldData fd : fdc.getFieldDataList()) {
			HBox fieldBox = new HBox(5);
			fieldBox.setAlignment(Pos.CENTER_LEFT);
			//ラベル
			fieldBox.getChildren().add(fd.getFieldLabel());
			fieldBox.getChildren().add((TextField)fd.getFieldItem());
			
			root.getChildren().add(fieldBox);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}

package com.officina_hide.ui.view;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.ui.model.FX_View;
import com.officina_hide.ui.model.I_FV_Album_List;
import com.officina_hide.ui.model.X_FX_View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * アルバム情報リスト画面[Album information list screen]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/07/23 Ver. 1.00
 */
public class FV_Album_List extends Application implements I_FV_Album_List {
	
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
		
		VBox root = new VBox(5);
		root.setPadding(new Insets(5, 5, 5, 5));
		//項目セット
		setItem(root);
		
		
		stage.show();
	}

	/**
	 * リスト表示画面用項目セット[Item set for list display screen]<br>
	 * @author officina-hide.net
	 * @since 2022/07/28 Ver. 1.00
	 * @param root ルート[Root]
	 */
	private void setItem(VBox root) {
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}

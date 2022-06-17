package com.officina_hide.ui.view;

import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.ui.model.FX_Field;
import com.officina_hide.ui.model.FX_View;
import com.officina_hide.ui.model.X_FX_Field;
import com.officina_hide.ui.model.X_FX_View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
		setItem(root);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle(xview.getFD_Name());
		stage.show();
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
		FX_Field field = new FX_Field(env);
		List<X_FX_Field> flist = field.getList(env, xview.getFX_View_ID());
		for(X_FX_Field fd : flist) {
			HBox fieldBox = new HBox(5);
			fieldBox.setAlignment(Pos.CENTER_LEFT);
			//ラベル
			Label label = new Label(fd.getFD_Name());
			label.setPrefWidth(100);
			label.setAlignment(Pos.CENTER_RIGHT);
			label.setFont(new Font("Meiryo UI", 14));
			fieldBox.getChildren().add(label);
			switch(fd.getFX_Field_Type(env).getFD_Reference_Code()) {
			case FD_Item_String:
				TextField text = new TextField("");
				text.setFont(new Font("Meiryo UI", 12));
				fieldBox.getChildren().add(text);
				break;
			}
			System.out.println(fd.getFX_Field_Type(env).getFD_Reference_Code());
			root.getChildren().add(fieldBox);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}

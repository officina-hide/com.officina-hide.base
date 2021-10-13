package com.officina_hide.fx.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.fx.model.FD_Params;
import com.officina_hide.fx.model.FX_Field;
import com.officina_hide.fx.model.FX_TabProcess;
import com.officina_hide.fx.model.I_FX_Field;
import com.officina_hide.fx.model.I_FX_Tab;
import com.officina_hide.fx.model.I_FX_TabProcess;
import com.officina_hide.fx.model.I_FX_View;
import com.officina_hide.fx.model.V_FX_Login;
import com.officina_hide.fx.model.X_FX_Tab;
import com.officina_hide.fx.model.X_FX_TabProcess;
import com.officina_hide.fx.model.X_FX_View;
import com.officina_hide.fx.model.X_FX_Field;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * ログイン画面[Login screen]
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/06 Ver. 1.00
 */
public class V_Login extends Application implements I_FD_DB {

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
		 * 3. 画面項目設定
		 */
		//1.
		FD_WhereData where = new FD_WhereData(I_FX_View.COLUMNNAME_FX_View_Name, V_FX_Login.FX_View_Name);
		X_FX_View view = new X_FX_View(env, where);
		//2.
		where = new FD_WhereData(I_FX_Tab.COLUMNNAME_FX_View_ID	, view.getFX_View_ID());
		X_FX_Tab tab = new X_FX_Tab(env, where);
		//3.
		VBox root = new VBox(5);
		root.setPadding(new Insets(5, 5, 5, 5));
		root.setStyle("-fx-font-family: Meiryo UI; -fx-font-size: 12");

		setItem(root, tab.getFX_Tab_ID(), stage);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle(view.getFD_Name());
		stage.showAndWait();
	}

	/**
	 * 画面項目設定[Screen item setting]
	 * @author officina-hide.net
	 * @since 2021/10/07 Ver. 1.00
	 * @param root ルート[Root]
	 * @param stage ステージ[Stage]
	 * @param tab タブ情報ID[Tab information ID]
	 */
	private void setItem(VBox root, long tabID, Stage stage) {
		/*
		 * 1-1. 画面項目情報抽出
		 * 1-2. 画面項目一覧から項目タイトルと項目の組み合わせを作成する。
		 * 2. タブ処理情報抽出
		 */
		//1-1.
		FX_Field field = new FX_Field();
		FD_WhereData where = new FD_WhereData(I_FX_Field.COLUMNNAME_FX_Tab_ID, tabID);
		List<X_FX_Field> flist = field.getList(env, where);
		//1-2.
		for(X_FX_Field fd : flist) {
			HBox fbox = new HBox(5);
			fbox.setAlignment(Pos.CENTER_LEFT);
			//ラベルセット
			Label label = new Label(fd.getFD_Name());
			fbox.getChildren().add(label);
			//項目セット
			switch(fd.getFD_TypeItem(env).getFD_TypeItem_Name()) {
			case FD_Field_SimpleText:
				TextField textField = new TextField();
				fbox.getChildren().add(textField);
				break;
			case FD_Field_Password:
				PasswordField pass = new PasswordField();
				fbox.getChildren().add(pass);
				break;
			}
			root.getChildren().add(fbox);
		}
		
		//2.
		FX_TabProcess tp = new FX_TabProcess();
		where = new FD_WhereData(I_FX_TabProcess.COLUMNNAME_FX_Tab_ID, tabID);
		List<X_FX_TabProcess> tlist = tp.getList(env, where);
		HBox buttonBox = new HBox(5);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
		for(X_FX_TabProcess tpro : tlist) {
			Button button = new Button(tpro.getFD_Name());
			buttonBox.getChildren().add(button);
			button.setOnMouseClicked(event->{
				//プロセスCall[Process call]
				FD_Params params = new FD_Params(env, tpro.getFD_Process_ID());
				params.setValue("stage", stage);
				callProcess(event, env, tpro, params);
			});
		}
		root.getChildren().add(buttonBox);
	}

	/**
	 * 処理呼び出し[Call process]<br>
	 * @author officina-hide.net
	 * @since 2021/10/11 Ver. 1.00
	 * @param event イベント情報[Event information]
	 * @param env 環境情報[Environment information]
	 * @param tp タブ処理情報[Tab process information]
	 * @param params 処理変数情報[Process variable information]
	 */
	private void callProcess(MouseEvent event, FD_EnvData env, X_FX_TabProcess tp, FD_Params params) {
		try {
			System.out.println(tp.getFD_Process(env).getFD_CallProcess_Name());
			Class<?> callClass = Class.forName(tp.getFD_Process(env).getFD_CallProcess_Name());
			Constructor<?> constructor = callClass.getConstructor();
			Object cc = constructor.newInstance();
			Method exeMethod = callClass.getMethod("execute", FD_Params.class);
			exeMethod.invoke(cc, params);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException |
				InstantiationException | IllegalAccessException | IllegalArgumentException |
				InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}

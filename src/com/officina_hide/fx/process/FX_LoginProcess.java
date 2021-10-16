package com.officina_hide.fx.process;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.FD_Params;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.model.I_FD_User;
import com.officina_hide.base.model.X_FD_Login;
import com.officina_hide.base.model.X_FD_User;
import com.officina_hide.fx.view.V_Menu;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * ログイン認証処理[Login authentication process]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/14 Ver. 1.00
 */
public class FX_LoginProcess implements I_FD_DB {

	/**
	 * 処理実行[Process execution]<br>
	 * @author officina-hide.net
	 * @since 2021/10/14 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param params 処理変数[Process variable]
	 */
	public void execute(FD_EnvData env, FD_Params params) {
		System.out.println("Login Process");
		/*
		 * 1. 認証
		 * 　　入力されたユーザー名とログインパスワードでユーザー情報を呼び出す。
		 * 　　認証エラーの情報をセットしログイン情報保保管する。 TODO 未実装 2021/10/16
		 * 2. 認証したログイン情報を抽出保管する。 TODO 未実装 2021/10/16
		 * 3. メニユー表示
		 */
		Stage stage = (Stage) params.getObject("stage");
		X_FD_Login login = (X_FD_Login) stage.getScene().getRoot().getUserData();
		//1.
		FD_WhereData where = new FD_WhereData();
		where.add(null, I_FD_User.COLUMNNAME_FD_User_Name, login.getFD_User_Name());
		where.add("AND", I_FD_User.COLUMNNAME_FD_Login_Password, login.getFD_Login_Password());
		X_FD_User user = new X_FD_User(env, where);
		if(user.getFD_User_ID() == 0) {
			//認証エラー
			Alert alert =new Alert(AlertType.ERROR, "ユーザー名かパスワードが間違っています。", ButtonType.OK);
			alert.showAndWait();
			return;
		}
		//3.
		V_Menu menu = new V_Menu();
		try {
			stage.hide();
			menu.start(new Stage());
			stage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

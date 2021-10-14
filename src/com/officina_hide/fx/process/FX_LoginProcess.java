package com.officina_hide.fx.process;

import com.officina_hide.base.model.FD_Params;
import com.officina_hide.base.model.X_FD_Login;

import javafx.stage.Stage;

/**
 * ログイン認証処理[Login authentication process]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/14 Ver. 1.00
 */
public class FX_LoginProcess {

	/**
	 * 処理実行[Process execution]<br>
	 * @author officina-hide.net
	 * @since 2021/10/14 Ver. 1.00
	 * @param params 処理変数[Process variable]
	 */
	public void execute(FD_Params params) {
		System.out.println("Login Process");
		/*
		 * 1. 認証
		 * 2. メニユー表示
		 */
		Stage stage = (Stage) params.getObject("stage");
		X_FD_Login login = (X_FD_Login) stage.getScene().getRoot().getUserData();
		System.out.println(login);
		//1.
		
	}

}

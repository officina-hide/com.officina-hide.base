package com.officina_hide.fx.tools;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.X_FD_Process;
import com.officina_hide.base.tools.CreatePackage;

/**
 * Fx情報のベースクラスを構築する。<br>
 * @author officine-hide.com
 * @version 1.10
 * @since 2020/10/28
 */
public class CreateFxBase {

	public static void main(String[] args) {
		/*
		 * 本来はCreatePackageの結果をここでRestoreしてから、Fx用のベース情報を構築する。<br>
		 * 当面は、ここからCreatePakageを呼び出す。<br>
		 * 実際には常に起動する必要はなく、Packageベースに変更があった時に起動させる。<br>
		 */
//		CreatePackage.main(null);
		
		Date startDate = new Date();
		
		//環境情報のPathを設定する。
		FD_EnvData env = null;
		try {
			String propPath = new File(".").getCanonicalPath()+"\\data\\base.properties";
			//環境情報の取込
			env = new FD_EnvData(propPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//プロセス情報登録
		X_FD_Process process = new X_FD_Process(env);
		
		//Fx画面情報構築
		FXView view = new FXView();
		view.createTable(env);
	}

}

package com.officina_hide.fx.tools;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_Process;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.tools.CreatePackage;
import com.officina_hide.base.tools.FDProcess;

/**
 * Fx情報のベースクラスを構築する。<br>
 * @author officine-hide.com
 * @version 1.10
 * @since 2020/10/28
 */
public class CreateFxBase {
	
	/** 実行プロセス情報ID */
	private final static int ThisProcess_ID = 102;

	public static void main(String[] args) {
		/*
		 * 本来はCreatePackageの結果をここでRestoreしてから、Fx用のベース情報を構築する。<br>
		 * 当面は、ここからCreatePakageを呼び出す。<br>
		 * 実際には常に起動する必要はなく、Packageベースに変更があった時に起動させる。<br>
		 */
		Date startDate = new Date();
		
		//環境情報のPathを設定する。
		FD_EnvData env = null;
		try {
			String propPath = new File(".").getCanonicalPath()+"\\data\\base.properties";
			//環境情報の取込
			env = new FD_EnvData(propPath);
			env.setActiveProcessID(ThisProcess_ID);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//関連情報リセット処理
		resetFxBaseData(env);
		
		//プロセス情報登録
		FDProcess process = new FDProcess();
		process.addData(env, ThisProcess_ID,  CreatePackage.class.getSimpleName(), startDate);	
		
		//Fx画面情報構築
		FXView view = new FXView();
		view.createTable(env);
//		
//		//機能テスト用
//		//Fx画面情報登録用
//		view.addData(env, "FX_Task_List", "タスク情報一覧");
		
	}

	/**
	 * Fxベースクラス登録用情報リセット<br>
	 * <p>実行プロセス情報IDを持つベース情報を削除して、ベース構築処理をリセットする。</p>
	 * @author officine-hide.com
	 * @since 1.10 2020/11/03
	 * @param env 環境情報
	 */
	private static void resetFxBaseData(FD_EnvData env) {
		//プロセス情報削除
		FD_DB_Utility dbutil = new FD_DB_Utility();
		dbutil.deleteDataByProcessId(env, I_FD_Process.Table_Name, ThisProcess_ID);
		dbutil.deleteDataByProcessId(env, I_FD_Log.Table_Name, ThisProcess_ID);
		dbutil.deleteDataByProcessId(env, I_FD_Table.Table_Name, ThisProcess_ID);
		dbutil.deleteDataByProcessId(env, I_FD_TableColumn.Table_Name, ThisProcess_ID);
	}

}

package com.officina_hide.project.tools;

import java.io.File;
import java.io.IOException;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_Process;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.tools.FDProcess;

/**
 * プロジェクトパッケージのベースを構築する。<br>
 * @author officine-hide.com
 * @version 1.20
 * @since 2020/11/05
 * @deprecated 2020/11/17 CreateBaseEnvironmentクラスに移管
 */
public class CreateProjectPackage {

	private static int thisPorcessId = 201;
	
	public static void main(String[] args) {
		
		//環境情報のPathを設定する。
		FD_EnvData env = null;
		try {
			String propPath = new File(".").getCanonicalPath()+"\\data\\base.properties";
			//環境情報の取込
			env = new FD_EnvData(propPath);
			env.setActiveProcessID(thisPorcessId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//関連情報リセット処理
		resetFxBaseData(env);

		//プロセス情報登録
		FDProcess process = new FDProcess();
		process.createProcess(env, thisPorcessId, CreateProjectPackage.class.getSimpleName());
		
		//プロジェクト情報構築
		FDProject project = new FDProject();
		project.createTable(env);
		//タスク情報構築
		FDTask task = new FDTask();
		task.createTable(env);
		
//		//プロジェクトサンプル登録処理
//		LoadProjectSample lps = new LoadProjectSample();
//		lps.createData(env);
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
		dbutil.deleteDataByProcessId(env, I_FD_Process.Table_Name, thisPorcessId);
		dbutil.deleteDataByProcessId(env, I_FD_Log.Table_Name, thisPorcessId);
		dbutil.deleteDataByProcessId(env, I_FD_Table.Table_Name, thisPorcessId);
		dbutil.deleteDataByProcessId(env, I_FD_TableColumn.Table_Name, thisPorcessId);
		dbutil.deleteDataByProcessId(env, I_FD_Numbering.Table_Name, thisPorcessId);
	}

}

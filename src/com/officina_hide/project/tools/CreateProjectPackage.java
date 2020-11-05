package com.officina_hide.project.tools;

import java.io.File;
import java.io.IOException;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.tools.FDProcess;

/**
 * プロジェクトパッケージのベースを構築する。<br>
 * @author officine-hide.com
 * @version 1.20
 * @since 2020/11/05
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

		//プロセス情報登録
		FDProcess process = new FDProcess();
		process.createProcess(env, thisPorcessId, CreateProjectPackage.class.getSimpleName());
	}

}

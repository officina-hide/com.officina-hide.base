package com.officina_hide.ui.tools;

import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.tools.CreateBaseInformation;
import com.officina_hide.medical.tools.CreateMedicalInformation;

/**
 * 医療情報プロジェクト生成[Medical information project generation]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/05/02 Ver. 1.00
 */
public class CreateMedicalProject {
	
	/** 環境情報[Environment information] */
	private static FD_EnvData env;

	public static void main(String[] args) {
		//開始メッセージ[Start message]
		System.out.println("医療情報管理構成 開始 : "+new Date());
		//環境情報取得[Environmental information acquisition]
		env = new FD_EnvData("Medical.prop");
		//基本情報生成
		CreateBaseInformation cbi = new CreateBaseInformation(env);
		cbi.execute();

		//医療情報生成
		CreateMedicalInformation cmi = new CreateMedicalInformation(env);
		cmi.execute();
		
		//開始メッセージ[End message]
		System.out.println("医療情報管理構成 終了 : "+new Date());
	}

}

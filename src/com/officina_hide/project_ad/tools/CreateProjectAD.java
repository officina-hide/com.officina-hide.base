package com.officina_hide.project_ad.tools;

import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_Table;
import com.officina_hide.project_ad.fx.I_FX_Project;
import com.officina_hide.project_ad.model.FD_Project;
import com.officina_hide.ui.model.FX_Field;
import com.officina_hide.ui.model.FX_View;

/**
 * プロジェクト管理構成クラス[Project management configuration class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2022/03/31 Ver. 1.00
 */
public class CreateProjectAD {
	
	/** 環境情報[Environment information] */
	private static FD_EnvData env;

	public static void main(String[] args) {
		//開始メッセージ[Start message]
		System.out.println("プロジェクト管理構成 開始 : "+new Date());
		//環境情報取得[Environmental information acquisition]
		env = new FD_EnvData("Project_AD.prop");
		/*
		 * 基本環境設定[Basic environment setting]<br>
		 * 1.　テーブル情報生成
		 * 2. テーブル項目
		 */
		//1.
		FD_Table table = new FD_Table(env);
		table.createTable();
		//2.
		FD_Column column = new FD_Column(env);
		column.createTable();
		
		//プロジェクト情報生成
		FD_Project project = new FD_Project(env);
		project.createTable();
		//画面情報生成
		FX_View view = new FX_View(env);
		view.createTable();
		//画面項目情報生成
		FX_Field field = new FX_Field(env);
		field.createTable();
		
		//情報登録
		view.entry(I_FX_Project.FV_View_Data);
	}

}

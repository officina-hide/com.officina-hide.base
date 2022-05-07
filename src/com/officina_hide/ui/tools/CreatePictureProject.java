package com.officina_hide.ui.tools;

import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_File;
import com.officina_hide.base.model.FD_FileData;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;
import com.officina_hide.base.model.I_FD_File;
import com.officina_hide.base.model.I_FD_FileData;
import com.officina_hide.base.tools.CreateBaseInformation;

/**
 * 写真保存用プロジェクト生成[Generate a project for saving photos]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/04/16 Ver. 1.00
 */
public class CreatePictureProject {
	
	/** 環境情報[Environment information] */
	private static FD_EnvData env;

	public static void main(String[] args) {
		//開始メッセージ[Start message]
		System.out.println("画像管理構成 開始 : "+new Date());
		//環境情報取得[Environmental information acquisition]
		env = new FD_EnvData("Picture.prop");
		//基本情報生成
		CreateBaseInformation cbi = new CreateBaseInformation(env);
		cbi.execute();

		FD_Numbering num = new FD_Numbering(env);
		FD_Table table = new FD_Table(env);
		FD_Column column = new FD_Column(env);

		//プロジェクト情報生成
		//ファイル情報生成
		FD_File file = new FD_File(env);
		file.createTable();
		table.add(I_FD_File.Entry_FD_Table);
		num.add(I_FD_File.Entry_FD_Number);
		column.add(I_FD_File.Entry_FD_Column_FD_File_ID);
		column.add(I_FD_File.Entry_FD_Column_FD_File_Code);
		num.add(I_FD_File.Entry_FD_Number_FD_File_Code);
		//ファイルデータ情報生成
		FD_FileData fld = new FD_FileData(env);
		fld.createTable();
		table.add(I_FD_FileData.Entry_FD_Table);
		num.add(I_FD_FileData.Entry_FD_Number);
	}

}

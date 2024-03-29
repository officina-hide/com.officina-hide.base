package com.officina_hide.ui.tools;

import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.tools.CreateBaseInformation;
import com.officina_hide.base.tools.CreateScreenInformation;
import com.officina_hide.ui.model.FP_Album;
import com.officina_hide.ui.model.FX_Field;
import com.officina_hide.ui.model.FX_View;
import com.officina_hide.ui.model.I_FP_Album;
import com.officina_hide.ui.model.I_FV_Album_List;
import com.officina_hide.ui.view.I_FV_Album_Entry;

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
		//画面情報生成
		CreateScreenInformation csi = new CreateScreenInformation(env);
		csi.execute();

		/**
		 * @since 2022/06/01
		 * 写真集
		 * ・写真一覧（アルバム）→単一階層（複数階層化は別途検討）
		 * ・写真閲覧（写真、タイトル、撮影情報、メモ、タグ）
		 * FP_Album → 登録用 FV_Album_Entry、一覧用 FXL_Album_List
		 * FP_Picture
		 * FP_MediaData
		 */
		//プロジェクト情報生成
		FP_Album pic = new FP_Album(env);
		pic.createTable();
		

		// FV_Album_Entry用画面情報構築
		FX_View view = new FX_View(env);
		view.add(I_FP_Album.Entry_FX_View_FV_Album_Entry);
		// FV_Album_Entry用画面項目構築
		FX_Field field = new FX_Field(env);
		field.add(I_FV_Album_Entry.Entry_Field_FP_Album_Code);
		field.add(I_FV_Album_Entry.Entry_Field_FD_Name);
		// FV_Album_List用画面情報構築
		view.add(I_FV_Album_List.Entry_Fx_View);
		// FV_Album_List用画面項目構築
		field.add(I_FV_Album_List.Entry_Field_FP_Album_Code);
		field.add(I_FV_Album_List.Entry_Field_FD_Name);
		
		
//		//ファイル情報生成
//		FD_File file = new FD_File(env);
//		file.createTable();
//		table.add(I_FD_File.Entry_FD_Table);
//		num.add(I_FD_File.Entry_FD_Number);
//		column.add(I_FD_File.Entry_FD_Column_FD_File_ID);
//		column.add(I_FD_File.Entry_FD_Column_FD_File_Code);
//		num.add(I_FD_File.Entry_FD_Number_FD_File_Code);
//		//ファイルデータ情報生成
//		FD_FileData fld = new FD_FileData(env);
//		fld.createTable();
//		table.add(I_FD_FileData.Entry_FD_Table);
//		num.add(I_FD_FileData.Entry_FD_Number);
		
		//終了メッセージ[End message]
		System.out.println("画像管理構成 終了 : "+new Date());
	}

}

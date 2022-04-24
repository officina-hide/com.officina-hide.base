package com.officina_hide.ui.tools;

import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_File;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;
import com.officina_hide.base.model.I_FD_Column;
import com.officina_hide.base.model.I_FD_File;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_Table;

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
		/*
		 * テーブルをインターフェースクラス内のSQL文で生成する。
		 * 採番情報の登録（採番情報IDは固定値を使用）→基本情報テーブルの固定値は一覧で管理する。
		 */
		//採番情報生成
		FD_Numbering num = new FD_Numbering(env);
		num.createTable();
		//テーブル情報生成
		FD_Table table = new FD_Table(env);
		table.createTable();
		//テーブル項目情報生成
		FD_Column column = new FD_Column(env);
		column.createTable();
		/*
		 * 採番情報を登録する。
		 * 採番情報IDは基本情報の場合、テーブル情報IDと同様とする。
		 */
		num.add(I_FD_Table.Entry_FD_Number);
		num.add(I_FD_Column.Entry_FD_Number);
		num.add(I_FD_Numbering.Entry_FD_Number);

		//プロジェクト情報生成
		//ファイル情報生成
		FD_File file = new FD_File(env);
		file.createTable();
		table.add(I_FD_File.Entry_FD_Table);
		num.add(I_FD_File.Entry_FD_Number);
		column.add(I_FD_File.Entry_FD_Column_FD_File_ID);
		column.add(I_FD_File.Entry_FD_Column_FD_File_Code);
		num.add(I_FD_File.Entry_FD_Number_FD_File_Code);
	}

}

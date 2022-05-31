package com.officina_hide.ui.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.tools.FD_Backup;

/**
 * Pictureデータベースのテーブルダンプを作成する。
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/05/31 Ver. 1.00
 */
public class BackupData01 extends FD_DB {
	
	/** 環境情報[Environment information] */
	private static FD_EnvData env;

	public static void main(String[] args) {
		//環境情報取得[Environmental information acquisition]
		env = new FD_EnvData("Picture.prop");
		//バックアップ処理
		FD_Backup fdbk = new FD_Backup();
	}

	/**
	 * 情報取得
	 */
	private static void getData() {
		
	}

}

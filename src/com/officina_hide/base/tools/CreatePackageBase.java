package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.I_FD_DB;

/**
 * パッケージの基盤情報を構築するためのプロセスを起動するクラス。<br>
 * A class that launches a process to build the base information of a package.<br>
 * <p>このクラスは、パッケージの基盤情報を構築するクラスを呼び出すために使用される。<br>
 * 将来的には、選択的にパッケージの機能を構築していくための最初のプロセスとして使用する。</p>
 * <p>This class is used to call the class that builds the base information of the package.<br>
 * In the future, it will be used as the first process to selectively build the functionality of the package.</p>
 * 
 * @author officina-hide.com
 * @version 1.00
 * @since 2021/03/31
 */
public class CreatePackageBase {
	//環境情報の取得
	private static FD_EnvData env = new FD_EnvData();

	/**
	 * @since 1.00 2021/03/31
	 * @param args
	 */
	public static void main(String[] args) {
		/** 処理レベルをセットする。 */
		env.setRunLevel(0);
		env.setDbName("FDBASE");
		//処理者の設定(Systemユーザ)
		env.setActionUserID(I_FD_DB.SYSTEM_USER_ID);
		// TODO 開始メッセージ
		// TODO 環境情報の取得
		//基盤情報構築
		CreateBaseInformation cbi = new CreateBaseInformation(env);
		cbi.execute();
		//FX画面用基盤情報構築
		CreateFxInformation cfi = new CreateFxInformation(env);
		cfi.execute();
		//システム関連画面群構築
		CreateFxSystemInformation cfs = new CreateFxSystemInformation(env);
		cfs.execute();
		// TODO 終了メッセージ
	}

}

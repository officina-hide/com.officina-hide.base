package com.officina_hide.base.tools;

/**
 * パッケージの基盤情報を構築するためのプロセスを起動するクラス。<br>
 * A class that launches a process to build the base information of a package.<br>
 * <p>このクラスは、パッケージの基盤情報を構築するクラスを呼び出すために使用される。<br>
 * 将来的には、選択的にパッケージの機能を構築していくための最初のプロセスとして使用する。</p>
 * <p>This class is used to call the class that builds the base information of the package.<br>
 * In the future, it will be used as the first process to selectively build the functionality of the package.</p>
 * 
 * @author officine-hide.com
 * @version 1.00
 * @since 2021/03/31
 */
public class CreatePackageBase {

	/**
	 * @since 1.00 2021/03/31
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 開始メッセージ
		// TODO 環境情報の取得
		//構築クラス呼び出し
		CreateBaseInformation cbi = new CreateBaseInformation();
		cbi.execute();
		// TODO 終了メッセージ
	}

}

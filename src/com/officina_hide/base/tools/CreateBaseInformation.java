package com.officina_hide.base.tools;

import java.io.File;

/**
 * 基盤情報構築クラス<br>
 * Basic information construction class.<br>
 * <p>基盤情報には以下の情報があります。<br>
 * ・テーブル関連情報（テーブル、カラム等データベースを構築するうえで必要な情報）</p>
 * <p>The basic information includes the following information.<br>
 * ・Table-related information (information necessary for building a database such as tables and columns)</p>
 * 
 * @author officine-hide.com
 * @version 1.00
 * @since 2021/03/31
 */
public class CreateBaseInformation {

	/**
	 * 構築実行<br>
	 * Build execution.
	 * @since 1.00 2021/03/31
	 */
	public void execute() {
		/*
		 * テーブル情報テーブルの生成（dicument install内のSQLを使用して生成する。)
		 */
		createTableByXML("FD_Table", "com.officina_hide.base.model");
		//テーブル項目情報テーブルの生成
	}

	/**
	 * XMLファイルからテーブルを生成する。<br>
	 * Generate a table from an XML file.
	 * @param name XMLファイル名
	 * @param libraryURI 関連クラス保管場所
	 */
	private void createTableByXML(String name, String libraryURI) {
		File file = new File(new File(".").getAbsoluteFile()+"\\src\\"+libraryURI.replaceAll("\\.", "\\\\\\\\"));
		System.out.println(file.getAbsolutePath()+":"+file.exists());
	}

}

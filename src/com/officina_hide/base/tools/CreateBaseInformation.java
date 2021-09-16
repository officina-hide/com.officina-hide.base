package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.I_FD_Numbering;

/**
 * 基盤情報構築クラス<br>
 * Basic information construction class.<br>
 * <p>基盤情報には以下の情報があります。<br>
 * ・テーブル関連情報（テーブル、カラム等データベースを構築するうえで必要な情報）</p>
 * <p>The basic information includes the following information.<br>
 * ・Table-related information (information necessary for building a database such as tables and columns)</p>
 * 
 * @author officine-hide.com
 * @version 1.00 新規作成
 * @version 2.00 構築手順の全面見直し
 * @since 2021/03/31
 */
public class CreateBaseInformation {

	//環境情報の取得
	FD_EnvData env = new FD_EnvData();

	public CreateBaseInformation(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 構築実行[Build execution]<br>
	 * 本メソッドでは必要なテーブル、情報の登録を順次実行する。
	 * @since 1.00 2021/03/31
	 * @since 2.00 2021/09/15
	 */
	public void execute() {
		/*
		 * 1. ID採番用テーブル構築、情報登録
		 */
		FD_Numbering num = new FD_Numbering();
		num.createTable(env);
		num.add(env, 101, I_FD_Numbering.Table_ID, 101, 101);
	}
}

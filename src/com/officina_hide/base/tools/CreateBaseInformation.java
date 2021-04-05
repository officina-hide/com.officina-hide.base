package com.officina_hide.base.tools;

import com.officina_hide.base.model.FD_Table;

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
		//テーブル情報生成
		FD_Table table = new FD_Table();
	}
}

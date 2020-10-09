package com.officina_hide.base.tools;

import java.io.File;
import java.io.IOException;

import com.officina_hide.base.common.FD_EnvData;

/**
 * パッケージ構築クラス<br>
 * <p>本パッケージを初期状態から構築するための機能を提供する。<br>
 * 将来的には、ウィザード形式でシステムが構築できるようにしていく。</p>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/10/08
 */
public class CreatePackage {

	public static void main(String[] args) {
		/*
		 * 1.環境情報の取込
		 * 2.ベース情報の構築
		 * 3.ベース情報の登録
		 */
		//環境情報のPathを設定する。
		FD_EnvData env = null;
		try {
			String propPath = new File(".").getCanonicalPath()+"\\data\\base.properties";
			//環境情報の取込
			env = new FD_EnvData(propPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//テーブル情報
		FDTable table = new FDTable();
		table.createTable(env);
	}

}

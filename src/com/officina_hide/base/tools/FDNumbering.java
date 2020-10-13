package com.officina_hide.base.tools;

import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Numbering;

/**
 * 採番情報クラス<br>
 * @author officina-hide.com
 * @version 1.00
 * @since 2020/10/13
 */
public class FDNumbering extends FD_DB implements I_FD_Numbering {

	/**
	 * 採番情報テーブル構築<br>
	 * @author officina-hide.com
	 * @since 1.00 2020/10/13
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		//テーブル生成
		createDBTable(env);
	}

	/**
	 * 採番情報テーブル生成<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/13
	 * @param env 環境情報
	 */
	private void createDBTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されているテーフル情報を削除する。
		sql.append("DROP TABLE IF EXISTS ").append(Table_Name);
		DBexecute(env, sql.toString());
		//テーブル情報の再構築
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS ").append(Table_Name).append(" (");
		
		sql.append(COLUMNNAME_FD_Numbering_ID).append(" INT UNSIGNED NOT NULL PRIMARY KEY COMMENT ")
			.append(FD_SQ).append(NAME_FD_Numbering_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_Table_ID).append(" INT UNSIGNED NOT NULL COMMENT ")
			.append(FD_SQ).append(NAME_FD_Table_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Current_Number).append(" INT UNSIGNED DEFAULT 0 COMMENT ")
			.append(FD_SQ).append(NAME_Current_Number).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Initial_Number).append(" INT UNSIGNED DEFAULT 0 COMMENT ")
			.append(FD_SQ).append(NAME_Initial_Number).append(FD_SQ).append(",");

		sql.append(COLUMNNAME_FD_CREATE).append(" DATETIME COMMENT ")
			.append(FD_SQ).append(NAME_FD_CREATE).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_CREATED).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_FD_CREATED).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATE).append(" DATETIME COMMENT ")
			.append(FD_SQ).append(NAME_FD_UPDATE).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATED).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_FD_UPDATED).append(FD_SQ).append(" ");
		
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=").append(FD_SQ).append(NAME).append(FD_SQ);

		DBexecute(env, sql.toString());
		
		System.out.println(new Date() + " : " + "採番情報テーブル生成完了");
	}

}

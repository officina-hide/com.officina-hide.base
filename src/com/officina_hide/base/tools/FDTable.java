package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.X_FD_Table;

/**
 * テーブル情報クラス<br>
 * @author officine-hide.com
 *@version 1.00
 *@since 2020/10/08
 */
public class FDTable extends FD_DB implements I_FD_Table {

	/**
	 * テーブル情報テーブル生成<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/08
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		//テーブル生成
		createDBTable(env);
	}

	/**
	 * テーブル情報テーブル構築<br>
	 * @author officine-hide.com
	 * @since 2020/10/08
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
		sql.append(COLUMNNAME_FD_Table_ID).append(" INT UNSIGNED NOT NULL PRIMARY KEY COMMENT ")
			.append(FD_SQ).append(NAME_FD_Table_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Table_Name).append(" Varchar(100) COMMENT ")
			.append(FD_SQ).append(NAME_Table_Name).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_Comment).append(" Text COMMENT ")
			.append(FD_SQ).append(NAME_FD_Comment).append(FD_SQ).append(",");
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
	}

	/**
	 * テーブル情報登録<br>
	 * @author officina-hide.com
	 * @since 1.00 2020/10/10
	 * @param env 環境情報
	 * @param tableId テーブル情報ID
	 * @param tableName テーブル名
	 * @param name テーブル論理名
	 * @param comment テーブル説明
	 */
	public void addData(FD_EnvData env, int tableId, String tableName, String name, String comment) {
		X_FD_Table table = new X_FD_Table(env);
		table.setValueByName(env, COLUMNNAME_FD_Table_ID, tableId);
		table.setValueByName(env, COLUMNNAME_Table_Name, tableName);
		table.setValueByName(env, COLUMNNAME_FD_Name, name);
		table.setValueByName(env, COLUMNNAME_FD_Comment, comment);
		table.save(env);
	}

}

package com.officina_hide.base.common;

import java.sql.SQLException;
import java.sql.Statement;

import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Process;
import com.officina_hide.base.tools.FDTableColumn;

/**
 * DB取扱汎用クラス<br>
 * @author officine-hide.com
 * @version 1.20
 * @since 2020/11/05
 */
public class FD_DB_Utility extends FD_DB {

	/**
	 * テーブル情報削除<br>
	 * <p>指定されたテーブルに保管されている情報の内、指定されてプロセス情報IDを持つ情報を削除する。</p>
	 * @author officine-hide.com
	 * @since 1.10 2020/11/03
	 * @param env 環境情報
	 * @param tableName テーブル名
	 * @param processId プロセス情報ID
	 */
	public void deleteDataByProcessId(FD_EnvData env, String tableName, int processId) {	
		Statement stmt = null;
		StringBuffer sql = new StringBuffer();
		FD_DB DB = new FD_DB();
		try {
			sql.append("DELETE FROM ").append(tableName).append(" ");
			sql.append("WHERE ").append(I_FD_Process.COLUMNNAME_FD_Process_ID).append(" = ").append(processId);
			DB.connection(env);
			stmt = DB.createStatement();
			stmt.executeUpdate(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(stmt);
		}
	}

	/**
	 * テーブル項目基本情報登録<br>
	 * @author officina-hide.com
	 * @since 1.21 2020/11/21
	 * @param env 環境情報
	 * @param tableId テーブル情報ID
	 */
	public void baseColumnEntry(FD_EnvData env, int tableId) {
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.addData(env, 0, tableId, COLUMNNAME_FD_Process_ID, NAME_FD_Process_ID, COMMENT_FD_Process_ID
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 900, "N", "N");
		column.addData(env, 0, tableId, COLUMNNAME_FD_CREATE, NAME_FD_CREATE, COMMENT_FD_CREATE
				, COLUMNTYPE_ID_FD_Date, null, 0, 910, "N", "N");
		column.addData(env, 0, tableId, COLUMNNAME_FD_CREATED, NAME_FD_CREATED, COMMENT_FD_CREATED
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 920, "N", "N");
		column.addData(env, 0, tableId, COLUMNNAME_FD_UPDATE, NAME_FD_UPDATE, COMMENT_FD_UPDATE
				, COLUMNTYPE_ID_FD_Date, null, 0, 930, "N", "N");
		column.addData(env, 0, tableId, COLUMNNAME_FD_UPDATED, NAME_FD_UPDATED, COMMENT_FD_UPDATED
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 940, "N", "N");
	}

}

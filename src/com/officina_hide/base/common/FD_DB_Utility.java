package com.officina_hide.base.common;

import java.sql.SQLException;
import java.sql.Statement;

import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Process;

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

}

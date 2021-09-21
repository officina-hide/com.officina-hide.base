package com.officina_hide.base.model;

import java.sql.SQLException;
import java.sql.Statement;

import com.officina_hide.base.common.FD_EnvData;

/**
 * テーブル項目情報クラス[Table item information class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/21
 */
public class FD_Column extends FD_DB implements I_FD_Column {

	/**
	 * テーブル項目テーブル生成[Table item table generation]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/21
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		Statement stmt = null;
		try {
			connection(env);
			stmt = getConn().createStatement();
			stmt.executeUpdate(Table_Drop_SQL);
			stmt.executeUpdate(Table_Create_SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(stmt, null);
		}
	}

}

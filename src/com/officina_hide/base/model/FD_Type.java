package com.officina_hide.base.model;

import java.sql.SQLException;
import java.sql.Statement;

import com.officina_hide.base.common.FD_EnvData;

/**
 * 属性情報クラス[Type infoemation class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/21
 */
public class FD_Type extends FD_DB implements I_FD_Type {

	/**
	 * 属性情報テーブル生成[Type information table generation]
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

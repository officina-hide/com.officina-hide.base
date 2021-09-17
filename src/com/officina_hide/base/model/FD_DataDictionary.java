package com.officina_hide.base.model;

import java.sql.SQLException;
import java.sql.Statement;

import com.officina_hide.base.common.FD_EnvData;

/**
 * 辞書情報クラス[Dictionary information class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/17
 */
public class FD_DataDictionary extends FD_DB implements I_FD_DataDictionary {

	/**
	 * 辞書テーブル生成[Dictionary Table generate]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/17
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

	/**
	 * 辞書テーブル初期登録[Dictionary table initial registration]<br>
	 * @author officina-hide.net
	 * @since 2021/09/17
	 * @param env 環境情報[Environment information]
	 */
	public void addBaseData(FD_EnvData env) {
		//採番情報の登録
		FD_Numbering num = new FD_Numbering();
		num.add(env, 0, Table_ID, 101, 0);
	}

}

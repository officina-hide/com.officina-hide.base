package com.officina_hide.base.tools;

import java.sql.SQLException;
import java.sql.Statement;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_DataDictionary;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;
import com.officina_hide.base.model.I_FD_DataDictionary;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.I_FD_TypeItem;

/**
 * 属性項目情報クラス[Type item information class]<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2021/09/22
 */
public class FD_TypeItem extends FD_DB implements I_FD_TypeItem {

	/**
	 * 属性項目情報生成[Type item information generate]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/22
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
	 * テーブル毎の登録処理を行う。[Perform registration processing for each table.]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/22
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name]
	 */
	public void addData(FD_EnvData env, String tableName) {
		switch(tableName) {
		case I_FD_Numbering.Table_Name:
			FD_Numbering num = new FD_Numbering();
			num.add(env, 0, Table_ID, 101, 0);
			break;
		case I_FD_DataDictionary.Table_Name:
			FD_DataDictionary dd = new FD_DataDictionary();
			dd.add(env, 0, COLUMNNAME_FD_TypeItem_ID, NAME_FD_TypeItem_ID, COMMENT_FD_TypeItem_ID);
			dd.add(env, 0, COLUMNNAME_FD_TypeItem_Name, NAME_FD_TypeItem_Name, COMMENT_FD_TypeItem_Name);
			break;
		case I_FD_Table.Table_Name:
			FD_Table table = new FD_Table();
			table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
			break;
		}
	}

}

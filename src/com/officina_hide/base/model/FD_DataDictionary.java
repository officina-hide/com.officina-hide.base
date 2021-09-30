package com.officina_hide.base.model;

import java.sql.SQLException;
import java.sql.Statement;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;

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
	 * テーブル毎の登録処理を行う。[Perform registration processing for each table.]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/18
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name]
	 */
	public void addData(FD_EnvData env, String tableName) {
		switch(tableName) {
		case Table_Name:
			add(env, 0, COLUMNNAME_FD_DataDictionary_ID, NAME_FD_DataDictionary_ID, COMMENT_FD_DataDictionary_ID);
			add(env, 0, COLUMNNAME_FD_DataDictionary_Name, NAME_FD_DataDictionary_Name
					, COMMENT_FD_DataDictionary_Name);
			break;
		case I_FD_Numbering.Table_Name:
			//採番情報の登録
			FD_Numbering num = new FD_Numbering();
			num.add(env, 0, Table_ID, 101, 0);
			break;
		case I_FD_Table.Table_Name:
			FD_Table table = new FD_Table();
			table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
			break;
		case I_FD_Column.Table_Name:
			FD_Column column = new FD_Column();
			column.add(env, 0, Table_ID, COLUMNNAME_FD_DataDictionary_ID, FD_Item_ID, 0, false, true, null);
			column.add(env, 0, Table_ID, COLUMNNAME_FD_DataDictionary_Name, FD_Item_String, 100, true, false, null);
			column.add(env, 0, Table_ID, COLUMNNAME_FD_Name, FD_Item_String, 100, true, false, null);
			column.add(env, 0, Table_ID, COLUMNNAME_FD_Description, FD_Item_Text, 0, true, false, null);
			addCommonColumn(env, Table_ID);
			break;
		}
	}

	/**
	 * 辞書情報の登録[Registration of dictionary information]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/18
	 * @param env 環境情報[Environment information]
	 * @param id 辞書情報ID[Dictionary information ID]
	 * @param name 辞書識別名[Dictionary distinguished name]
	 * @param dispName 辞書表示名[Dictionary display name]
	 * @param description 辞書説明[Dictionaty description]
	 */
	public void add(FD_EnvData env, int id, String name, String dispName, String description) {
		X_FD_DataDictionary dd = new X_FD_DataDictionary(env, 0);
		if(id == 0) {
			//採番処理
			FD_Numbering num = new FD_Numbering();
			dd.setFD_DataDictionary_ID(num.getNumber(env, Table_ID));
		}
		dd.setFD_DataDictionary_Name(name);
		dd.setFD_Name(dispName);
		dd.setFD_Description(description);
		dd.setFD_Group_ID(SYSTEM_GROUP_ID);
		dd.save(env);
	}

	/**
	 * 辞書識別名から辞書情報ID取得[Obtain dictionary information ID from dictionary identification name]<br>
	 * @author officina-hide.net
	 * @since 1.00 2021/09/23
	 * @param env 環境情報[Enfironment information]
	 * @param name 辞書識別名[Dictionary distinguished name]
	 * @return 辞書情報ID[Dictionary information ID]
	 */
	public long getIDByName(FD_EnvData env, String name) {
		FD_WhereData where = new FD_WhereData(COLUMNNAME_FD_DataDictionary_Name, name);
		X_FD_DataDictionary dd = new X_FD_DataDictionary(env, where);
		return dd.getFD_DataDictionary_ID();
	}

}

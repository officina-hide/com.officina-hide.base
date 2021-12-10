package com.officina_hide.base.model;

import java.sql.SQLException;
import java.sql.Statement;

import com.officina_hide.base.common.FD_EnvData;

/**
 * 属性情報クラス[Type information class]<br>
 * @author officina-hide.net
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
			dd.add(env, 0, COLUMNNAME_FD_Type_ID, NAME_FD_Type_ID, COMMENT_FD_Type_ID);
			dd.add(env, 0, COLUMNNAME_FD_Type_Name, NAME_FD_Type_Name, COMMENT_FD_Type_Name);
			break;
		case I_FD_Table.Table_Name:
			FD_Table table = new FD_Table();
			table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
			break;
		case I_FD_Column.Table_Name:
			FD_Column column = new FD_Column();
			column.add(env, 0, Table_ID, COLUMNNAME_FD_Type_ID, FD_ITEM_ID, 0, false, true, null);
			column.add(env, 0, Table_ID, COLUMNNAME_FD_Type_Name, FD_ITEM_String, 100, true, false, null);
			column.add(env, 0, Table_ID, COLUMNNAME_FD_Name, FD_ITEM_String, 100, true, false, null);
			column.add(env, 0, Table_ID, COLUMNNAME_FD_Description, FD_ITEM_Text, 0, true, false, null);
			addCommonColumn(env, Table_ID);
			break;
		}
	}

	/**
	 * 情報登録[Infoemation registration]
	 * @param env 環境情報[Environment information]
	 * @param typeID 属性情報ID[Type information ID]
	 * @param typeName 属性識別名[Attribute distinguished name]
	 * @param name 表示名[display name]
	 * @param description 解説[description]
	 * @return id 情報ID[information ID]
	 */
	public long add(FD_EnvData env, int typeID, String typeName, String name, String description) {
		long id = 0;
		
		X_FD_Type type = new X_FD_Type(env, 0);
		type.setFD_Type_ID(typeID);
		type.setFD_Type_Name(typeName);
		type.setFD_Name(name);
		type.setFD_Description(description);
		type.setFD_Group_ID(SYSTEM_GROUP_ID);
		type.save(env);
		
		id = type.getFD_Type_ID();
		
		return id;
	}

}

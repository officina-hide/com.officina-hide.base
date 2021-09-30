package com.officina_hide.base.model;

import java.sql.SQLException;
import java.sql.Statement;

import com.officina_hide.base.common.FD_EnvData;

/**
 * 属性項目設定情報クラス[Attribute item setting information class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/27
 */
public class FD_TypeITem_Param extends FD_DB implements I_FD_TypeITem_Param {

	/**
	 * 属性項目設定情報テーブル生成[Attribute item setting information table generation]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/27
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
	 * @since 1.00 2021/09/27
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
			dd.add(env, 0, COLUMNNAME_FD_TypeItem_Param_ID, NAME_FD_TypeItem_Param_ID
					, COMMENT_FD_TypeItem_Param_ID);
			dd.add(env, 0, COLUMNNAME_FD_TypeItem_Param_Name, NAME_FD_TypeItem_Param_Name
					, COMMENT_FD_TypeItem_Param_Name);
			break;
		case I_FD_Table.Table_Name:
			FD_Table table = new FD_Table();
			table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
			break;
		case I_FD_Column.Table_Name:
			FD_Column column = new FD_Column();
			column.add(env, 0, Table_ID, COLUMNNAME_FD_TypeItem_Param_ID, FD_Item_ID, 0, false, true, null);
			column.add(env, 0, Table_ID, COLUMNNAME_FD_TypeItem_Param_Name, FD_Item_String, 100, true, false, null);
			column.add(env, 0, Table_ID, COLUMNNAME_FD_TypeItem_ID, FD_Item_ID, 0, true, false, null);
			column.add(env, 0, Table_ID, COLUMNNAME_FD_Name, FD_Item_String, 100, true, false, null);
			column.add(env, 0, Table_ID, COLUMNNAME_FD_Value, FD_Item_String, 256, true, false, null);
			column.add(env, 0, Table_ID, COLUMNNAME_FD_Description, FD_Item_Text, 0, true, false, null);
			addCommonColumn(env, Table_ID);
			break;
		}
	}

	/**
	 * 情報登録[Save data]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/27
	 * @param env 環境情報[Environment information]
	 * @param typeItemParamID 属性項目設定情報ID[Attribute item setting information ID]
	 * @param typeItemName 属性項目設定名[Attribute item setting name]
	 * @param typeItemID 属性項目情報ID[Attribute item information ID]
	 * @param name 表示名[Displey name]
	 * @param value 値[value]
	 * @param description 説明[description]
	 */
	public void add(FD_EnvData env, int typeItemParamID, String typeItemName, long typeItemID, String name, String value,
			String description) {
		X_FD_TypeItem_Param ftp = new X_FD_TypeItem_Param(env, 0);
		ftp.setFD_TypeItemParamID(typeItemParamID);
		ftp.setFD_TypeItemParamName(typeItemName);
		ftp.setFD_TypeItemID(typeItemID);
		ftp.setFD_Value(value);
		ftp.setFD_Name(name);
		ftp.setFD_Description(description);
		ftp.setFD_Group_ID(SYSTEM_GROUP_ID);
		ftp.save(env);
	}

}

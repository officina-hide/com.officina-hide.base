package com.officina_hide.base.model;

import java.sql.SQLException;
import java.sql.Statement;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;

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

	/**
	 * テーブル毎の登録処理を行う。[Perform registration processing for each table.]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/19
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name]
	 */
	public void addData(FD_EnvData env, String tableName) {
		switch(tableName) {
		case  I_FD_Numbering.Table_Name:
			FD_Numbering num =  new FD_Numbering();
			num.add(env, 0, Table_ID, 101, 0);
			break;
		case I_FD_DataDictionary.Table_Name:
			FD_DataDictionary dd = new FD_DataDictionary();
			dd.add(env, 0, COLUMNNAME_FD_Column_ID, NAME_FD_Column_ID, COMMENT_FD_Column_ID);
			dd.add(env, 0, COLUMNNAME_FD_Column_Size, NAME_FD_Column_Size, COMMENT_FD_Column_Size);
			break;
		case I_FD_Table.Table_Name:
			FD_Table table = new FD_Table();
			table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
			break;
		case Table_Name:
			add(env, 0, Table_ID, COLUMNNAME_FD_Column_ID, FD_Item_ID, 0);
			add(env, 0, Table_ID, COLUMNNAME_FD_DataDictionary_ID, FD_Item_ID, 0);
			add(env, 0, Table_ID, COLUMNNAME_FD_Table_ID, FD_Item_ID, 0);
			add(env, 0, Table_ID, COLUMNNAME_FD_TypeItem_ID, FD_Item_ID, 0);
			add(env, 0, Table_ID, COLUMNNAME_FD_Column_Size, FD_ITEM_Unsigned_Int, 0);
			add(env, 0, Table_ID, COLUMNNAME_FD_Name, FD_Item_String, 100);
			add(env, 0, Table_ID, COLUMNNAME_FD_Description, FD_Item_Text, 0);
			addCommonColumn(env, Table_ID);
			break;
		}
	}

	/**
	 * 情報登録[Save information]<br>
	 * @author officina-hide.net
	 * @since 1.00 2021/09/23
	 * @param env 環境情報[Enfironment information]
	 * @param columnID テーブル項目情報ID[Table item information ID]
	 * @param tableID テーブル情報ID[Table information ID]
	 * @param columnName テーブル項目名[Table item name]
	 * @param typeName 属性名[Type name]
	 * @param size 属性桁数[Type size]
	 */
	public void add(FD_EnvData env, int columnID, long tableID, String columnName, String typeName, int size) {
		X_FD_Column column = new X_FD_Column(env, 0);
		column.setFD_Column_ID(0);
		column.setFD_Table_ID(tableID);
		FD_DataDictionary dd = new FD_DataDictionary();
		column.setFD_DataDictionary_ID(dd.getIDByName(env, columnName));
		column.setFD_TypeItem_ID(getTypeItemID(env, typeName));
		column.setFD_Group_ID(SYSTEM_GROUP_ID);
		column.setFD_ColumnSize(size);
		column.save(env);
	}

	/**
	 * テーブル項目用属性項目情報ID取得[Get attribute item information ID for table item]<br>
	 * @author officina-hide.net
	 * @param env 環境情報[Enfironment information]
	 * @since 1.00 2021/09/23
	 * @param typeName 属性項目名[Type item name]
	 * @return 属性項目情報ID[Type item information ID]
	 */
	private long getTypeItemID(FD_EnvData env, String typeName) {
		//属性情報ID取得
		X_FD_Type type = new X_FD_Type(env, new FD_WhereData(I_FD_Type.COLUMNNAME_FD_Type_Name,FD_Column_Type));
		//属性項目情報ID取得
		FD_WhereData where = new FD_WhereData(I_FD_TypeItem.COLUMNNAME_FD_Type_ID, type.getFD_Type_ID());
		where.add("AND", I_FD_TypeItem.COLUMNNAME_FD_TypeItem_Name, typeName);
		X_FD_TypeItem typeItem = new X_FD_TypeItem(env, where);
		return typeItem.getFD_TypeItem_ID();
	}

}

package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.officina_hide.base.common.FD_EnvData;

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
		case I_FD_Column.Table_Name:
			FD_Column column = new FD_Column();
			column.add(env, 0, Table_ID, COLUMNNAME_FD_TypeItem_ID, FD_ITEM_ID, 0, false, true, null);
			column.add(env, 0, Table_ID, COLUMNNAME_FD_TypeItem_Name, FD_ITEM_String, 100, true, false, null);
			column.add(env, 0, Table_ID, COLUMNNAME_FD_Type_ID, FD_ITEM_ID, 0, true, false, null);
			column.add(env, 0, Table_ID, COLUMNNAME_FD_Name, FD_ITEM_String, 100, true, false, null);
			column.add(env, 0, Table_ID, COLUMNNAME_FD_Description, FD_ITEM_Text, 0, true, false, null);
			addCommonColumn(env, Table_ID);
			break;
		}
	}

	/**
	 * 情報保存[save information]
	 * @param env 環境情報[Enfironment information]
	 * @param typeItemID 属性項目情報ID[Type item information ID]
	 * @param typeItemName 属性項目識別ID[Attribute item identification ID]
	 * @param typeID 属性情報ID[Type information ID]
	 * @param name 
	 * @param description
	 * @return id 属性情報ID[Type item information ID]
	 */
	public long add(FD_EnvData env, int typeItemID, String typeItemName, long typeID
			, String name, String description) {
		X_FD_TypeItem typeItem = new X_FD_TypeItem(env, 0);
		typeItem.setFD_TypeItem_ID(typeItemID);
		typeItem.setFD_TypeItem_Name(typeItemName);
		typeItem.setFD_Type_ID(typeID);
		typeItem.setFD_Name(name);
		typeItem.setFD_Description(description);
		typeItem.setFD_Group_ID(SYSTEM_GROUP_ID);
		typeItem.save(env);
		
		return typeItem.getFD_TypeItem_ID();
	}

	/**
	 * 属性項目情報ID取得[Attribute item information ID acquisition]<br>
	 * @param env 環境情報[Environment information]
	 * @param type 属性名[Attribute name]
	 * @param typeItem 属性項目名[Attribute item name]
	 * @return 属性項目情報ID[Attribute item information ID]
	 */
	public long getTypeItemID(FD_EnvData env, String type, String typeItem) {
		long id = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT ").append("ti.").append(I_FD_TypeItem.COLUMNNAME_FD_TypeItem_ID)
				.append(" FROM ").append(I_FD_TypeItem.Table_Name).append(" ti ");
			sql.append("LEFT JOIN ").append(I_FD_Type.Table_Name).append(" t ")
				.append(" ON ").append("t.").append(I_FD_Type.COLUMNNAME_FD_Type_ID).append(" = ")
				.append("ti.").append(I_FD_TypeItem.COLUMNNAME_FD_Type_ID).append(" ");
			sql.append("WHERE ").append("t.").append(I_FD_Type.COLUMNNAME_FD_Type_Name).append(" = ? ");
			sql.append("AND ").append("ti.").append(I_FD_TypeItem.COLUMNNAME_FD_TypeItem_Name).append(" = ? ");
			connection(env);
			pstmt = getConn().prepareStatement(sql.toString());
			pstmt.setString(1, type);
			pstmt.setString(2, typeItem);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				id = rs.getLong("ti."+I_FD_TypeItem.COLUMNNAME_FD_TypeItem_ID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
		return id;
	}

}

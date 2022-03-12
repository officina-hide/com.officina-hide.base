package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Items;

/**
 * テーブル項目I/Oクラス[Table item I / O class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/04
 */
public class X_FD_Column extends FD_DB implements I_FD_Column {
	
	/** 環境情報 */
	FD_EnvData env = new FD_EnvData();
	
	/** 項目 */
	private long FD_Column_ID;
	private long FD_DataDictionary_ID;
	private X_FD_DataDictionary FD_DataDictionary;
	private long FD_Table_ID;
	private X_FD_Table FD_Table;
	private long FD_TypeItem_ID;
	private X_FD_TypeItem FD_TypeItem;
	private int FD_ColumnSize;
	private boolean FD_Is_Null;
	private boolean FD_Is_Key;
	private String FD_Default;
	private Boolean FD_IS_Common;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officina-hide.net
	 * @since 1.00 2021/09/05
	 * @param env 環境情報[Environment Information]
	 * @param columnID テーブル項目情報ID[Table Item Information ID]
	 */
	public X_FD_Column(FD_EnvData env, long columnID) {
		this.env = env;
		
		items = new FD_Items();
		items.add(COLUMNNAME_FD_Column_ID, null, FD_ITEM_ID);
		items.add(COLUMNNAME_FD_DataDictionary_ID, null, FD_ITEM_ID);
		items.add(COLUMNNAME_FD_Table_ID, null, FD_ITEM_ID);
		items.add(COLUMNNAME_FD_TypeItem_ID, null, FD_ITEM_ID);
		items.add(COLUMNNAME_FD_Column_Size, null, FD_ITEM_Unsigned_Int);
		items.add(COLUMNNAME_FD_IS_Null, null, FD_ITEM_YES_NO);
		items.add(COLUMNNAME_FD_IS_Key, null, FD_ITEM_YES_NO);
		items.add(COLUMNNAME_FD_Default, null, FD_ITEM_String);
		baseItemSet(items);
		items.setTableId(Table_ID);
		items.setTableName(Table_Name);

		if(columnID > 0) {
			load(env, Table_Name, columnID, items);
		}
	}

	/**
	 * コンストラクタ[Constructor]<br>
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name]
	 * @param columnName テーブル項目名[Table item name]
	 */
	public X_FD_Column(FD_EnvData env, String tableName, String columnName) {
		createItemList(env, Table_Name);
		//テーブル項目情報取得
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT ").append(I_FD_Column.COLUMNNAME_FD_Column_ID)
				.append(" FROM ").append(Table_Name).append(" c ");
			sql.append("LEFT JOIN ").append(I_FD_DataDictionary.Table_Name).append(" dd ")
				.append(" ON ").append("dd.").append(I_FD_DataDictionary.COLUMNNAME_FD_DataDictionary_ID).append(" = ")
				.append("c.").append(I_FD_Column.COLUMNNAME_FD_DataDictionary_ID).append(" ");
			sql.append("LEFT JOIN ").append(I_FD_Table.Table_Name).append(" t ")
				.append(" ON ").append("t.").append(I_FD_Table.COLUMNNAME_FD_Table_ID).append(" = ")
				.append("c.").append(I_FD_Column.COLUMNNAME_FD_Table_ID).append(" ");
			sql.append("WHERE ").append("t.").append(I_FD_Table.COLUMNNAME_FD_Table_Name).append(" = ? ");
			sql.append("AND ").append("dd.").append(I_FD_DataDictionary.COLUMNNAME_FD_DataDictionary_Name).append(" = ? ");
			connection(env);
			pstmt = getConn().prepareStatement(sql.toString());
			pstmt.setString(1, tableName);
			pstmt.setString(2, columnName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				load(env, Table_Name, rs.getLong(I_FD_Column.COLUMNNAME_FD_Column_ID), items);
			} else {
				System.out.println("Not found !! :"+tableName+" : "+columnName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 情報登録[save information]
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
	}

	public long getFD_Column_ID() {
		FD_Column_ID = items.getlongData(COLUMNNAME_FD_Column_ID);
		return FD_Column_ID;
	}
	public void setFD_Column_ID(long columnID) {
		items.setValue(COLUMNNAME_FD_Column_ID, columnID);
	}
	public long getFD_DataDictionary_ID() {
		FD_DataDictionary_ID = items.getlongData(COLUMNNAME_FD_DataDictionary_ID);
		return FD_DataDictionary_ID;
	}
	public void setFD_DataDictionary_ID(long dataDictionaryID) {
		items.setValue(COLUMNNAME_FD_DataDictionary_ID, dataDictionaryID);
	}
	public X_FD_DataDictionary getFD_DataDictionary() {
		if(FD_DataDictionary == null) {
			if(getFD_DataDictionary_ID() == 0) {
				return null;
			} else {
				FD_DataDictionary = new X_FD_DataDictionary(env, getFD_DataDictionary_ID());
			}
		}
		return FD_DataDictionary;
	}
	public long getFD_Table_ID() {
		FD_Table_ID = items.getlongData(COLUMNNAME_FD_Table_ID);
		return FD_Table_ID;
	}
	public void setFD_Table_ID(long tableID) {
		items.setValue(COLUMNNAME_FD_Table_ID, tableID);
	}
	public X_FD_Table getFD_Table() {
		if(FD_Table == null) {
			if(getFD_Table_ID() == 0) {
				return null;
			} else {
				FD_Table = new X_FD_Table(env, getFD_Table_ID());
			}
		}
		return FD_Table;
	}
	public long getFD_TypeItem_ID() {
		FD_TypeItem_ID = items.getlongData(COLUMNNAME_FD_TypeItem_ID);
		return FD_TypeItem_ID;
	}
	public void setFD_TypeItem_ID(long typeItemID) {
		items.setValue(COLUMNNAME_FD_TypeItem_ID, typeItemID);
	}
	public X_FD_TypeItem getFD_TypeItem() {
		if(FD_TypeItem == null) {
			if(getFD_TypeItem_ID() == 0) {
				return null;
			}
			FD_TypeItem = new X_FD_TypeItem(env, getFD_TypeItem_ID());
		}
		return FD_TypeItem;
	}
	public int getFD_ColumnSize() {
		FD_ColumnSize = items.getintData(COLUMNNAME_FD_Column_Size);
		return FD_ColumnSize;
	}
	public void setFD_ColumnSize(int columnSize) {
		items.setValue(COLUMNNAME_FD_Column_Size, columnSize);
	}
	public boolean isFD_Is_Null() {
		FD_Is_Null = items.getBooleanData(COLUMNNAME_FD_IS_Null);
		return FD_Is_Null;
	}
	public void setFD_Is_Null(boolean isnull) {
		items.setValue(COLUMNNAME_FD_IS_Null, isnull);
	}
	public boolean isFD_Is_Key() {
		FD_Is_Key = items.getBooleanData(COLUMNNAME_FD_IS_Key);
		return FD_Is_Key;
	}
	public void setFD_Is_Key(boolean isKey) {
		items.setValue(COLUMNNAME_FD_IS_Key, isKey);
	}
	public String getFD_Default() {
		FD_Default = items.getStringData(COLUMNNAME_FD_Default);
		return FD_Default;
	}
	public void setFD_Default(String valueData) {
		items.setValue(COLUMNNAME_FD_Default, valueData);
	}
	public Boolean isFD_IS_Common() {
		FD_IS_Common = items.getBooleanData(COLUMNNAME_FD_IS_Common);
		return FD_IS_Common;
	}
	public void setFD_IS_Common(Boolean isCommon) {
		items.setValue(COLUMNNAME_FD_IS_Common, isCommon);
	}
}

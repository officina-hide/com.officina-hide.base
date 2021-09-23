package com.officina_hide.base.model;

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
	private FD_Items items;
	private long FD_Column_ID;
	private long FD_DataDictionary_ID;
	private X_FD_DataDictionary FD_DataDictionary;
	private long FD_Table_ID;
	private X_FD_Table FD_Table;
	private long FD_Type_ID;
	private X_FD_Type FD_Type;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/05
	 * @param env 環境情報[Environment Information]
	 * @param columnId テーブル項目情報ID[Table Item Information ID]
	 */
	public X_FD_Column(FD_EnvData env, int columnId) {
		this.env = env;
		
		items = new FD_Items();
		items.add(COLUMNNAME_FD_Column_ID, null, FD_Item_ID);
		items.add(COLUMNNAME_FD_Column_Name, null, FD_Item_String);
		items.add(COLUMNNAME_FD_DataDictionary_ID, null, FD_Item_ID);
		items.add(COLUMNNAME_FD_Table_ID, null, FD_Item_ID);
		items.add(COLUMNNAME_FD_Type_ID, null, FD_Item_ID);
		baseItemSet(items);
		items.setTableId(Table_ID);
		items.setTableName(Table_Name);

//		if(columnId > 0) {
//			load(env, Table_Name, columnId, items);
//		}
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
	public long getFD_Type_ID() {
		FD_Type_ID = items.getlongData(COLUMNNAME_FD_Type_ID);
		return FD_Type_ID;
	}
	public void setFD_Type_ID(long typeID) {
		items.setValue(COLUMNNAME_FD_Type_ID, typeID);
	}
	public X_FD_Type getFD_Type() {
		if(FD_Type == null) {
			if(getFD_Table_ID() == 0) {
				return null;
			} else {
				FD_Type = new X_FD_Type(env, getFD_Type_ID());
			}
		}
		return FD_Type;
	}
}

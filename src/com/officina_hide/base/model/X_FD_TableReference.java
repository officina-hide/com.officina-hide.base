package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;

/**
 * テーブル参照情報I/Oクラス[Table reference information I/O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/12/21 Ver. 1.00
 */
public class X_FD_TableReference extends FD_DB implements I_FD_TableReference {

	/** 項目 : テーブル参照情報ID */
	private long FD_TableReference_ID;
	/** 項目 : 参照情報ID */
	private long FD_Reference_ID;
	/** 情報 : 参照情報 */
	private X_FD_Reference FD_Reference;
	/** 項目 : テーブル情報ID */
	private long FD_Table_ID;
	/** 情報 : テーブル情報 */
	private X_FD_Table FD_Table;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2021/12/21 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tableReferenceId テーブル参照情報ID[Talbe reference information ID]
	 */
	public X_FD_TableReference(FD_EnvData env, long tableReferenceId) {
		createItemList(env, Table_Name);
		if(tableReferenceId > 0) {
			load(env, Table_Name, tableReferenceId, items);
		}
	}

	/**
	 * コンストラクタ[Constructor]
	 * @author officina-hide.net
	 * @since 2021/12/25 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param where 抽出条件[Extraction condition]
	 */
	public X_FD_TableReference(FD_EnvData env, FD_WhereData where) {
		createItemList(env, Table_Name);
		items.setTableName(Table_Name);
		load(env, items, where);
	}

	/**
	 * 情報登録[Data save]<br>
	 * @author officina-hide.net
	 * @since 2021/12/21 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
	}

	public long getFD_TableReference_ID() {
		FD_TableReference_ID = items.getlongData(COLUMNNAME_FD_TableReference_ID);
		return FD_TableReference_ID;
	}
	public void setFD_TableReference_ID(long tableReferenceId) {
		items.setValue(COLUMNNAME_FD_Table_ID, tableReferenceId);
	}
	public long getFD_Reference_ID() {
		FD_Reference_ID = items.getlongData(COLUMNNAME_FD_Reference_ID);
		return FD_Reference_ID;
	}
	public void setFD_Reference_ID(long referenceId) {
		items.setValue(COLUMNNAME_FD_Reference_ID, referenceId);
	}
	public long getFD_Table_ID() {
		FD_Table_ID = items.getlongData(COLUMNNAME_FD_Table_ID);
		return FD_Table_ID;
	}
	public void setFD_Table_ID(long tableId) {
		items.setValue(COLUMNNAME_FD_Table_ID, tableId);
	}
	public X_FD_Reference getFD_Reference(FD_EnvData env) {
		if(FD_Reference == null) {
			if(getFD_Reference_ID() == 0) {
				return null;
			} else {
				FD_Reference = new X_FD_Reference(env, getFD_Reference_ID());
			}
		} else {
			if(FD_Reference.getFD_Reference_ID() != getFD_Reference_ID()) {
				FD_Reference = new X_FD_Reference(env, getFD_Reference_ID());
			}
		}
		return FD_Reference;
	}
	public X_FD_Table getFD_Table(FD_EnvData env) {
		if(FD_Table == null) {
			if(getFD_Table_ID() == 0) {
				return null;
			} else {
				FD_Table = new X_FD_Table(env, getFD_Table_ID());
			}
		} else {
			if(FD_Table.getFD_Table_ID() != getFD_Table_ID()) {
				FD_Table = new X_FD_Table(env, getFD_Table_ID());
			}
		}
		return FD_Table;
	}

}

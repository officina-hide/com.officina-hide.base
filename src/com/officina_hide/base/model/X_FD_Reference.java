package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_EnvData;

/**
 * 参照情報I/Oクラス[Reference information I/O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/12/16 Ver. 1.00
 */
public class X_FD_Reference extends FD_DB implements I_FD_Reference {

	/** 項目 : 参照情報ID */
	private long FD_Reference_ID;
	/** 項目 : 参照情報名 */
	private String FD_Reference_Name;
	/** 項目 : 参照種別ID */
	private long FD_ReferenceType_ID;
	/** 項目 : 参照種別ID */
	private X_FD_TypeItem FD_ReferenceType;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2021/12/16 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param referenceId 参照情報ID[Reference information ID]
	 */
	public X_FD_Reference(FD_EnvData env, long referenceId) {
		createItemList(env, Table_Name);
		if(referenceId > 0) {
			load(env, Table_Name, referenceId, items);
		}
	}

	/**
	 * 情報保存[Save data]<br>
	 * @author officina-hide.net
	 * @since 2021/12/16 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
	}

	public long getFD_Reference_ID() {
		FD_Reference_ID = items.getlongData(COLUMNNAME_FD_Reference_ID);
		return FD_Reference_ID;
	}
	public void setFD_Reference_ID(long referenceId) {
		items.setValue(COLUMNNAME_FD_Reference_ID, referenceId);
	}
	public String getFD_Reference_Name() {
		FD_Reference_Name = items.getStringData(COLUMNNAME_FD_Reference_Name);
		return FD_Reference_Name;
	}
	public void setFD_Reference_Name(String referenceName) {
		items.setValue(COLUMNNAME_FD_Reference_Name, referenceName);
	}
	public long getFD_ReferenceType_ID() {
		FD_ReferenceType_ID = items.getlongData(COLUMNNAME_FD_ReferenceType_ID);
		return FD_ReferenceType_ID;
	}
	public void setFD_ReferenceType_ID(long referenceTypeId) {
		items.setValue(COLUMNNAME_FD_ReferenceType_ID, referenceTypeId);
	}
	public X_FD_TypeItem getFD_ReferenceType(FD_EnvData env) {
		if(FD_ReferenceType == null) {
			if(getFD_ReferenceType_ID() == 0) {
				return null;
			} else {
				FD_ReferenceType  = new X_FD_TypeItem(env, getFD_ReferenceType_ID());
			}
		} else {
			if(FD_ReferenceType.getFD_TypeItem_ID() != getFD_ReferenceType_ID()) {
				FD_ReferenceType  = new X_FD_TypeItem(env, getFD_ReferenceType_ID());
			}
		}
		return FD_ReferenceType;
	}

}

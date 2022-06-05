package com.officina_hide.medical.model;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * 検査項目情報I/Oクラス[Inspection item information I/O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/05/23 Ver. 1.00
 */
public class X_FM_InspectionItem extends FD_DB implements I_FM_InspectionItem {

	/** 項目 : 検査項目情報ID */
	private long FM_InspectionItem_ID;
	/** 項目 : 検査項目コード */
	private String FM_InspectionItem_Code;
	
	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/05/23 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param entry 登録情報[Entry data]
	 */
	public X_FM_InspectionItem(FD_EnvData env, FD_Collections entry) {
		createColumnList(env, Table_Name);
		columnCollection.setData(entry);
	}

	/**
	 * 情報登録[Data save]<br>
	 * @author officina-hide.net
	 * @since 2022/05/26 Ver. 1.00 
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

	public long getFM_InspectionItem_ID() {
		FM_InspectionItem_ID = (long) columnCollection.getValue(COLUMNNAME_FM_InspectionItem_ID);
		return FM_InspectionItem_ID;
	}
	public void setFM_InspectionItem_ID(long imspectionItemId) {
		columnCollection.setValue(COLUMNNAME_FM_InspectionItem_ID, imspectionItemId);
	}
	public String getFM_InspectionItem_Code() {
		FM_InspectionItem_Code = (String) columnCollection.getValue(COLUMNNAME_FM_InspectionItem_Code);
		return FM_InspectionItem_Code;
	}
	public void setFM_InspectionItem_Code(String inspectionItemCode) {
		columnCollection.setValue(COLUMNNAME_FM_InspectionItem_Code, inspectionItemCode);
	}
}

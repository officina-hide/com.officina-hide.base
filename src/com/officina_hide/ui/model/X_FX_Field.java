package com.officina_hide.ui.model;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.X_FD_Reference;

/**
 * 画面項目I/Oクラス[Screen item I/O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/06/09 Ver. 1.00
 */
public class X_FX_Field extends FD_DB implements I_FX_Field {

	/** 項目 : 画面項目情報ID */
	private long FX_Field_ID;
	/** 項目 : 画面項目コード */
	private String FX_Field_Code;
	/** 項目 : 画面項目種別(ID) */
	private long FX_Field_Type_ID;
	/** 情報 : 画面項目種別(FD_Reference) */
	private X_FD_Reference FX_Field_Type;
	
	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/06/09 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param entry 登録情報
	 */
	public X_FX_Field(FD_EnvData env, FD_Collections entry) {
		createColumnList(env, Table_Name);
		columnCollection.setData(entry);
	}

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/06/11 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param fieldId 画面項目情報ID[Screen item information ID]
	 */
	public X_FX_Field(FD_EnvData env, long fieldId) {
		createColumnList(env, Table_Name);
		load(env, fieldId, Table_Name);
	}

	/**
	 * 情報登録[Data entry]<br>
	 * @author officina-hide.net
	 * @since 2022/06/09 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

	public long getFX_Field_ID() {
		FX_Field_ID = (long) columnCollection.getValue(COLUMNNAME_FX_Field_ID);
		return FX_Field_ID;
	}
	public void setFX_Field_ID(long fieldId) {
		columnCollection.setValue(COLUMNNAME_FX_Field_ID, fieldId);
	}
	public String getFX_Field_Code() {
		FX_Field_Code = (String) columnCollection.getValue(COLUMNNAME_FX_Field_Code);
		return FX_Field_Code;
	}
	public void setFX_Field_Code(String fieldCode) {
		columnCollection.setValue(COLUMNNAME_FX_Field_Code, fieldCode);
	}
	public long getFX_Field_Type_ID() {
		FX_Field_Type_ID = (long) columnCollection.getValue(COLUMNNAME_FX_Field_Type_ID);
		return FX_Field_Type_ID;
	}
	public void setFX_Field_Type_ID(long fieldTypeId) {
		columnCollection.setValue(COLUMNNAME_FX_Field_Type_ID, fieldTypeId);
	}
	public X_FD_Reference getFX_Field_Type(FD_EnvData env) {
		if(getFX_Field_Type_ID() != 0) {
			if(FX_Field_Type == null || FX_Field_Type.getFD_Reference_ID() != getFX_Field_Type_ID()) {
				FX_Field_Type = new X_FD_Reference(env, getFX_Field_Type_ID());
			}
		}
		return FX_Field_Type;
	}

}

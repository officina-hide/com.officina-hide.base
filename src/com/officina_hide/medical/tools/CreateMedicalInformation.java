package com.officina_hide.medical.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.medical.model.FM_InspectionData;
import com.officina_hide.medical.model.FM_InspectionItem;
import com.officina_hide.medical.model.I_FM_InspectionItem;

/**
 * 医療情報生成処理[Medical information generation processing]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/05/02 Ver. 1.00
 */
public class CreateMedicalInformation {

	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクター[Constructor]
	 * @author officina-hide.net
	 * @since 2022/05/02 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public CreateMedicalInformation(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 医療情報生成[Medical information generation]<br>
	 * @author officina-hide.net
	 * @since 2022/05/02 Ver. 1.00
	 */
	public void execute() {
		//検査項目情報生成
		FM_InspectionItem item = new FM_InspectionItem(env);
		item.createTable();
		//検査項目情報登録
		entryInspectionItem();
		
		FM_InspectionData ind = new FM_InspectionData(env);
		ind.createTable();
	}

	/**
	 * 検査海句情報登録[Inspection sea phrase information registration]<br>
	 * @author officina-hide.net
	 * @since 2022/05/23 Ver. 1.00
	 */
	private void entryInspectionItem() {
		FM_InspectionItem item = new FM_InspectionItem(env);
		item.add(I_FM_InspectionItem.Entry_MaxBloodPressure);
	}

}

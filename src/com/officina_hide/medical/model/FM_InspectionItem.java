package com.officina_hide.medical.model;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;

/**
 * 検査項目情報クラス[Inspection item information class]<br>
 * @author officina-hide.net
 * @version 新規作成[New create]
 * @since 2022/05/09 Ver. 1.00
 */
public class FM_InspectionItem extends FD_DB implements I_FM_InspectionItem {
	
	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/05/09 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public FM_InspectionItem(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 検査項目情報テーブル生成[Inspection item information table generation]<br>
	 * @author officina-hide.net
	 * @since 2022/05/09 Ver. 1.00
	 */
	public void createTable() {
		FD_Table table = new FD_Table(env);
		table.add(Entry_FD_Table);
		FD_Column column = new FD_Column(env);
		column.add(Entry_FD_Column_FM_InspectionItem_ID);
		column.add(Entry_FD_Column_FM_InspectionItem_Code);
		column.add(Entry_FD_Column_FD_Name);
		
		deleteTable(env, Table_Name);
		createTable(env, Table_Name, Table_Disp_Name);
		//採番情報登録
		FD_Numbering num = new FD_Numbering(env);
		num.add(Entry_FD_Number);
	}

	/**
	 * 情報登録[Data entry]<br>
	 * @author officina-hide.net
	 * @since 2022/05/23 Ver. 1.00
	 * @param entryData 登録情報
	 */
	public void add(String entryData) {
		FD_Collections entry = new FD_Collections(env, entryData);
		X_FM_InspectionItem item = new X_FM_InspectionItem(env, entry);
		//新規追加のみ
		item.save(env);
 	}

}

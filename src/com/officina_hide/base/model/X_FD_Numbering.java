package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_Collect;
import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_ColumnData;
import com.officina_hide.base.common.FD_ColumnDataCollection;

/**
 * 採番情報I/Oクラス[Numbering information I/O class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成
 * @since 2022/03/24 Ver. 1.50
 */
public class X_FD_Numbering extends FD_DB implements I_FD_Numbering {

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/03/24 Ver. 1.50
	 */
	public X_FD_Numbering() {
		createColumnList();
	}

	/**
	 * コンストラクタ[Constructor]
	 * @author officina-hide.net
	 * @since 2022/03/26 Ver. 1.50
	 * @param entry 登録情報
	 */
	public X_FD_Numbering(FD_Collections entry) {
		createColumnList();
		for(FD_ColumnData cd : columnCollection.getList()) {
			
		}
	}

	/**
	 * 採番情報用テーブル項目リスト生成[Table item list generation for numbering information]<br>
	 * @author officina-hide.net
	 * @since 2022/03/24 Ver. 1.50
	 */
	private void createColumnList() {
		columnCollection = new FD_ColumnDataCollection();
		columnCollection.add(COLUMNNAME_FD_Numbering_ID, FD_Item_ID);
		columnCollection.add(COLUMNNAME_FD_Table_ID, FD_Item_ID);
		columnCollection.add(COLUMNNAME_FD_Column_ID, FD_Item_ID);
		columnCollection.add(COLUMNNAME_FD_NumberFormat, FD_Item_String);
		columnCollection.add(COLUMNNAME_FD_InitialNumber, FD_Item_Long);
		columnCollection.add(COLUMNNAME_FD_CurrentNumber, FD_Item_Long);
	}

}

package com.officina_hide.base.model;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;

/**
 * テーブル情報I/Oクラス[Table information I/O class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成[New create]
 * @since 2022/04/21 Ver. 1.50
 */
public class X_FD_Table extends FD_DB implements I_FD_Table {

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/04/21 Ver. 1.50
	 * @param entryData 登録情報[Entry data]
	 */
	public X_FD_Table(FD_Collections entry) {
		createColumnList();
	}

	/**
	 * テーブル情報用テーブル項目リスト生成[Table item list generation for numbering information]<br>
	 * @author officina-hide.net
	 * @since 2022/04/21 Ver. 1.50
	 * TODO 汎用化予定
	 */
	private void createColumnList() {
		columnCollection.clear();
		columnCollection.add(COLUMNNAME_FD_Table_ID, FD_Item_ID, (long) 0);
		columnCollection.add(COLUMNNAME_FD_Table_Code, FD_Item_String);
		columnCollection.add(COLUMNNAME_FD_Name, FD_Item_String);
		columnCollection.add(COLUMNNAME_FD_Description, FD_Item_Text);
	}

	/**
	 * 情報登録[Save data]<br>
	 * @author officina-hide.net
	 * @since 2022/04/21 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		
	}

}

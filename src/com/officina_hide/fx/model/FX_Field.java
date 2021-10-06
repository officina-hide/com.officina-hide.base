package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_DataDictionary;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;

/**
 * 画面項目情報クラス[Screen item information class]<br>
 * @author officine-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/06 Ver. 1.00
 */
public class FX_Field extends FD_DB implements I_FX_Field {

	/**
	 * 画面項目テーブル構築[Screen item table construction]<br>
	 * @author officine-hide.net
	 * @since 2021/10/06 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
		//採番情報登録
		FD_Numbering num = new FD_Numbering();
		num.add(env, Table_ID, Table_ID, 101, 0);
		//辞書情報
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FX_Field_ID, NAME_FX_Field_ID, COMMENT_FX_Field_ID);
		dd.add(env, 0, COLUMNNAME_FX_Field_Name, NAME_FX_Field_Name, COMMENT_FX_Field_Name);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, Table_ID, COLUMNNAME_FX_Field_ID, FD_Item_ID, 0, false, true, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FX_Field_Name, FD_Item_String, 100, true, false, null);
		addCommonColumn(env, Table_ID);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

}

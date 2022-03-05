package com.officina_hide.project.model;

import com.officina_hide.base.common.FD_ColumnCollection;
import com.officina_hide.base.common.FD_CreateXClass;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_DataDictionary;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;

/**
 * プロジェクト情報クラス[Project information class]
 * @author officina-hide.net
 * @version 新規作成
 * @since 2022/02/24 Ver. 1.00
 */
public class FD_Project extends FD_DB implements I_FD_Project {

	/**
	 * プロジェクト情報テーブル構築[Project information table construction]
	 * @author officina-hide.net
	 * @since 2022/02/24 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		long tableId = table.add(env, 0, Table_Name, Table_Disp_Name, Table_Comment);
		//採番情報登録
		FD_Numbering num = new FD_Numbering();
		num.add(env, 0, tableId, 100001, 0);
		//辞書情報登録
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FD_Project_ID, NAME_FD_Project_ID, COMMENT_FD_Project_ID);
		dd.add(env, 0, COLUMNNAME_FD_Project_Name, NAME_FD_Project_Name, COMMENT_FD_Project_Name);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, new FD_ColumnCollection(Table_Name, DATA_FD_Project_ID));
		column.add(env, 0, tableId, COLUMNNAME_FD_Project_ID, FD_ITEM_ID, 0, false, true, "0");
		column.add(env, 0, tableId, COLUMNNAME_FD_Project_Name, FD_ITEM_Text, 100, true, false, null);
		addCommonColumn(env, tableId);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
		//I/Oクラス生成
		FD_CreateXClass cxc = new FD_CreateXClass();
		cxc.createIO(env, Table_Name, classPath, packageUri);

	}

}

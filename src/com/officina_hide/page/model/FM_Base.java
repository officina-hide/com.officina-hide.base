package com.officina_hide.page.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_DataDictionary;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;

/**
 * ホームページ基盤情報クラス[Homepage basic information class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/02 Ver 1.00
 */
public class FM_Base extends FD_DB implements I_FM_Base {

	/**
	 * ホームページ基盤情報テーブル生成[Homepage base information table generation]<br>
	 * @author officina-hide.net
	 * @since 2021/10/02 新規作成
	 * @param env 環境情報[Enfironment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		long tableId = table.add(env, 0, Table_Name, Table_Disp_Name, Table_Comment);
		//採番情報
		FD_Numbering num = new FD_Numbering();
		num.add(env, 0, tableId, 100001, 0);
		//辞書情報
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FM_Base_ID, NAME_FM_Base_ID, COMMENT_FM_Base_ID);
		dd.add(env, 0, COLUMNNAME_FM_Title, NAME_FM_Title, COMMENT_FM_Title);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, tableId, COLUMNNAME_FM_Base_ID, FD_ITEM_ID, 0, false, true, null);
		column.add(env, 0, tableId, COLUMNNAME_FM_Title, FD_ITEM_String, 200, true, false, null);
		addCommonColumn(env, tableId);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

	/**
	 * 情報登録[Save information]<br>
	 * @author officina-hide.net
	 * @since 2021/10/02 1.00
	 * @param env 環境情報[Enfironment information]
	 * @param fmBaseId ホームページ基盤情報ID[Homepage base information ID]
	 * @param title タイトル[Title]
	 */
	public void add(FD_EnvData env, int fmBaseId, String title) {
		X_FM_Base base = new X_FM_Base(env, 0);
		base.setFM_Base_ID(0);
		base.setFM_Title("秀さんの情報工房");
		base.setFD_Group_ID(env.getActionUserID());
		base.save(env);
	}

}

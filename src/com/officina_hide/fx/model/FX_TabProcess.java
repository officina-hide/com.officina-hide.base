package com.officina_hide.fx.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_DataDictionary;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;

/**
 * タブ処理情報クラス[Tab process information class]
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/08 Ver. 1.00
 */
public class FX_TabProcess extends FD_DB implements I_FX_TabProcess {

	/**
	 * タブ処理情報テーブル構築[Tab processing information table construction]<br>
	 * @author officine-hide.net
	 * @since 2021/10/09 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
		//採番情報
		FD_Numbering num = new FD_Numbering();
		num.add(env, Table_ID, Table_ID, 101, 0);
		//辞書情報
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FX_TabProcess_ID, NAME_FX_TabProcess_ID, COMMENT_FX_TabProcess_ID);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, Table_ID, COLUMNNAME_FX_TabProcess_ID, FD_Item_ID, 0, false, true, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FX_Tab_ID, FD_Item_ID, 0, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Process_ID, FD_Item_ID, 0, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Name, FD_Item_String, 100, true, false, null);
		addCommonColumn(env, Table_ID);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

	/**
	 * 情報登録[Save data]<br>
	 * @author officine-hide.net
	 * @since 2021/10/11 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tabProcesId タブ処理情報ID[Tab process information ID]
	 * @param tabId タブ情報ID [Tab information ID]
	 * @param name 表示名[Display name]
	 * @param processId 処理情報ID[Process information ID]
	 */
	public void add(FD_EnvData env, int tabProcesId, long tabId, String name, long processId) {
		X_FX_TabProcess tp = new X_FX_TabProcess(env, 0);
		tp.setFX_TabProcess_ID(tabProcesId);
		tp.setFX_Tab_ID(tabId);
		tp.setFD_Name(name);
		tp.setFD_Process_ID(processId);
		tp.setFD_Group_ID(env.getActionUserID());
		tp.save(env);
	}

	/**
	 * タブ処理情報リスト生成[Tab processing information list generation]<br>
	 * @author officine-hide.net
	 * @since 2021/10/11 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param where 抽出条件[Extraction condition]
	 * @return タブ処理情報リスト生成[Tab processing information list]
	 */
	public List<X_FX_TabProcess> getList(FD_EnvData env, FD_WhereData where) {
		List<X_FX_TabProcess> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(I_FX_TabProcess.Table_Name).append(" ");
			sql.append(where.toString()).append(" ");
			connection(env);
			pstmt = getConn().prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new X_FX_TabProcess(env, rs.getLong(COLUMNNAME_FX_TabProcess_ID)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
		return list;
	}

}

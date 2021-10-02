package com.officina_hide.page.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;
import com.officina_hide.base.common.FD_Items;
import com.officina_hide.base.model.FD_DB;

/**
 * ホームページ基盤情報I/Oクラス[Homepage base information I/O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/02 Ver. 1.00
 */
public class X_FM_Base extends FD_DB implements I_FM_Base {

	/** 項目 : ホームページ基盤情報ID */
	private long FM_Base_ID;
	/** 項目 : メインタイトル */
	private String FM_Title;
	
	/**
	 * コンストラクタ[Constructor]
	 * @param env 環境情報[Enfironment information]
	 * @param baseId ホームページ基盤情報ID[Homepage base information ID]
	 */
	public X_FM_Base(FD_EnvData env, int baseId) {
		createItemList(env);
		if(baseId > 0) {
			load(env, Table_Name, baseId, items);
		}
	}

	/**
	 * 項目一覧セット[Item list setting]
	 * @param env 環境情報[Enfironment information]
	 */
	private void createItemList(FD_EnvData env) {
		items = new FD_Items();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(ITEM_LIST_SQL);
			pstmt.setString(1, Table_Name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FD_Item item = new FD_Item();
				item.setName(rs.getString(ITEM_LIST_SQL_Name));
				item.setData(null);
				item.setType(rs.getString(ITEM_LIST_SQL_TypeName));
				items.getItems().add(item);
				if(items.getTableId() == 0) {
					items.setTableId(rs.getLong(ITEM_LIST_SQL_TableID));
					items.setTableName(Table_Name);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
	}

	/**
	 * 情報保存[Save information]
	 * @author officina-hide.net
	 * @since 2021/10/02 Ver. 1.00
	 * @param env 環境情報[Enfironment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name, items);
	}

	public long getFM_Base_ID() {
		FM_Base_ID = items.getlongData(COLUMNNAME_FM_Base_ID);
		return FM_Base_ID;
	}
	public void setFM_Base_ID(long baseId) {
		items.setValue(COLUMNNAME_FM_Base_ID, baseId);
	}
	public String getFM_Title() {
		FM_Title = items.getStringData(COLUMNNAME_FM_Title);
		return FM_Title;
	}
	public void setFM_Title(String title) {
		items.setValue(COLUMNNAME_FM_Title, title);
	}

}

package com.officina_hide.fx.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_ItemCollection;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_DB;

/**
 * Fx画面基本情報I/Oクラス[Fx screen basic information I / O class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/08/23
 */
public class X_Fx_View extends FD_DB implements I_Fx_View {

	/** Fx画面基本情報ID */
	private int Fx_View_ID;
	/** Fx画面識別名 */
	private String Fx_View_Name;
	/** テーブル項目コレクション */
	private FD_ItemCollection itemList;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/23
	 * @param env 環境情報[Environment Information]
	 * @param where 条件情報[Where clouse Information]
	 */
	public X_Fx_View(FD_EnvData env, FD_WhereData where) {
		/** 項目リスト作成 */
		itemList = createItemList();
		load(env, Table_Name, where);
	}

	/**
	 * テーブル項目リスト生成[Table item list generation]<br>
	 * TODO 汎用化予定　2021/08/26
	 * @author officine-hide.net
	 * @since 1.00 2021/08/26
	 * @return テーブル項目リスト[Table Item List]
	 */
	private FD_ItemCollection createItemList() {
		itemList = new FD_ItemCollection();
		itemList.add(I_Fx_View.COLUMNNAME_Fx_View_ID, 0, I_FD_DB.Item_Value_Type_ID);
		itemList.add(I_Fx_View.COLUMNNAME_Fx_View_Name, null, I_FD_DB.Item_Value_Type_String);
		return itemList;
	}

	/**
	 * 情報抽出[Information extraction]<br>
	 * TODO FD_DBに汎用化する。(2021/08/23)
	 * @author officine-hide.net
	 * @since 1.00 2021/08/23
	 * @param env 環境情報[Environment Information]
	 * @param tableName テーブル名[Table Name]
	 * @param where 条件情報[Where Clouse]
	 */
	private void load(FD_EnvData env, String tableName, FD_WhereData where) {
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			//SQL文作成
			sql.append("SELECT * FROM ").append(tableName).append(" ");
			sql.append(where.toString()).append(" ");
			//データ抽出
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				itemList.setItem(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(stmt, rs);
		}
	}

	public int getFx_View_ID() {
		return Fx_View_ID;
	}

	public void setFx_View_ID(int fx_View_ID) {
		Fx_View_ID = fx_View_ID;
	}

	public String getFx_View_Name() {
		return Fx_View_Name;
	}

	public void setFx_View_Name(String fx_View_Name) {
		Fx_View_Name = fx_View_Name;
	}

}

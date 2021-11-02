package com.officina_hide.fx.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_DataDictionary;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;
import com.officina_hide.base.model.FD_TypeItem;

/**
 * メニュー情報クラス[Menu information class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/16 Ver. 1.00
 */
public class FX_Menu extends FD_DB implements I_FX_Menu {

	/**
	 * メニュー情報テーブル生成[Menu information table generation]<br>
	 * @author officina-hide.net
	 * @since 2021/10/16 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table();
		table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
		//辞書情報登録
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FX_Menu_ID, NAME_FX_Menu_ID, COMMENT_FX_Menu_ID);
		dd.add(env, 0, COLUMNNAME_FX_Menu_Name, NAME_FX_Menu_Name, COMMENT_FX_Menu_Name);
		dd.add(env, 0, COLUMNNAME_FX_Target_ID, NAME_FX_Target_ID, COMMENT_FX_Target_ID);
		//採番情報登録
		FD_Numbering num = new FD_Numbering();
		num.add(env, Table_ID, Table_ID, 101, 0);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, Table_ID, COLUMNNAME_FX_Menu_ID, FD_ITEM_ID, 0, false, true, "0");
		column.add(env, 0, Table_ID, COLUMNNAME_FX_Menu_Name, FD_ITEM_String, 100, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FX_Target_ID, FD_ITEM_ID, 0, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_TypeItem_ID, FD_ITEM_ID, 0, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Name, FD_ITEM_String, 100, true, false, null);
		addCommonColumn(env, Table_ID);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

	/**
	 * 情報登録[Save data]<br>
	 * @author officina-hide.net
	 * @since 2021/10/19 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param menuId メニュー情報ID[Menu information ID]
	 * @param menuName メニュー名[Menu name]
	 * @param targetId 遷移先名[Target information ID] 
	 * @param targetType 遷移先種別[Target type name]
	 * @param name メニュー表示名[Menu display name]
	 */
	public void add(FD_EnvData env, int menuId, String menuName, long targetId, String targetType, String name) {
		X_FX_Menu menu = new X_FX_Menu(env, 0);
		menu.setFX_Menu_ID(menuId);
		menu.setFX_Menu_Name(menuName);
		menu.setFX_Target_ID(targetId);
		menu.setFD_Name(name);
		FD_TypeItem typeItem = new FD_TypeItem();
		long typeItemId = typeItem.getTypeItemID(env, FD_MENU_Type, targetType);
		menu.setFD_TypeItem_ID(typeItemId);
		menu.setFD_Group_ID(env.getActionUserID());
		menu.save(env);
	}

	/**
	 * メニュー情報一覧生成[Menu information list generation]<br>
	 * @author officina-hide.net
	 * @since 2021/11/02 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @return メニュー情報一覧[Menu information list]
	 */
	public List<X_FX_Menu> getMenuList(FD_EnvData env) {
		List<X_FX_Menu> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(Table_Name);
			connection(env);
			pstmt = getConn().prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new X_FX_Menu(env, rs.getLong(COLUMNNAME_FX_Menu_ID)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
		
		return list;
	}

}

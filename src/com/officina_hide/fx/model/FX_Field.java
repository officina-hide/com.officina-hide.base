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
import com.officina_hide.base.model.FD_TypeItem;
import com.officina_hide.base.model.X_FD_Column;

/**
 * 画面項目情報クラス[Screen item information class]<br>
 * @author officina-hide.net
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
		column.add(env, 0, Table_ID, COLUMNNAME_FX_Field_ID, FD_ITEM_ID, 0, false, true, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FX_Field_Name, FD_ITEM_String, 100, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Name, FD_ITEM_String, 200, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FX_Tab_ID, FD_ITEM_ID, 0, true, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_TypeItem_ID, FD_ITEM_ID, 0, true, false, null);
		addCommonColumn(env, Table_ID);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

	/**
	 * 情報登録[Save data]
	 * @author officina-hide.net
	 * @since 2021/10/06 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param fieldId 画面項目情報ID[Screen item information ID]
	 * @param fieldName 画面項目名[Screen item name]
	 * @param name 表示名[Display name]
	 * @param tabId タブ情報ID[Tab information ID]
	 * @param fieldTypeName 属性項目名[Attribute item name]
	 */
	public void add(FD_EnvData env, int fieldId, String tableName, String ColumnName, long tabId, String fieldTypeName) {
		/*
		 * FX_Field_Nameはテーブル項目情報のテーブル項目名を設定<br>
		 * FD_Nameはテーブル項目情報のテーブル表示名を設定
		 */
		X_FX_Field field = new X_FX_Field(env, 0);
		field.setFX_Field_ID(fieldId);
		X_FD_Column co = new X_FD_Column(env, tableName, ColumnName);
		field.setFx_Field_Name(ColumnName);
		field.setFD_Name(co.getFD_DataDictionary().getFD_Name());
		field.setFX_Tab_ID(tabId);
		field.setFD_Group_ID(env.getActionUserID());
		FD_TypeItem typeItem = new FD_TypeItem();		
		field.setFD_TypeItem_ID(typeItem.getTypeItemID(env, FD_Field_Type, fieldTypeName));
		field.save(env);
	}

	/**
	 * 画面項目一覧生成[Screen item list generation]
	 * @author officina-hide.net
	 * @since 2021/10/07 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param where 抽出条件[Extraction condition]
	 * @return 画面項目一覧[Screen item list]
	 */
	public List<X_FX_Field> getList(FD_EnvData env, FD_WhereData where) {
		List<X_FX_Field> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(I_FX_Field.Table_Name).append(" ");
			sql.append(where.toString()).append(" ");
			connection(env);
			pstmt = getConn().prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new X_FX_Field(env, rs.getLong(COLUMNNAME_FX_Field_ID)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
		return list;
	}

}

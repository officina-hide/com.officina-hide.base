package com.officina_hide.base.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.model.I_FD_UniqueColumn;
import com.officina_hide.base.model.I_FD_UniqueIndex;
import com.officina_hide.base.model.X_FD_UniqueIndex;

/**
 * ユニーク制約インデックス情報クラス<br>
 * @author officine-hide.com
 * @version 1.20
 * @since 2020/11/13
 */
public class FDUniqueIndex extends FD_DB implements I_FD_UniqueIndex {

	/**
	 * ユニーク制約インデックス情報テーブル構築<br>
	 * @author officine-hide.com
	 * @since 1.20 2020/11/13
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築開始");

		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, TABLE_ID, Table_Name, NAME, COMMENT);
		
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_UniqueIndex_ID, NAME_FD_UniqueIndex_ID, COMMENT_FD_UniqueIndex_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 10, "Y", "Y");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Table_ID, NAME_FD_Table_ID, COMMENT_FD_Table_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 20, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_Index_Name, NAME_Index_Name, COMMENT_Index_Name
				, COLUMNTYPE_ID_FD_Text, null, 100, 30, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Name, NAME_FD_Name, COMMENT_FD_Name
				, COLUMNTYPE_ID_FD_Text, null, 100, 40, "N", "N");
		
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Process_ID, NAME_FD_Process_ID, COMMENT_FD_Process_ID
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 900, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_CREATE, NAME_FD_CREATE, COMMENT_FD_CREATE
				, COLUMNTYPE_ID_FD_Date, null, 0, 910, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_CREATED, NAME_FD_CREATED, COMMENT_FD_CREATED
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 920, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_UPDATE, NAME_FD_UPDATE, COMMENT_FD_UPDATE
				, COLUMNTYPE_ID_FD_Date, null, 0, 930, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_UPDATED, NAME_FD_UPDATED, COMMENT_FD_UPDATED
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 940, "N", "N");
		
		//テーブル生成
		createDBTable(env, TABLE_ID);
		
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.addData(env, TABLE_ID, TABLE_ID, 0, 1000001, 0, null);
		
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築完了");
	}

	/**
	 * 情報登録<br>
	 * @author officina-hide.com
	 * @since 1.20 2020/11/15
	 * @param env 環境情報
	 * @param uniqueInexId ユニーク制約インデックス情報ID
	 * @param tableId テーブル情報ID
	 * @param indexName ユニーク制約インデックス名
	 * @param name ユニーク制約インデックス表示名
	 * @return ユニーク制約インデックス情報ID
	 */
	public int addData(FD_EnvData env, int uniqueInexId, int tableId, String indexName, String name) {
		X_FD_UniqueIndex uidx = new X_FD_UniqueIndex(env);
		uidx.setValueByName(env, COLUMNNAME_FD_UniqueIndex_ID, uniqueInexId);
		uidx.setValueByName(env, COLUMNNAME_FD_Table_ID, tableId);
		uidx.setValueByName(env, COLUMNNAME_Index_Name, indexName);
		uidx.setValueByName(env, COLUMNNAME_FD_Name, name);
		uidx.save(env);
		
		return uidx.getintOfValue(COLUMNNAME_FD_UniqueIndex_ID);
	}

	/**
	 * ユニーク制約構築<br>
	 * @param env 環境情報
	 * @param uiId ユニーク制約インデックス情報ID
	 */
	public void createUniqueKey(FD_EnvData env, int uiId) {
		StringBuffer sql = new StringBuffer();
		StringBuffer alter = new StringBuffer();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			sql.append("SELECT").append(" ");
			sql.append("t.").append(I_FD_Table.COLUMNNAME_Table_Name).append(",");
			sql.append("cm.").append(I_FD_TableColumn.COLUMNNAME_TableColumn_Name).append(",");
			sql.append("ui.").append(I_FD_UniqueIndex.COLUMNNAME_Index_Name).append(",");
			sql.append("ui.").append(I_FD_UniqueIndex.COLUMNNAME_FD_Name).append(" ");
			sql.append("FROM").append(" ").append(I_FD_UniqueIndex.Table_Name).append(" ui ");
			sql.append("LEFT JOIN").append(" ").append(I_FD_Table.Table_Name).append(" t ").append(" ON ")
				.append("t.").append(I_FD_Table.COLUMNNAME_FD_Table_ID).append(" = ")
				.append("ui.").append(COLUMNNAME_FD_Table_ID).append(" ");
			sql.append("LEFT JOIN").append(" ").append(I_FD_UniqueColumn.Table_Name).append(" uc ").append(" ON ")
				.append("uc.").append(I_FD_UniqueColumn.COLUMNNAME_FD_UniqueIndex_ID).append(" = ")
				.append("ui.").append(I_FD_UniqueIndex.COLUMNNAME_FD_UniqueIndex_ID).append(" ");
			sql.append("LEFT JOIN").append(" ").append(I_FD_TableColumn.Table_Name).append(" cm ").append(" ON ")
				.append("cm.").append(I_FD_TableColumn.COLUMNNAME_FD_TableColumn_ID).append(" = ")
				.append("uc.").append(I_FD_UniqueColumn.COLUMNNAME_FD_TableColumn_ID).append(" ");
			sql.append("WHERE").append(" ").append("ui.").append(COLUMNNAME_FD_UniqueIndex_ID).append(" = ").append(uiId).append(" ");
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			int cnt = 0;
			String comment = null;
			while(rs.next()) {
				if(cnt == 0) {
					alter.append("ALTER TABLE").append(" ").append(rs.getString("t." + I_FD_Table.COLUMNNAME_Table_Name)).append(" ");
					alter.append("ADD UNIQUE").append(" " ).append(rs.getString("ui."+I_FD_UniqueIndex.COLUMNNAME_Index_Name)).append(" ");
					alter.append("(");
					comment = rs.getString("ui." + I_FD_UniqueIndex.COLUMNNAME_FD_Name);
				} else {
					alter.append(",");
				}
				alter.append(rs.getString("cm."+I_FD_TableColumn.COLUMNNAME_TableColumn_Name));
				cnt++;
			}
			alter.append(")").append(" ");
			if(comment != null) {
				alter.append("COMMENT").append(" ").append(FD_SQ).append(comment).append(FD_SQ).append(" ");
			}
			
			DBexecute(env, alter.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}
	}

}

package com.officina_hide.base.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.officina_hide.base.model.FDLog;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_Process;
import com.officina_hide.base.model.I_FD_Reference;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.model.X_FD_Table;

/**
 * DB取扱汎用クラス[DB handling general class]<br>
 * @author officine-hide.com
 * @version 1.30
 * @since 2020/12/09 
 */
public class FD_DB_Utility extends FD_DB {

	/**
	 * 共通テーブル項目構築用SQL文取得[Get SQL statement for common table item construction]<br>
	 * @author officine-hide.com
	 * @since 1.30 2020/12/09
	 * @param name コメント用テーブル名
	 * @return SQL文
	 */
	public String getBaseSQLStrings(String name) {
		StringBuffer sql = new StringBuffer();
		
		if(name.equals(I_FD_Process.NAME) == false) {
			sql.append(COLUMNNAME_FD_Process_ID).append("  INT UNSIGNED ")
			.append("COMMENT ").append(FD_SQ).append(NAME_FD_Process_ID).append(FD_SQ).append(",");
		}
		sql.append(COLUMNNAME_FD_CREATE).append(" DATETIME COMMENT ")
			.append(FD_SQ).append(NAME_FD_CREATE).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_CREATED).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_FD_CREATED).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATE).append(" DATETIME COMMENT ")
			.append(FD_SQ).append(NAME_FD_UPDATE).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATED).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_FD_UPDATED).append(FD_SQ).append(" ");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=").append(FD_SQ).append(name).append(FD_SQ);
		
		return sql.toString();
	}

	/**
	 * 指定されたテーブルの内、指定されたプロセス情報IDを持つ情報を削除する。<br>
	 * Deletes the information with the specified process information ID in the specified table.<br>
	 * @author officina-hide.com
	 * @since 1.30 2020/12/31
	 * @param env 環境情報
	 * @param tableName テーブル名
	 * @param processId プロセス情報ID
	 */
	public void deleteDataByProcessId(FD_EnvData env, String tableName, int processId) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM ").append(tableName).append(" ");
		sql.append("WHERE ").append(I_FD_Process.COLUMNNAME_FD_Process_ID).append(" = ").append(processId);
		Statement stmt = null;
		connection(env);
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBclose(stmt, null);
		}
	}

	/**
	 * テーブル構築[Table construction]<br>
	 * @author officina-hide.com
	 * @since 1.30 2021/01/04
	 * @param env 環境情報
	 * @param tableId テーブル情報ID[Table information ID]
	 */
	public void createDBTable(FD_EnvData env, int tableId) {
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer dropSql = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		try {
			//テーブル情報取得
			X_FD_Table table = new X_FD_Table(env, tableId);
			//既にテーブルが存在するときは削除する。
			dropSql.append("DROP TABLE IF EXISTS ").append(table.getValueOfString(I_FD_Table.COLUMNNAME_Table_Name));
			DBUpdateExecution(env, dropSql.toString());
			//テーブル項目情報より関連する情報のリストを作成する。
			sql.append("SELECT * FROM ").append(I_FD_TableColumn.Table_Name).append(" ");
			sql.append("LEFT JOIN ").append(I_FD_Reference.Table_Name).append(" ON " )
				.append(I_FD_Reference.Table_Name).append(".").append(I_FD_Reference.COLUMNNAME_FD_Reference_ID).append(" = ")
				.append(I_FD_TableColumn.COLUMNNAME_TableColumn_Type_ID).append(" ");
			sql.append("WHERE ").append(I_FD_TableColumn.COLUMNNAME_FD_Table_ID).append(" = ").append(tableId).append(" ");
			sql.append("ORDER BY ").append(I_FD_TableColumn.COLUMNNAME_Column_Sort_Order).append(" ");
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			List<Map<String, String>> list = new ArrayList<>();
			while(rs.next()) {
				Map<String, String> map = new HashMap<>();
				map.put(I_FD_TableColumn.COLUMNNAME_TableColumn_Name
						, rs.getString(I_FD_TableColumn.COLUMNNAME_TableColumn_Name));
				map.put("Column_Type_Name", rs.getString(I_FD_Reference.COLUMNNAME_Reference_Name));
				map.put(I_FD_TableColumn.COLUMNNAME_TableColumn_Size
						, rs.getString(I_FD_TableColumn.COLUMNNAME_TableColumn_Size));
				map.put(I_FD_TableColumn.COLUMNNAME_FD_Name
						, rs.getString(I_FD_TableColumn.COLUMNNAME_FD_Name));
				map.put(I_FD_TableColumn.COLUMNNAME_Is_Null, rs.getString(I_FD_TableColumn.COLUMNNAME_Is_Null));
				map.put(I_FD_TableColumn.COLUMNNAME_Is_Primary, rs.getString(I_FD_TableColumn.COLUMNNAME_Is_Primary));
				list.add(map);
			}
			//テーブル構築
			sql = new StringBuffer();
			sql.append("CREATE TABLE IF NOT EXISTS ").append(table.getValueOfString(I_FD_Table.COLUMNNAME_Table_Name)).append(" ");
			sql.append(" (");
			
			StringBuffer items = new StringBuffer();
			for(Map<String, String> map : list) {
				if(items.toString().length() > 0) {
					items.append(",");
				}
				items.append(map.get(I_FD_TableColumn.COLUMNNAME_TableColumn_Name).toString()).append(" ");
				switch(map.get("Column_Type_Name").toString()) {
				case COLUMNTYPE_FD_Text:
					items.append("Varchar(").append(map.get(I_FD_TableColumn.COLUMNNAME_TableColumn_Size)).append(")").append(" ");
					break;
				case COLUMNTYPE_FD_Field_Text:
					items.append("TEXT").append(" ");
					break;
				case COLUMNTYPE_FD_Information_ID:
				case COLUMNTYPE_FD_Number:
				case COLUMNTYPE_FD_List:
					items.append("INT UNSIGNED").append(" ");
					break;
				case COLUMNTYPE_FD_Date:
					items.append("DATETIME").append(" ");
					break;
				case COLUMNTYPE_FD_YesNo:
					items.append("ENUM(")
						.append(FD_SQ).append("Y").append(FD_SQ).append(", ")
						.append(FD_SQ).append("N").append(FD_SQ).append(")").append(" ");
					break;
				}
				if(map.get(I_FD_TableColumn.COLUMNNAME_Is_Null).toString().equals(I_FD_TableColumn.IS_NULL_Yes)){
					items.append("NOT NULL").append(" ");
				}
				if(map.get(I_FD_TableColumn.COLUMNNAME_Is_Primary).toString().equals(I_FD_TableColumn.IS_PRIMARY_Yes)){
					items.append("PRIMARY KEY").append(" ");
				}
				if(map.get(I_FD_TableColumn.COLUMNNAME_FD_Name) != null &&
						map.get(I_FD_TableColumn.COLUMNNAME_FD_Name).toString().length() > 0) {
					items.append("COMMENT").append(" ")
						.append(FD_SQ).append(map.get(I_FD_TableColumn.COLUMNNAME_FD_Name).toString()).append(FD_SQ).append(" ");
				}
			}
			
			sql.append(items.toString()).append(" ) ");
			if(table.getValueOfString(I_FD_TableColumn.COLUMNNAME_FD_Name).length() > 0) {
				sql.append(" COMMENT ")
					.append(FD_SQ).append(table.getValueOfString(I_FD_Table.COLUMNNAME_FD_Name)).append(FD_SQ).append(" ");
			}

			DBUpdateExecution(env, sql.toString());
			
			FDLog log = new FDLog();
			log.addLog(env, I_FD_Log.LOGTYPE_Table_Drop_ID, changeEscape(dropSql.toString()));
			log.addLog(env, I_FD_Log.LOGTYPE_Table_Create_ID, changeEscape(sql.toString()));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBclose(stmt, rs);
		}
		
	}
	
	/**
	 * 項目リスト初期化<br>
	 * @author officina-hide.com
	 * @since 1.20 2020/11/08
	 * @param env 環境情報
	 * @param tableId テーブル情報ID
	 * @param itemList 
	 */
	public void initializeItemList(FD_EnvData env, int tableId, FD_Items itemList) {
		itemList.clear();
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(I_FD_TableColumn.Table_Name).append(" ");
			sql.append("LEFT JOIN ").append(I_FD_Reference.Table_Name).append(" ON ")
				.append(I_FD_Reference.Table_Name).append(".").append(I_FD_Reference.COLUMNNAME_FD_Reference_ID).append(" = ")
				.append(I_FD_TableColumn.COLUMNNAME_TableColumn_Type_ID).append(" ");
			sql.append("WHERE ").append(I_FD_TableColumn.COLUMNNAME_FD_Table_ID).append(" = ").append(tableId).append(" ");
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				itemList.add(rs.getString(I_FD_TableColumn.COLUMNNAME_TableColumn_Name)
						, null, rs.getString(I_FD_Reference.Table_Name+"."+I_FD_Reference.COLUMNNAME_Reference_Name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBclose(stmt, rs);
		}
	}
	
}

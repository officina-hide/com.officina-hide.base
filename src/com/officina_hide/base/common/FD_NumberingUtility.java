package com.officina_hide.base.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_Table;

/**
 * 採番クラス[Numbering class]<br>
 * @author officine-hide.com
 * @version 1.30
 * @since 2020/12/16
 */
public class FD_NumberingUtility extends FD_DB  {

	/**
	 * 新規情報ID採番[A new information ID is assigned.]<br>
	 * @author officine-hide.com
	 * @since 2020/12/16
	 * @param env 環境情報
	 * @param tableName テーブル名
	 * @return 新規情報ID
	 */
	public int getNewID(FD_EnvData env, String tableName) {
		int id = 0;
		/*
		 * 対象のテーブル名の情報IDを持つ採番情報を抽出する。<br>
		 * この時、採番keyはnull
		 */
		Statement stmt = null;
		ResultSet rs = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT ").append(I_FD_Numbering.COLUMNNAME_Current_Number).append(",");
			sql.append(I_FD_Numbering.COLUMNNAME_Initial_Number).append(",");
			sql.append(I_FD_Table.Table_Name).append(".").append(I_FD_Table.COLUMNNAME_FD_Table_ID).append(" ");
			sql.append("FROM ").append(I_FD_Numbering.Table_Name).append(" ");
			sql.append("LEFT JOIN ").append(I_FD_Table.Table_Name).append(" ON ")
				.append(I_FD_Table.Table_Name).append(".").append(I_FD_Table.COLUMNNAME_Table_Name).append(" = ")
				.append(FD_SQ).append(tableName).append(FD_SQ).append(" ");
			sql.append("WHERE ").append(I_FD_Numbering.Table_Name).append(".").append(I_FD_Numbering.COLUMNNAME_FD_Table_ID)
				.append(" = ")
				.append(I_FD_Table.Table_Name).append(".").append(I_FD_Table.COLUMNNAME_FD_Table_ID).append(" ");
			sql.append("AND ").append(I_FD_Numbering.COLUMNNAME_Numbering_Key).append(" IS NULL ");
			sql.append("FOR UPDATE").append(" ");
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				if(rs.getInt(I_FD_Numbering.COLUMNNAME_Current_Number) == 0) {
					id = rs.getInt(I_FD_Numbering.COLUMNNAME_Initial_Number);
				} else {
					id = rs.getInt(I_FD_Numbering.COLUMNNAME_Current_Number) + 1;
				}
				//採番情報更新
				sql = new StringBuffer();
				int tableId = rs.getInt(I_FD_Table.COLUMNNAME_FD_Table_ID);
				sql.append("UPDATE ").append(I_FD_Numbering.Table_Name).append(" SET ");
				sql.append(I_FD_Numbering.COLUMNNAME_Current_Number).append(" = ").append(id).append(",");
				sql.append(COLUMNNAME_FD_UPDATE).append(" = ")
					.append(FD_SQ).append(df.format(new Date().getTime())).append(FD_SQ).append(",");
				sql.append(COLUMNNAME_FD_UPDATED).append(" = ").append(env.getLoginUserID()).append(" ");
				sql.append("WHERE ").append(I_FD_Numbering.COLUMNNAME_FD_Table_ID).append(" = ").append(tableId).append(" ");
				DBUpdateExecution(env, sql.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBclose(stmt, rs);
		}
		
		return id;
	}

}

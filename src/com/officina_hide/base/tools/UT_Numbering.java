package com.officina_hide.base.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_DB;
import com.officina_hide.base.model.I_FD_Numbering;

/**
 * 採番クラス<br>
  * @author officine-hide.com
 * @version 1.21
 * @since 2020/11/28
 */
public class UT_Numbering extends FD_DB {

	/**
	 * 採番処理<br>
	 * <p>Check if the numbering information[FD_Numbering] with the table information ID and Key exists.<br>
	 * If it exists, the numbering information is extracted by exclusive control.<br>
	 * If it does not exist, create new numbering information, set the current value, and register it.<br>
	 * The current value and the start value are 1.</p>
	 * @author officine-hide.com
	 * @since 1.21 2020/11/28
	 * @param env 環境情報
	 * @param tableName テーブル名
	 * @param key 採番Key
	 * @return 採番数値 
	 */
	public int getNumberID(FD_EnvData env, String tableName, String key) {
		int num = 0;
		/*
		 * テーブル情報IDとKeyを持った採番情報が存在するか確認する。
		 * もし、存在する時は、採番情報を更新排他制御で抽出する。
		 * もし、存在しない時は、新規に採番情報を作成し、現在値をセットして登録する。
		 * ※この時の現在値と開始値は1とする。
		*/
		int tableId = getTableId(env, tableName);
		if(isExist(env, tableId, key)) {
			num = numberUpdate(env, tableId, key);
		}
		return num;
	}

	/**
	 * 採番情報存在チェック<br>
	 * @author officine-hide.com
	 * @since 1.21 2020/11/28
	 * @param env 環境情報
	 * @param tableId テーブル情報ID
	 * @param key 採番Key
	 * @return 存否確認 true - 情報有り、false - 情報未登録
	 */
	private boolean isExist(FD_EnvData env, int tableId, String key) {
		boolean chk = false;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT ").append(I_FD_Numbering.COLUMNNAME_FD_Numbering_ID).append(" ")
				.append("FROM ").append(I_FD_Numbering.Table_Name).append(" ");
			sql.append("WHERE ").append(I_FD_Numbering.COLUMNNAME_FD_Table_ID).append(" = ").append(tableId).append(" ");
			if(key == null) {
				sql.append("AND ").append(I_FD_Numbering.COLUMNNAME_Numbering_Key).append(" IS NULL").append(" ");
			} else {
				sql.append("AND ").append(I_FD_Numbering.COLUMNNAME_Numbering_Key).append(" = ")
					.append(FD_SQ).append(key).append(FD_SQ).append(" ");
			}
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}
		return chk;
	}

	/**
	 * 採番情報更新<br>
	 * @author officine-hide.com
	 * @since 1.21 2020/11/28
	 * @param env 環境情報
	 * @param tableId テーブル情報ID
	 * @param key 採番Key
	 * @return 採番数値
	 */
	private int numberUpdate(FD_EnvData env, int tableId, String key) {
		int num = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT ").append(I_FD_Numbering.COLUMNNAME_Current_Number).append(",")
				.append(I_FD_Numbering.COLUMNNAME_Initial_Number).append(" ")
				.append("FROM ").append(I_FD_Numbering.Table_Name).append(" ");
			sql.append("WHERE ").append(I_FD_Numbering.COLUMNNAME_FD_Table_ID).append(" = ").append(tableId).append(" ");
			if(key == null) {
				sql.append("AND ").append(I_FD_Numbering.COLUMNNAME_Numbering_Key).append(" IS NULL ");
			} else {
				sql.append("AND ").append(I_FD_Numbering.COLUMNNAME_Numbering_Key).append(" = ")
					.append(FD_SQ).append(key).append(FD_SQ).append(" ");
			}
			sql.append("FOR UPDATE ");
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				//採番数値取得
				if(rs.getInt(I_FD_Numbering.COLUMNNAME_Current_Number) == 0) {
					num = rs.getInt(I_FD_Numbering.COLUMNNAME_Initial_Number);
				} else {
					num = rs.getInt(I_FD_Numbering.COLUMNNAME_Current_Number) + 1;
				}
				//採番情報更新
				sql = new StringBuffer();
				sql.append("UPDATE ").append(I_FD_Numbering.Table_Name).append(" SET ");
				sql.append(I_FD_Numbering.COLUMNNAME_Current_Number).append(" = ").append(num).append(",");
				sql.append(I_DB.COLUMNNAME_FD_UPDATE).append(" = ")
					.append(FD_SQ).append(df.format(new Date().getTime())).append(FD_SQ).append(",");
				sql.append(I_DB.COLUMNNAME_FD_UPDATED).append(" = ").append(env.getLogin_User_ID()).append(" ");
				sql.append("WHERE ").append(I_FD_Numbering.COLUMNNAME_FD_Table_ID).append(" = ").append(tableId).append(" ");
				if(key == null) {
					sql.append("AND ").append(I_FD_Numbering.COLUMNNAME_Numbering_Key).append(" IS NULL ");
				} else {
					sql.append("AND ").append(I_FD_Numbering.COLUMNNAME_Numbering_Key).append(" = ")
						.append(FD_SQ).append(key).append(FD_SQ).append(" ");
				}
				DBexecute(env, sql.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}
		return num;
	}

}

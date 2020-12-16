package com.officina_hide.base.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;

/**
 * ログ情報クラス[Log information class]<br>
 * @author officine-hide.com
 * @version 1.30
 * @since 2020/12/09
 */
public class FDLog extends FD_DB implements I_FD_Log {

	/**
	 * ログ情報テーブル構築[Log information table construction]<br>
	 * @author officine-hide.com
	 * @since 1.30 2020/12/09
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		StringBuffer sqlDrop = new StringBuffer();
		//既に登録されているログ情報を削除する。
		sqlDrop.append("DROP TABLE IF EXISTS ").append(Table_Name);
		DBUpdateExecution(env, sqlDrop.toString());
		//ログ情報テーブル生成
		StringBuffer sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS ").append(Table_Name).append(" (");
		sql.append(COLUMNNAME_FD_Log_ID).append(" INT UNSIGNED NOT NULL PRIMARY KEY COMMENT ")
			.append(FD_SQ).append(NAME_FD_Log_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Log_Type_ID).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_Log_Type_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Log_Data).append(" TEXT COMMENT ")
			.append(FD_SQ).append(NAME_Log_Data).append(FD_SQ).append(",");
		
		FD_DB_Utility dutil = new FD_DB_Utility();
		sql.append(dutil.getBaseSQLStrings(NAME));		
		DBUpdateExecution(env, sql.toString());
		
		//ログ情報の構築をログ情報に登録する。
		addLog(env, LOGTYPE_Table_Drop_ID, changeEscape(sqlDrop.toString()));
		addLog(env, LOGTYPE_Table_Create_ID, changeEscape(sql.toString()));
		
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築完了");

	}

	/**
	 * ログ情報登録[registration of log information]<br>
	 * @author officine-hide.com
	 * @since 1.30 2020/12/09
	 * @param env 環境情報
	 * @param logTypeId ログ種別id
	 * @param logData ログ内容
	 */
	public void addLog(FD_EnvData env, int logTypeId, String logData) {
		SimpleDateFormat msgDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		/*
		 * ログ種別がインフォメーション[IDが200～209の時]はメッセージを出力する。
		 */
		if(logTypeId >= 201 && logTypeId <= 209) {
			System.out.println(msgDateFormat.format(new Date())+" : "+logData);
		}
		
		/*
		 * ログ情報の追加はDBExecuteメソッドを利用せず、循環処理を避けるために、直接ここでInsertを行う。
		 */
		Statement stmt = null;
		StringBuffer sql = new StringBuffer();
		int logId = getNewLogId(env);
		try {
			sql.append("INSERT INTO ").append(I_FD_Log.Table_Name).append(" SET ");
			sql.append(I_FD_Log.COLUMNNAME_FD_Log_ID).append(" = ").append(logId).append(",");
			sql.append(I_FD_Log.COLUMNNAME_Log_Type_ID).append(" = ").append(logTypeId).append(",");
			sql.append(I_FD_Log.COLUMNNAME_Log_Data).append(" = ").append(FD_SQ).append(logData).append(FD_SQ).append(",");
			
			sql.append(COLUMNNAME_FD_Process_ID).append(" = ").append(env.getActiveProcessID()).append(",");
			sql.append(COLUMNNAME_FD_CREATE).append(" = ")
				.append(FD_SQ).append(msgDateFormat.format(new Date().getTime())).append(FD_SQ).append(",");
			sql.append(COLUMNNAME_FD_CREATED).append(" = ").append(env.getLoginUserID()).append(",");
			sql.append(COLUMNNAME_FD_UPDATE).append(" = ")
				.append(FD_SQ).append(msgDateFormat.format(new Date().getTime())).append(FD_SQ).append(",");
			sql.append(COLUMNNAME_FD_UPDATED).append(" = ").append(env.getLoginUserID()).append(" ");
			
			connection(env);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBclose(stmt);
		}
	}

	/**
	 * 新規ログ情報ID取得<br>
	 * <p>ログ情報IDは他の情報IDと違って採番情報から取得せずに、初期値からの増分で独自に付与する。</p>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/20
	 * @param env 環境情報
	 * @return 新規ログ情報ID
	 */
	private int getNewLogId(FD_EnvData env) {
		int logId = I_FD_Log.INITIAL_LOG_ID;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT MAX(FD_Log_ID) FROM FD_Log ");
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				if(rs.getInt("Max(FD_Log_ID)") > 0) {
					logId = rs.getInt("Max(FD_Log_ID)") + 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBclose(stmt, rs);
		}
		
		return logId;
	}

	/**
	 * ログ情報のテーブル項目情報登録<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/27
	 * @param env
	 */
	public void addTableColumn(FD_EnvData env) {
		addLog(env, LOGTYPE_Info_ID, NAME+"のテーブル項目情報登録開始");
		
		FDTableColumn column = new FDTableColumn();
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Log_ID, NAME_FD_Log_ID, COMMENT_FD_Log_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 10, "Y", "Y");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_Log_Type_ID, NAME_Log_Type_ID, COMMENT_Log_Type_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 20, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_Log_Data, COLUMNNAME_Log_Data, COMMENT_Log_Data
				, COLUMNTYPE_ID_FD_Field_Text, null, 0, 30, "N", "N");

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

		addLog(env, LOGTYPE_Info_ID, NAME+"のテーブル項目情報登録終了");
	}

}

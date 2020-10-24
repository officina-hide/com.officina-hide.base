package com.officina_hide.base.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Log;

/**
 * ログ情報クラス<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/10/20
 */
public class FDLog extends FD_DB implements I_FD_Log {

	/**
	 * ログ情報構築
	 * @author officine-hide.com
	 * @since 1.00 2020/10/20
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		//テーブル構築
		createDBTable(env);
	}

	/**
	 * ログ情報テーブル構築<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/20
	 * @param env 環境情報
	 */
	private void createDBTable(FD_EnvData env) {
		StringBuffer sqlDrop = new StringBuffer();
		//既に登録されているログ情報を削除する。
		sqlDrop.append("DROP TABLE IF EXISTS ").append(Table_Name);
		DBexecute(env, sqlDrop.toString());
		//ログ情報テーブル構築
		StringBuffer sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS ").append(Table_Name).append(" (");
		sql.append(COLUMNNAME_FD_Log_ID).append(" INT UNSIGNED NOT NULL PRIMARY KEY COMMENT ")
			.append(FD_SQ).append(NAME_FD_Log_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Log_Type_ID).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_Log_Type_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Log_Data).append(" TEXT COMMENT ")
			.append(FD_SQ).append(NAME_Log_Data).append(FD_SQ).append(",");
		
		sql.append(COLUMNNAME_FD_CREATE).append(" DATETIME COMMENT ")
			.append(FD_SQ).append(NAME_FD_CREATE).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_CREATED).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_FD_CREATED).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATE).append(" DATETIME COMMENT ")
			.append(FD_SQ).append(NAME_FD_UPDATE).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATED).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_FD_UPDATED).append(FD_SQ).append(" ");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=").append(FD_SQ).append(NAME).append(FD_SQ);
		DBexecute(env, sql.toString());
		
		System.out.println(new Date() + " : " + NAME + "テーブル生成完了");

		//ログ情報の構築をログ情報に登録する。
		addLog(env, LOGTYPE_DB_Drop_ID, changeEscape(sqlDrop.toString()));
		addLog(env, LOGTYPE_DB_Create_ID, changeEscape(sql.toString()));
		
	}

	/**
	 * ログ情報追加<br>
	 * @param env
	 * @param logTypeId
	 * @param logData
	 */
	private void addLog(FD_EnvData env, int logTypeId, String logData) {
		/*
		 * ログ情報の追加はDBExecuteメソッドを利用せず、循環処理を避けるために、直接ここでInsertを行う。
		 */
		Statement stmt = null;
		StringBuffer sql = new StringBuffer();
		int logId = getNewLogId(env);
		try {
			sql.append("INSERT INTO ").append(Table_Name).append(" SET ");
			sql.append(COLUMNNAME_FD_Log_ID).append(" = ").append(logId).append(",");
			sql.append(COLUMNNAME_Log_Type_ID).append(" = ").append(logTypeId).append(",");
			sql.append(COLUMNNAME_Log_Data).append(" = ").append(FD_SQ).append(logData).append(FD_SQ).append(",");
			sql.append(COLUMNNAME_FD_CREATE).append(" = ")
				.append(FD_SQ).append(dateFormat.format(new Date().getTime())).append(FD_SQ).append(",");
			sql.append(COLUMNNAME_FD_CREATED).append(" = ").append(env.getLogin_User_ID()).append(",");
			sql.append(COLUMNNAME_FD_UPDATE).append(" = ")
				.append(FD_SQ).append(dateFormat.format(new Date().getTime())).append(FD_SQ).append(",");
			sql.append(COLUMNNAME_FD_UPDATED).append(" = ").append(env.getLogin_User_ID()).append(" ");
			
			connection(env);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
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
		int logId = INITIAL_LOG_ID;
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
			close(stmt, rs);
		}
		
		return logId;
	}

}

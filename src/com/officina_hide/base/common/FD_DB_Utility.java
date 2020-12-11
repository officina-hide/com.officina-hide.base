package com.officina_hide.base.common;

import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.model.I_FD_Process;

/**
 * DB取扱汎用クラス[DB handling general class]<br>
 * @author officine-hide.com
 * @version 1.30
 * @since 2020/12/09 
 */
public class FD_DB_Utility implements I_FD_DB {

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

}

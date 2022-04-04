package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_Collect;
import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;

/**
 * 採番情報クラス[Numbering information class]
 * @author officina-hide.com
 * @version 1.50 新規作成
 * @since 2022/03/19 Ver. 1.50
 */
public class FD_Numbering extends FD_DB implements I_FD_Numbering {

	/**
	 * 採番情報テーブル構築[Numbering information table construction]<br>
	 * 採番情報テーブルは初期構築の為、生成は標準外で行う。<br>
	 * Since the numbering information table is initially constructed, it is generated outside the standard.<br>
	 * @author officina-hide.net
	 * @since 2022/03/19 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		PreparedStatement pstmt = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(Table_Drop_SQL);
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = getConn().prepareStatement(Table_Create_SQL);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

	/**
	 * 採番情報登録[Numbering information entry]<br>
	 * @author officina-hide.net
	 * @since 2022/03/23 Ver. 1.50
	 * @param entryData 登録情報[Entry data]
	 */
	public void add(String entryData) {
		FD_Collections entry = new FD_Collections(entryData);
		X_FD_Numbering xnum = new X_FD_Numbering(entry);
		for(FD_Collect en : entry.getList()) {
			xnum.setValue(en.getName(), en.getValue());
		}
	}

}

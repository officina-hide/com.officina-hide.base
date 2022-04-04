package com.officina_hide.project_ad.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * プロジェクト情報クラス[Project information class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2022/04/03 Ver. 1.00
 */
public class FD_Project extends FD_DB implements I_FD_Project {

	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/04/05 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public FD_Project(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * プロジェクト情報Table生成[Generate project information table]<br>
	 * @author officina-hide.net
	 * @since 2022/04/05 Ver. 1.00
	 */
	public void createTable() {
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

}

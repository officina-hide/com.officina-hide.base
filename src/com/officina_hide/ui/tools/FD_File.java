package com.officina_hide.ui.tools;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_File;

/**
 * ファイル情報クラス[File information class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/04/17 Ver. 1.00
 */
public class FD_File extends FD_DB implements I_FD_File {

	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/04/17 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public FD_File(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * ファイル情報テーブル生成[File information table generation]<br>
	 * @author officina-hide.net
	 * @since 2022/04/17 Ver. 1.00
	 */
	public void createTable() {
		PreparedStatement pstmt = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(Table_Drop_SQL);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
		
	}

}

package com.officina_hide.ui.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * 画面項目情報クラス[Screen item information class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 */
public class FX_Field extends FD_DB implements I_FX_Field {
	
	/** 環境情報[Environment information] */
	private  FD_EnvData env;

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/04/13 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public FX_Field(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 画面項目テーブル生成[Screen item table generation]<br>
	 * @author officina-hide.net
	 * @since 2022/04/13 Ver. 1.00
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

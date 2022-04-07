package com.officina_hide.ui.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * 画面情報クラス[View information class]<br>
 * @author officina-hide.net
 * @version 1.00
 * @since 2022/04/07 Ver. 1.00
 */
public class FX_View extends FD_DB implements I_FX_View {
	
	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/04/07 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public FX_View(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 画面情報テーブル生成[View information table generate]
	 * @author officina-hide.net
	 * @since 2022/04/07 Ver. 1.00
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
			System.out.println("画面情報テーブル生成 : " + new Date());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	
	}

}

package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;

/**
 * テーブル情報クラス[Table information class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成[New create]
 * @since 2022/04/11 Ver. 1.50
 */
public class FD_Table extends FD_DB implements I_FD_Table {
	
	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクター[COnstructor]
	 * @author officina-hide.net
	 * @since 2022/04/11 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 */
	public FD_Table(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * テーブル情報テーブル生成[Table information Table generation]<br>
	 * @author officina-hide.net
	 * @since 2022/04/11 Ver. 1.50
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
			System.out.println(Table_Disp_Name+"テーブル生成 : " + new Date());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

}

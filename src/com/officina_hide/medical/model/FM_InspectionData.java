package com.officina_hide.medical.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * 検査情報クラス[Inspection information class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/05/02 Ver. 1.00
 */
public class FM_InspectionData extends FD_DB implements I_FM_InspectionData {
	
	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/05/02 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public FM_InspectionData(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 医療情報テーブル生成[Medical information table generation]<br>
	 * @author officina-hide.net
	 * @since 2022/05/02 Ver. 1.00
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
			System.out.println(Table_Disp_Name+"テーブル構築完了 : " + new Date());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

}

package com.officina_hide.ui.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.officina_hide.base.common.FD_Collections;
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
			System.out.println(Table_Disp_Name+"テーブル生成 : " + new Date());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	
	}

	/**
	 * 画面情報登録[Screen information registration]<br>
	 * @author officina-hide.net
	 * @since 2022/04/08 Ver. 1.00
	 * @param entryData 登録情報[Entry data]
	 */
	public void entry(String entryData) {
		//とりあえず新規作成のみ対応 2022/04/11
		FD_Collections dataList = new FD_Collections(entryData);
		X_FX_View view = new X_FX_View(env, dataList);
		view.save(env);
	}

}

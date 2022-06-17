package com.officina_hide.ui.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;

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
		//テーブル情報登録
		FD_Table table = new FD_Table(env);
		table.add(Entry_FD_Table);
		//テーブル項目情報登録
		FD_Column column = new FD_Column(env);
		column.add(Entry_FD_Column_FV_View_ID);
		column.add(Entry_FD_Column_FV_View_Code);
		column.add(Entry_FD_Column_FD_Name);
		//採番情報登録
		FD_Numbering num = new FD_Numbering(env);
		num.add(Entry_FD_Number);
		
		//テーブル構築
		deleteTable(env, Table_Name);
		createTable(env, Table_Name, Table_Disp_Name);	
	}

	/**
	 * 画面情報登録[Screen information registration]<br>
	 * @author officina-hide.net
	 * @since 2022/04/08 Ver. 1.00
	 * @param entryData 登録情報[Entry data]
	 */
	public void entry(String entryData) {
		//とりあえず新規作成のみ対応 2022/04/11
		FD_Collections dataList = new FD_Collections(env, entryData);
		X_FX_View view = new X_FX_View(env, dataList);
		view.save(env);
	}

	/**
	 * 情報登録[Entry data]<br>
	 * @author officina-hide.net
	 * @since 2022/06/03 Ver. 1.00
	 * @param entryData 登録情報[Entry data]
	 */
	public void add(String entryData) {
		FD_Collections entry = new FD_Collections(env, entryData);
		X_FX_View view = new X_FX_View(env, entry);
		// TODO 新規追加のみ 2022/06/04
		view.save(env);
	}

	/**
	 * 画面情報ID取得[Getting screen information ID]<br>
	 * @author officina-hide.net
	 * @since 2022/06/05 Ver. 1.00
	 * @param viewCode 画面コード[Screen code]
	 * @return 画面情報ID[Screen information ID]
	 */
	public long getIDbyCode(String viewCode) {
		long id = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(SQL_GetID_ByCode);
			pstmt.setString(1, viewCode);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				id = rs.getLong(COLUMNNAME_FX_View_ID);
			} else {
				System.out.println("Error!! View Data not found! ["+viewCode+"]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
		return id;
	}

}

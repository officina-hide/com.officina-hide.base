package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;

/**
 * 参照情報クラス[Reference information class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成[New create]
 * @since 2022/05/10 Ver. 1.50
 */
public class FD_Reference extends FD_DB implements I_FD_Reference {

	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/05/10 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 */
	public FD_Reference(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 参照情報テーブル生成[Reference 
	 */
	public void createTable() {
		PreparedStatement pstmt = null;
		deleteTable(env, Table_Name);
		try {
			connection(env);
			pstmt = getConn().prepareStatement(Table_Create_SQL);
			pstmt.executeUpdate();
			System.out.println(Table_Disp_Name+"テーブル構築完了 : " + new Date());
			//テーブル情報登録
			FD_Table table = new FD_Table(env);
			table.add(Entry_FD_Table);
			//採番情報登録
			FD_Numbering num = new FD_Numbering(env);
			num.add(Entry_FD_Number);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

	/**
	 * 情報登録[Save data]
	 * @author officina-hide.net
	 * @since 2022/05/12 Ver. 1.50
	 * @param entryData 登録情報[Entry data]
	 */
	public void add(String entryData) {
		FD_Collections entry = new FD_Collections(env, entryData);
		X_FD_Reference ref = new X_FD_Reference(env, entry);
	}

}

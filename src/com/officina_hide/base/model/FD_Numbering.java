package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;

/**
 * 採番情報クラス[Numbering information class]
 * @author officina-hide.com
 * @version 1.50 新規作成
 * @since 2022/03/19 Ver. 1.50
 */
public class FD_Numbering extends FD_DB implements I_FD_Numbering {
	
	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/04/18 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public FD_Numbering(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 採番情報テーブル構築[Numbering information table construction]<br>
	 * 採番情報テーブルは初期構築の為、生成は標準外で行う。<br>
	 * Since the numbering information table is initially constructed, it is generated outside the standard.<br>
	 * @author officina-hide.net
	 * @since 2022/03/19 Ver. 1.50
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
			System.out.println("採番情報テーブル構築完了 : " + new Date());
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
		X_FD_Numbering num = new X_FD_Numbering(entry);
		//TODO 新規登録のみ(2022/04/19)
		num.save(env);
	}

	/**
	 * 新規採番[Create numbering]<br>
	 * @author officina-hide.net
	 * @since 2022/04/19 Ver. 1.50
	 * @param tableName テーブル名[Table name]
	 * @param columnName テーブル項目名[Column name]
	 * @return 採番結果[Numbering result]
	 */
	public String getNewNumber(String tableName, String columnName) {
		return null;
	}
}

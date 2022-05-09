package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_EnvData;

/**
 * ファイル拡張子情報クラス[File Extension Class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[Create new]
 * @since 2022/05/07 Ver. 1.00 
 */
public class FD_ExtensionType extends FD_DB implements I_FD_ExtensionType {
	
	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/05/07 Ver. .100
	 * @param env 環境情報[Environment information]
	 */
	public FD_ExtensionType(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * ファイル拡張子情報テーブル生成[File extension information table generation]<br>
	 * @author officina-hide.net
	 * @since 2022/05/07 Ver. 1.00
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

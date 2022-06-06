package com.officina_hide.ui.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_Table;

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
		// テーブル情報登録[Table information registration]
		FD_Table table = new FD_Table(env);
		table.add(Entry_FD_Table);
		// テーブル項目情報登録[Table column information registration]
		FD_Column column = new FD_Column(env);
		column.add(Entry_FD_Column_FX_Field_ID);
		column.add(Entry_FD_Column_FX_Field_Code);
		column.add(Entry_FD_Column_FX_View_ID);
		
		//テーブル削除
		deleteTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name, Table_Disp_Name);
		
//		PreparedStatement pstmt = null;
//		try {
//			connection(env);
//			pstmt = getConn().prepareStatement(Table_Drop_SQL);
//			pstmt.executeUpdate();
//			pstmt.close();
//			pstmt = getConn().prepareStatement(Table_Create_SQL);
//			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DBClose(pstmt, null);
//		}
	}

}

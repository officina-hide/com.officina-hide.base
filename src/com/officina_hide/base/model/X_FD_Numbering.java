package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_ColumnDataCollection;
import com.officina_hide.base.common.FD_EnvData;

/**
 * 採番情報I/Oクラス[Numbering information I/O class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成
 * @since 2022/03/24 Ver. 1.50
 */
public class X_FD_Numbering extends FD_DB implements I_FD_Numbering {

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/03/24 Ver. 1.50
	 */
	public X_FD_Numbering() {
		createColumnList();
	}

	/**
	 * コンストラクタ[Constructor]
	 * @author officina-hide.net
	 * @since 2022/03/26 Ver. 1.50
	 * @param entry 登録情報
	 */
	public X_FD_Numbering(FD_Collections entry) {
		createColumnList();
		columnCollection.setData(entry);
	}

	/**
	 * 採番情報用テーブル項目リスト生成[Table item list generation for numbering information]<br>
	 * @author officina-hide.net
	 * @since 2022/03/24 Ver. 1.50
	 */
	private void createColumnList() {
		columnCollection = new FD_ColumnDataCollection();
		columnCollection.add(COLUMNNAME_FD_Numbering_ID, FD_Item_ID);
		columnCollection.add(COLUMNNAME_FD_Table_ID, FD_Item_ID);
		columnCollection.add(COLUMNNAME_FD_Column_ID, FD_Item_ID);
		columnCollection.add(COLUMNNAME_FD_NumberFormat, FD_Item_String);
		columnCollection.add(COLUMNNAME_FD_InitialNumber, FD_Item_Long);
		columnCollection.add(COLUMNNAME_FD_CurrentNumber, FD_Item_Long);
	}

	/**
	 * 情報登録[Save information]<br>
	 * TODO 汎用化予定
	 * @author officina-hide.net
	 * @since 2022/04/19 Ver. 1.50 
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		PreparedStatement pstmt = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(columnCollection.getInsertSQL(Table_Name));
			int rs = pstmt.executeUpdate();
			if(rs != 1) {
				System.out.println("Save Error!!");
			} else {
				System.out.println(Table_Disp_Name+"登録完了["+columnCollection.getTableId(Table_Name)+"]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

}

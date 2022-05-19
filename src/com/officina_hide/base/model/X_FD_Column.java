package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;

/**
 * テーブル項目情報I/Oクラス[Table column information I/O class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成[New create]
 * @since 2022/04/22 Ver. 1.50
 */
public class X_FD_Column extends FD_DB implements I_FD_Column {

	/** 項目 : テーブル項目情報ID */
	private long FD_Column_ID;
	
	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/04/22 Ver. 1.50 新規作成[New create]
	 * @param entry 登録情報[Entry data]
	 */
	public X_FD_Column(FD_Collections entry) {
		createColumnList();
		columnCollection.setData(entry);
	}

	/**
	 * テーブル情報用テーブル項目リスト生成[Table item list generation for numbering information]<br>
	 * @author officina-hide.net
	 * @since 2022/04/22 Ver. 1.50
	 * TODO 汎用化予定
	 */
	private void createColumnList() {
		columnCollection.clear();
		columnCollection.add(COLUMNNAME_FD_Column_ID, FD_Item_ID, ID_ZERO);
		columnCollection.add(COLUMNNAME_FD_Column_Code, FD_Item_String);
		columnCollection.add(COLUMNNAME_FD_Table_ID, FD_Item_ID, ID_ZERO);
		columnCollection.add(COLUMNNAME_FD_Name, FD_Item_String);
		columnCollection.add(COLUMNNAME_FD_ColumnType_ID, FD_Item_ID, ID_ZERO);
		columnCollection.add(COLUMNNAME_FD_Column_Size, FD_Item_Int, INT_ZERO);
	}

	/**
	 * 情報登録[Save data]<br>
	 * @author officina-hide.net
	 * @since 2022/04/22 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		//テーブル項目情報IDが0の時は、新規採番する。
		if(getFD_Column_ID() == 0) {
			FD_Numbering num = new FD_Numbering(env);
			setFD_Column_ID(num.getNewId(Table_ID));
		}
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

	public long getFD_Column_ID() {
		FD_Column_ID = (long) columnCollection.getValue(COLUMNNAME_FD_Column_ID);
		return FD_Column_ID;
	}
	public void setFD_Column_ID(long columnId) {
		columnCollection.setValue(COLUMNNAME_FD_Column_ID, columnId);
	}

}

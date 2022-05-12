package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;

/**
 * 参照グループ情報I/Oクラス[Reference group information I/O class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成[New create]
 * @since 2022/05/12 Ver. 1.50
 */
public class X_FD_ReferenceGroup extends FD_DB implements I_FD_ReferenceGroup {

	/** 項目 : 参照グループ情報ID */
	private long FD_ReferenceGroup_ID;
	
	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/05/12 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 * @param entry 登録情報[Entry data]
	 */
	public X_FD_ReferenceGroup(FD_EnvData env, FD_Collections entry) {
		createColumnList();
		columnCollection.setData(entry);
	}

	/**
	 * 参照グループ情報テーブル項目リスト生成[Reference group information table Item list generation]<br>
	 * @author officina-hide.net
	 * @since 2022/05/12 Ver. 1.50
	 */
	private void createColumnList() {
		columnCollection.clear();
		columnCollection.add(COLUMNNAME_FD_ReferenceGroup_ID, FD_Item_ID, ID_ZERO);
		columnCollection.add(COLUMNNAME_FD_ReferenceGroup_Code, FD_Item_String);
		columnCollection.add(COLUMNNAME_FD_Name, FD_Item_String);
	}

	/**
	 * 情報登録[Save data]
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		PreparedStatement pstmt = null;
		if(getFD_ReferenceGroup_ID() == 0) {
			//情報ID新規採番
			FD_Numbering num = new FD_Numbering(env);
			setFD_ReferenceGroup_ID(num.getNewId(Table_ID));
		}
		try {
			connection(env);
			pstmt = getConn().prepareStatement(columnCollection.getInsertSQL(Table_Name));
			int rs = pstmt.executeUpdate();
			if(rs != 1) {
				System.out.println(pstmt.toString());
				System.out.println("Error FD_ReferenceGroup Save Error!");
			} else {
				System.out.println(Table_Disp_Name+"登録完了["+getFD_ReferenceGroup_ID()+"]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

	public long getFD_ReferenceGroup_ID() {
		FD_ReferenceGroup_ID = (long) columnCollection.getValue(COLUMNNAME_FD_ReferenceGroup_ID);
		return FD_ReferenceGroup_ID;
	}
	public void setFD_ReferenceGroup_ID(long referenceGroupId) {
		columnCollection.setValue(COLUMNNAME_FD_ReferenceGroup_ID, referenceGroupId);
	}

}

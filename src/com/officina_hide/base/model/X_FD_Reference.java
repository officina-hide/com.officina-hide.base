package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;

/**
 * 参照情報I/Oクラス[Reference information I/O class]<br>
 * @author officina-hide.net
 * @since 2022/05/12 Ver. 1.50
 */
public class X_FD_Reference extends FD_DB implements I_FD_Reference {

	/** 項目 : 参照情報ID */
	private long FD_Reference_ID;
	/** 項目 : 参照コード */
	private String FD_Reference_Code;
	
	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/05/13 Ver. 1.50
	 * @param env
	 * @param entry
	 */
	public X_FD_Reference(FD_EnvData env, FD_Collections entry) {
		createColumnList();
		columnCollection.setData(entry);
	}

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/05/24 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 * @param referenceId 参照情報ID[Reference information ID]
	 */
	public X_FD_Reference(FD_EnvData env, long referenceId) {
		createColumnList();
		if(referenceId > 0) {
			load(env, referenceId);
		}
	}

	/**
	 * 情報取得[Data load]<br>
	 * @author officina-hide.net
	 * @since 2022/05/24 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 * @param referenceId 参照情報ID[Reference information ID]
	 */
	private void load(FD_EnvData env, long referenceId) {
		load(env, referenceId, Table_Name);
	}

	/**
	 * 参照情報テーブル項目リスト生成[Reference information table Item list generation]<br>
	 * @author officina-hide.net
	 * @since 2022/05/13 Ver. 1.50
	 */
	private void createColumnList() {
		columnCollection.clear();
		columnCollection.add(COLUMNNAME_FD_Reference_ID, FD_Item_ID, ID_ZERO);
		columnCollection.add(COLUMNNAME_FD_ReferenceGroup_ID, FD_Item_ID, ID_ZERO);
		columnCollection.add(COLUMNNAME_FD_Reference_Code, FD_Item_String);
		columnCollection.add(COLUMNNAME_FD_Name, FD_Item_String);
	}

	/**
	 * 情報保存[Data save]<br>
	 * @author officina-hide.net
	 * @since 2022/05/16 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		PreparedStatement pstmt = null;
		if(getFD_Reference_ID() == 0) {
			//参照情報採番
			FD_Numbering num = new FD_Numbering(env);
			setFD_Reference_ID(num.getNewId(Table_ID));
		}
		//情報保管
		try {
			connection(env);
			pstmt = getConn().prepareStatement(columnCollection.getInsertSQL(Table_Name));
			int rs = pstmt.executeUpdate();
			if(rs != 1) {
				System.out.println(pstmt.toString());
				System.out.println("Error FD_Reference Save Error!");
			} else {
				System.out.println(Table_Disp_Name+"登録完了["+getFD_Reference_ID()+"]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
		
	}

	public long getFD_Reference_ID() {
		FD_Reference_ID = (long) columnCollection.getValue(COLUMNNAME_FD_Reference_ID);
		return FD_Reference_ID;
	}
	public void setFD_Reference_ID(long referenceId) {
		columnCollection.setValue(COLUMNNAME_FD_Reference_ID, referenceId);
	}
	public String getFD_Reference_Code() {
		FD_Reference_Code = (String) columnCollection.getValue(COLUMNNAME_FD_Reference_Code);
		return FD_Reference_Code;
	}
	public void setFD_Reference_Code(String referenceCode) {
		columnCollection.setValue(COLUMNNAME_FD_Reference_Code, referenceCode);
	}

}

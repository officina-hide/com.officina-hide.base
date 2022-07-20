package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;

/**
 * テーブル情報I/Oクラス[Table information I/O class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成[New create]
 * @since 2022/04/21 Ver. 1.50
 */
public class X_FD_Table extends FD_DB implements I_FD_Table {

	/** 項目 : テーブル情報ID */
	private long FD_Table_ID;
	/** 項目 : テーブルコード */
	private String FD_Table_Code;
	
	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/04/21 Ver. 1.50
	 * @param entryData 登録情報[Entry data]
	 */
	public X_FD_Table(FD_Collections entry) {
		createColumnList();
		columnCollection.setData(entry);
	}

	/**
	 * @param env
	 * @param tableId
	 */
	public X_FD_Table(FD_EnvData env, long tableId) {
		createColumnList();
		load(env, tableId, Table_Name);
	}

	/**
	 * テーブル情報用テーブル項目リスト生成[Table item list generation for numbering information]<br>
	 * @author officina-hide.net
	 * @since 2022/04/21 Ver. 1.50
	 * TODO 汎用化予定
	 */
	private void createColumnList() {
		columnCollection.clear();
		columnCollection.add(COLUMNNAME_FD_Table_ID, FD_Item_ID, (long) 0);
		columnCollection.add(COLUMNNAME_FD_Table_Code, FD_Item_String);
		columnCollection.add(COLUMNNAME_FD_Name, FD_Item_String);
		columnCollection.add(COLUMNNAME_FD_Description, FD_Item_Text);
	}

	/**
	 * 情報登録[Save data]<br>
	 * @author officina-hide.net
	 * @since 2022/04/21 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		PreparedStatement pstmt = null;
		//テーブル情報IDが0の時は新規に採番する。
		if(getFD_Table_ID() == 0) {
			FD_Numbering num = new FD_Numbering(env);
			setFD_Table_ID(num.getNewId(Table_ID));
		}
		try {
			connection(env);
			pstmt = getConn().prepareStatement(columnCollection.getInsertSQL(Table_Name));
			int rs = pstmt.executeUpdate();
			if(rs != 1) {
				System.out.println("Error FD_Table Save Error!!");
			} else {
				System.out.println(Table_Disp_Name+"登録完了["+getFD_Table_ID()+"]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}		
	}

	public long getFD_Table_ID() {
		FD_Table_ID = (long) columnCollection.getValue(COLUMNNAME_FD_Table_ID);
		return FD_Table_ID;
	}
	public void setFD_Table_ID(long tableId) {
		columnCollection.setValue(COLUMNNAME_FD_Table_ID, tableId);
	}
	public String getFD_Table_Code() {
		FD_Table_Code = (String) columnCollection.getValue(COLUMNNAME_FD_Table_Code);
		return FD_Table_Code;
	}
	public void setFD_Table_Code(String tableCode) {
		columnCollection.setValue(COLUMNNAME_FD_Table_Code, tableCode);
	}

}

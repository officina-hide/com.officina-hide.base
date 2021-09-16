package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Items;

/**
 * 採番情報I/Oクラス[Numbering information I/O class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/16
 */
public class X_FD_Numbering extends FD_DB implements I_FD_Numbering {


	/** 項目リスト */
	private FD_Items items;
	
	/** 項目 : 採番情報ID */
	private int FD_Numbering_ID;
	/** 項目 : テーブル情報ID */
	private int FD_Table_ID;
	/** 項目 : 初期採番番号 */
	private long FD_InitialNumber;
	/** 項目 : 現在採番番号 */
	private long FD_CurrentNumber;
	/** 項目 : グループ情報ID */
	private int FD_Group_ID;
	/** 項目 : 登録日時 */
	private Calendar FD_Created;
	/** 項目 : 登録者情報ID */
	private int FD_CreatedBy;
	/** 項目 : 更新日時 */
	private Calendar FD_updated;
	/** 項目 : 更新者情報ID */
	private int FD_UpdatedBy;
	
	/**
	 * コンストラクタ[Constructor]
	 * @param env 環境情報[Environment information]
	 * @param numberingID 採番情報ID[Numbering information ID]
	 */
	public X_FD_Numbering(FD_EnvData env, int numberingID) {
		//項目リスト設定
		items = new FD_Items();
		items.add(COLUMNNAME_FD_Numbering_ID, null, Item_Value_Type_ID);
		items.add(COLUMNNAME_FD_Table_ID, null, Item_Value_Type_ID);
		items.add(COLUMNNAME_FD_InitialNumber, null, Item_Value_Type_Bigint);
		items.add(COLUMNNAME_FD_CurrentNumber, null, Item_Value_Type_Bigint);
		baseItemSet(items);

		// TODO 未実装 : 採番情報IDがゼロ以外の時にload()をする。
	}

	/**
	 * 情報保存[Information save] 
	 * TODO 汎用化予定
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("INSERT INTO ").append(Table_Name).append(" ");
			sql.append("(").append(items.getInsertItemStrings()).append(")").append(" ");
			sql.append("VALUES");
			sql.append("(").append(items.getPrepardStrings()).append(")");
			
			connection(env);
			pstmt = getConn().prepareStatement(sql.toString());
			pstmt.setInt(1, items.getintData(COLUMNNAME_FD_Numbering_ID));
			pstmt.setInt(2, items.getintData(COLUMNNAME_FD_Table_ID));
			pstmt.setLong(3, items.getlongData(COLUMNNAME_FD_InitialNumber));
			pstmt.setLong(4, items.getlongData(COLUMNNAME_FD_CurrentNumber));
			setCommonPrepared(pstmt, items, 5);
			if(pstmt.executeUpdate() != 1) {
				new Exception("採番情報保存エラー");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

	public int getFD_Numbering_ID() {
		FD_Numbering_ID = items.getintData(COLUMNNAME_FD_Numbering_ID);
		return FD_Numbering_ID;
	}
	public void setFD_Numbering_ID(int numberingID) {
		items.setValue(COLUMNNAME_FD_Numbering_ID, numberingID);
	}
	public int getFD_Table_ID() {
		FD_Table_ID = items.getintData(COLUMNNAME_FD_Table_ID);
		return FD_Table_ID;
	}
	public void setFD_Table_ID(int tableID) {
		items.setValue(COLUMNNAME_FD_Table_ID, tableID);
	}
	public long getFD_InitialNumber() {
		FD_InitialNumber = items.getlongData(COLUMNNAME_FD_InitialNumber);
		return FD_InitialNumber;
	}
	public void setFD_InitialNumber(long initialNumber) {
		items.setValue(COLUMNNAME_FD_InitialNumber, initialNumber);
	}
	public long getFD_CurrentNumber() {
		FD_CurrentNumber = items.getintData(COLUMNNAME_FD_CurrentNumber);
		return FD_CurrentNumber;
	}
	public void setFD_CurrentNumber(long currentNumber) {
		items.setValue(COLUMNNAME_FD_CurrentNumber, currentNumber);
	}
	public int getFD_Group_ID() {
		FD_Group_ID = items.getintData(COLUMNNAME_FD_Group_ID);
		return FD_Group_ID;
	}
	public void setFD_Group_ID(int groupID) {
		items.setValue(COLUMNNAME_FD_Group_ID, groupID);
	}
	public Calendar getFD_Created() {
		FD_Created = items.getDateData(COLUMNNAME_FD_Created);
		return FD_Created;
	}
	public void setFD_Created(Calendar created) {
		items.setValue(COLUMNNAME_FD_Created, created);
	}
	public int getFD_CreatedBy() {
		FD_CreatedBy = items.getintData(COLUMNNAME_FD_CreatedBy);
		return FD_CreatedBy;
	}
	public void setFD_CreatedBy(int createdBy) {
		items.setValue(COLUMNNAME_FD_CreatedBy, createdBy);
	}
	public Calendar getFD_updated() {
		FD_updated = items.getDateData(COLUMNNAME_FD_Updated);
		return FD_updated;
	}
	public void setFD_updated(Calendar updated) {
		items.setValue(COLUMNNAME_FD_Updated, updated);
	}
	public int getFD_UpdatedBy() {
		FD_UpdatedBy = items.getintData(COLUMNNAME_FD_UpdatedBy);
		return FD_UpdatedBy;
	}
	public void setFD_UpdatedBy(int updatedBy) {
		items.setValue(COLUMNNAME_FD_UpdatedBy, updatedBy);
	}

}

package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;
import com.officina_hide.base.common.FD_Items;
import com.officina_hide.base.common.FD_WhereData;

/**
 * 採番情報I/Oクラス[Numbering information I/O class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/16
 */
public class X_FD_Numbering extends FD_DB implements I_FD_Numbering {
	
//	/** 項目一覧 */
//	private FD_Items items;

	/** 項目 : 採番情報ID */
	private long FD_Numbering_ID;
	/** 項目 : テーブル情報ID */
	private long FD_Table_ID;
	/** 項目 : 初期採番番号 */
	private long FD_InitialNumber;
	/** 項目 : 現在採番番号 */
	private long FD_CurrentNumber;
	
	/**
	 * コンストラクタ[Constructor]
	 * @param env 環境情報[Environment information]
	 * @param numberingID 採番情報ID[Numbering information ID]
	 */
	public X_FD_Numbering(FD_EnvData env, int numberingID) {
		//項目リストの初期化
		initItems();

		if(numberingID > 0) {
			load(env, Table_Name, numberingID, items);
		}
	}

	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officina-hide.net
	 * @since 1.00 2021/09/17
	 * @param env 環境情報[Enfironment information]
	 * @param where 条件情報[Condition information]
	 */
	public X_FD_Numbering(FD_EnvData env, FD_WhereData where) {
		Statement stmt = null;
		ResultSet rs = null;
		
		//項目リストの初期化
		initItems();
		//指定された条件で抽出する。
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(Table_Name).append(" ");
			sql.append(where.toString());
			connection(env);
			stmt = getConn().createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				for(FD_Item item : items.getItems()) {
					switch(item.getType()) {
					case FD_Item_ID:
					case FD_ITEM_BigInt:
						items.setValue(item.getName(), rs.getLong(item.getName()));
						break;
					case FD_Item_String:
					case FD_Item_Text:
						items.setValue(item.getName(), rs.getString(item.getName()));
						break;
					case FD_ITEM_Date:
						Calendar cal = new GregorianCalendar(new Locale(Locale.JAPAN.getLanguage(), Locale.JAPAN.getCountry()));
						cal.setTime(rs.getTimestamp(item.getName()));
						items.setValue(item.getName(), cal);
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(stmt, rs);
		}
	}

	/**
	 * 項目一覧の初期化[Item list initialization]<br>
	 * @author officina-hide.net
	 * @since 1.00 2021/09/17
	 */
	private void initItems() {
		items = new FD_Items();
		items.add(COLUMNNAME_FD_Numbering_ID, null, FD_Item_ID);
		items.add(COLUMNNAME_FD_Table_ID, null, FD_Item_ID);
		items.add(COLUMNNAME_FD_InitialNumber, null, FD_ITEM_BigInt);
		items.add(COLUMNNAME_FD_CurrentNumber, null, FD_ITEM_BigInt);
		baseItemSet(items);
		items.setTableName(Table_Name);
		items.setTableId(Table_ID);
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
			//情報IDがテーブル内に存在しているか確認する。
			if(isRecoedExits(env, Table_Name, getFD_Numbering_ID())) {
				//更新用SQL作成
				sql.append("UPDATE ").append(Table_Name).append(" ").append(" SET ");
				sql.append(items.getUpdateItemStrings()).append(" ");
				sql.append("WHERE ").append(I_FD_Numbering.COLUMNNAME_FD_Numbering_ID)
					.append(" = ").append(getFD_Numbering_ID()).append(" ");
			} else {
				//新規登録用SQL作成
				sql.append("INSERT INTO ").append(Table_Name).append(" ");
				sql.append("(").append(items.getInsertItemStrings()).append(")").append(" ");
				sql.append("VALUES");
				sql.append("(").append(items.getPrepardStrings()).append(")");
			}
			connection(env);
			pstmt = getConn().prepareStatement(sql.toString());
			pstmt.setLong(1, items.getlongData(COLUMNNAME_FD_Numbering_ID));
			pstmt.setLong(2, items.getlongData(COLUMNNAME_FD_Table_ID));
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

	public long getFD_Numbering_ID() {
		FD_Numbering_ID = items.getlongData(COLUMNNAME_FD_Numbering_ID);
		return FD_Numbering_ID;
	}
	public void setFD_Numbering_ID(long numberingID) {
		items.setValue(COLUMNNAME_FD_Numbering_ID, numberingID);
	}
	public long getFD_Table_ID() {
		FD_Table_ID = items.getlongData(COLUMNNAME_FD_Table_ID);
		return FD_Table_ID;
	}
	public void setFD_Table_ID(long tableID) {
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
		FD_CurrentNumber = items.getlongData(COLUMNNAME_FD_CurrentNumber);
		return FD_CurrentNumber;
	}
	public void setFD_CurrentNumber(long currentNumber) {
		items.setValue(COLUMNNAME_FD_CurrentNumber, currentNumber);
	}
//	public FD_Items getItems() {
//		return items;
//	}

}

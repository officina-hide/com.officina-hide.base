package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;
import com.officina_hide.base.common.FD_Items;
import com.officina_hide.base.common.FD_WhereData;

/**
 * 辞書情報I/Oクラス[Dictionary information I/O class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/18
 */
public class X_FD_DataDictionary extends FD_DB implements I_FD_DataDictionary {
	
	/** 項目 : 辞書情報ID */
	private long FD_DataDictionary_ID;
	/** 項目 : 辞書情報ID */
	private String FD_DataDictionary_Name;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/18
	 * @param env 環境情報[Environment information]
	 * @param id 辞書情報ID[Dictionary information ID]
	 */
	public X_FD_DataDictionary(FD_EnvData env, long id) {
		//項目一覧初期化
		initItems();
		//idが0以上の時は情報を抽出する。
		if(id > 0) {
			load(env, Table_Name, id, items);
		}
	}

	/**
	 * コンストラクタ[Condtructor]
	 * @param env 環境情報[Enfironment information]
	 * @param where 抽出条件[Extraction condition]
	 */
	public X_FD_DataDictionary(FD_EnvData env, FD_WhereData where) {
		//項目一覧初期化
		initItems();
		if(where != null) {
			load(env, items, where);
		}
	}

	/**
	 * 項目一覧の初期化[Item list initialization]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/18
	 */
	private void initItems() {
		items = new FD_Items();
		items.add(COLUMNNAME_FD_DataDictionary_ID, null, FD_ITEM_ID);
		items.add(COLUMNNAME_FD_DataDictionary_Name, null, FD_ITEM_String);
		items.add(COLUMNNAME_FD_Name, null, FD_ITEM_String);
		items.add(COLUMNNAME_FD_Description, null, FD_ITEM_Text);
		baseItemSet(items);
		items.setTableId(Table_ID);
		items.setTableName(Table_Name);
	}
	
	/**
	 * 情報登録[Information Entry]
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		PreparedStatement pstmt = null;
		Calendar nowDate = new GregorianCalendar(new Locale(Locale.JAPAN.getLanguage(), Locale.JAPAN.getCountry()));
		nowDate.setTime(new Date());
		StringBuffer sql = new StringBuffer();
		if(getFD_DataDictionary_ID() > 0 && isRecoedExits(env, Table_Name, getFD_DataDictionary_ID())) {
			//情報更新
			//更新日時、更新者セット
			items.setValue(COLUMNNAME_FD_Updated, nowDate);
			items.setValue(COLUMNNAME_FD_UpdatedBy, env.getActionUserID());
			//更新用SQL作成
			sql.append("UPDATE ").append(Table_Name).append(" ").append(" SET ");
			sql.append(items.getUpdateItemStrings()).append(" ");
			sql.append("WHERE ").append(I_FD_Numbering.COLUMNNAME_FD_Numbering_ID)
				.append(" = ").append(getFD_DataDictionary_ID()).append(" ");
		} else {
			//新規登録
			//登録日時、登録者、更新日時、更新者セット
			items.setValue(COLUMNNAME_FD_Created, nowDate);
			items.setValue(COLUMNNAME_FD_CreatedBy, env.getActionUserID());
			items.setValue(COLUMNNAME_FD_Updated, nowDate);
			items.setValue(COLUMNNAME_FD_UpdatedBy, env.getActionUserID());
			//新規登録用SQL作成
			sql.append("INSERT INTO ").append(Table_Name).append(" ");
			sql.append("(").append(items.getInsertItemStrings()).append(")").append(" ");
			sql.append("VALUES");
			sql.append("(").append(items.getPrepardStrings()).append(")");
		}
		
		connection(env);
		try {
			pstmt = getConn().prepareStatement(sql.toString());
			int idx = 1;
			for(FD_Item item : items.getItems()) {
				switch(item.getType()) {
				case FD_ITEM_ID:
				case FD_ITEM_BigInt:
					pstmt.setLong(idx, items.getlongData(item.getName()));
					break;
				case FD_ITEM_String:
				case FD_ITEM_Text:
					// FIXME エスケープ対象文字未対応
					pstmt.setString(idx, items.getStringData(item.getName()));
					break;
				case FD_ITEM_Date:
					pstmt.setTimestamp(idx, new Timestamp(items.getDateData(item.getName()).getTimeInMillis()));
					break;
				}
				idx++;
			}
			//情報保存
			if(pstmt.executeUpdate() != 1) {
				new Exception("採番情報保存エラー");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

	public long getFD_DataDictionary_ID() {
		FD_DataDictionary_ID = items.getlongData(COLUMNNAME_FD_DataDictionary_ID);
		return FD_DataDictionary_ID;
	}
	public void setFD_DataDictionary_ID(long dictionaryId) {
		items.setValue(COLUMNNAME_FD_DataDictionary_ID, dictionaryId);
	}
	public String getFD_DataDictionary_Name() {
		FD_DataDictionary_Name = items.getStringData(COLUMNNAME_FD_DataDictionary_Name);
		return FD_DataDictionary_Name;
	}
	public void setFD_DataDictionary_Name(String dictionaryName) {
		items.setValue(COLUMNNAME_FD_DataDictionary_Name, dictionaryName);
	}

//	public FD_Items getItems() {
//		return items;
//	}

}

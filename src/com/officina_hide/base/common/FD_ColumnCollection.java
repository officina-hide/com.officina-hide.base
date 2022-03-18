package com.officina_hide.base.common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_Table;
import com.officina_hide.base.model.I_FD_Column;
import com.officina_hide.base.model.X_FD_Column;

/**
 * テーブル項目集約情報[Table item collection]
 * @author officina-hide.net
 * @version 新規作成
 * @since 2022/03/03 Ver. 1.00
 */
public class FD_ColumnCollection extends FD_DB {

	/** テーブル項目情報リスト */
	private List<FD_Collection> list;

	/**
	 * コンストラクター[Constructor]
	 * @author officina-hide.net
	 * @since 2022/03/03 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name] 
	 * @param dataList 登録データリスト[Entry data list]
	 */
	public FD_ColumnCollection(FD_EnvData env, String tableName, String dataList) {
		createList(env, tableName);
		getDataMap(env, dataList);
	}

	/**
	 * 登録情報配列取得[Get registration information map]
	 * @author officina-hide.net
	 * @since 2022/03/07 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param dataList 登録データリスト[Entry data list]
	 */
	private void getDataMap(FD_EnvData env, String dataList) {
		String[] wk = dataList.split(",");
		for(String data : wk) {
			String[] dlist = data.split(":");
			FD_Collection collect = null;
			//項目名でコレクション抽出
			for(FD_Collection chk : list) {
				if(chk.getName().equals(dlist[0])) {
					collect = chk;
					break;
				}
			}
			if(collect == null) {
				System.out.println("Error!! Column not found : "+dataList);
				new Exception();
			}
			if(dlist[1].substring(0, 1).equals("@")) {
				//関数処理
				switch(dlist[1].substring(1)) {
				case "getId":
					long id = getId(env, dlist[2], dlist[3],dlist[4]);
					collect.setLongValue(id);
					break;
				}
			} else {
				//値渡し
				switch(collect.getTypeName()) {
				case FD_ITEM_YES_NO:
				case FD_ITEM_String:
					collect.setStringValue(dlist[1]);
					break;
				}
			}
		}
	}

	/**
	 * 情報ID取得[Information ID acquisition]<br>
	 * @author officina-hide.net
	 * @since 2022/03/10 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name]
	 * @param columnName テーブル項目名[Table item name]
	 * @param data 比較情報[Comparison information]
	 * @return 
	 */
	private long getId(FD_EnvData env, String tableName, String columnName, String data) {
		long id = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("select ").append(tableName).append("_ID from ").append(tableName).append(" ");
			sql.append("where ").append(columnName).append(" = ? ");
			connection(env);
			pstmt = getConn().prepareStatement(sql.toString());
			pstmt.setString(1, data);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				id = rs.getLong(tableName + "_ID");
			} else {
				System.out.println("Error : data not found : "+tableName+","+columnName+","+data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
		return id;
	}

	/**
	 * テーブル項目情報リスト初期化[Table item information list initialization]
	 * @author officina-hide.net
	 * @since 2022/03/04 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名
	 */
	private void createList(FD_EnvData env, String tableName) {
		/*
		 * テーブル項目情報からリストを取得する。
		 * 値は初期値とする。
		 */
		FD_DB DB = new FD_DB();
		FD_Table table = new FD_Table(env);
		long tableId = table.getTableId(tableName);
		List<Integer> ids = DB.getAllId(tableName, new FD_WhereData(I_FD_Column.COLUMNNAME_FD_Table_ID, tableId), env);
		for(int id : ids) {
			X_FD_Column column = new X_FD_Column(env, id);
			FD_Collection collect = new FD_Collection();
			collect.setName(column.getFD_DataDictionary().getFD_DataDictionary_Name());
			collect.setTypeName(column.getFD_TypeItem().getFD_TypeItem_Name());
			collect.setInitialValue(column.getFD_Default());
			if(column.getFD_Default() != null) {
				switch(collect.getTypeName()) {
				case FD_ITEM_ID:
					collect.setLongValue(Long.parseLong(column.getFD_Default()));
					break;
				case FD_ITEM_Unsigned_Int:
					collect.setIntValue(Integer.parseInt(column.getFD_Default()));
					break;
				case FD_ITEM_YES_NO:
					collect.setStringValue(column.getFD_Default());
					break;
				}
			}
			getCollectionList().add(collect);
		}
	}

	/**
	 * テーブル項目情報リスト取得[Get table item collection list]
	 * @author officina-hide.net
	 * @since 2022/03/03 Ver. 1.00
	 * @return テーブル項目情報リスト[Table item collection list]
	 */
	public List<FD_Collection> getCollectionList() {
		if(list == null) {
			list = new ArrayList<>();
		}
		return list;
	}
}

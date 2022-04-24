package com.officina_hide.base.common;

import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.model.I_FD_DB;

/**
 * テーブル項目コレクション[Table item collection]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成
 * @since 2022/03/24 Ver. 1.50
 */
public class FD_ColumnDataCollection implements I_FD_DB {

	/** 情報リスト */
	private List<FD_ColumnData> list = new ArrayList<>();
	
	/**
	 * テーブル項目追加[Table item entry]
	 * @param columnName テーブル項目名[Table item name]
	 * @param typeName 属性名[Table item type name]
	 */
	public void add(String columnName, String typeName) {
		FD_ColumnData cd = new FD_ColumnData();
		cd.setColumnName(columnName);
		cd.setColumnType(typeName);
		list.add(cd);
	}

	/**
	 * テーブル項目追加[Table item entry]
	 * @param columnName テーブル項目名[Table item name]
	 * @param typeName 属性名[Table item type name]
	 * @param data 値[Table item data]
	 */
	public void add(String columnName, String typeName, Object data) {
		FD_ColumnData cd = new FD_ColumnData();
		cd.setColumnName(columnName);
		cd.setColumnType(typeName);
		if(data != null) {
			cd.setColumnData(data);
		}
		list.add(cd);
	}

	/**
	 * テーブル項目抽出[Table item extraction]<br>
	 * @author officina-hide.net
	 * @since 2022/04/08 Ver. 1.00
	 * @param dataName 登録情報名[Entry information name]<br>
	 * @return テーブル項目情報[Table item information]
	 */
	public FD_ColumnData getItem(String dataName) {
		for(FD_ColumnData cd : list) {
			if(cd.getColumnName().equals(dataName)) {
				return cd;
			}
		}
		return null;
	}

	/**
	 * テーブル情報ID取得[Table information ID getting]<br>
	 * @author officina-hide.net
	 * @since 2022/04/08 Ver. 1.00
	 * @param tableName テーブル名[Table name]
	 * @return テーブル情報ID[Table information ID]
	 */
	public long getTableId(String tableName) {
		FD_ColumnData idcd = getItem(tableName+"_ID");
		return (long) idcd.getColumnData();
	}

	/**
	 * 新規登録用SQL生成
	 * @param tableName 
	 * @return 
	 */
	public String getInsertSQL(String tableName) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ").append(tableName).append(" ");
		StringBuffer column = new StringBuffer();
		StringBuffer value = new StringBuffer();
		for(FD_ColumnData cd : list) {
			if(column.length() > 0) {
				column.append(",");
				value.append(",");
			}
			column.append(cd.getColumnName());
			switch(cd.getColumnType()) {
			case FD_Item_ID:
			case FD_Item_Long:
				value.append(cd.getColumnData());
				break;
			case FD_Item_String:
			case FD_Item_Text:
				if(cd.getColumnData() != null) {
					value.append(FD_SQ).append(cd.getColumnData().toString()).append(FD_SQ);
				} else {
					value.append(FD_SQ).append(FD_SQ);
				}
				break;
			}
		}
		sql.append("(").append(column).append(") VALUES (").append(value).append(")");
		return sql.toString();
	}

	/**
	 * 情報リストを返す[Returns a list of information]<br>
	 * @author officina-hide.net
	 * @since 2022/04/12 Ver. 1.00
	 * @return 情報リスト[Information list]
	 */
	public List<FD_ColumnData> getList() {
		return list;
	}

	/**
	 * 情報リスト設定[Information list setting]
	 * @author officina-hide.net
	 * @since 2022/04/19 Ver. 1.50
	 * @param entry 登録情報[Entry data]
	 */
	public void setData(FD_Collections entry) {
		for(FD_Collect co : entry.getList()) {
			FD_ColumnData cl = getItem(co.getName());
			cl.setColumnData(co.getValue());
			switch(cl.getColumnType()) {
			case FD_Item_ID:
			case FD_Item_Long:
				cl.setColumnData(Long.parseLong(co.getValue()));
				break;
			case FD_Item_String:
			case FD_Item_Text:
				cl.setColumnData(co.getValue());
				break;
			}
		}
	}

	/**
	 * @param columnnameFdFileId
	 * @return
	 */
	public Object getValue(String columnName) {
		FD_ColumnData cd = getItem(columnName);
		return cd.getColumnData();
	}

	public void setValue(String columnName, Object data) {
		FD_ColumnData cd = getItem(columnName);
		cd.setColumnData(data);
	}

	/**
	 * 一覧クリア[List clear]<br>
	 * @author officina-hide.net
	 * @since 2022/04/21 Ver. 1.00
	 */
	public void clear() {
		list = new ArrayList<>();
	}

}

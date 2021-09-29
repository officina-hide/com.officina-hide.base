package com.officina_hide.base.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.model.X_FD_Table;

/**
 * テーブル項目リスト[Table Item List]<br>
 * @author officina-hide.com
 * @version 1.00
 * @since 2021/05/05
 */
public class FD_Items implements I_FD_DB {

	/** テーブル項目配列 */
	private List<FD_Item> items = new ArrayList<>();
	/** テーブル名 */
	private String tableName;
	/** テーブル情報ID */
	private long tableId;
	/** テーブル情報 */
	private X_FD_Table FD_Table;

	/**
	 * 項目情報追加[Item information added]
	 * @author officina-hide.net
	 * @since 1.00 2021/05/22
	 * @param name 項目名[Item Name]
	 * @param data 項目値[Item Value]
	 * @param type 項目属性名[Item attribute name]
	 */
	public void add(String name, Object data, String type) {
		FD_Item item = new FD_Item();
		item.setName(name);
		item.setData(data);
		item.setType(type);
		items.add(item);
	}

	/**
	 * 項目情報追加[Item information added]
	 * @author officine-hide.net
	 * @since 1.00 2021/09/29
	 * @param name 項目名[Item Name]
	 * @param data 項目値[Item Value]
	 * @param typeItemID 属性項目情報ID[Attribute item information ID]
	 */
	public void add(String name, Object data, long typeItemID) {
		FD_Item item = new FD_Item();
		item.setName(name);
		item.setData(data);
		item.setFD_ItemType_ID(typeItemID);
		items.add(item);
	}

	/**
	 * リスト項目数取得[Get the number of list items]
	 * @author officina-hide.net
	 * @since 1.00 2021/05/22
	 * @return 項目数[number of list items]
	 */
	public int getLength() {
		return items.size();
	}

	/**
	 * 項目値取得[Item value acquisition]<br>
	 * 指定された項目番号の位置にある項目値を取得する。<br>
	 * Gets the item value at the position of the specified item number.<br>
	 * もし、対象の項目が見つからない時はnullを返す。<br>
	 * If the target item is not found, null is returned.<br>
	 * @param no 項目番号[item number]
	 * @return 項目値[Item Value]
	 */
	public Object getDataAt(int no) {
		if(no >= items.size()) {
			return null;
		}
		FD_Item item = items.get(no);
		return item.getData();
	}

	/**
	 * 文字列取得[Get string]<br>
	 * テーブル項目配列から指定された項目名の情報を文字列で取得する。<br>
	 * Get the information of the specified item name from the table item array as a character string.
	 * @param itemName 項目名[item name]
	 * @return 文字列情報[String Information]
	 */
	public String getStringData(String itemName) {
		String str = null;
		for(FD_Item item : items) {
			if(item.getName().equals(itemName)) {
				str = (String) item.getData();
				break;
			}
		}
		return str;
	}

	public List<FD_Item> getItems() {
		return items;
	}

	/**
	 * 項目情報セット[Item Information Set]<br>
	 * @author officine-hide.net
	 * @since 1/00 2021/07/15
	 * @param itemName 項目名[Item Name]
	 * @param value 項目値[Item Value]
	 */
	public void setValue(String itemName, Object value) {
		for(FD_Item item : getItems()) {
			if(item.getName().equals(itemName)) {
				item.setData(value);
			}
		}
	}

	/**
	 * 数値情報取得[Numerical information acquisition]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/07/15
	 * @param itemName 項目名[item name]
	 */
	public int getintData(String itemName) {
		int data = 0;
		for(FD_Item item : items) {
			if(item.getName().equals(itemName)) {
				data = (int) item.getData();
				break;
			}
		}
		return data;
	}

	/**
	 * 数値情報取得(Bigint対応)[Numerical information acquisition (Bigint compatible)]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/16
	 * @param itemName 項目名[item name]
	 * @return 数値情報(Bigint対応)[Numerical information (Bigint compatible)]
	 */
	public long getlongData(String itemName) {
		long data = 0;
		for(FD_Item item : items) {
			if(item.getName().equals(itemName)) {
				data = (long) item.getData();
				break;
			}
		}
		return data;
	}

	/**
	 * SQLの挿入で使用する項目一覧のSQL文字列[SQL string of item list used for inserting SQL]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/16
	 * @return SQL文字列[SQL string]
	 */
	public String getInsertItemStrings() {
		StringBuffer sql = new StringBuffer();
		for(FD_Item item : getItems()) {
			if(sql.length() > 0) {
				sql.append(", ");
			}
			sql.append(item.getName());
		}
		return sql.toString();
	}

	/**
	 * SQLの挿入で使用するPrepared Parameter用文字列生成
	 * [String generation for Prepared Parameter used for SQL insertion]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/16
	 * @return SQL文字列[SQL string]
	 */
	public String getPrepardStrings() {
		StringBuffer sql = new StringBuffer();
		for(int ix = 0; ix < getLength(); ix++) {
			if(ix > 0) {
				sql.append(",");
			}
			sql.append("?");
		}
		return sql.toString();
	}

	/**
	 * 日付情報取得[Get date information]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/16
	 * @param itemName 項目名[item name]
	 * @return 日付情報[Date information]
	 */
	public Calendar getDateData(String itemName) {
		Calendar cal = new GregorianCalendar(new Locale(Locale.JAPAN.getLanguage(), Locale.JAPAN.getCountry()));
		for(FD_Item item : items) {
			if(item.getName().equals(itemName)) {
				cal = (Calendar) item.getData();
				break;
			}
		}
		return cal;
	}

	/**
	 * SQLの更新で使用する項目一覧生成[Generate a list of items used for updating SQL]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/18
	 * @return SQL文字列[SQL string]
	 */
	public String getUpdateItemStrings() {
		StringBuffer sql = new StringBuffer();
		for(FD_Item item : getItems()) {
			if(sql.length() > 0) {
				sql.append(" ").append(",");
			}
			sql.append(item.getName()).append(" = ?");
		}
		return sql.toString();
	}

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public long getTableId() {
		return tableId;
	}
	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

	/**
	 * テーブル構築用SQL文字列生成[SQL string generation for table construction]
	 * @author officine-hide.net
	 * @since 1.00 2021/09/26
	 * @return SQL文字列[SQL Strings]
	 * @param env 環境情報[Environment information]
	 */
	public String getCreateTableString(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		for(int ix = 0; ix < items.size(); ix++) {
			FD_Item item = items.get(ix);
			if(ix > 0) {
				sql.append(",");
			}
			sql.append(item.getName()).append(" ");
			String type = item.getParamValue(env, "SQL_String");
			if(type.indexOf("@size@") > 0) {
				type = type.replaceAll("@size@", Integer.toString(item.getSize()));
			}
			sql.append(type);
			
		}
		return sql.toString();
	}

	public X_FD_Table getFD_Table(FD_EnvData env) {
		if(FD_Table == null) {
			if(getTableId() == 0) {
				return null;
			}
			FD_Table = new X_FD_Table(env, getTableId());
		}
		return FD_Table;
	}
}

package com.officina_hide.base.common;

import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.model.I_FD_DB;

/**
 * テーブル項目リスト[Table Item List]<br>
 * @author officina-hide.com
 * @version 1.00
 * @since 2021/05/05
 */
public class FD_Items implements I_FD_DB {

	/** テーブル項目配列 */
	private List<FD_Item> items = new ArrayList<>();

	/**
	 * 項目情報追加[Item information added]
	 * @author officina-hide.net
	 * @since 1.00 2021/05/22
	 * @param name 項目名[Item Name]
	 * @param data 項目値[Item Value]
	 */
	public void add(String name, Object data) {
		FD_Item item = new FD_Item();
		item.setName(name);
		item.setData(data);
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
	
//	/**
//	 * XML情報よりテーブル項目リストを作成する。<br>
//	 * Create a table item list from XML information.<br>
//	 * @author officina-hide.com
//	 * @since 1.00 2021/05/05
//	 * @param xmlData[XML Information]
//	 */
//	public FD_Items(Element xmlData) {
//		NodeList columns = xmlData.getElementsByTagName("column");
//		for(int ix = 0; ix < columns.getLength(); ix++) {
//			Element column = (Element) columns.item(ix);
//			FD_Item item = new FD_Item();
//			item.setName(column.getAttribute(COLUMN_NAME));
//			item.setType(column.getAttribute(COLUMN_TYPE));
//			if(column.hasAttribute(COLUMN_LENGTH)) {
//				item.setSize(Integer.parseInt(column.getAttribute(COLUMN_LENGTH)));
//			}
//			items.add(item);
//		}
//	}
}

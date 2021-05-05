package com.officina_hide.base.common;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
	 * XML情報よりテーブル項目リストを作成する。<br>
	 * Create a table item list from XML information.<br>
	 * @author officina-hide.com
	 * @since 1.00 2021/05/05
	 * @param xmlData[XML Information]
	 */
	public FD_Items(Element xmlData) {
		NodeList columns = xmlData.getElementsByTagName("column");
		for(int ix = 0; ix < columns.getLength(); ix++) {
			Element column = (Element) columns.item(ix);
			FD_Item item = new FD_Item();
			item.setName(column.getAttribute(COLUMN_NAME));
			item.setType(column.getAttribute(COLUMN_TYPE));
			if(column.hasAttribute(COLUMN_LENGTH)) {
				item.setSize(Integer.parseInt(column.getAttribute(COLUMN_LENGTH)));
			}
			items.add(item);
		}
	}
}

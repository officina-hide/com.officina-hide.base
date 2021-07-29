package com.officina_hide.base.model;

import org.w3c.dom.Element;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;
import com.officina_hide.base.common.FD_Items;

/**
 * テーブルI/Oクラス[Table I / O class]
 * @author officina-hide.net
 * @version 1.00
 * @since 2021/05/22
 */
public class X_FD_Table extends FD_DB implements I_FD_Table {
	/** 項目リスト[item list] */
	private FD_Items items = new FD_Items();
	/** テーブル項目 */
	private int FD_Table_ID;
	private String FD_Table_Name;
	private String FD_DisplayName;

	/**
	 * インスタンス時に、XMLデータからテーブル情報をセットする。<br>
	 * Set table information from XML data at instance time.<br>
	 * @author officina-hide.net
	 * @since 1.00 2021/05/22
	 * @param xmlData XMLデータ[XML data]
	 */
	public X_FD_Table(Element xmlData) {
		if(xmlData.getTagName().equals("Table")) {
//			items.add(I_FD_Table.COLUMNNAME_FD_Table_ID, xmlData.getAttribute(I_FD_Table.COLUMNNAME_FD_Table_ID));
//			items.add(I_FD_Table.COLUMNNAME_FD_Table_Name, xmlData.getAttribute(I_FD_Table.COLUMNNAME_FD_Table_Name));
//			items.add(I_FD_Table.COLUMNNAME_FD_DisplayName, xmlData.getAttribute(I_FD_Table.COLUMNNAME_FD_DisplayName));
//			items.add(I_FD_Table.COLUMNNAME_FD_Description, xmlData.getAttribute(I_FD_Table.COLUMNNAME_FD_Description));
		}
	}

	/**
	 * インスタンス時に、指定されたIDを持つ情報を取得する。<br>
	 * At the time instance, to get the information with the specified ID.<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/07/13
	 * @param env 環境情報[Environment Information]
	 * @param id 情報ID[Information ID]
	 */
	public X_FD_Table(FD_EnvData env, int id) {
		items.add(I_FD_Table.COLUMNNAME_FD_Table_ID, 0, FD_Item.ITEM_TYPE_Integer);
		items.add(I_FD_Table.COLUMNNAME_FD_Table_Name, null, FD_Item.ITEM_TYPE_String);
		load(env, Table_Name, id, items);
		setFD_Table_ID(items.getintData(COLUMNNAME_FD_Table_ID));
		setFD_Table_Name(items.getStringData(COLUMNNAME_FD_Table_Name));
		setFD_DisplayName(items.getStringData(COLUMNNAME_FD_DisplayName));
	}

	/**
	 * テーブル情報の項目リストを返す。<br>
	 * Returns a list of table information items.<br>
	 * @author officine-hide.com
	 * @since 1.00 2021/05/24
	 * @return テーブル項目リスト[Table item list]
	 */
	public FD_Items getItems() {
		return items;
	}

	/**
	 * テーブル情報テーブル生成[Table information table generation]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/06/03
	 * @param env 環境情報[Environment Information]
	 */
	public void createTable(FD_EnvData env) {
		//既構築テーブルの削除[Delete existing table]
		deleteTable(env, items.getStringData(COLUMNNAME_FD_Table_Name));
		//テーブル生成[Table generation]
		createTable(env, items.getStringData(COLUMNNAME_FD_Table_Name),
				items.getStringData(COLUMNNAME_FD_DisplayName), getItems());
	}

	public String getFD_Table_Name() {
		return FD_Table_Name;
	}

	public void setFD_Table_Name(String fD_Table_Name) {
		FD_Table_Name = fD_Table_Name;
	}

	public int getFD_Table_ID() {
		return FD_Table_ID;
	}

	public void setFD_Table_ID(int fD_Table_ID) {
		FD_Table_ID = fD_Table_ID;
	}

	public String getFD_DisplayName() {
		return FD_DisplayName;
	}

	public void setFD_DisplayName(String fD_DisplayName) {
		FD_DisplayName = fD_DisplayName;
	}

}

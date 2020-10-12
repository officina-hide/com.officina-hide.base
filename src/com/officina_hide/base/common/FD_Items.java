package com.officina_hide.base.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.model.I_DB;

/**
 * 項目リストクラス<br>
 * <p>項目情報をリストとして扱うときのクラス</p>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/10/12
 */
public class FD_Items {
	/** 項目リスト */
	protected List<FD_Item> itemList = new ArrayList<>();
	/** 日付フォーマッ */
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * 項目リストクリア<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/12
	 */
	public void clear() {
		itemList.clear();
	}

	/**
	 * 項目追加<br>
	 * @param itemName 項目名
	 * @param itemData 項目情報 
	 * @param itemType 項目種別
	 */
	public void add(String itemName, Object itemData, String itemType) {
		itemList.add(new FD_Item(itemName, itemData, itemType));
	}

	/**
	 * 項目情報登録<br>
	 * <p>指定された項目名を持つ項目情報に項目データをセットする。<br>
	 * もし、指定された項目が見つからない時は、falseを返す。</p>
	 * @param columnName 項目名
	 * @param columnData 項目データ
	 * @return true - セット完了、false - 項目名Not Found
	 */
	public boolean setData(String columnName, Object columnData) {
		boolean chk = false;
		for(FD_Item item : itemList) {
			if(item.getItemName().equals(columnName)) {
				item.setItemData(columnData);
				chk = true;
				break;
			}
		}
		return chk;
	}

	/**
	 * 項目名の一覧リスト取得<br>
	 * @return 項目名リスト
	 */
	public List<String> getNameList() {
		List<String> list = new ArrayList<>();
		for(FD_Item item : itemList) {
			list.add(item.getItemName());
		}
		return list;
	}

	/**
	 * 項目データ取得<br>
	 * @param columnName 項目名
	 * @return
	 */
	public Object getValueOfItem(String columnName) {
		return null;
	}

	/**
	 * テーブル構築用SQL文字列生成<br>
	 * @author officina-hide.com
	 * @since 1.00 2020/10/12
	 * @param columnName 項目名
	 * @return SQL文字列
	 */
	public String getSQLString(String columnName) {
		StringBuffer sql = new StringBuffer();
		for(FD_Item item : itemList) {
			if(item.getItemName().equals(columnName)) {
				if(item.getItemData() == null) {
					sql.append(item.getItemName()).append(" = null");
				} else {
					switch(item.getItemType()) {
					case I_DB.COLUMNTYPE_FD_Text:
					case I_DB.COLUMNTYPE_FD_Field_Text:
						sql.append(item.getItemName()).append(" = ")
							.append(I_DB.FD_SQ).append(item.getStringOfValue()).append(I_DB.FD_SQ);
						break;
					case I_DB.COLUMNTYPE_FD_Information_ID:
					case I_DB.COLUMNTYPE_FD_Number:
						sql.append(item.getItemName()).append(" = ").append(item.getIntOfValue());
						break;
					case I_DB.COLUMNTYPE_FD_Date:
						sql.append(item.getItemName()).append(" = ")
							.append(I_DB.FD_SQ).append(dateFormat.format(item.getDateOfValue().getTime())).append(I_DB.FD_SQ);
						break;
					}
				}
			}
		}
		
		return sql.toString();
	}

}

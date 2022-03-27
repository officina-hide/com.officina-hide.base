package com.officina_hide.base.common;

import java.util.ArrayList;
import java.util.List;

/**
 * データ保管クラス[Data collection class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成
 * @since 2022/03/23 Ver. 1.50
 */
public class FD_Collections {

	/** 収蔵リスト[Collection list] */
	List<FD_Collect> list = new ArrayList<>();
	
	/**
	 * コンストラクター[Constructor]
	 * @author officina-hide.net
	 * @since 2022/03/24 Ver. 1.50
	 * @param collectData 収蔵データ[Collection data]
	 */
	public FD_Collections(String collectData) {
		String[] sentence = collectData.split(",");
		for(String wk : sentence) {
			String[] dt = wk.split(":");
			FD_Collect collect = new FD_Collect();
			switch(dt.length) {
			case 1:
				collect.setName(dt[0]);
				break;
			case 2:
				collect.setName(dt[0]);
				collect.setValue(dt[1]);
				break;
			}
			list.add(collect);
		}
	}
	
	/**
	 * 収蔵リスト取得[Getting collection list]
	 * @author officina-hide.net
	 * @since 2022/03/26 Ver. 1.50 
	 * @return 収蔵リスト[Collection list]
	 */
	public List<FD_Collect> getList() {
		if(list == null) {
			list = new ArrayList<>();
		}
		return list;
	}
}

package com.officina_hide.base.common;

import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_Reference;
import com.officina_hide.base.model.FD_ReferenceGroup;
import com.officina_hide.base.model.FD_Table;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.ui.model.FX_View;

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
	 * @param env 環境情報[Environment information]
	 * @param collectData 収蔵データ[Collection data]
	 */
	public FD_Collections(FD_EnvData env, String collectData) {
		String[] sentence = collectData.split(",");
		FD_Reference ref = new FD_Reference(env);
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
			default:
				collect.setName(dt[0]);
				switch(dt[1]) {
				case "@getID":
					//引数[2]のテーブル名でテーブル情報IDを取得しValueにセットする。
					collect.setValue(getID(env, dt[2]));
					break;
				case "@getColumnID":
					FD_Column column = new FD_Column(env);
					collect.setValue(column.getColumnID(dt[2], dt[3]));
					break;
				case "@getRefGroupId":
					FD_ReferenceGroup refGroup = new FD_ReferenceGroup(env);
					collect.setValue(refGroup.getID(dt[2]));
					break;
				case "@getItemID":
					collect.setValue(ref.getID(I_FD_DB.FD_REFGROUP_COLUMN, dt[2]));
					break;
				case "@getFieldID":
					collect.setValue(ref.getID(I_FD_DB.FD_REFGROUP_FIELD, dt[2]));
					break;
				case "@getViewId":
					FX_View view = new FX_View(env);
					collect.setValue(view.getIDbyCode(dt[2]));
					break;
				default:
					System.out.println("Error!! FD_Collections method not found ["+dt[1]+"]");
				}
			}
			list.add(collect);
		}
	}
	
	public FD_Collections() {
	}

	/**
	 * テーブル情報ID取得[Get table information ID]<br>
	 * @author officina-hide.net
	 * @param env 
	 * @since 2022/04/22 Ver. 1.50
	 * @param tableName テーブル名[Table name]
	 * @return テーブル情報ID[Table information ID]
	 */
	private long getID(FD_EnvData env, String tableName) {
		long id = 0;
		FD_Table table = new FD_Table(env);
		id = table.getTableID(tableName);
		return id;
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

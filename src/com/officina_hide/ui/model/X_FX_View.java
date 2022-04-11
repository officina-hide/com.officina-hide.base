package com.officina_hide.ui.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_Collect;
import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_ColumnData;
import com.officina_hide.base.common.FD_ColumnDataCollection;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

/**
 * 画面情報I/Oクラス[View information I/O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/04/08 Ver. 1.00
 */
public class X_FX_View extends FD_DB implements I_FX_View {

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/04/08 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param dataList 登録情報リスト[Entry data list]
	 */
	public X_FX_View(FD_EnvData env, FD_Collections dataList) {
		createColumnList();
		setColumnData(dataList);
		long id = columnCollection.getTableId(Table_Name);
		if(id > 0) {
			//load機能の追加(登録済の情報を抽出しテーブル項目リストにセットする。
			//テーブル情報ID以外のすべての項目をセットする。
		}
	}

	/**
	 * テーブル項目一覧生成[Table item list generate]<br>
	 * @author officina-hide.net
	 * @since 2022/04/08 Ver. 1.00
	 */
	private void createColumnList() {
		if(columnCollection == null) {
			columnCollection = new FD_ColumnDataCollection();
		}
		columnCollection.add(COLUMNNAME_FX_View_ID, FD_Item_ID);
		columnCollection.add(COLUMNNAME_FX_View_Name, FD_Item_String);
		columnCollection.add(COLUMNNAME_FD_Name, FD_Item_String);
	}

	/**
	 * テーブル項目情報セット[Table item information setting]<br>
	 * @author officina-hide.net
	 * @since 2022/04/08 Ver. 1.00
	 * @param dataList 登録情報リスト[Entry data list]
	 */
	private void setColumnData(FD_Collections dataList) {
		for(FD_Collect data : dataList.getList()) {
			FD_ColumnData cd = columnCollection.getItem(data.getName());
			switch(cd.getColumnType()) {
			case FD_Item_ID:
				cd.setColumnData(Long.parseLong(data.getValue()));
				break;
			case FD_Item_String:
				cd.setColumnData(data.getValue());
				break;
			}
		}
	}

	/**
	 * 情報保存[Save data]<br>
	 * @author officina-hide.net
	 * @param tableName テーブル名[Table name]
	 * @param env 環境情報[Environment information]
	 * @since 2022/04/11 Ver. 1.50
	 */
	public void save(FD_EnvData env) {
		PreparedStatement pstmt = null;
		String sql = columnCollection.getInsertSQL(Table_Name);
		try {
			connection(env);
			pstmt = getConn().prepareStatement(sql);
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

}

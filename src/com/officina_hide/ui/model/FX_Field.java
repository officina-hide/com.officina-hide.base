package com.officina_hide.ui.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;

/**
 * 画面項目情報クラス[Screen item information class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 */
public class FX_Field extends FD_DB implements I_FX_Field {
	
	/** 環境情報[Environment information] */
	private  FD_EnvData env;

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/04/13 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public FX_Field(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 画面項目テーブル生成[Screen item table generation]<br>
	 * @author officina-hide.net
	 * @since 2022/04/13 Ver. 1.00
	 */
	public void createTable() {
		// テーブル情報登録[Table information registration]
		FD_Table table = new FD_Table(env);
		table.add(Entry_FD_Table);
		// テーブル項目情報登録[Table column information registration]
		FD_Column column = new FD_Column(env);
		column.add(Entry_FD_Column_FX_Field_ID);
		column.add(Entry_FD_Column_FX_Field_Code);
		column.add(Entry_FD_Column_FX_View_ID);
		column.add(Entry_FD_Column_FD_Name);
		column.add(Entry_FD_Column_FX_Field_Type_ID);
		//採番情報登録[Numbering information registration]
		FD_Numbering num = new FD_Numbering(env);
		num.add(Entry_FD_Number);
		
		//テーブル削除
		deleteTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name, Table_Disp_Name);
	}

	/**
	 * 情報登録[Data entry]
	 * @author officina-hide.net
	 * @since 2022/06/07 Ver. 1.00
	 * @param entryData 登録情報
	 */
	public void add(String entryData) {
		FD_Collections entry = new FD_Collections(env, entryData);
		X_FX_Field field = new X_FX_Field(env, entry);
		field.save(env);
	}

	/**
	 * 画面項目リスト取得[Getting screen item list]<br>
	 * @author officina-hide.net
	 * @since 2022/06/11 Ver 1.00
	 * @param env 環境情報[Environment information]
	 * @param viewId 画面情報ID[Screen information ID]
	 * @return 画面項目リスト[Screen item list]
	 */
	public List<X_FX_Field> getList(FD_EnvData env, long viewId) {
		List<X_FX_Field> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(SQL_GET_FIELD_LIST);
			pstmt.setLong(1, viewId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new X_FX_Field(env, rs.getLong(COLUMNNAME_FX_Field_ID)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
		return list;
	}

}

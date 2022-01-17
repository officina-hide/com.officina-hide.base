package com.officina_hide.base.common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.X_FD_Table;

/**
 * 共通情報抽出クラス[Common information extraction class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2022/01/17 Ver. 1.00
 */
public class FD_SelectTableData extends FD_DB {

	/**
	 * 指定されたテーブル情報IDを持つテーブルに登録されているすべての情報を抽出する。<br>
	 * Extract all the information registered in the table with the specified table information ID.<br>
	 * @author officina-hide.net
	 * @since 2022/01/17 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tableId テーブル情報ID
	 * @return 項目一覧リスト
	 */
	public List<FD_Items> getDataList(FD_EnvData env, long tableId) {
		List<FD_Items> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		X_FD_Table table = new X_FD_Table(env, tableId);
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(table.getFD_Table_Name()).append(" ");
			connection(env);
			pstmt = getConn().prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FD_Items items = createItems(env, tableId, rs);
				list.add(items);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
		return list;
	}

}

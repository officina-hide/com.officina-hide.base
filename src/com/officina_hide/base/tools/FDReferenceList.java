package com.officina_hide.base.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_ReferenceList;
import com.officina_hide.base.model.X_FD_ReferenceList;

/**
 * リファレンスリスト情報クラス<br>
 * @author officina-hide.com
 * @version 1.21
 * @since 2020/11/23
 */
public class FDReferenceList extends FD_DB implements I_FD_ReferenceList {

	/**
	 * リファレンスリスト情報テーブル構築<br>
	 * @author officina-hide.com
	 * @since 1.21 2020/11/23
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME + "テーブル構築開始");

		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, TABLE_ID, Table_Name, NAME, COMMENT);
		
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_ReferenceList_ID, NAME_FD_ReferenceList_ID, COMMENT_FD_ReferenceList_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 10, "Y", "Y");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Reference_ID, NAME_FD_Reference_ID, COMMENT_FD_Reference_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 20, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Sequence, NAME_FD_Sequence, COMMENT_FD_Sequence
				, COLUMNTYPE_ID_FD_Number, "0", 0, 30, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Code, NAME_FD_Code, COMMENT_FD_Code
				, COLUMNTYPE_ID_FD_Text, null, 100, 40, "N", "N");
		
		FD_DB_Utility dutil = new FD_DB_Utility();
		dutil.baseColumnEntry(env, TABLE_ID);
		
		//テーブル生成
		createDBTable(env, TABLE_ID);
		
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.addData(env, TABLE_ID, TABLE_ID, 0, 1000001);
	
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME + "テーブル構築開始");
}

	/**
	 * リファレンスリスト情報登録<br>
	 * @author officine-hide.com
	 * @since 2020/11/26
	 * @param env 環境情報
	 * @param referenceId リファレンス情報ID
	 * @param seq リスト並び順
	 * @param code リスト文字列
	 */
	public void addData(FD_EnvData env, int referenceId, int seq, String code) {
		X_FD_ReferenceList refList = new X_FD_ReferenceList(env);
		refList.setValueByName(env, COLUMNNAME_FD_ReferenceList_ID, 0);
		refList.setValueByName(env, COLUMNNAME_FD_Reference_ID, referenceId);
		refList.setValueByName(env, COLUMNNAME_FD_Sequence, seq);
		refList.setValueByName(env, COLUMNNAME_FD_Code, code);
		refList.save(env);
	}

	/**
	 * リファレンスリスト情報ID取得<br>
	 * @author officine-hide.com
	 * @since 1.21 2020/11/26
	 * @param env 環境情報
	 * @param referenceId リファレンス情報ID
	 * @param code リファレンスリストコード
	 * @return リファレンスリスト情報ID
	 */
	public int getReferenceListId(FD_EnvData env, int referenceId, String code) {
		int id = 0;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT ").append(COLUMNNAME_FD_ReferenceList_ID).append(" ")
				.append("FROM ").append(Table_Name).append(" ");
			sql.append("WHERE ").append(COLUMNNAME_FD_Reference_ID).append(" = ").append(referenceId).append(" ");
			sql.append("AND ").append(COLUMNNAME_FD_Code).append(" = ").append(FD_SQ).append(code).append(FD_SQ).append(" ");
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				id = rs.getInt(COLUMNNAME_FD_ReferenceList_ID);
			} else {
				//エラー表示
				addLog(env, I_FD_Log.LOGTYPE_ERROR_ID, "リファレンスリストに対象のコードが有りません。["+code+"]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}
		return id;
	}

}

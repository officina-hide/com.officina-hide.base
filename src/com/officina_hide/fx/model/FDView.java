package com.officina_hide.fx.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.common.FDSQLWhere;
import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FDLog;
import com.officina_hide.base.model.FDNumbering;
import com.officina_hide.base.model.FDTable;
import com.officina_hide.base.model.FDTableColumn;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Log;

/**
 * Fx画面情報クラス[Fx screen information class]<br>
 * @author officine-hide.com
 * @version 1.31
 * @since 2021/02/04
 */
public class FDView extends FD_DB implements I_FD_View {

	/**
	 * Fx画面テーブル構築[Fx screen table construction]<br>
	 * @author officine-hide.com
	 * @since 1.31 2021/02/04
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		FDLog log = new FDLog();
		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築開始");
		
		//テーブル情報登録
		FDTable table = new FDTable();
		int tableId = table.addData(env, TABLE_ID, Table_Name, NAME, COMMENT);
		
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_View_ID, NAME_FD_View_ID, COMMENT_FD_View_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 10, "Y", "Y");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_View_Name, NAME_View_Name, COMMENT_View_Name
				, COLUMNTYPE_ID_FD_Field_Text, null, 100, 20, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_Initial_Width, NAME_Initial_Width, COMMENT_Initial_Width
				, COLUMNTYPE_ID_FD_Number, null, 0, 30, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_Initial_Height, NAME_Initial_Height, COMMENT_Initial_Height
				, COLUMNTYPE_ID_FD_Number, null, 0, 40, "N", "N");
		column.addBaseTableColumnData(env, tableId);
		
		//テーブル生成
		FD_DB_Utility dbUtil = new FD_DB_Utility();
		dbUtil.createDBTable(env, TABLE_ID);
		
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.addData(env, TABLE_ID, TABLE_ID, 0, 1000001);

		log.addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築完了");
	}

	/**
	 * 情報登録[Entry of information.]
	 * @author officina-hide.com
	 * @since 1.31 2021/02/08
	 * @param env 環境情報
	 * @param viewId Fx画面情報ID
	 * @param viewName Fx画面情報名
	 * @param j 
	 * @param  
	 * @return 
	 */
	public int addData(FD_EnvData env, int viewId, String viewName, int width, int height) {
		X_FD_View view = new X_FD_View(env);
		view.setValueByName(env, COLUMNNAME_FD_View_ID, viewId);
		view.setValueByName(env, COLUMNNAME_View_Name, viewName);
		view.setValueByName(env, COLUMNNAME_Initial_Width, width);
		view.setValueByName(env, COLUMNNAME_Initial_Height, height);
		view.save(env);
		
		return view.getintOfValue(COLUMNNAME_FD_View_ID);
	}

	/**
	 * 情報リスト生成[Information list generation.]<br>
	 * 指定された条件により抽出し、情報リストを作成する。<br>
	 * Extract according to the specified conditions and create an information list.
	 * @param env 環境情報
	 * @param where 条件
	 * @return 情報リスト
	 */
	public List<X_FD_View> getDataList(FD_EnvData env, FDSQLWhere where) {
		List<X_FD_View> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer(); 
		try {
			sql.append("SELECT * FROM ").append(I_FD_View.Table_Name).append(" ");
			sql.append("WHERE ").append(where.toString()).append(" ");
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				X_FD_View view = new X_FD_View(env, rs.getInt(I_FD_View.COLUMNNAME_FD_View_ID));
				list.add(view);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBclose(stmt, rs);
		}
		return list;
	}

}

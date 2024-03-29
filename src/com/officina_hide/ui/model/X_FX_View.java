package com.officina_hide.ui.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_ColumnData;
import com.officina_hide.base.common.FD_ColumnDataCollection;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.FD_DB;

/**
 * 画面情報I/Oクラス[View information I/O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/04/08 Ver. 1.00
 */
public class X_FX_View extends FD_DB implements I_FX_View {

	/** 項目 : 画面情報ID */
	private long FX_View_ID;
	/** 項目 : 画面コード */
	private String FX_View_Code;
	
	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/04/08 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param entry 登録情報リスト[Entry data list]
	 */
	public X_FX_View(FD_EnvData env, FD_Collections entry) {
		createColumnList(env, Table_Name);
		columnCollection.setData(entry);
	}

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/04/12 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param where 条件句[Conditional clause]
	 */
	public X_FX_View(FD_EnvData env, FD_WhereData where) {
		createColumnList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// TODO FD_DBでの汎用化予定
		try {
			connection(env);
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * FROM ").append(Table_Name).append(" ");
			sql.append("WHERE ").append(where.toString()).append(" ");
			pstmt = getConn().prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				for(FD_ColumnData cd :  columnCollection.getList()) {
					switch(cd.getColumnType()) {
					case FD_Item_String:
						cd.setColumnData(rs.getString(cd.getColumnName()));
						break;
					case FD_Item_ID:
						cd.setColumnData(rs.getLong(cd.getColumnName()));
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
	}

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/06/06 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param viewId 画面情報ID
	 */
	public X_FX_View(FD_EnvData env, long viewId) {
		createColumnList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(SQL_GetData);
			pstmt.setLong(1, viewId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				for(FD_ColumnData cd  : columnCollection.getList()) {
					switch(cd.getColumnType()) {
					case FD_Item_ID:
						cd.setColumnData(rs.getLong(cd.getColumnName()));
						break;
					case FD_Item_String:
						cd.setColumnData(rs.getString(cd.getColumnName()));
						break;
					default:
						System.out.println("Error!! Data Type not found(X_FX_View) ["+cd.getColumnType()+"]");
					}
				}
			} else {
				System.out.println("Error!! View Data not found! ["+viewId+"]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
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
		columnCollection.add(COLUMNNAME_FX_View_Code, FD_Item_String);
		columnCollection.add(COLUMNNAME_FD_Name, FD_Item_String);
	}

	/**
	 * 情報保存[Save data]<br>
	 * @author officina-hide.net
	 * @param tableName テーブル名[Table name]
	 * @param env 環境情報[Environment information]
	 * @since 2022/04/11 Ver. 1.50
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

	/**
	 * 文字列情報取得[Getting string information]<br>
	 * @author officina-hide.net
	 * @since 2022/04/12 Ver. 1.00
	 * @param columnName テーブル項目名[Table column name]
	 * @return 文字列情報[String information]
	 * @deprecated
	 */
	public String getStringValue(String columnName) {
		FD_ColumnData cd = columnCollection.getItem(columnName);
		return (String) cd.getColumnData();
	}

	/**
	 * @param columnName
	 * @return
	 * @deprecated
	 */
	public long getLongValue(String columnName) {
		FD_ColumnData cd = columnCollection.getItem(columnName);
		return (long) cd.getColumnData();
	}
	
	
	public long getFX_View_ID() {
		FX_View_ID = (long) columnCollection.getValue(COLUMNNAME_FX_View_ID);
		return FX_View_ID;
	}
	public void setFX_View_ID(long viewId) {
		columnCollection.setValue(COLUMNNAME_FX_View_ID, viewId);
	}
	public String getFX_View_Code() {
		FX_View_Code = (String) columnCollection.getValue(COLUMNNAME_FX_View_Code);
		return FX_View_Code;
	}
	public void setFX_View_Code(String viewCode) {
		columnCollection.setValue(COLUMNNAME_FX_View_Code, viewCode);
	}

}

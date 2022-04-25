package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;

/**
 * テーブル項目情報クラス[Table column information class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成[New create]
 * @since 2022/04/11 Ver. 1.50
 */
public class FD_Column extends FD_DB implements I_FD_Column {
	
	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.com
	 * @since 2022/04/11 Ver. 1.50.
	 * @param env 環境情報[Environment information]
	 */
	public FD_Column(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * テーブル項目情報テーブル生成[Table column information table generation]<br>
	 * @author officina-hide.net
	 * @since 2022/04/11 Ver. 1.50
	 */
	public void createTable() {
		PreparedStatement pstmt = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(Table_Drop_SQL);
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = getConn().prepareStatement(Table_Create_SQL);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

	/**
	 * テーブル項目情報登録[Table column information entry]<br>
	 * @author officina-hide.net
	 * @since 2022/04/22 Ver. 1.50
	 * @param entryData 登録情報[Entry data]
	 */
	public void add(String entryData) {
		FD_Collections entry = new FD_Collections(env, entryData);
		X_FD_Column column = new X_FD_Column(entry);
		column.save(env);
	}

	/**
	 * テーブル項目情報ID取得[Getting table column information ID]
	 * @param tableName テーブル名[Table name]
	 * @param columnName テーブル項目名[Table column name]
	 * @return テーブル項目情報ID[Table column information ID]
	 */
	public long getColumnID(String tableName, String columnName) {
		long id = 0;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(SQL_GET_COLUMN_ID);
			pstmt.setString(1, tableName);
			pstmt.setString(2, columnName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				id = rs.getLong(COLUMNNAME_FD_Column_ID);
			} else {
				System.out.println("Error Column not found!!["+tableName+":"+columnName+"]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
		return id;
	}

	private final String SQL_GET_COLUMN_ID =
			"SELECT "+COLUMNNAME_FD_Column_ID+" FROM "+Table_Name+" c "
			+ "LEFT JOIN "+I_FD_Table.Table_Name+" t ON t."+I_FD_Table.COLUMNNAME_FD_Table_ID
				+" = c."+I_FD_Column.COLUMNNAME_FD_Table_ID+" "
			+ "WHERE t."+I_FD_Table.COLUMNNAME_FD_Table_Code+" = ? "
			+ "AND c."+COLUMNNAME_FD_Column_Code+" = ? ";
			
}

package com.officina_hide.base.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.officina_hide.base.common.FD_ColumnDataCollection;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;

/**
 * DB処理クラス[DB processing class]
 * @author officina-hide.net
 * @version 1.50 新規作成
 * @since 2022/03/19 Ver. 1.50
 */
public class FD_DB implements I_FD_DB {

	/** テーブル項目リスト */
	public FD_ColumnDataCollection columnCollection = new FD_ColumnDataCollection();
	
	/** データベース接続情報[Database connection information] */
	private static Connection conn = null;
	
	/**
	 * データベース接続[Database connection]
	 * @author officina-hide.net
	 * @since 2022/03/21 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 */
	public void connection(FD_EnvData env) {
		if(conn == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				StringBuffer url = new StringBuffer();
				url.append("jdbc:mysql:")
					.append(env.getDBPath()).append(":").append(env.getDBPort()).append("/").append(env.getDBName());
				conn = DriverManager.getConnection(url.toString(), env.getDBUser(), env.getDBPass());
				System.out.println(new Date() + " : "+"Database Connected.");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 接続情報クローズ[Connection information closed]<br>
	 * @author officina-hide.net
	 * @since 2021/08/24 Ver. 1.40
	 * @param pstmt ステートメント情報[Statement Information]
	 * @param rs 検索結果[search results]
	 */
	public void DBClose(PreparedStatement pstmt, ResultSet rs) {
		try {
			if(pstmt != null) {
				pstmt.close();
			}
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConn() {
		return conn;
	}

	/**
	 * 情報IDリスト生成[Information list generation]<br>
	 * @author officina-hide.net
	 * @since 2022/04/15 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name]
	 * @param where 条件句[Where clause]
	 * @return 情報IDリスト[Information list]
	 */
	public List<Integer> getAllId(FD_EnvData env, long tableName, FD_WhereData where) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ").append(tableName).append("_ID FROM ").append(tableName).append(" ");
		sql.append("WHERE ").append(where.toString()).append(" ");
		System.out.println(sql.toString());
		return null;
	}

	/**
	 * テーブル削除[Delete table]<br>
	 * @author officina-hide.net
	 * @since 2022/05/09 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name]
	 */
	public void deleteTable(FD_EnvData env, String tableName) {
		PreparedStatement pstmt = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(SQL_Table_Drop.replace("?", tableName));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}
	
	/**
	 * テーブル生成[Generate table]<br>
	 * @author officina-hide.net
	 * @since 2022/05/09 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name]
	 * @param tableDispName テーブル表示名[Table comment name]
	 */
	public void createTable(FD_EnvData env, String tableName, String tableDispName) {
		StringBuffer sql = new StringBuffer("CREATE TABLE IF NOT EXISTS ").append(tableName)
				.append(" ( @columnEntry@ ) ")
				.append("ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="+ FD_SQ + tableDispName + FD_SQ);
		
		//ここからは汎用化予定
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer columndata = new StringBuffer();
		try {
			connection(env);
			pstmt = getConn().prepareStatement(SQL_Get_ColumnData);
			pstmt.setString(1, tableName);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(columndata.length() > 0) {
					columndata.append(", ");
				}
				columndata.append(rs.getString(I_FD_Column.COLUMNNAME_FD_Column_Code));
				String idType = rs.getString(I_FD_Reference.COLUMNNAME_FD_Reference_Code);
				switch(idType) {
				case FD_Item_ID:
					columndata.append(ID_KEY_TYPE);
					break;
				case FD_Item_String:
					columndata.append(UNSIGNED_INT);
					break;
				default:
					System.out.println("Error!! Column Data not Found!! ["+idType+"]");
				}
				if(rs.getString(I_FD_Column.COLUMNNAME_FD_Name) != null) {
					columndata.append(COMMENT).append(FD_SQ)
					.append(rs.getString(I_FD_Column.COLUMNNAME_FD_Name)).append(FD_SQ);
				}
			}
			pstmt.close();
			pstmt = getConn().prepareStatement(sql.toString().replaceAll("@columnEntry@", columndata.toString()));
			System.out.println(pstmt.toString());
			pstmt.executeUpdate();
			System.out.println(tableDispName+"テーブル構築完了 : " + new Date());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
	}
	
	/** テーブル項目情報抽出用SQL文 */
	private final String SQL_Get_ColumnData =
			"SELECT * FROM " + I_FD_Column.Table_Name + " c "
			+ "LEFT JOIN " + I_FD_Table.Table_Name + " t ON t." + I_FD_Table.COLUMNNAME_FD_Table_ID + " = "
				+ "c." + I_FD_Column.COLUMNNAME_FD_Table_ID + " "
			+ "LEFT JOIN " + I_FD_Reference.Table_Name + " r ON r." + I_FD_Reference.COLUMNNAME_FD_Reference_ID + " = "
				+ "c." + I_FD_Column.COLUMNNAME_FD_ColumnType_ID + " "
			+ "WHERE t." + I_FD_Table.COLUMNNAME_FD_Table_Code + " = ? ";
}

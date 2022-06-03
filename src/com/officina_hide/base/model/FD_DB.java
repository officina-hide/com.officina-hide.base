package com.officina_hide.base.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.officina_hide.base.common.FD_ColumnData;
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
	/** 共通項目 : 名前 */
	private String FD_Name;
	
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
				int size = rs.getInt(I_FD_Column.COLUMNNAME_FD_Column_Size);
				String isUnique = rs.getString(I_FD_Column.COLUMNNAME_FD_IS_Unoque);
				//項目設定
				switch(idType) {
				case FD_Item_ID:
					columndata.append(ID_KEY_TYPE);
					break;
				case FD_Item_String:
					columndata.append(VARCHAR.replaceAll("n", Integer.toString(size)));
					break;
				case FD_Item_Date:
					columndata.append(DATETIME);
					break;
				default:
					System.out.println("Error!! Column Data not Found!! ["+idType+"]");
				}
				//重複制約判定
				if(isUnique.equals(FD_YES)) {
					columndata.append(UNIQUE);
				}
				//項目コメント
				if(rs.getString(I_FD_Column.COLUMNNAME_FD_Name) != null) {
					columndata.append(COMMENT).append(FD_SQ)
					.append(rs.getString(I_FD_Column.COLUMNNAME_FD_Name)).append(FD_SQ);
				}
			}
			pstmt.close();
			pstmt = getConn().prepareStatement(sql.toString().replaceAll("@columnEntry@", columndata.toString()));
			pstmt.executeUpdate();
			System.out.println(tableDispName+"テーブル構築完了 : " + new Date());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
	}
	
	/**
	 * 情報取得[Getting data]<br>
	 * @author officina-hide.net
	 * @since 2022/05/25 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param id 情報ID[Information ID]
	 * @param tableName テーブル名[Table name]
	 * @return
	 */
	public boolean load(FD_EnvData env, long id, String tableName) {
		boolean chk = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(tableName).append(" ");
			sql.append("WHERE ").append(tableName).append("_ID = ? ");
			connection(env);
			pstmt = getConn().prepareStatement(sql.toString());
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				for(FD_ColumnData cd : columnCollection.getList()) {
					switch(cd.getColumnType()) {
					case FD_Item_ID:
						cd.setColumnData(rs.getLong(cd.getColumnName()));
						break;
					case FD_Item_String:
						cd.setColumnData(rs.getString(cd.getColumnName()));
						break;
					case FD_Item_Date:
						cd.setColumnData(rs.getDate(cd.getColumnName()));
						break;
					default:
						System.out.println("Error Type Not Exception ["+cd.getColumnType()+"]");
					}
				}
			} else {
				System.out.println("Error!! Data Not Found ["+id+"]");
			}
			//取得成功
			chk = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
		
		return  chk;
	}

	/**
	 * 情報登録[Data save]<br>
	 * @author officina-hide.net
	 * @since 2022/05/26 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name]
	 * @return success - true, error - false;
	 */
	public boolean save(FD_EnvData env, String tableName) {
		boolean chk = false;
		long id = (long) columnCollection.getItem(tableName+"_ID").getColumnData();
		if(id == 0) {
			//新規採番
			FD_Numbering num = new FD_Numbering(env);
			FD_Table table = new FD_Table(env);
			id = num.getNewId(table.getTableID(tableName));
			columnCollection.getItem(tableName+"_ID").setColumnData(id);
		}
		//新規登録のみ
		PreparedStatement pstmt = null;
		try {
			connection(env);
			pstmt  = getConn().prepareStatement(columnCollection.getInsertSQL(tableName));
			int rs = pstmt.executeUpdate();
			if(rs == 0) {
				System.out.println("Error!! Save Error ["+tableName+"]");
			} else {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
		System.out.println(tableName+"登録完了["+id+"]");
		
		return chk;
	}

	/**
	 * テーブル項目リスト初期化[Table item list initialization]<br>
	 * @author officina-hide.net
	 * @since 2022/05/23 Ver. 1.00
	 * @param tableName テーブル名[Table name]
	 * @param env 環境情報[Environment information]
	 */
	public void createColumnList(FD_EnvData env, String tableName) {
		columnCollection.clear();
		//テーブル項目リスト取得
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(SQL_Get_ColumnData);
			pstmt.setString(1, tableName);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				long id = rs.getLong(I_FD_Column.COLUMNNAME_FD_Column_ID);
				X_FD_Column column = new X_FD_Column(env, id);
				if(column.getFD_Column_DefaultValue() != null && column.getFD_Column_DefaultValue().length() > 0) {
					switch(column.getFD_ColumnType(env).getFD_Reference_Code()) {
					case FD_Item_ID:
						columnCollection.add(column.getFD_Column_Code(), column.getFD_ColumnType(env).getFD_Reference_Code(),
								Long.parseLong(column.getFD_Column_DefaultValue()));
						break;
					case FD_Item_String:
						columnCollection.add(column.getFD_Column_Code(), column.getFD_ColumnType(env).getFD_Reference_Code(),
								column.getFD_Column_DefaultValue());
						break;
					default:
						System.out.println("Error Default Value Type Name ["+column.getFD_ColumnType(env).getFD_Reference_Code()+"]");
					}
				} else {
					columnCollection.add(column.getFD_Column_Code(), column.getFD_ColumnType(env).getFD_Reference_Code());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
	}
	
	/** 項目Setter,Getter */
	public String getFD_Name() {
		FD_Name = (String) columnCollection.getValue(COLUMNNAME_FD_Name);
		return FD_Name;
	}
	public void setFD_Name(String fdName) {
		columnCollection.setValue(COLUMNNAME_FD_Name, fdName);
	}
}

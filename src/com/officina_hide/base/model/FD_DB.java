package com.officina_hide.base.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;
import com.officina_hide.base.common.FD_Items;

/**
 * DB操作クラス[DB operation class]<br>
 * @author officina-hide.net
 * @version 1.00
 * @since 2021/05/22
 */
public class FD_DB implements I_FD_DB {
	/** 項目リスト */
	protected FD_Items items;

	/** 項目 : グループ情報ID */
	private long FD_Group_ID;
	/** 項目 : 登録日時 */
	private Calendar FD_Created;
	/** 項目 : 登録者情報ID */
	private long FD_CreatedBy;
	/** 項目 : 更新日時 */
	private Calendar FD_updated;
	/** 項目 : 更新者情報ID */
	private long FD_UpdatedBy;
	/** 項目 : 表示名 */
	private String FD_Name;
	/** 項目 : 説明 */
	private String FD_Description;

	/** 
	 * データベース接続情報[Database connection information]
	 */
	private static Connection conn;

	/**
	 * データベース接続[Database connection]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/05/25
	 * @param env 環境情報[Evvironment Information]
	 */
	public void connection(FD_EnvData env) {
		if(conn == null) {
			try {
				// TODO 接続に関する情報を環境情報から取得する(2021/05/25)
				Class.forName("com.mysql.cj.jdbc.Driver");
//				StringBuffer url  = new StringBuffer().append("jdbc:mysql://www.officina-hide.com:3306/FDBASE");
				StringBuffer url  = new StringBuffer().append("jdbc:mysql://www.officina-hide.net:3306/FDBASE");
				conn = DriverManager.getConnection(url.toString(), "fdadmin", "fdadminqAz*01");
				System.out.println(new Date() + " : "+"Database Connected.");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 接続情報クローズ[Connection information closed]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/24
	 * @param stmt ステートメント情報[Statement Information]
	 * @param rs 検索結果[search results]
	 */
	public void DBClose(Statement stmt, ResultSet rs) {
		try {
			if(stmt != null) {
				stmt.close();
			}
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * テーブル削除[Drop Table]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/06/03
	 * @param env 環境情報[Environment Information]
	 * @param tableName テーブル名[Table Name]
	 */
	public void deleteTable(FD_EnvData env, String tableName) {
		try {
			connection(env);
			StringBuffer sql = new StringBuffer();
			sql.append("DROP TABLE IF EXISTS ").append(tableName);
			PreparedStatement ptmt = conn.prepareStatement(sql.toString());
			System.out.println(sql.toString());
			ptmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * テーブル生成[Create Table]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/06/08
	 * @param env 環境情報[Environment Information]
	 * @param tableName テーブル名[Table Name]
	 * @param comment 
	 * @param items テーブル項目リスト[Table Item List]
	 */
	public void createTable(FD_EnvData env, String tableName, String comment, FD_Items items) {
		StringBuffer sql  = new StringBuffer();
		StringBuffer column = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");
		for(FD_Item item : items.getItems()) {
			if(column.length() > 0) {
				column.append(",");
			}
			column.append(item.getName()).append(" ");
			//項目種別
			switch(item.getType()) {
			case Item_Value_Type_ID:
				sql.append("int(10) unsigned NOT NULL").append(" ");
				break;
			case Item_Value_Type_String:
				sql.append("varchar(").append(item.getSize()).append(")").append(" ");
				break;
			case Item_Value_Type_Date:
				sql.append("datetime").append(" ");
				break;
			case Item_Value_Type_Text:
				sql.append("text").append(" ");
			}
			//コメント
			sql.append("COMMENT ").append(FD_SQ).append("").append(FD_SQ).append(" ");
		}
		sql.append(column.toString()).append(")");
		sql.append("ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=")
			.append(FD_SQ).append(comment).append(FD_SQ).append(";");
		
		System.out.println(sql.toString());
	}

	/**
	 * 情報ID一覧取得[Get information ID list]<br>
	 * @author officina-hide.net
	 * @since 1.00 2021/07/12
	 * @param tableName テーブル名[Table name]<br>
	 * @param where 条件句[SQL Where clause]<br>
	 * @param env 環境情報[Environment Information]
	 * @return 情報ID一覧[information ID list]
	 */
	public List<Integer> getAllId(String tableName, String where, FD_EnvData env) {
		List<Integer> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			sql.append("SELECT ").append(tableName).append("_ID FROM ").append(tableName).append(" ");
			sql.append("WHERE ").append(where).append(" ");
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				list.add(rs.getInt(tableName+"_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 情報抽出[Load Information]<br>
	 * 指定された情報IDを持つ情報を抽出し項目リストに格納する。<br>
	 * Extracts the information with the specified information ID and stores it in the item list.
	 * @param env 環境情報[Environment Information]
	 * @param tableName テーブル名[Table Name]
	 * @param id 情報ID[Information ID]
	 * @param items 項目リスト[List of Items]
	 */
	public void load(FD_EnvData env, String tableName, int id, FD_Items items) {
		StringBuffer sql = new StringBuffer();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			sql.append("SELECT * FROM ").append(tableName).append(" ");
			sql.append("WHERE ").append(tableName).append("_ID = ").append(id).append(" ");
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				for(FD_Item item : items.getItems()) {
					switch(item.getType()) {
					case Item_Value_Type_ID:
						items.setValue(item.getName(), rs.getInt(item.getName()));
						break;
					case Item_Value_Type_String:
						items.setValue(item.getName(), rs.getString(item.getName()));
						break;
					}
				}
			} else {
				System.out.println("Table Record Not Found! : "+id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 項目一覧に共通項目の情報を追加する。<br>
	 * Add common item information to the item list.<br>
	 * @param items 項目一覧[Item list]
	 */
	public void baseItemSet(FD_Items items) {
		items.add(COLUMNNAME_FD_Group_ID, null, Item_Value_Type_ID);
		items.add(COLUMNNAME_FD_Created, null, Item_Value_Type_Date);
		items.add(COLUMNNAME_FD_CreatedBy, null, Item_Value_Type_ID);
		items.add(COLUMNNAME_FD_Updated, null, Item_Value_Type_Date);
		items.add(COLUMNNAME_FD_UpdatedBy, null, Item_Value_Type_ID);
	}

	/**
	 * Prepared Statmentに共通項目をセットする。<br>
	 * Set common items in Prepared Statement.
	 * @param pstmt Prepared Statment
	 * @param items 項目一覧[Item list]
	 * @param startPos セット開始位置[Set start position]
	 */
	public void setCommonPrepared(PreparedStatement pstmt, FD_Items items, int startPos) {
		try {
			pstmt.setLong(startPos, items.getlongData(COLUMNNAME_FD_Group_ID));
			pstmt.setTimestamp(startPos + 1, new Timestamp(items.getDateData(COLUMNNAME_FD_Created).getTimeInMillis()));
			pstmt.setLong(startPos + 2, items.getlongData(COLUMNNAME_FD_CreatedBy));
			pstmt.setTimestamp(startPos + 3, new Timestamp(items.getDateData(COLUMNNAME_FD_Updated).getTimeInMillis()));
			pstmt.setLong(startPos + 4, items.getlongData(COLUMNNAME_FD_UpdatedBy));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		return conn;
	}

	/**
	 * 指定されたテーブルに指定された情報IDが存在するかチェックする。<br>
	 * [Checks if the specified information ID exists in the specified table.]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/18
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name]
	 * @param id 情報ID[Information ID]
	 * @return 情報が存在する時は true 無い時は false を返す。<br>
	 *              Returns true if the information exists, false if it does not exist.<br>
	 */
	public boolean isRecoedExits(FD_EnvData env, String tableName, long id) {
		boolean chk = false;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(tableName).append(" ");
			sql.append("WHERE ").append(tableName).append("_ID").append(" = ").append(id).append(" ");
			connection(env);
			stmt = getConn().createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(stmt, rs);
		}
		return chk;
	}

	/**
	 * 共通項目の辞書情報登録[Registration of dictionary information for common items]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/18
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name]
	 */
	public void addData(FD_EnvData env, String tableName) {
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FD_Group_ID, NAME_FD_Group_ID, COMMENT_FD_Group_ID);
		dd.add(env, 0, COLUMNNAME_FD_Created, NAME_FD_Created, COMMENT_FD_Created);
		dd.add(env, 0, COLUMNNAME_FD_CreatedBy, NAME_FD_CreatedBy, COMMENT_FD_CreatedBy);
		dd.add(env, 0, COLUMNNAME_FD_Updated, NAME_FD_Updated, COMMENT_FD_Updated);
		dd.add(env, 0, COLUMNNAME_FD_UpdatedBy, NAME_FD_UpdatedBy, COMMENT_FD_UpdatedBy);
	}
	
	public long getFD_Group_ID() {
		FD_Group_ID = items.getlongData(COLUMNNAME_FD_Group_ID);
		return FD_Group_ID;
	}
	public void setFD_Group_ID(long groupID) {
		items.setValue(COLUMNNAME_FD_Group_ID, groupID);
	}
	public Calendar getFD_Created() {
		FD_Created = items.getDateData(COLUMNNAME_FD_Created);
		return FD_Created;
	}
	public void setFD_Created(Calendar created) {
		items.setValue(COLUMNNAME_FD_Created, created);
	}
	public long getFD_CreatedBy() {
		FD_CreatedBy = items.getlongData(COLUMNNAME_FD_CreatedBy);
		return FD_CreatedBy;
	}
	public void setFD_CreatedBy(long createdBy) {
		items.setValue(COLUMNNAME_FD_CreatedBy, createdBy);
	}
	public Calendar getFD_updated() {
		FD_updated = items.getDateData(COLUMNNAME_FD_Updated);
		return FD_updated;
	}
	public void setFD_updated(Calendar updated) {
		items.setValue(COLUMNNAME_FD_Updated, updated);
	}
	public long getFD_UpdatedBy() {
		FD_UpdatedBy = items.getlongData(COLUMNNAME_FD_UpdatedBy);
		return FD_UpdatedBy;
	}
	public void setFD_UpdatedBy(long updatedBy) {
		items.setValue(COLUMNNAME_FD_UpdatedBy, updatedBy);
	}
	public String getFD_Name() {
		FD_Name = items.getStringData(COLUMNNAME_FD_Name);
		return FD_Name;
	}
	public void setFD_Name(String name) {
		items.setValue(COLUMNNAME_FD_Name, name);
	}
	public String getFD_Description() {
		FD_Description = items.getStringData(COLUMNNAME_FD_Description);
		return FD_Description;
	}
	public void setFD_Description(String description) {
		items.setValue(COLUMNNAME_FD_Description, description);
	}

}

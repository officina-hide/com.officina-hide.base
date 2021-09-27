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
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;
import com.officina_hide.base.common.FD_Items;
import com.officina_hide.base.common.FD_WhereData;

/**
 * DB操作クラス[DB operation class]<br>
 * @author officina-hide.net
 * @version 1.00
 * @since 2021/05/22
 */
public class FD_DB implements I_FD_DB {
	/** 項目リスト */
	protected FD_Items items;

//	/** 項目 : グループ情報ID */
//	private long FD_Group_ID;
//	/** 項目 : 登録日時 */
//	private Calendar FD_Created;
//	/** 項目 : 登録者情報ID */
//	private long FD_CreatedBy;
//	/** 項目 : 更新日時 */
//	private Calendar FD_updated;
//	/** 項目 : 更新者情報ID */
//	private long FD_UpdatedBy;
//	/** 項目 : 表示名 */
//	private String FD_Name;
//	/** 項目 : 説明 */
//	private String FD_Description;

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
	 * 情報登録[Information registration]
	 * @param env 環境情報[Enfironment information]
	 * @param tableName テーブル名[Table name]
	 * @param items 項目一覧[item list]
	 */
	public void save(FD_EnvData env, String tableName, FD_Items items) {
		PreparedStatement pstmt = null;
		Calendar nowDate = new GregorianCalendar(new Locale(Locale.JAPAN.getLanguage(), Locale.JAPAN.getCountry()));
		nowDate.setTime(new Date());
		StringBuffer sql = new StringBuffer();
		//情報ID取得
		long id = items.getlongData(tableName+"_ID");
		if(id > 0 && isRecoedExits(env, tableName, id)) {
			//情報更新
			//更新日時、更新者セット
			items.setValue(COLUMNNAME_FD_Updated, nowDate);
			items.setValue(COLUMNNAME_FD_UpdatedBy, env.getActionUserID());
			//更新用SQL作成
			sql.append("UPDATE ").append(tableName).append(" ").append(" SET ");
			sql.append(items.getUpdateItemStrings()).append(" ");
			sql.append("WHERE ").append(I_FD_Numbering.COLUMNNAME_FD_Numbering_ID)
				.append(" = ").append(id).append(" ");
		} else {
			//採番処理
			if(id == 0) {
				FD_Numbering num = new FD_Numbering();
				items.setValue(tableName + "_ID", num.getNumber(env, items.getTableId()));
			}
			//新規登録
			//登録日時、登録者、更新日時、更新者セット
			items.setValue(COLUMNNAME_FD_Created, nowDate);
			items.setValue(COLUMNNAME_FD_CreatedBy, env.getActionUserID());
			items.setValue(COLUMNNAME_FD_Updated, nowDate);
			items.setValue(COLUMNNAME_FD_UpdatedBy, env.getActionUserID());
			//新規登録用SQL作成
			sql.append("INSERT INTO ").append(tableName).append(" ");
			sql.append("(").append(items.getInsertItemStrings()).append(")").append(" ");
			sql.append("VALUES");
			sql.append("(").append(items.getPrepardStrings()).append(")");
		}
		
		connection(env);
		FD_Item wkItem = null;
		try {
			pstmt = getConn().prepareStatement(sql.toString());
			int idx = 1;
			for(FD_Item item : items.getItems()) {
				wkItem = item;
				switch(item.getType()) {
				case FD_Item_ID:
				case FD_ITEM_BigInt:
				case FD_ITEM_Unsugned_BigInt:
					pstmt.setLong(idx, items.getlongData(item.getName()));
					break;
				case FD_ITEM_Int:
				case FD_ITEM_Unsigned_Int:
					pstmt.setInt(idx, items.getintData(item.getName()));
					break;
				case FD_Item_String:
				case FD_Item_Text:
					// FIXME エスケープ対象文字未対応
					pstmt.setString(idx, items.getStringData(item.getName()));
					break;
				case FD_ITEM_Date:
					pstmt.setTimestamp(idx, new Timestamp(items.getDateData(item.getName()).getTimeInMillis()));
					break;
				}
				idx++;
			}
			//情報保存
			if(pstmt.executeUpdate() != 1) {
				new Exception("採番情報保存エラー");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println(wkItem.getName()+" is null!!");
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

	/**
	 * テーブル削除[Drop Table]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/06/03
	 * @param env 環境情報[Environment Information]
	 * @param tableName テーブル名[Table Name]
	 */
	public void dropTable(FD_EnvData env, String tableName) {
		try {
			connection(env);
			StringBuffer sql = new StringBuffer();
			sql.append("DROP TABLE IF EXISTS ").append(tableName);
			PreparedStatement ptmt = conn.prepareStatement(sql.toString());
			ptmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * テーブル生成[Create Table]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/25
	 * @param env 環境情報[Enfironment information]
	 * @param tableName テーブル名
	 */
	public void createTable(FD_EnvData env, String tableName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			//項目一覧生成
			items = new FD_Items();
			sql.append("SELECT * FROM ").append(I_FD_Column.Table_Name).append(" c ");
			sql.append("LEFT JOIN ").append(I_FD_Table.Table_Name).append(" t ")
				.append(" ON ").append("t.").append(I_FD_Table.COLUMNNAME_FD_Table_ID).append(" = ")
				.append("c.").append(I_FD_Column.COLUMNNAME_FD_Table_ID).append(" ");
			sql.append("LEFT JOIN ").append(I_FD_DataDictionary.Table_Name).append(" d ")
				.append(" ON ").append("d.").append(I_FD_DataDictionary.COLUMNNAME_FD_DataDictionary_ID).append(" = ")
				.append("c.").append(I_FD_Column.COLUMNNAME_FD_DataDictionary_ID).append(" ");
			sql.append("LEFT JOIN ").append(I_FD_TypeItem.Table_Name).append(" ty ")
				.append(" ON ").append("ty.").append(I_FD_TypeItem.COLUMNNAME_FD_TypeItem_ID).append(" = ")
				.append("c.").append(I_FD_Column.COLUMNNAME_FD_TypeItem_ID).append(" ");
			sql.append("WHERE ").append("t.").append(I_FD_Table.COLUMNNAME_FD_Table_Name).append(" = ?");
			
			connection(env);
			pstmt = getConn().prepareStatement(sql.toString());
			pstmt.setString(1, tableName);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				items.add(rs.getString("d."+I_FD_DataDictionary.COLUMNNAME_FD_DataDictionary_Name), null
						, rs.getString("ty."+I_FD_TypeItem.COLUMNNAME_FD_TypeItem_Name));
				if(items.getTableId() == 0) {
					items.setTableId(rs.getLong("t."+I_FD_Table.COLUMNNAME_FD_Table_ID));
				}
				if(items.getTableName() == null) {
					items.setTableName(rs.getString(I_FD_Table.COLUMNNAME_FD_Table_Name));
				}
			}

			//テーブル生成用SQL作成
			sql = new StringBuffer();
			sql.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");
			sql.append(items.getCreateTableString());
			sql.append(") ");
			sql.append("ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=")
				.append(FD_SQ).append(items.getFD_Table(env).getFD_Name()).append(FD_SQ).append(" ");
			System.out.println(sql.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
		
//		//テーブル情報取得
//		FD_WhereData where = new FD_WhereData(I_FD_Table.COLUMNNAME_FD_Table_Name, tableName);
//		FD_Table table = new FD_Table(env, where);
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
			case FD_Item_ID:
				sql.append("int(10) unsigned NOT NULL").append(" ");
				break;
			case FD_Item_String:
				sql.append("varchar(").append(item.getSize()).append(")").append(" ");
				break;
			case FD_ITEM_Date:
				sql.append("datetime").append(" ");
				break;
			case FD_Item_Text:
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
	 * 項目一覧設定[Item list setting]<br>
	 * @author officina-hide.net
	 * @since 1.00 2021/09/25
	 * @param env 環境情報[Enfironment information]
	 * @param tableId テーブル情報ID[Table information ID]
	 */
	public void createItemList(FD_EnvData env, long tableId) {
		items = new FD_Items();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(I_FD_Column.Table_Name).append(" c ");
			sql.append("LEFT JOIN ").append(I_FD_DataDictionary.Table_Name).append(" dd ")
				.append(" ON ").append("dd.").append(I_FD_DataDictionary.COLUMNNAME_FD_DataDictionary_ID).append(" = ")
				.append(" c.").append(I_FD_Column.COLUMNNAME_FD_DataDictionary_ID).append(" ");
			sql.append("LEFT JOIN ").append(I_FD_TypeItem.Table_Name).append(" t ")
				.append(" ON ").append("c.").append(I_FD_TypeItem.COLUMNNAME_FD_TypeItem_ID).append(" = ")
				.append(" c.").append(I_FD_Column.COLUMNNAME_FD_TypeItem_ID).append(" ");
			sql.append("WHERE ").append(I_FD_Column.COLUMNNAME_FD_Column_ID).append(" = ?");
			connection(env);
			pstmt = getConn().prepareStatement(sql.toString());
			pstmt.setLong(1, tableId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				items.add(rs.getString("dd."+I_FD_DataDictionary.COLUMNNAME_FD_DataDictionary_Name), null,
						"t."+I_FD_TypeItem.COLUMNNAME_FD_TypeItem_Name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
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
	public void load(FD_EnvData env, String tableName, long id, FD_Items items) {
		FD_WhereData where = new FD_WhereData(tableName+"_ID", id);
		load(env, items, where);
	}

	/**
	 * 情報抽出[Load information]<br>
	 * 指定された抽出条件でレコードを抽出する。[Extract records with the specified extraction conditions.]<br>
	 * @param env 環境情報[Enfironment information]
	 * @param items 項目一覧[Item list]
	 * @param where 抽出条件[extraction conditions]
	 */
	public void load(FD_EnvData env, FD_Items items, FD_WhereData where) {
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(items.getTableName()).append(" ");
			sql.append(where.toString());
			connection(env);
			stmt = getConn().createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				setItems(rs, items);
			} else {
				System.out.println("Data Dictionnary Not Found!! : "+ where.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(stmt, rs);
		}
	}

	/**
	 * 項目セット[Item set]
	 * @param rs 抽出結果[Extraction result]
	 * @param items 項目一覧[Item list]
	 */
	private void setItems(ResultSet rs, FD_Items items) {
		try {
			for(FD_Item item : items.getItems()) {
				switch(item.getType()) {
				case FD_Item_ID:
				case FD_ITEM_BigInt:
					item.setData(rs.getLong(item.getName()));
					break;
				case FD_Item_String:
				case FD_Item_Text:
					item.setData(rs.getString(item.getName()));
					break;
				case FD_ITEM_Date:
					Calendar cal = new GregorianCalendar(new Locale(Locale.JAPAN.getLanguage(), Locale.JAPAN.getCountry()));
					cal.setTime(rs.getTimestamp(item.getName()));
					item.setData(cal);
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 項目一覧に共通項目の情報を追加する。<br>
	 * Add common item information to the item list.<br>
	 * @param items 項目一覧[Item list]
	 */
	public void baseItemSet(FD_Items items) {
		items.add(COLUMNNAME_FD_Group_ID, null, FD_Item_ID);
		items.add(COLUMNNAME_FD_Created, null, FD_ITEM_Date);
		items.add(COLUMNNAME_FD_CreatedBy, null, FD_Item_ID);
		items.add(COLUMNNAME_FD_Updated, null, FD_ITEM_Date);
		items.add(COLUMNNAME_FD_UpdatedBy, null, FD_Item_ID);
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
	 * @param tableName 
	 */
	public void addData(FD_EnvData env, String tableName) {
		switch(tableName) {
		case I_FD_DataDictionary.Table_Name:
			FD_DataDictionary dd = new FD_DataDictionary();
			dd.add(env, 0, COLUMNNAME_FD_Group_ID, NAME_FD_Group_ID, COMMENT_FD_Group_ID);
			dd.add(env, 0, COLUMNNAME_FD_Created, NAME_FD_Created, COMMENT_FD_Created);
			dd.add(env, 0, COLUMNNAME_FD_CreatedBy, NAME_FD_CreatedBy, COMMENT_FD_CreatedBy);
			dd.add(env, 0, COLUMNNAME_FD_Updated, NAME_FD_Updated, COMMENT_FD_Updated);
			dd.add(env, 0, COLUMNNAME_FD_UpdatedBy, NAME_FD_UpdatedBy, COMMENT_FD_UpdatedBy);
			dd.add(env, 0, COLUMNNAME_FD_Name, NAME_FD_Name, COMMENT_FD_Name);
			dd.add(env, 0, COLUMNNAME_FD_Description, NAME_FD_Description, COMMENT_FD_Description);
			dd.add(env, 0, COLUMNNAME_FD_Value, NAME_FD_Value, COMMENT_FD_Value);
			break;
		}
	}

	/**
	 * 共通項目をテーブル項目情報に登録[Register common items in table item information]<br>
	 * @author officina-hide.net
	 * @since 1.00 2021/09/25
	 * @param env 環境情報[Enfironment information]
	 * @param tableId テーブル情報ID[Table information ID]
	 */
	public void addCommonColumn(FD_EnvData env, long tableId) {
		FD_Column column = new FD_Column();
		column.add(env, 0, tableId, COLUMNNAME_FD_Group_ID, FD_Item_ID, 0);
		column.add(env, 0, tableId, COLUMNNAME_FD_Created, FD_ITEM_Date, 0);
		column.add(env, 0, tableId, COLUMNNAME_FD_CreatedBy, FD_Item_ID, 0);
		column.add(env, 0, tableId, COLUMNNAME_FD_Updated, FD_ITEM_Date, 0);
		column.add(env, 0, tableId, COLUMNNAME_FD_UpdatedBy, FD_Item_ID, 0);
	}
	
	public long getFD_Group_ID() {
		return items.getlongData(COLUMNNAME_FD_Group_ID);
	}
	public void setFD_Group_ID(long groupID) {
		items.setValue(COLUMNNAME_FD_Group_ID, groupID);
	}
	public Calendar getFD_Created() {
		return items.getDateData(COLUMNNAME_FD_Created);
	}
	public void setFD_Created(Calendar created) {
		items.setValue(COLUMNNAME_FD_Created, created);
	}
	public long getFD_CreatedBy() {
		return items.getlongData(COLUMNNAME_FD_CreatedBy);
	}
	public void setFD_CreatedBy(long createdBy) {
		items.setValue(COLUMNNAME_FD_CreatedBy, createdBy);
	}
	public Calendar getFD_updated() {
		return items.getDateData(COLUMNNAME_FD_Updated);
	}
	public void setFD_updated(Calendar updated) {
		items.setValue(COLUMNNAME_FD_Updated, updated);
	}
	public long getFD_UpdatedBy() {
		return items.getlongData(COLUMNNAME_FD_UpdatedBy);
	}
	public void setFD_UpdatedBy(long updatedBy) {
		items.setValue(COLUMNNAME_FD_UpdatedBy, updatedBy);
	}
	public String getFD_Name() {
		return items.getStringData(COLUMNNAME_FD_Name);
	}
	public void setFD_Name(String name) {
		items.setValue(COLUMNNAME_FD_Name, name);
	}
	public String getFD_Description() {
		return items.getStringData(COLUMNNAME_FD_Description);
	}
	public void setFD_Description(String description) {
		items.setValue(COLUMNNAME_FD_Description, description);
	}

}

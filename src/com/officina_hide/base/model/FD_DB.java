package com.officina_hide.base.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;
import com.officina_hide.base.common.FD_Items;

/**
 * データベース操作クラス<br>
 * @author officine-hide.com
 * @version 1.00 新規作成
 * @since 2020/10/08
 */
public class FD_DB implements I_DB {
	/** データベース接続情報	 */
	protected static Connection conn;
	/** 項目リスト */
	protected FD_Items  itemList = new FD_Items();
	/** 日付フォーマッ */
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * テーブル構築<br>
	 * @author officine-hide.com
	 * @since 1.10 2020/11/05
	 * @param env 環境情報
	 * @param tableId テーブル情報ID
	 */
	public void createDBTable(FD_EnvData env, int tableId) {
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer dropSql = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		StringBuffer items = new StringBuffer();
		List<Map<String, String>> list = new ArrayList<>();
		try {
			//テーブル情報取得
			X_FD_Table table = new X_FD_Table(env, tableId);
			//既にテーブルが存在するときは削除する。
			dropSql.append("DROP TABLE IF EXISTS ").append(table.getValueOfString(I_FD_Table.COLUMNNAME_Table_Name));
			DBexecute(env, dropSql.toString());
			//テーブル項目情報より関連する情報のリストを作成する。
			sql.append("SELECT * FROM ").append(I_FD_TableColumn.Table_Name).append(" ");
			sql.append("LEFT JOIN ").append(I_FD_Reference.Table_Name).append(" ON " )
				.append(I_FD_Reference.COLUMNNAME_FD_Reference_ID).append(" = ")
				.append(I_FD_TableColumn.COLUMNNAME_TableColumn_Type_ID).append(" ");
			sql.append("WHERE ").append(I_FD_TableColumn.COLUMNNAME_FD_Table_ID).append(" = ").append(tableId).append(" ");
			sql.append("ORDER BY ").append(I_FD_TableColumn.COLUMNNAME_Column_Sort_Order).append(" ");
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				Map<String, String> map = new HashMap<>();
				map.put(I_FD_TableColumn.COLUMNNAME_TableColumn_Name
						, rs.getString(I_FD_TableColumn.COLUMNNAME_TableColumn_Name));
				map.put("Column_Type_Name", rs.getString(I_FD_Reference.COLUMNNAME_Reference_Name));
				map.put(I_FD_TableColumn.COLUMNNAME_TableColumn_Size
						, rs.getString(I_FD_TableColumn.COLUMNNAME_TableColumn_Size));
				map.put(I_FD_TableColumn.COLUMNNAME_FD_Name
						, rs.getString(I_FD_TableColumn.COLUMNNAME_FD_Name));
				map.put(I_FD_TableColumn.COLUMNNAME_Is_Null, rs.getString(I_FD_TableColumn.COLUMNNAME_Is_Null));
				map.put(I_FD_TableColumn.COLUMNNAME_Is_Primary, rs.getString(I_FD_TableColumn.COLUMNNAME_Is_Primary));
				list.add(map);
			}
			//テーブル構築
			sql = new StringBuffer();
			sql.append("CREATE TABLE IF NOT EXISTS ").append(table.getValueOfString(I_FD_Table.COLUMNNAME_Table_Name)).append(" ");
			sql.append(" (");
			
			for(Map<String, String> map : list) {
				if(items.toString().length() > 0) {
					items.append(",");
				}
				items.append(map.get(I_FD_TableColumn.COLUMNNAME_TableColumn_Name).toString()).append(" ");
				switch(map.get("Column_Type_Name").toString()) {
				case COLUMNTYPE_FD_Text:
					items.append("Varchar(").append(map.get(I_FD_TableColumn.COLUMNNAME_TableColumn_Size)).append(")").append(" ");
					break;
				case COLUMNTYPE_FD_Field_Text:
					items.append("TEXT").append(" ");
					break;
				case COLUMNTYPE_FD_Information_ID:
				case COLUMNTYPE_FD_Number:
					items.append("INT UNSIGNED").append(" ");
					break;
				case COLUMNTYPE_FD_Date:
					items.append("DATETIME").append(" ");
					break;
				case COLUMNTYPE_FD_YesNo:
					items.append("ENUM(")
						.append(FD_SQ).append("Y").append(FD_SQ).append(", ")
						.append(FD_SQ).append("N").append(FD_SQ).append(")").append(" ");
					break;
				}
				if(map.get(I_FD_TableColumn.COLUMNNAME_Is_Null).toString().equals(I_FD_TableColumn.IS_NULL_Yes)){
					items.append("NOT NULL").append(" ");
				}
				if(map.get(I_FD_TableColumn.COLUMNNAME_Is_Primary).toString().equals(I_FD_TableColumn.IS_PRIMARY_Yes)){
					items.append("PRIMARY KEY").append(" ");
				}
				if(map.get(I_FD_TableColumn.COLUMNNAME_FD_Name) != null &&
						map.get(I_FD_TableColumn.COLUMNNAME_FD_Name).toString().length() > 0) {
					items.append("COMMENT").append(" ")
						.append(FD_SQ).append(map.get(I_FD_TableColumn.COLUMNNAME_FD_Name).toString()).append(FD_SQ).append(" ");
				}
			}
			
			sql.append(items.toString()).append(" ) ");
			if(table.getValueOfString(I_FD_TableColumn.COLUMNNAME_FD_Name).length() > 0) {
				sql.append(" COMMENT ")
					.append(FD_SQ).append(table.getValueOfString(I_FD_Table.COLUMNNAME_FD_Comment)).append(FD_SQ).append(" ");
			}
			
			DBexecute(env, sql.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}
		
		addLog(env, I_FD_Log.LOGTYPE_Table_Drop_ID, changeEscape(dropSql.toString()));
		addLog(env, I_FD_Log.LOGTYPE_Table_Create_ID, changeEscape(sql.toString()));
	}

	/**
	 * DB更新<br>
	 * @author officine-hide.com
	 * @since 2020/10/08
	 * @param env 環境情報
	 * @param sql SQL文
	 */
	public void DBexecute(FD_EnvData env, String sql) {
		Statement stmt = null;
		connection(env);
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, null);
		}
	}

	/**
	 * データベース接続<br>
	 * @author officina-hide.com
	 * @since 2020/10/09
	 * @param env 環境情報
	 */
	public void connection(FD_EnvData env) {
		if(conn == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				StringBuffer url  = new StringBuffer().append("jdbc:mysql://")
						.append(env.getDB_Host())
						.append(":3306/")
						.append(env.getDB_Name());
				conn = DriverManager.getConnection(url.toString(), env.getDB_User(), env.getDB_Password());
				System.out.println(new Date() + " : "+"Database Connected.");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}

	/**
	 * データベースクローズ(statement)<br>
	 * @author officina-hide.com
	 * @since 2020/10/10
	 * @param stmt ステートメント
	 * @param rs レザルトセット
	 */
	public void close(Statement stmt, ResultSet rs) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * データベースクローズ(statement)<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/24
	 * @param stmt ステートメント
	 */
	public void close(Statement stmt) {
		close(stmt, null);
	}

	/**
	 * 項目リストセット<br>
	 * <p>項目リストの指定された項目名を持つ情報に対して、指定された項目情報をセットする。</p>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/15
	 * @param env 環境情報
	 * @param columnName テーブル項目名
	 * @param columnData テーブル項目情報
	 */
	public void setValueByName(FD_EnvData env, String columnName, Object columnData) {
		if(itemList.setData(columnName, columnData) == false) {
			System.out.println(new Date()+" : "+"Column Name Not Found!! ["+columnName+"]");
			new Exception();
		}
	}
	
	/**
	 * 情報保存<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/15
	 * @param env 環境情報
	 * @param tableName テーブル名
	 */
	public void save(FD_EnvData env, String tableName) {
		StringBuffer sql = new StringBuffer();
		StringBuffer setItem = new StringBuffer();
		StringBuffer where = new StringBuffer();
		
		//情報IDチェック
		int id = itemList.getItemByName(tableName+"_ID").getIntOfValue();
		if(id == 0) {
			itemList.setData(tableName+"_ID",  getNewID(env, tableName));
			sql.append("INSERT INTO ").append(tableName).append(" SET ");
		} else {
			if(existsId(env, tableName, id)) {
				sql.append("UPDATE ").append(tableName).append(" SET ");
				where.append("WHERE ").append(tableName).append("_ID = ")
					.append(itemList.getItemByName(tableName+"_ID").getIntOfValue());
			} else {
				sql.append("INSERT INTO ").append(tableName).append(" SET ");
			}
		}
		
		//登録日、更新日設定
		if(itemList.getValueOfItem(COLUMNNAME_FD_CREATE) == null) {
			itemList.setData(COLUMNNAME_FD_Process_ID, env.getActiveProcessID());
			itemList.setData(COLUMNNAME_FD_CREATE, new Date());
			itemList.setData(COLUMNNAME_FD_UPDATE, new Date());
			itemList.setData(COLUMNNAME_FD_CREATED, env.getLogin_User_ID());
			itemList.setData(COLUMNNAME_FD_UPDATED, env.getLogin_User_ID());
		} else {
			itemList.setData(COLUMNNAME_FD_Process_ID, env.getActiveProcessID());
			itemList.setData(COLUMNNAME_FD_UPDATE, new Date());
			itemList.setData(COLUMNNAME_FD_UPDATED, env.getLogin_User_ID());
		}

		for(String columnName : itemList.getNameList()) {
			if(setItem.length() > 0) {
				setItem.append(",");
			}
			setItem.append(itemList.getSQLString(columnName));
		}
		sql.append(setItem.toString()).append(" ");
		if(where != null && where.length() > 9) {
			sql.append(where.toString());
		}

		addLog(env, I_FD_Log.LOGTYPE_Data_Update, changeEscape(sql.toString()));
		
		DBexecute(env, sql.toString());
	}

	/**
	 * テーブル中に情報IDの存在チェックを行う。<br>
	 * @author officine-hide.com
	 * @since 1.10 2020/11/02
	 * @param env 環境情報
	 * @param tableName テーブル名
	 * @param id 情報ID
	 * @return true - 存在、false - 未登録
	 */
	private boolean existsId(FD_EnvData env, String tableName, int id) {
		boolean chk = false;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(tableName).append(" ");
			sql.append("WHERE ").append(tableName).append("_ID = ").append(id);
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}
		return chk;
	}

	/**
	 * 新規情報ID取得<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/16
	 * @param env 環境情報
	 * @param tableName テーブル名
	 */
	private int getNewID(FD_EnvData env, String tableName) {
		int tableId = 0;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT Current_Number, Initial_Number, FD_Table.FD_Table_ID FROM ").append(I_FD_Numbering.Table_Name).append(" ");
			sql.append("LEFT JOIN FD_Table ON FD_Table.FD_Table_ID = FD_Numbering.FD_Table_ID ");
			sql.append("WHERE FD_Table.Table_Name = ").append(FD_SQ).append(tableName).append(FD_SQ).append(" ");
			sql.append("FOR UPDATE");
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				if(rs.getInt(I_FD_Numbering.COLUMNNAME_Current_Number) == 0) {
					tableId = rs.getInt(I_FD_Numbering.COLUMNNAME_Initial_Number);
				} else {
					tableId = rs.getInt(I_FD_Numbering.COLUMNNAME_Current_Number) + 1;
				}
				//現在値の更新
				sql = new StringBuffer();
				sql.append("UPDATE ").append(I_FD_Numbering.Table_Name).append(" SET ");
				sql.append(I_FD_Numbering.COLUMNNAME_Current_Number).append(" = ").append(tableId).append(" ");
				sql.append("WHERE FD_Table_ID = ").append(rs.getInt(I_FD_Table.COLUMNNAME_FD_Table_ID));
				DBexecute(env, sql.toString());
			} else {
				System.out.println(new Date()+" : "+"ERROR!! Numbering Data Not Found!!");
				// TODO エラー処理方法要件等(2020/10/19 ueno)
				new Exception();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}
		
		return tableId;
	}

	/**
	 * エスケープ処理<br>
	 * <p>SQLインジェクション対策の為、指定されたデータのコードを表示するエスケープ処理する。</p>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/20
	 * @param data 処理対象情報
	 * @return エスケープ処理済情報
	 */
	public String changeEscape(String data) {
		String out = data;
		out = out.replaceAll("\'", "\'\'");
		out = out.replaceAll("\\\\", "\\\\\\\\");
		return out;
	}

	/**
	 * ログ情報追加<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/20 新規作成
	 * @since 1.10 2020/10/31 プロセス情報項目追加
	 * @param env
	 * @param logTypeId
	 * @param logData
	 */
	public void addLog(FD_EnvData env, int logTypeId, String logData) {
		SimpleDateFormat msgDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		/*
		 * ログ種別がインフォメーション[IDが200～209の時]はメッセージを出力する。
		 */
		if(logTypeId >= 201 && logTypeId <= 209) {
			System.out.println(msgDateFormat.format(new Date())+" : "+logData);
		}
		
		/*
		 * ログ情報の追加はDBExecuteメソッドを利用せず、循環処理を避けるために、直接ここでInsertを行う。
		 */
		Statement stmt = null;
		StringBuffer sql = new StringBuffer();
		int logId = getNewLogId(env);
		try {
			sql.append("INSERT INTO ").append(I_FD_Log.Table_Name).append(" SET ");
			sql.append(I_FD_Log.COLUMNNAME_FD_Log_ID).append(" = ").append(logId).append(",");
			sql.append(I_FD_Log.COLUMNNAME_Log_Type_ID).append(" = ").append(logTypeId).append(",");
			sql.append(I_FD_Log.COLUMNNAME_Log_Data).append(" = ").append(FD_SQ).append(logData).append(FD_SQ).append(",");
			
			sql.append(COLUMNNAME_FD_Process_ID).append(" = ").append(env.getActiveProcessID()).append(",");
			sql.append(COLUMNNAME_FD_CREATE).append(" = ")
				.append(FD_SQ).append(dateFormat.format(new Date().getTime())).append(FD_SQ).append(",");
			sql.append(COLUMNNAME_FD_CREATED).append(" = ").append(env.getLogin_User_ID()).append(",");
			sql.append(COLUMNNAME_FD_UPDATE).append(" = ")
				.append(FD_SQ).append(dateFormat.format(new Date().getTime())).append(FD_SQ).append(",");
			sql.append(COLUMNNAME_FD_UPDATED).append(" = ").append(env.getLogin_User_ID()).append(" ");
			
			connection(env);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	/**
	 * 新規ログ情報ID取得<br>
	 * <p>ログ情報IDは他の情報IDと違って採番情報から取得せずに、初期値からの増分で独自に付与する。</p>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/20
	 * @param env 環境情報
	 * @return 新規ログ情報ID
	 */
	private int getNewLogId(FD_EnvData env) {
		int logId = I_FD_Log.INITIAL_LOG_ID;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT MAX(FD_Log_ID) FROM FD_Log ");
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				if(rs.getInt("Max(FD_Log_ID)") > 0) {
					logId = rs.getInt("Max(FD_Log_ID)") + 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}
		
		return logId;
	}
	
	/**
	 * 情報抽出<br>
	 * @author officine-hide.com
	 * @since 1.10 2020/11/02
	 * @param env 環境情報
	 * @param TableName テーブル名
	 * @param Id 情報ID
	 */
	public void load(FD_EnvData env, String TableName, int Id) {
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(TableName).append(" ");
			sql.append("WHERE ").append(TableName).append("_ID = ").append(Id);
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				for(FD_Item item : itemList.getItemList()) {
					switch(item.getItemType()) {
					case I_DB.COLUMNTYPE_FD_Text:
					case I_DB.COLUMNTYPE_FD_Field_Text:
					case I_DB.COLUMNTYPE_FD_YesNo:
						item.setItemData(rs.getString(item.getItemName()));
						break;
					case I_DB.COLUMNTYPE_FD_Information_ID:
					case I_DB.COLUMNTYPE_FD_Number:
						item.setItemData(rs.getInt(item.getItemName()));
						break;
					case I_DB.COLUMNTYPE_FD_Date:
						if(rs.getTimestamp(item.getItemName()) != null) {
							item.setItemData(new Date(rs.getTimestamp(item.getItemName()).getTime()));
						} else {
							item.setItemData(null);
						}
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}
		
	}
	
	/**
	 * DBステートメント生成<br>
	 * @author officine-hide.com ueno
	 * @since 1.10 2020/09/01
	 * @return DBステートメント
	 */
	public Statement createStatement() {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}

	/**
	 * 項目の文字列情報を返す<br>
	 * @author officine-hide.com
	 * @since 1.10 2020/11/02
	 * @param columnName 項目名
	 * @return 文字列情報
	 */
	public String getValueOfString(String columnName) {
		FD_Item item = itemList.getItemByName(columnName);
		return item.getStringOfValue();
	}

	/**
	 * 項目の日付情報を返す<br>
	 * @author officine-hide.com
	 * @since 1.10 2020/11/02
	 * @param columnName 項目名
	 * @return 日付情報
	 */
	public Date getDateOfValue(String columnName) {
		FD_Item item = itemList.getItemByName(columnName);
		return item.getDateOfValue();
	}

	/**
	 * 項目の数値情報を返す。<br>
	 * @author officine-hide.com
	 * @since 1.10 2020/11/03
	 * @param columnName 項目名
	 * @return 数値情報
	 */
	public int getintOfValue(String columnName) {
		FD_Item item = itemList.getItemByName(columnName);
		return item.getIntOfValue();
	}
}

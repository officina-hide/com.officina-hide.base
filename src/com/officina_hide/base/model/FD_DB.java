package com.officina_hide.base.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import com.officina_hide.base.common.FDSQLWhere;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;
import com.officina_hide.base.common.FD_Items;
import com.officina_hide.base.common.FD_NumberingUtility;
import com.officina_hide.fx.model.X_FD_ViewColumn;

/**
 * データベース操作クラス[Database operation class]<br>
 * @author officine-hide.com
 * @version 1.30
 * @since 2020/12/08
 */
public class FD_DB implements I_FD_DB {
	/** データベース接続情報	 */
	protected static Connection conn;
	/** 項目リスト */
	protected FD_Items  itemList = new FD_Items();

	/**
	 * データベース更新実行[Database update execution]<br>
	 * @author officine-hide.com
	 * @since 2020/12/08
	 * @param env 環境情報
	 * @param sql SQL文
	 */
	public void DBUpdateExecution(FD_EnvData env, String sql) {
		Statement stmt = null;
		connection(env);
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBclose(stmt, null);
		}
	}

	/**
	 * データベースクローズ[Database close]<br>
	 * @author officine-hide.com
	 * @since 1.30 2020/12/09
	 * @param stmt ステートメント
	 */
	public void DBclose(Statement stmt) {
		DBclose(stmt, null);
	}

	/**
	 * データベースクローズ[Database close]<br>
	 * @author officine-hide.com
	 * @since 1.30 2020/12/09
	 * @param stmt ステートメント
	 * @param rs 検索結果
	 */
	public void DBclose(Statement stmt, ResultSet rs) {
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
	 * データベース接続[Database connection]<br>
	 * @author officine-hide.com
	 * @since 2020/12/09
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
	 * エスケープ処理<br>
	 * <p>SQLインジェクション対策の為、指定されたデータのコードをエスケープ処理する。<br>
	 * Escape the code of the specified data to prevent SQL injection.</p>
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
			FD_NumberingUtility nutil = new FD_NumberingUtility();
			itemList.setData(tableName+"_ID",  nutil.getNewID(env, tableName));
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
		if(itemList.getItemByName(COLUMNNAME_FD_CREATE).isNullData()) {
			itemList.setData(COLUMNNAME_FD_CREATE, new Date());
			itemList.setData(COLUMNNAME_FD_UPDATE, new Date());
			itemList.setData(COLUMNNAME_FD_CREATED, env.getLoginUserID());
			itemList.setData(COLUMNNAME_FD_UPDATED, env.getLoginUserID());
		} else {
			itemList.setData(COLUMNNAME_FD_UPDATE, new Date());
			itemList.setData(COLUMNNAME_FD_UPDATED, env.getLoginUserID());
		}
		if(tableName.equals(I_FD_Process.Table_Name) == false) {
			itemList.setData(COLUMNNAME_FD_Process_ID, env.getActiveProcessID());
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

//		System.out.println(sql.toString());
		
		DBUpdateExecution(env, sql.toString());
		FDLog log = new FDLog();
		log.addLog(env, I_FD_Log.LOGTYPE_Data_Update, changeEscape(sql.toString()));
	}
	
	/**
	 * 情報ID登録確認[Information ID registration confirmation]<br>
	 * @author officina-hide.com
	 * @since 1.30 2021/01/25
	 * @param env 環境情報
	 * @param tableName テーブル名
	 * @param id 情報ID
	 * @return true - 登録済、false - 未登録
	 */
	private boolean existsId(FD_EnvData env, String tableName, int id) {
		boolean chk = false;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT ").append(tableName).append("_ID").append(" FROM ").append(tableName).append(" ");
			sql.append("WHERE ").append(tableName).append("_ID").append(" = ").append(id);
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBclose(stmt, rs);
		}
		return chk;
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

	/**
	 * 項目の日付情報を返す。[Return item information by date]<br>
	 * @author officina-hide.com
	 * @since 1.31 2021/02/20
	 * @param columnName
	 * @return 日付情報
	 */
	public Date getDateOfValue(String columnName) {
		FD_Item item = itemList.getItemByName(columnName);
		return item.getDateOfValue();
	}

	/**
	 * 情報取得[Get information.]<br>
	 * @author officina-hide.com
	 * @since 1.30 2021/01/04
	 * @param env 環境情報
	 * @param TableName テーブル名[Table name]
	 * @param Id 情報ID[Information ID]
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
					case COLUMNTYPE_FD_Text:
					case COLUMNTYPE_FD_Field_Text:
					case COLUMNTYPE_FD_YesNo:
						item.setItemData(rs.getString(item.getItemName()));
						break;
					case COLUMNTYPE_FD_Information_ID:
					case COLUMNTYPE_FD_Number:
						item.setItemData(rs.getInt(item.getItemName()));
						break;
					case COLUMNTYPE_FD_Date:
						if(rs.getTimestamp(item.getItemName()) != null) {
							item.setItemData(new Date(rs.getTimestamp(item.getItemName()).getTime()));
						} else {
							item.setItemData(null);
						}
						break;
					}
				}
			} else {
				FDLog log = new FDLog();
				log.addLog(env, I_FD_Log.LOGTYPE_ERROR_ID, "Data Load Error : "+TableName+"["+Id+"]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBclose(stmt, rs);
		}
	}

	/**
	 * ステートメント生成[Create statement]<br>
	 * @author officina-hide.com
	 * @since 1.31 2021/02/20
	 * @param env 環境情報
	 * @return ステートメント
	 */
	public Statement createStatement(FD_EnvData env) {
		if(conn == null) {
			connection(env);
		}
		try {
			return conn.createStatement();
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * 情報取得[Get Information.]<br>
	 * 指定されたテーブルから指定された条件を満たす情報を１件抽出する。<br>
	 * Extract one piece of information that satisfies the conditions from the table.<br>
	 * @param env 環境情報
	 * @param tableName テーブル名
	 * @param where 条件
	 */
	public void load(FD_EnvData env, String tableName, FDSQLWhere where) {
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(tableName).append(" ");
			sql.append("WHERE ").append(where.toString()).append(" ");
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				for(FD_Item item : itemList.getItemList()) {
					switch(item.getItemType()) {
					case COLUMNTYPE_FD_Text:
					case COLUMNTYPE_FD_Field_Text:
					case COLUMNTYPE_FD_YesNo:
						item.setItemData(rs.getString(item.getItemName()));
						break;
					case COLUMNTYPE_FD_Information_ID:
					case COLUMNTYPE_FD_Number:
						item.setItemData(rs.getInt(item.getItemName()));
						break;
					case COLUMNTYPE_FD_Date:
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
			DBclose(stmt, rs);
		}
	}

	public List<X_FD_ViewColumn> getDataList(FD_EnvData env, String tableName, FDSQLWhere where) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}

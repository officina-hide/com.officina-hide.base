package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;

/**
 * 採番情報クラス[Numbering information class]
 * @author officina-hide.com
 * @version 1.50 新規作成
 * @since 2022/03/19 Ver. 1.50
 */
public class FD_Numbering extends FD_DB implements I_FD_Numbering {
	
	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/04/18 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public FD_Numbering(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 採番情報テーブル構築[Numbering information table construction]<br>
	 * 採番情報テーブルは初期構築の為、生成は標準外で行う。<br>
	 * Since the numbering information table is initially constructed, it is generated outside the standard.<br>
	 * @author officina-hide.net
	 * @since 2022/03/19 Ver. 1.50
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
			System.out.println("採番情報テーブル構築完了 : " + new Date());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

	/**
	 * 採番情報登録[Numbering information entry]<br>
	 * @author officina-hide.net
	 * @since 2022/03/23 Ver. 1.50
	 * @param entryData 登録情報[Entry data]
	 */
	public void add(String entryData) {
		FD_Collections entry = new FD_Collections(env, entryData);
		X_FD_Numbering num = new X_FD_Numbering(entry);
		//TODO 新規登録のみ(2022/04/19)
		num.save(env);
	}

	/**
	 * 新規採番[Create numbering]<br>
	 * @author officina-hide.net
	 * @since 2022/04/19 Ver. 1.50
	 * @param tableName テーブル名[Table name]
	 * @param columnName テーブル項目名[Column name]
	 * @return 採番結果[Numbering result]
	 */
	public String getNewNumber(String tableName, String columnName) {
		String nm = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(GET_DATA_By_Column);
			pstmt.setString(1, tableName);
			pstmt.setString(2, columnName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String fmt = rs.getString(I_FD_Numbering.COLUMNNAME_FD_NumberFormat);
				if(fmt.indexOf("$n") >= 0) {
					int cnt = Integer.parseInt(fmt.substring(fmt.indexOf("$n")+2, fmt.indexOf("$n")+4));
					String fmtStr = "";
					for(int ix = 0; ix < cnt; ix++) {
						fmtStr = fmtStr + "0";
					}
					DecimalFormat df = new DecimalFormat(fmtStr);
				}
			} else {
				System.out.println("Error Numbering data ["+tableName+":"+columnName+"]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
		return nm;
	}

	/**
	 * 新規ID採番[New ID numbering]<br>
	 * @author officina-hide.net
	 * @since 2022/04/22 Ver. 1.50
	 * @param tableId テーブル情報ID
	 * @return 新規ID[New ID]
	 */
	public long getNewId(int tableId) {
		long id = 0;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(GET_DATA);
			pstmt.setLong(1, tableId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getLong(COLUMNNAME_FD_CurrentNumber) == 0) {
					id = rs.getLong(COLUMNNAME_FD_InitialNumber);
				} else {
					id = rs.getLong(COLUMNNAME_FD_CurrentNumber) + 1;
				}
				//現在値更新
				pstmt.close();
				pstmt = getConn().prepareStatement(UPDATE_DATA);
				pstmt.setLong(1, id);
				pstmt.setLong(2, tableId);
				int cnt = pstmt.executeUpdate();
				if(cnt != 1) {
					System.out.println("Error Update FD_Numbering!!");
				}
			} else {
				System.out.println("Error Numbering Data not found["+tableId+"]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
		return id;
	}
	
	/** 採番情報取得（テーブルに対する情報ID用） */
	private final String GET_DATA = 
			"SELECT * FROM "+I_FD_Numbering.Table_Name + " "
			+ "WHERE "+I_FD_Numbering.COLUMNNAME_FD_Table_ID+" = ? "
			+ "AND "+I_FD_Numbering.COLUMNNAME_FD_Column_ID+" IS NULL ";
	
	/** 採番情報更新（現在値更新:情報ID用） */
	private final String UPDATE_DATA =
			"UPDATE "+Table_Name+" SET "
			+ COLUMNNAME_FD_CurrentNumber+" = ? "
			+ "WHERE "+COLUMNNAME_FD_Table_ID+" = ? ";
	
	/** 採番情報取得(テーブル名、テーブル項目名) */
	private final String GET_DATA_By_Column =
			"SELECT * FROM "+I_FD_Numbering.Table_Name+" n "
			+ "LEFT JOIN "+I_FD_Table.Table_Name+" t ON t."+I_FD_Table.COLUMNNAME_FD_Table_ID+" = "
				+ " n."+I_FD_Numbering.COLUMNNAME_FD_Table_ID+" "
			+ "LEFT JOIN "+I_FD_Column.Table_Name+" c ON c."+I_FD_Column.COLUMNNAME_FD_Column_ID+" = "
				+ "n."+I_FD_Numbering.COLUMNNAME_FD_Column_ID+" "
			+ "WHERE t."+I_FD_Table.COLUMNNAME_FD_Table_Code+" = ? "
			+ "AND c."+I_FD_Column.COLUMNNAME_FD_Column_Code+" = ? ";
}

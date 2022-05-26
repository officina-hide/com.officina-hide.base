package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_ColumnData;
import com.officina_hide.base.common.FD_EnvData;

/**
 * テーブル項目情報I/Oクラス[Table column information I/O class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成[New create]
 * @since 2022/04/22 Ver. 1.50
 */
public class X_FD_Column extends FD_DB implements I_FD_Column {

	/** 項目 : テーブル項目情報ID */
	private long FD_Column_ID;
	/** 項目 : テーブル項目コード */
	private String FD_Column_Code;
	/** 項目 : テーブル項目種別 */
	private long FD_ColumnType_ID;
	/** 情報 : テーブル項目種別（参照情報） */
	private X_FD_Reference FD_ColumnType;
	/** 項目 : テーブル項目サイズ */
	private int FD_Column_Size;
	/** 項目 : テーブル項目初期値 */
	private String FD_Column_DefaultValue;
	
	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/04/22 Ver. 1.50 新規作成[New create]
	 * @param entry 登録情報[Entry data]
	 */
	public X_FD_Column(FD_Collections entry) {
		createColumnList();
		columnCollection.setData(entry);
	}

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/05/24 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 * @param id テーブル項目情報ID[Table item information ID]
	 */
	public X_FD_Column(FD_EnvData env, long id) {
		createColumnList();
		if(id > 0) {
			load(env, id);
		}
	}

	/**
	 * 情報取得[Load data]<br>
	 * TODO 汎用化予定(2021/05/24)
	 * @author officina-hide.net
	 * @since 2022/05/24 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 * @param columnId テーブル項目情報ID[Table item information ID]
	 */
	private void load(FD_EnvData env, long columnId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(SQL_Get_ColumnData);
			pstmt.setLong(1, columnId);
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
					}
				}
			} else {
				System.out.println("Errot!! FD_Column ID not found : ["+columnId+"]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
	}

	/**
	 * テーブル情報用テーブル項目リスト生成[Table item list generation for numbering information]<br>
	 * @author officina-hide.net
	 * @since 2022/04/22 Ver. 1.50
	 * TODO 汎用化予定
	 */
	private void createColumnList() {
		columnCollection.clear();
		columnCollection.add(COLUMNNAME_FD_Column_ID, FD_Item_ID, ID_ZERO);
		columnCollection.add(COLUMNNAME_FD_Column_Code, FD_Item_String);
		columnCollection.add(COLUMNNAME_FD_Table_ID, FD_Item_ID, ID_ZERO);
		columnCollection.add(COLUMNNAME_FD_Name, FD_Item_String);
		columnCollection.add(COLUMNNAME_FD_ColumnType_ID, FD_Item_ID, ID_ZERO);
		columnCollection.add(COLUMNNAME_FD_Column_Size, FD_Item_Int, INT_ZERO);
		columnCollection.add(COLUMNNAME_FD_Column_DefaultValue, FD_Item_String);
	}

	/**
	 * 情報登録[Save data]<br>
	 * @author officina-hide.net
	 * @since 2022/04/22 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		//テーブル項目情報IDが0の時は、新規採番する。
		if(getFD_Column_ID() == 0) {
			FD_Numbering num = new FD_Numbering(env);
			setFD_Column_ID(num.getNewId(Table_ID));
		}
		PreparedStatement pstmt = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(columnCollection.getInsertSQL(Table_Name));
			int rs = pstmt.executeUpdate();
			if(rs != 1) {
				System.out.println("Save Error!!");
			} else {
				System.out.println(Table_Disp_Name+"登録完了["+columnCollection.getTableId(Table_Name)+"]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

	/**
	 * 値取得[Getting value by column name]<br>
	 * @author officina-hide.net
	 * @since 2022/05/24 Ver. 1.50
	 * @param columnName テーブル項目名[Column name]
	 * @return 値[Value]
	 */
	public Object getValue(String columnName) {
		for(FD_ColumnData cd : columnCollection.getList()) {
			if(cd.getColumnName().equals(columnName)) {
				return cd.getColumnData();
			}
		}
		System.out.println("Error!! Column not found ["+columnName+"]");
		return null;
	}

	public long getFD_Column_ID() {
		FD_Column_ID = (long) columnCollection.getValue(COLUMNNAME_FD_Column_ID);
		return FD_Column_ID;
	}
	public void setFD_Column_ID(long columnId) {
		columnCollection.setValue(COLUMNNAME_FD_Column_ID, columnId);
	}
	public String getFD_Column_Code() {
		FD_Column_Code = (String) columnCollection.getValue(COLUMNNAME_FD_Column_Code);
		return FD_Column_Code;
	}
	public void setFD_Column_Code(String columnCode) {
		columnCollection.setValue(COLUMNNAME_FD_Column_Code, columnCode);
	}
	public long getFD_ColumnType_ID() {
		FD_ColumnType_ID = (long) columnCollection.getValue(COLUMNNAME_FD_ColumnType_ID);
		return FD_ColumnType_ID;
	}
	public void setFD_ColumnType_ID(long columnTypeId) {
		columnCollection.setValue(COLUMNNAME_FD_ColumnType_ID, columnTypeId);
	}
	public int getFD_Column_Size() {
		FD_Column_Size = (int) columnCollection.getValue(COLUMNNAME_FD_Column_Size);
		return FD_Column_Size;
	}
	public void setFD_Column_Size(int columnSize) {
		columnCollection.setValue(COLUMNNAME_FD_Column_Size, columnSize);
	}
	public X_FD_Reference getFD_ColumnType(FD_EnvData env) {
		FD_ColumnType = null;
		if(getFD_ColumnType_ID() > 0) {
			FD_ColumnType = new X_FD_Reference(env, getFD_ColumnType_ID());
		}
		return FD_ColumnType;
	}
	public String getFD_Column_DefaultValue() {
		FD_Column_DefaultValue = (String) columnCollection.getValue(COLUMNNAME_FD_Column_DefaultValue);
		return FD_Column_DefaultValue;
	}
	public void setFD_Column_DefaultValue(String defaultValue) {
		columnCollection.setValue(COLUMNNAME_FD_Column_DefaultValue, defaultValue);
	}

	/** テーブル項目情報抽出（情報ID） */
	private final String SQL_Get_ColumnData =
			"SELECT * FROM " + Table_Name + " "
			+ "WHERE " + COLUMNNAME_FD_Column_ID + " = ? ";
}

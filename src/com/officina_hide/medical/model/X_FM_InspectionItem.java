package com.officina_hide.medical.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_Table;
import com.officina_hide.base.model.I_FD_Column;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.X_FD_Column;

/**
 * 検査項目情報I/Oクラス[Inspection item information I/O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/05/23 Ver. 1.00
 */
public class X_FM_InspectionItem extends FD_DB implements I_FM_InspectionItem {

	/** 項目 : 検査項目情報ID */
	private long FM_InspectionItem_ID;
	/** 項目 : 検査項目コード */
	private String FM_InspectionItem_Code;
	
	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/05/23 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param entry 登録情報[Entry data]
	 */
	public X_FM_InspectionItem(FD_EnvData env, FD_Collections entry) {
		createColumnList(env, Table_Name);
		columnCollection.setData(entry);
	}

	/**
	 * テーブル項目リスト初期化[Table item list initialization]<br>
	 * @author officina-hide.net
	 * @since 2022/05/23 Ver. 1.00
	 * @param tableName テーブル名[Table name]
	 * @param env 環境情報[Environment information]
	 */
	private void createColumnList(FD_EnvData env, String tableName) {
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

	/**
	 * 情報登録[Data save]<br>
	 * @author officina-hide.net
	 * @since 2022/05/26 Ver. 1.00 
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		save(env, Table_Name);
	}

	private final String SQL_Get_ColumnData =
			"SELECT "+I_FD_Column.COLUMNNAME_FD_Column_ID+" FROM "+I_FD_Column.Table_Name+" c "
			+ "LEFT JOIN " + FD_Table.Table_Name + " t ON t." + I_FD_Table.COLUMNNAME_FD_Table_ID + " = "
				+ "c." + I_FD_Column.COLUMNNAME_FD_Table_ID + " "
			+"WHERE " + I_FD_Table.COLUMNNAME_FD_Table_Code + " = ? ";

	public long getFM_InspectionItem_ID() {
		FM_InspectionItem_ID = (long) columnCollection.getValue(COLUMNNAME_FM_InspectionItem_ID);
		return FM_InspectionItem_ID;
	}
	public void setFM_InspectionItem_ID(long imspectionItemId) {
		columnCollection.setValue(COLUMNNAME_FM_InspectionItem_ID, imspectionItemId);
	}
	public String getFM_InspectionItem_Code() {
		FM_InspectionItem_Code = (String) columnCollection.getValue(COLUMNNAME_FM_InspectionItem_Code);
		return FM_InspectionItem_Code;
	}
	public void setFM_InspectionItem_Code(String inspectionItemCode) {
		columnCollection.setValue(COLUMNNAME_FM_InspectionItem_Code, inspectionItemCode);
	}
}

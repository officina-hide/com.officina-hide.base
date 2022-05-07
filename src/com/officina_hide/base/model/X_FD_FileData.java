package com.officina_hide.base.model;

import java.io.ByteArrayInputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.officina_hide.base.common.FD_ColumnDataCollection;
import com.officina_hide.base.common.FD_EnvData;

/**
 * ファイルデータ情報I/Oクラス[File data information I/O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[Create new]
 * @since 2022/05/04 Ver. 1.00
 */
public class X_FD_FileData extends FD_DB implements I_FD_FileData {

	/** 項目 : ファイルデータ情報ID */
	private long FD_FileData_ID;
	/** 項目 : ファイル情報ID */
	private long FD_File_ID;
	/** 項目 : ファイルデータ */
	private byte[] FD_FileData;

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/05/04 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param fileDataId ファイルデータ情報ID[File data information ID]
	 */
	public X_FD_FileData(FD_EnvData env, long fileDataId) {
		createColumnList();
		columnCollection.setValue(COLUMNNAME_FD_FileData_ID, fileDataId);
	}

	/**
	 * テーブル項目リスト生成[Table item list generation]<br>
	 * TODO テーブル項目情報登録後は汎用化予定(2022/05/05)
	 * @author officina-hide.net
	 * @since 2022/05/05 Ver. 1.00
	 */
	private void createColumnList() {
		columnCollection = new FD_ColumnDataCollection();
		columnCollection.add(COLUMNNAME_FD_FileData_ID, FD_Item_ID, ID_ZERO);
		columnCollection.add(COLUMNNAME_FD_File_ID, FD_Item_ID);
		columnCollection.add(COLUMNNAME_FD_File_Data, FD_Item_LongBlob);
	}
	
	/**
	 * 情報保存[Save data]<br>
	 * @author officina-hide.net
	 * @since 2022/05/07 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		if(getFD_FileData_ID() == 0) {
			FD_Numbering num = new FD_Numbering(env);
			FD_Table table = new FD_Table(env);
			setFD_FileData_ID(num.getNewId(table.getTableID(Table_Name)));
		}
		//TODO BLOBデータが有るため本情報の登録は別途SQLを構築する。→汎用化予定(2022/05/07)
		PreparedStatement pstmt = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(SQL_DATA_INSERT);
			pstmt.setLong(1, getFD_FileData_ID());
			pstmt.setLong(2, getFD_File_ID());
			pstmt.setBinaryStream(3, new ByteArrayInputStream(getFD_FileData()), getFD_FileData().length);
			System.out.println(pstmt.toString());
			int rs = pstmt.executeUpdate();
			if(rs != 1) {
				System.out.println("Error Data not save!!["+Table_Name+"]");
			} else {
				System.out.println(Table_Disp_Name+"登録["+getFD_FileData_ID()+"]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

	public long getFD_FileData_ID() {
		FD_FileData_ID = (long) columnCollection.getValue(COLUMNNAME_FD_FileData_ID);
		return FD_FileData_ID;
	}
	public void setFD_FileData_ID(long fileDataId) {
		columnCollection.setValue(COLUMNNAME_FD_FileData_ID, fileDataId);
	}
	public long getFD_File_ID() {
		FD_File_ID = (long) columnCollection.getValue(COLUMNNAME_FD_File_ID);
		return FD_File_ID;
	}
	public void setFD_File_ID(long fileId) {
		columnCollection.setValue(COLUMNNAME_FD_File_ID, fileId);
	}
	public byte[] getFD_FileData() {
		FD_FileData = (byte[]) columnCollection.getValue(COLUMNNAME_FD_File_Data);
		return FD_FileData;
	}
	public void setFD_FileData(byte[] fileData) {
		columnCollection.setValue(COLUMNNAME_FD_File_Data, fileData);
	}

	private final String SQL_DATA_INSERT = 
			"INSERT INTO "+Table_Name+" "
			+ "("+COLUMNNAME_FD_FileData_ID+","
					+COLUMNNAME_FD_File_ID+","
					+COLUMNNAME_FD_File_Data
			+") VALUES (?,?,?)";
}

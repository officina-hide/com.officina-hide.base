package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.officina_hide.base.common.FD_ColumnData;
import com.officina_hide.base.common.FD_ColumnDataCollection;
import com.officina_hide.base.common.FD_EnvData;

/**
 * ファイル情報I/Oクラス[File information I/O class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/04/18 Ver. 1.00
 */
public class X_FD_File extends FD_DB implements I_FD_File {

	/** 項目 : ファイル情報ID */
	private long FD_File_ID;
	
	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/04/18 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param fileId ファイル情報ID
	 */
	public X_FD_File(FD_EnvData env, int fileId) {
		createColumnList();
		columnCollection.setValue(COLUMNNAME_FD_File_ID, fileId);
	}

	/**
	 * テーブル項目リスト生成[Table item list generation]<br>
	 * TODO テーブル項目情報登録後は汎用化予定(2022/04/18)
	 * @author officina-hide.net
	 * @since 2022/04/18 Ver. 1.00
	 */
	private void createColumnList() {
		columnCollection = new FD_ColumnDataCollection();
		columnCollection.add(COLUMNNAME_FD_File_ID, FD_Item_ID);
		columnCollection.add(COLUMNNAME_FD_File_Code, FD_Item_String);
		columnCollection.add(COLUMNNAME_FD_Name, FD_Item_String);
	}

	/**
	 * テーブル項目リスト値追加[Add value to table item list]<br>
	 * @author officina-hide.net
	 * @since 2022/04/18 Ver. 1.00
	 * @param columname テーブル項目名[Table item name]
	 * @param data 値[value]
	 */
	public void setValue(String columname, String data) {
		FD_ColumnData cd = columnCollection.getItem(columname);
		cd.setColumnData(data);
	}

	/**
	 * 保存[Save]<br>
	 * TODO 汎用化予定(2022/04/18)
	 * @author officina-hide.net
	 * @since 2022/04/18 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void save(FD_EnvData env) {
		PreparedStatement pstmt = null;
		//ファイル情報ID採番
		setFD_File_ID(100001);
		//TODO 新規登録モードのみ設定
		try {
			connection(env);
			String sql = columnCollection.getInsertSQL(Table_Name);
			pstmt = getConn().prepareStatement(sql);
			int rs = pstmt.executeUpdate();
			if(rs != 1) {
				System.out.println("Save Error!!");
			} else {
				System.out.println("ファイル情報登録["+getFD_File_ID()+"] "+new Date());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

	public long getFD_File_ID() {
		FD_File_ID = (long) columnCollection.getValue(COLUMNNAME_FD_File_ID);
		return FD_File_ID;
	}
	public void setFD_File_ID(long fileId) {
		columnCollection.setValue(COLUMNNAME_FD_File_ID, fileId);
	}

}

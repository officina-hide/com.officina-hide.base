package com.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.officina_hide.base.common.FD_Collections;
import com.officina_hide.base.common.FD_EnvData;

/**
 * 参照グループ情報クラス[Reference group information class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成[New create]
 * @since 2022/05/10 Ver. 1.50
 */
public class FD_ReferenceGroup extends FD_DB implements I_FD_ReferenceGroup {
	
	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/05/10 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 */
	public FD_ReferenceGroup(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 参照グループ情報テーブル生成[Reference group information table generation]<br>
	 * @author officina-hide.net
	 * @since 2022/05/10 Ver. 1.50
	 */
	public void createTable() {
		PreparedStatement pstmt = null;
		deleteTable(env, Table_Name);
		try {
			connection(env);
			pstmt = getConn().prepareStatement(Table_Create_SQL);
			pstmt.executeUpdate();
			System.out.println(Table_Disp_Name+"テーブル構築完了 : " + new Date());
			//テーブル情報登録
			FD_Table table = new FD_Table(env);
			table.add(Entry_FD_Table);
			//採番情報登録
			FD_Numbering num = new FD_Numbering(env);
			num.add(Entry_FD_Number);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

	/**
	 * 参照グループ情報登録[Reference group information entry]<br>
	 * @author officina-hide.net
	 * @since 2022/05/12 Ver. 1.50
	 * @param entryData 登録情報[Entry data]
	 * @return 参照グループ情報ID [Reference group information ID]
	 */
	public long add(String entryData) {
		FD_Collections entry = new FD_Collections(env, entryData);
		X_FD_ReferenceGroup rfg = new X_FD_ReferenceGroup(env, entry);
		rfg.save(env);
		//新規登録のみ
		return rfg.getFD_ReferenceGroup_ID();
	}

	/**
	 * 参照グループ情報ID取得[Getting reference group information ID]<br>
	 * @author officina-hide.net
	 * @since 2022/05/12 Ver. 1.50
	 * @param refGroupName 参照グループ名[Reference group name(code)]
	 */
	public long getID(String refGroupName) {
		long id = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(GET_Reference_ID_SQL);
			pstmt.setString(1, refGroupName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				id = rs.getLong(COLUMNNAME_FD_ReferenceGroup_ID);
			} else {
				System.out.println("Error!! Reference Group Code not Found!! ["+refGroupName+"]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
		return id;
	}

}

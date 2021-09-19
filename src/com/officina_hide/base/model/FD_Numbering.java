package com.officina_hide.base.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;

/**
 * 採番情報クラス[Numbering information class]<br>
 * <p>本クラスはパッケージ全体の採番を管理する為の処理クラスです。</p>
 * <p>This class is a processing class for managing the numbering of the entire package.</p>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/15
 */
public class FD_Numbering extends FD_DB implements I_FD_Numbering {


	/**
	 * 採番情報テーブル生成[Numbering information table generation]<br>
	 * <p>採番情報テーブルは初期の基盤情報のため、生成は個別に行う。</p>
	 * <p>Since the numbering information table is the initial basic information, it is generated individually.</p>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/15
	 * @param env 環境情報[Environment Information]
	 */
	public void createTable(FD_EnvData env) {
		Statement stmt = null;
		try {
			connection(env);
			stmt = getConn().createStatement();
			stmt.executeUpdate(Table_Drop_SQL);
			stmt.executeUpdate(Table_Create_SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(stmt, null);
		}
	}

	/**
	 * 情報登録[Information entry]
	 * @param env 環境情報[Environment information] 
	 * @param numberingId 採番情報ID[Numbering information ID]
	 * @param tableId テーブル情報ID[Table information ID]
	 * @param initialNo 初期採番番号[Initial numbering number]
	 * @param currentNo 現在採番番号[Current numbering number]
	 */
	public void add(FD_EnvData env, int numberingId, int tableId, int initialNo, int currentNo) {
		//項目セット
		X_FD_Numbering num = new X_FD_Numbering(env, 0);
		//採番情報IDがゼロの時は採番を行う。
		if(numberingId == 0) {
			//採番情報の採番処理
			num.setFD_Numbering_ID(getNumber(env, Table_ID));
		} else {
			num.setFD_Numbering_ID(numberingId);
		}
		num.setFD_Table_ID(tableId);
		num.setFD_InitialNumber(initialNo);
		num.setFD_CurrentNumber(currentNo);
		num.setFD_Group_ID(SYSTEM_GROUP_ID);
		Calendar now = new GregorianCalendar(new Locale(Locale.JAPAN.getLanguage(), Locale.JAPAN.getCountry()));
		now.setTime(new Date());
		num.setFD_Created(now);
		num.setFD_CreatedBy(SYSTEM_USER_ID);
		num.setFD_updated(now);
		num.setFD_UpdatedBy(SYSTEM_USER_ID);
		num.save(env);
	}

	/**
	 * テーブル情報ID指定による採番処理[Numbering process by specifying table information ID]<br>
	 * @author officina-hide.net
	 * @since 1.00 2021/09/17
	 * @param env 環境情報[Enfironment information]
	 * @param tableId テーブル情報ID[Table information ID]
	 * @return no 採番番号[Numbering number]
	 */
	public long getNumber(FD_EnvData env, int tableId) {
		long no = 0;
		FD_WhereData where = new FD_WhereData(COLUMNNAME_FD_Table_ID, tableId);
		X_FD_Numbering num = new X_FD_Numbering(env, where);
		if(num.getFD_CurrentNumber() == 0) {
			no = num.getFD_InitialNumber();
			num.setFD_CurrentNumber(num.getFD_InitialNumber());
		} else {
			no = num.getFD_CurrentNumber() + 1;
			num.setFD_CurrentNumber(no);
		}
		num.save(env);
		return no;
	}

	/**
	 * テーブル毎の登録処理を行う。[Perform registration processing for each table.]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/18
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name]
	 */
	public void addData(FD_EnvData env, String tableName) {
		switch(tableName) {
		case Table_Name:
			add(env, 101, I_FD_Numbering.Table_ID, 101, 101);
			break;
		case I_FD_DataDictionary.Table_Name:
			FD_DataDictionary dd = new FD_DataDictionary();
			dd.add(env, 0, COLUMNNAME_FD_Numbering_ID, NAME_FD_Numbering_ID, null);
			dd.add(env, 0, COLUMNNAME_FD_Table_ID, NAME_FD_Table_ID, COMMENT_FD_Table_ID);
			dd.add(env, 0, COLUMNNAME_FD_InitialNumber, NAME_FD_InitialNumber, null);
			dd.add(env, 0, COLUMNNAME_FD_CurrentNumber, NAME_FD_CurrentNumber, null);
			break;
		case I_FD_Table.Table_Name:
			break;
		}
	}

}

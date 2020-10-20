package com.officina_hide.base.tools;

import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Referecne;
import com.officina_hide.base.model.X_FD_Reference;

/**
 * リファレンス情報クラス<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/10/15
 */
public class FDReference extends FD_DB implements I_FD_Referecne {

	/**
	 * リファレンス情報テーブル構築
	 * @author officine-hide.com
	 * @since 1.00 2020/10/15
	 * @param env 環境情報
	 */
	public void createDBTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されているテーフル情報を削除する。
		sql.append("DROP TABLE IF EXISTS ").append(Table_Name);
		DBexecute(env, sql.toString());
		//テーブル情報の再構築
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS ").append(Table_Name).append(" (");
		sql.append(COLUMNNAME_FD_Reference_ID).append(" INT UNSIGNED NOT NULL PRIMARY KEY COMMENT ")
			.append(FD_SQ).append(NAME_FD_Reference_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Reference_Name).append(" Varchar(100) COMMENT ")
			.append(FD_SQ).append(NAME_Reference_Name).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_Name).append(" Varchar(100) COMMENT ")
			.append(FD_SQ).append(NAME_FD_Name).append(FD_SQ).append(",");
		
		sql.append(COLUMNNAME_FD_CREATE).append(" DATETIME COMMENT ")
			.append(FD_SQ).append(NAME_FD_CREATE).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_CREATED).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_FD_CREATED).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATE).append(" DATETIME COMMENT ")
			.append(FD_SQ).append(NAME_FD_UPDATE).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_UPDATED).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_FD_UPDATED).append(FD_SQ).append(" ");
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=").append(FD_SQ).append(NAME).append(FD_SQ);
		DBexecute(env, sql.toString());
		
		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, TABLE_ID, Table_Name, NAME, COMMENT);
		
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.addData(env, TABLE_ID, TABLE_ID, 0, 1000001);
		
		System.out.println(new Date() + " : " + NAME + "テーブル生成完了");

	}

	/**
	 * テーブル項目情報用リファレンス情報登録<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/15
	 * @param env 環境情報
	 */
	public void addColumnTypeReference(FD_EnvData env) {
		addData(env, COLUMNTYPE_FD_Information_ID, COLUMNTYPENAME_FD_Information_ID);
		addData(env, COLUMNTYPE_FD_Text, COLUMNTYPENAME_FD_Field_Text);
		addData(env, COLUMNTYPE_FD_Field_Text, COLUMNTYPENAME_FD_Field_Text);
		addData(env, COLUMNTYPENAME_FD_FD_Date, COLUMNTYPENAME_FD_FD_Date);
		addData(env, COLUMNTYPE_FD_Number, COLUMNTYPENAME_FD_FD_Number);
		addData(env, COLUMNTYPE_FD_YesNo, COLUMNTYPENAME_FD_FD_YesNo);
		addData(env, COLUMNTYPE_FD_List, COLUMNTYPENAME_FD_List);
	}

	/**
	 * リファレンス情報登録<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/15
	 * @param env 環境情報
	 * @param referenceName リファレンス名
	 * @param name リファレンス表示名
	 */
	public void addData(FD_EnvData env, String referenceName, String name) {
		X_FD_Reference ref = new X_FD_Reference(env);
		ref.setValueByName(env, COLUMNNAME_FD_Reference_ID, 0);
		ref.setValueByName(env, COLUMNNAME_Reference_Name, referenceName);
		ref.setValueByName(env, COLUMNNAME_FD_Name, name);
		ref.save(env);
	}

}

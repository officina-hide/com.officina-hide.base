package com.officina_hide.base.tools;

import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_TableColumn;
import com.officina_hide.base.model.X_FD_TableColumn;

/**
 * テーブル項目情報クラス
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/10/20
 */
public class FDTableColumn extends FD_DB implements I_FD_TableColumn {

	/**
	 * コンストラクター<br>
	 * 実体化時に、項目を初期化する。<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/20
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		//テーブル生成
		createDBTable(env);
	}

	/**
	 * テーブル項目情報テーブル生成
	 * @author officine-hide.com
	 * @since 1.00 2020/10/20
	 * @param env 環境情報
	 */
	private void createDBTable(FD_EnvData env) {
		StringBuffer sql = new StringBuffer();
		//既に登録されているテーフル情報を削除する。
		sql.append("DROP TABLE IF EXISTS ").append(Table_Name);
		DBexecute(env, sql.toString());
		//テーブル情報の再構築
		sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS ").append(Table_Name).append(" (");
		sql.append(COLUMNNAME_FD_TableColumn_ID).append(" INT UNSIGNED NOT NULL PRIMARY KEY COMMENT ")
			.append(FD_SQ).append(NAME_FD_TableColumn_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_FD_Table_ID).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_FD_Table_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_TableColumn_Name).append(" VARCHAR(100) COMMENT ")
			.append(FD_SQ).append(NAME_TableColumn_Name).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_TableColumn_Type_ID).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_TableColumn_Type_ID).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_TableColumn_Size).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_TableColumn_Size).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Defualt_Data).append(" VARCHAR(100) COMMENT ")
			.append(FD_SQ).append(NAME_Defualt_Data).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Column_Sort_Order).append(" INT UNSIGNED COMMENT ")
			.append(FD_SQ).append(NAME_Column_Sort_Order).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Is_Null).append(" ENUM (")
			.append(FD_SQ).append("Y").append(FD_SQ).append(",")
			.append(FD_SQ).append("N").append(FD_SQ).append(")  ")
			.append("COMMENT ").append(FD_SQ).append(NAME_Is_Null).append(FD_SQ).append(",");
		sql.append(COLUMNNAME_Is_Primary).append(" ENUM(")
			.append(FD_SQ).append("Y").append(FD_SQ).append(",")
			.append(FD_SQ).append("N").append(FD_SQ).append(")  ")
			.append("COMMENT ").append(FD_SQ).append(NAME_Is_Primary).append(FD_SQ).append(",");
		
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
		
		System.out.println(new Date() + " : " + NAME +"テーブル生成完了");
	}

	/**
	 * テーブル項目情報登録<br>
	 * @author officine-hide.com
	 * @since 1.00 2020/10/22
	 * @param env 環境情報
	 * @param columnId テーブル項目情報ID
	 * @param tableId テーブル情報ID
	 * @param columnName テーブル項目名
	 * @param name テーブル項目表示名
	 * @param comment テーブル項目説明
	 * @param typeId テーブル項目属性ID
	 * @param defaultData 初期値
	 * @param size 桁数
	 * @param sortOrder 並び順
	 * @param idNull notNull判定
	 * @param isPrimary PrimaryKey判定
	 */
	public void addData(FD_EnvData env, int columnId, int tableId, String columnName, String name,
			String comment, int typeId, String defaultData, int size, int sortOrder, boolean idNull, boolean isPrimary) {
		X_FD_TableColumn column = new X_FD_TableColumn(env);
		column.setValueByName(env, COLUMNNAME_FD_TableColumn_ID, columnId);
	}

}

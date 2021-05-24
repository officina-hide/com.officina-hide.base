package com.officina_hide.base.sql;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Items;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.model.X_FD_Table;

/**
 * SQL管理クラス[SQL management class]<br>
 * @author officina-hide.com
 * @version 1.00
 * @since 2021/04/25
 */
public class FD_sql implements I_FD_DB {

	/** SQL作成種別 : Create Table */
	public static final String CREATE_TABLE = "CreateTable";
	public static final String DELETE_TABLE = "DeleteTable";

	private static final String ENTRY_DATA_FD_DisplayName = "FD_DisplayName";
//	private static final String COLUMN_NAME = "name";
//	private static final String COLUMN_TYPE = "type";
//	private static final String COLUMN_COMMENT = "comment";
//	private static final String COLUMN_LENGTH = "length";

	/**
	 * SQLステートメント生成[SQL statement generation]<br>
	 * XML情報から生成種別により指定されたSQLステートメントを生成する。<br>
	 * Generates the SQL statement specified by the generation type from the XML information.
	 * @author officina-hide.com
	 * @since 1.00 2021/04/29
	 * @param env 環境情報[Environment Information]
	 * @param createType 生成種別[Generation Type]
	 * @param XMLData[XML Information]
	 * @return SQLステートメント[SQL Statement]
	 */
	public String createSqlStatement(FD_EnvData env, String createType, Element xmlData) {
		StringBuffer sql = new StringBuffer();
		switch(createType) {
		case DELETE_TABLE:
			deleteTableStatement(env, xmlData, sql);
			break;
		case CREATE_TABLE:
			createTableStatement(env, xmlData, sql);
			break;
		}
		return sql.toString();
	}

	/**
	 * TODO 要検討 別クラス化(2021/04/29)
	 * テーブル削除SQLステートメント生成[Delete table SQL statement generation]<br>
	 * @author officina-hide.com
	 * @since 1.00 2021/05/01
	 * @param env 環境情報[Environment Information]
	 * @param xmlData[XML Information]
	 * @param sql SQLステートメント[SQL Statement]
	 */
	private void deleteTableStatement(FD_EnvData env, Element xmlData, StringBuffer sql) {
		//テーブル情報取得
		NodeList entry = xmlData.getElementsByTagName("entry");
		NodeList datas = ((Element) entry.item(0)).getElementsByTagName("data");
		Element data = (Element) datas.item(0);
		String tableName = data.getAttribute("table");
		//SQL文編集
		sql.append("DROP TABLE IF EXISTS ").append(tableName).append(";");
	}

	/**
	 * TODO 要検討 別クラス化(2021/04/29)
	 * テーブル生成SQLステートメント生成[SQL statement generation to generate table]
	 * @author officina-hide.com
	 * @since 1.00 2021/04/29
	 * @param env 環境情報[Environment Information]
	 * @param xmlData[XML Information]
	 * @param sql SQLステートメント[SQL Statement]
	 */
	private void createTableStatement(FD_EnvData env, Element xmlData, StringBuffer sql) {
		//テーブル情報取得
		NodeList entry = xmlData.getElementsByTagName("entry");
		NodeList datas = ((Element) entry.item(0)).getElementsByTagName("data");
		Element data = (Element) datas.item(0);
		String tableName = data.getAttribute("table");
		String tableComment = data.getAttribute(ENTRY_DATA_FD_DisplayName);
		sql.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");
		//項目設定
		NodeList columns = xmlData.getElementsByTagName("column");
		for(int ix = 0; ix < columns.getLength(); ix++) {
			Element column = (Element) columns.item(ix);
//			System.out.println(column.getAttributes().item(1).getTextContent());
			if(ix > 0) {
				sql.append(",");
			}
			sql.append(column.getAttribute(COLUMN_NAME)).append(" ");
			//項目種別
			switch(column.getAttribute(COLUMN_TYPE)) {
			case Item_Value_Type_ID:
				sql.append("int(10) unsigned NOT NULL").append(" ");
				break;
			case Item_Value_Type_String:
				sql.append("varchar(").append(column.getAttribute(COLUMN_LENGTH)).append(")").append(" ");
				break;
			case Item_Value_Type_Date:
				sql.append("datetime").append(" ");
				break;
			case Item_Value_Type_Text:
				sql.append("text").append(" ");
			}
			//コメント
			sql.append("COMMENT ").append(FD_SQ).append(column.getAttribute(COLUMN_COMMENT)).append(FD_SQ).append(" ");
		}
		sql.append(") ");
		sql.append("ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=")
			.append(FD_SQ).append(tableComment).append(FD_SQ).append(";");
	}

	public String createSqlStatement(FD_EnvData env, String createType, FD_Items items) {
		StringBuffer sql = new StringBuffer();
		return sql.toString();
	}

}

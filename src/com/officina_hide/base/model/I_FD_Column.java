package com.officina_hide.base.model;

/**
 * テーブル項目インターフェースクラス[Table item interface class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/02
 */
public interface I_FD_Column {

	/** テーブル名 */
	public final static String Table_Name = "FD_Column";
	/** テーブル情報ID */
	public final static int Table_ID = 104;
	/** 項目情報 */
	/** テーブル項目情報ID */
	public final static String COLUMNNAME_FD_Column_ID = Table_Name + "_ID";
	/** テーブル項目識別名 */
	public final static String COLUMNNAME_FD_Column_Name = "FD_Column_Name";
	/** 辞書情報ID */
	public final static String COLUMNNAME_FD_DataDictionnary_ID = "FD_DataDictionnary_ID";
	/** テーブル情報ID */
	public final static String COLUMNNAME_FD_Table_ID = "FD_Table_ID";
	/** テーブル項目属性 */
	public final static String COLUMNNAME_FD_Type_ID = "FD_Type_ID";
}

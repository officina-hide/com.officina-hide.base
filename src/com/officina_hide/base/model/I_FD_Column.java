package com.officina_hide.base.model;

/**
 * テーブル項目情報インターフェースクラス[Table column information interface class]<br>
 * @author officina-hide.com
 * @version 1.50 新規作成[New create]
 * @since 2022/04/19 Ver. 1.50
 */
public interface I_FD_Column extends I_FD_DB {
	
	/** テーブル名 */
	public static final String Table_Name = "FD_Column";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "テーブル項目情報";
	
	/** 項目 : テーブル項目情報ID */
	public static final String COLUMNNAME_FD_Column_ID = Table_Name + "_ID";
	public static final String NAME_FD_Column_ID = Table_Disp_Name + "ID";
}

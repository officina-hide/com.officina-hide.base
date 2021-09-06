package com.officina_hide.base.model;

/**
 * 種別情報インターフェースクラス[Type information interface class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/06
 */
public interface I_FD_Type {

	/** テーブル名 */
	public final static String Table_Name = "FD_Type";
	/** テーブル情報ID */
	public final static int Table_ID = 105;
	
	/** 項目 */
	/** 種別情報ID */
	public final static String COLUMNNAME_FD_Type_ID = Table_Name + "_ID";
	/** 種別情報識別名 */
	public final static String COLUMNNAME_FD_Type_Name = "FD_Type_Name";
	/** 名前 */
	public final static String COLUMNNAME_FD_Name = "FD_Name";
	
}

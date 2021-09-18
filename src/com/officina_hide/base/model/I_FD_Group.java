package com.officina_hide.base.model;

/**
 * グルーブ情報インターフェースクラス[Group information interface class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/16
 */
public interface I_FD_Group extends I_FD_DB {
	
	/** テーブル識別名 */
	public static final String Table_Name = "FD_Group";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "グルーブ情報";

	/** 項目 : グループ情報ID */
	public static final String COLUMNNAME_FD_Group_ID = Table_Name + "_ID";
	public static final String  NAME_FD_Group_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FD_Group_ID = "管理グループを識別する情報ID";
}

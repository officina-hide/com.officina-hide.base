package com.officina_hide.base.model;

/**
 * グルーブ情報インターフェースクラス[Group information interface class]<br>
 * @author officina-hide.com
 * @version 1.50 新規作成
 * @since 2022/03/22 Ver. 1.50
 */
public interface I_FD_Group {
	
	/** テーブル名 */
	public static final String Table_Name = "FD_Group";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "グルーブ情報";

	/** 項目 : グループ情報ID */
	public static final String COLUMNNAME_FD_Group_ID = Table_Name + "_ID";
	public static final String  NAME_FD_Group_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FD_Group_ID = "管理グループを識別する情報ID";

}

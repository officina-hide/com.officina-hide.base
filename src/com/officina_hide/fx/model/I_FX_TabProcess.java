package com.officina_hide.fx.model;

import com.officina_hide.base.model.I_FD_DB;

/**
 * タブ処理情報インターフェースクラス[Tab process information interface class]<br>
 * @author officine-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/09 Ver. 1.00
 */
public interface I_FX_TabProcess extends I_FD_DB {
	
	/** テーブル名 */
	public static final String Table_Name = "FX_TabProcess";
	/** テーブル表示名 */
	public static final String Table_Disp_Name = "FXタブ処理情報";
	/** テーブル説明 */
	public static final String Table_Comment = "タブ上でボタン等の処理情報を管理する。";
	public static final String Table_Comment_Eng = "Manage processing information such as buttons on tabs.";
	/** テーブル情報ID */
	public final static long Table_ID = 204;
	
	/** 項目 : タブ処理情報ID */
	public static final String COLUMNNAME_FX_TabProcess_ID = Table_Name + "_ID";
	public static final String NAME_FX_TabProcess_ID = Table_Disp_Name + "ID";
	public static final String COMMENT_FX_TabProcess_ID = "タブ処理情報を識別する為の情報ID";
	/** 項目 : タブ情報ID */
	public static final String COLUMNNAME_FX_Tab_ID = I_FX_Tab.COLUMNNAME_FX_Tab_ID;
	public static final String NAME_FX_Tab_ID = I_FX_Tab.NAME_FX_Tab_ID;
	
	

}

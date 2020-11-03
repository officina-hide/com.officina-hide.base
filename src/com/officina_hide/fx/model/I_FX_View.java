package com.officina_hide.fx.model;

/**
 * Fx画面情報インターフェース<br>
 * @author officine-hide.com
 * @version 1.10
 * @since 2020/10/29
 */
public interface I_FX_View {

	/** Fx画面情報 : テーブル名 */
	public final String Table_Name = "FX_View";
	/** Fx画面情報 : テーブル表示名 */
	public final String NAME = "Fx画面情報";
	/** Fx画面情報 : テーブル説明 */
	public final String COMMENT = "画面を管理する為の情報";
	/** テーブル情報 : テーブル情報ID */
	public final int TABLE_ID = 201;
	
	/** Fx画面情報ID */
	public final String COLUMNNAME_FX_View_ID = Table_Name + "_ID";
	public final String NAME_FX_View_ID = "Fx画面情報ID";
	public final String COMMENT_FX_View_ID = "Fx画面を識別する為の情報ID";
	/** Fx画面名 */
	public final String COLUMNNAME_View_Name = "View_Name";
	public final String NAME_View_Name = "Fx画面名";
	public final String COMMENT_View_Name = "Fx画面の識別名称";
	/** Fx画面表示名 */
	public final String COLUMNNAME_FD_Name = "FD_Name";
	public final String NAME_FD_Name = "Fx画面表示名";
	public final String COMMENT_FD_Name = "Fx画面の名称";
}

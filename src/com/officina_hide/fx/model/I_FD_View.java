package com.officina_hide.fx.model;

/**
 * Fx画面情報インターフェースクラス[Fx screen information interface class]<br>
 * @author officine-hide.com
 * @version 1.31
 * @since 2021/02/04
 */
public interface I_FD_View {
	
	/** Fx画面情報 : テーブル名 */
	public final String Table_Name = "FD_View";
	/** Fx画面情報 : テーブル表示名 */
	public final String NAME = "Fx画面情報";
	/** Fx画面情報 : テーブル説明 */
	public final String COMMENT = "Fx画面を管理する為の情報";
	/**Fx画面情報 : テーブル情報ID */
	public final int TABLE_ID = 301;

	//テーブル項目情報
	/** Fx画面情報ID */
	public final String COLUMNNAME_FD_View_ID = Table_Name + "_ID";
	public final String NAME_FD_View_ID = NAME + "ID";
	public final String COMMENT_FD_View_ID = NAME + "を識別するための情報ID";
	/** Fx画面名 */
	public final String COLUMNNAME_View_Name = "View_Name";
	public final String NAME_View_Name = "Fx画面名";
	public final String COMMENT_View_Name = "Fx画面の名称";
}

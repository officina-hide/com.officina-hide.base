package com.officina_hide.fx.model;

/**
 * Fx画面項目情報インターフェースクラス[Fx screen item information interface class]<br>
 * @author officina-hide.com
 * @version 1.31
 * @since 2021/02/20
 */
public interface I_FD_ViewColumn {

	/** Fx画面項目情報 : テーブル名 */
	public final String Table_Name = "FD_ViewColumn";
	/** Fx画面項目情報 : テーブル表示名 */
	public final String NAME = "Fx画面項目情報";
	/** Fx画面項目情報 : テーブル説明 */
	public final String COMMENT = "Fx画面項目を管理する為の情報";
	/**Fx画面項目情報 : テーブル情報ID */
	public final int TABLE_ID = 302;

	//テーブル項目情報
	//Fx画面項目情報ID
	public final String COLUMNNAME_FD_ViewColumn_ID = Table_Name + "_ID";
	public final String NAME_FD_ViewColumn_ID = NAME + "_ID";
	public final String COMMENT_FD_ViewColumn_ID = NAME + "を識別するための情報ID";
	//Fx画面情報ID
	public final String COLUMNNAME_FD_View_ID = I_FD_View.Table_Name + "_ID";
	public final String NAME_FD_View_ID = I_FD_View.NAME + "ID";
	public final String COMMENT_FD_View_ID = "Fx画面項目情報が表示される画面の情報ID";
	//項目名
	public final String COLUMNNAME_ViewColumn_Name = "ViewColumn_Name";
	public final String NAME_ViewColumn_Name = "Fx画面項目名";
	public final String COMMENT_ViewColumn_Name = "Fx画面項目の物理名";
	//表示名
	public final String COLUMNNAME_FD_Name = "FD_Name";
	public final String NAME_FD_Name = "Fx画面項目表示名";
	public final String COMMENT_FD_Name = "Fx画面の標準ラベルとして使用される表示名";
}

package com.officina_hide.base.model;

/**
 * ログ情報インターフェース<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/10/20
 */
public interface I_FD_Log {
	
	/** ログ情報 : テーブル名 */
	public final String Table_Name = "FD_Log";
	/** ログ情報 : テーブル表示名 */
	public final String NAME = "ログ情報";
	/** ログ情報 : テーブル説明 */
	public final String COMMENT = "ログを管理する為の情報";
	/** ログ情報 : テーブル情報ID */
	public final int TABLE_ID = 108;

	//テーブル項目情報
	/** ログ情報ID */
	public final String COLUMNNAME_FD_Log_ID = Table_Name + "_ID";
	public final String NAME_FD_Log_ID = "ログ情報ID";
	public final String COMMENT_FD_Log_ID = "ログ情報を識別する為の情報ID";
	/** ログ種別ID（リファレンス情報ID） */
	public final String COLUMNNAME_Log_Type_ID = "Log_Type_ID";
	public final String NAME_Log_Type_ID = "ログ情報種別ID";
	public final String COMMENT_Log_Type_ID = "ログの種別を識別する為の情報ID（リファレンス情報ID）";
	/** ログ内容 */
	/*
	 * ログの内容には以下のものがある。<br>
	 * ・(Type = 'Info'(201)) メッセージ...メッセージ情報のIDとパラメータ情報（一部階層化を検討する）
	 * ・(Type = 'DB'(202)) DB更新内容...ダンプを作る際のベース情報となる。
	 */
	public final String COLUMNNAME_Log_Data = "Log_Data";
	public final String NAME_Log_Data = "ログ内容";
	public final String COMMENT_Log_Data = "ログの内容を格納する。";
	
	//ログ種別
	/** DB */
	public final int LOGTYPE_Info_ID = 201;
	public final int LOGTYPE_Table_Drop_ID = 211;
	public final int LOGTYPE_Table_Create_ID = 212;
	public final int LOGTYPE_Data_Update = 213;
	
	//ログ情報ID初期値
	public final int INITIAL_LOG_ID = 10000001;
}

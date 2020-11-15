package com.officina_hide.base.model;

/**
 * プロセス情報インターフェース<br>
 * @author officine-hide.com
 * @version 1.10
 * @since 2020/10/29
 */
public interface I_FD_Process {

	/** プロセス情報 : テーブル名 */
	public final String Table_Name = "FD_Process";
	/** プロセス情報 : テーブル表示名 */
	public final String NAME = "プロセス情報";
	/** プロセス情報 : テーブル説明 */
	public final String COMMENT = "プロセスを管理する為の情報";
	/** プロセス情報 : テーブル情報ID */
	public final int TABLE_ID = 107;
	
	/** プロセス情報ID */
	public final String COLUMNNAME_FD_Process_ID = "FD_Process_ID";
	public final String NAME_FD_Process_ID = "プロセス情報ID";
	public final String COMMENT_FD_Process_ID = "プロセス情報を識別するための情報ID";
	/** プロセス名 */
	public final String COLUMNNAME_Process_Name = "Process_Name";
	public final String NAME_Process_Name = "プロセス名";
	public final String COMMENT_Process_Name = "プロセスの実行クラス名";
	/** プロセス開始時刻 */
	public final String COLUMNNAME_Process_StartTime = "Process_StartTime";
	public final String NAME_Process_StartTime = "プロセス開始日時";
	public final String COMMENT_Process_StartTime = "プロセスの開始時刻";
	/** プロセス終了時刻 */
	public final String COLUMNNAME_Process_EndTime = "Process_EndTime";
	public final String NAME_Process_EndTime = "プロセス終了時刻";
	public final String COMMENT_Process_EndTime = "プロセスの終了時間";
	
}

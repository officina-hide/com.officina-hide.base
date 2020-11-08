package com.officina_hide.project.model;

/**
 * タスク情報インターフェース<br>
 * @author officine-hide.com
 * @version 1.20
 * @since 2020/11/09
 */
public interface I_FD_Task {

	/** タスク情報 : テーブル名 */
	public final String Table_Name = "FD_Task";
	/** タスク情報 : テーブル表示名 */
	public final String NAME = "タスク情報";
	/** タスク情報 : テーブル説明 */
	public final String COMMENT = "タスクを管理する為の情報";
	/** タスク情報 : テーブル情報ID */
	public final int TABLE_ID = 302;

	/** タスク情報ID */
	public final String COLUMNNAME_FD_Task_ID = Table_Name + "_ID";
	public final String NAME_FD_Task_ID = "タスク情報ID";
	public final String COMMENT_FD_Task_ID = "タスク情報を識別する為の情報ID";
	/** タスク番号 */
	public final String COLUMNNAME_Task_Number = "Task_Number";
	public final String NAME_Task_Number = "タスク番号";
	public final String COMMENT_Task_Number = "タスクを識別する為の表示番号（書式はシステムコンフィグで設定する）";
}

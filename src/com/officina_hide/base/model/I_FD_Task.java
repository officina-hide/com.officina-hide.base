package com.officina_hide.base.model;

/**
 * タスク情報インタフェースクラス[Task information interface class.]<br>
 * @author officina-hide.com
 * @version 1.30
 * @since 2021/01/04
 */
public interface I_FD_Task {

	/** タスク情報 : テーブル名 */
	public final String Table_Name = "FD_Task";
	/** タスク情報 : テーブル表示名 */
	public final String NAME = "タスク情報";
	/** タスク情報 : テーブル説明 */
	public final String COMMENT = "タスクを管理する為の情報";
	/** タスク情報 : テーブル情報ID */
	public final int TABLE_ID = 201;
	
	//テーブル項目情報
	//タスク情報ID
	public final String COLUMNNAME_FD_Task_ID = Table_Name + "_ID";
	public final String NAME_FD_Task_ID = NAME + "ID";
	public final String COMMENT_FD_Task_ID = NAME + "を識別するための情報ID";
	//タスク件名[Task subject]
	public final String COLUMNNAME_Task_Subject = "Task_Subject";
	public final String NAME_Task_Subject = "タスク件名";
	public final String COMMENT_Task_Subject = "タスク情報の表示件名";
	//タスク状態[Task Status]
	public final String COLUMNNAME_Task_Status = "Task_Status";
	public final String NAME_Task_Status = "タスク状態";
	public final String COMMENT_Task_Status = "タスク情報の処理状況";
	public final String COMMENT_Task_Status_Reference = "タスク状態を管理するリファレンス情報の情報ID";
	//タスク開始時間[Task start time]
	public final String COLUMNNAME_Task_StartDateTime = "Task_StartDateTime";
	public final String NAME_Task_StartDateTime = "タスク開始日時";
	public final String COMMENT_Task_StartDateTime = "タスクの処理開始された日時";
}

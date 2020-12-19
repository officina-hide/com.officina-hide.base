package com.officina_hide.workshop.task.tasklist;

import java.util.Calendar;

import com.officina_hide.base.common.FD_Date;

/**
 * タスクリスト用情報クラス[Information class for task list]<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/12/17
 */
public class TaskTableData {

	public TaskTableData(String title, Calendar start_Date) {
		setTitle(title);
		startDate.setDate(start_Date);
	}

	private String title;
	private FD_Date startDate = new FD_Date();
	
	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title セットする title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	public FD_Date getStartDate() {
		return startDate;
	}
}

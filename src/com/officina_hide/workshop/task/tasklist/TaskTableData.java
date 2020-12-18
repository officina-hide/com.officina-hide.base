package com.officina_hide.workshop.task.tasklist;

import java.util.Calendar;

/**
 * タスクリスト用情報クラス[Information class for task list]<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2020/12/17
 */
public class TaskTableData {

	public TaskTableData(String title, Calendar startDate) {
		setTitle(title);
		setStartDate(startDate);
	}

	private String title;
	private Calendar startDate;
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
	/**
	 * @return startDate
	 */
	public String getStartDate() {
		if(startDate == null) {
			return "";
		}
		return null;
	}
	/**
	 * @param startDate セットする startDate
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
}

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
		setCheck(true);
	}

	private Boolean check;
	private String status;
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
	/**
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status セットする status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean getCheck() {
		return check;
	}
	public void setCheck(Boolean check) {
		this.check = check;
	}
}

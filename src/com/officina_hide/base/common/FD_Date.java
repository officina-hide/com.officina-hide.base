package com.officina_hide.base.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 日付クラス
 * @author ueno
 *
 */
public class FD_Date {

	private Locale locale = new Locale("ja", "JP");
	private Calendar cal  = new GregorianCalendar(locale);
	
	/**
	 * toString書式<br>
	 * <p>初期値として「年月日時分秒」をセットする。</p>
	 */
	private String formatString = "yyyy/MM/dd HH:mm:ss";

	@Override
	public String toString() {
		//日付書式にしたがって編集した日付を文字列として返す。
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		return df.format(cal.getTime());
	}

	/**
	 * 日付をセットする（calendar型）<br>
	 * @author officine-hide.com
	 * @since 1.30 2020/12/19
	 * @param date 日付
	 */
	public void setDate(Calendar date) {
		this.cal = date;
	}
	
}

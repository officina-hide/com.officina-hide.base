package com.officina_hide.base.tests;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.officina_hide.base.common.FD_Date;

/**
 * FD_Dateクラステスト<br>
 * @author officine-hide.com
 * @version 1.30
 * @since 2020/12/19
 */
public class FD_Date_Test {

	public static void main(String[] args) {
		FD_Date date = new FD_Date();
		System.out.println(date.toString());
		Calendar caltest = new GregorianCalendar(new Locale("ja", "JP"));
		caltest.add(Calendar.YEAR, 1);
		date.setDate(caltest);
		System.out.println(date.toString());
		
	}

}

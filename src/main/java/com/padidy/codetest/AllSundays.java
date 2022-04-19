package com.padidy.codetest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AllSundays {


	public static void main(String[] args) {
		var st = getAllSundays("01-05-2021", "30-05-2021");
		System.out.println(st == 5);

	}

	public static int getAllSundays(String startDate, String endDate) {
		return getAllSundays(startDate, endDate, "dd-MM-yyyy");
	}

	public static int getAllSundays(String startDate, String endDate, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			return getAllSunday(simpleDateFormat.parse(startDate), simpleDateFormat.parse(endDate));
		} catch (Exception e) {
			return -1;
		}
	}


	public static int getAllSunday(Date startDate, Date endDate) {
		Calendar from = Calendar.getInstance();
		from.setTime(startDate);
		Calendar to = Calendar.getInstance();
		to.setTime(endDate);
		int sundays = 0;
		while (to.after(from)) {
			if (from.get(Calendar.DAY_OF_WEEK) == 7) {
				sundays += 1;
			}
			from.add(Calendar.DATE, 1);
		}
		return sundays;
	}
}

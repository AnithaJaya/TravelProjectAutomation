package com.automation.budget.commonutilities;

import com.ibm.icu.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDate {

	
	public static String getFutureDate(int dayCount)
	{
		
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, dayCount);
		dt = c.getTime();
		return new SimpleDateFormat("MM/dd/yyyy").format(dt);
	}
	
	public static String getCurrentDate() {
		
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String currentDate = formatter.format(date);
		System.out.println("Current Date is "+currentDate );
		return currentDate;
		
	}
	
}

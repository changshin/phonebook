package com.chang.ng.phone.utils;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CommonUtils {

	private static final Logger LOGGER = LogManager	.getLogger(CommonUtils.class);

	public static final String MM_DD_YYYY_DATE_FORMAT = "M/dd/yyyy";
	public static final String MMDDYYYY_DATE_FORMAT = "Mddyyyy";
	public static final String MMDDYYYY = "Mddyyyy";
	public static final String M_DD_YYYY_HYPHEN = "M-dd-yyyy";
	public static final String MM_DD_YYYY_HYPHEN = "MM-dd-yyyy";
	public static final String DD_MM_YYYY_HYPHEN = "dd-MM-yyyy";
	public static final String YYYY_MM_DD_HYPHEN = "yyyy-MM-dd";
	public static final String mmddyyyy_DATE_FORMAT = "MMddyyyy";
	public static final String MM_DD_YYYY_HH_MM_SS_HYPHEN = "M-dd-yyyy HH:MM:ss";
	public static final String YYYYMMDD = "yyyyMMdd";
	//first.last+??@gmail.com has invalid
	//private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String EMAIL_PATTERN   = "^[+_A-Za-z0-9-]+(\\.[+_A-Za-z0-9-]+)*@[+A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,4})$";
	private static final String[] spechChars = {"/","\\\\",":","\\*","\\?","\"","<",">","%","\\|"};

	public CommonUtils() {
	}

	// get fileName remove special characters( \ / : * ? " < > |
	public static String getRemoveSystemCharactersForFilename(String fileName) {
		if  ( fileName == null ) {
			return "";
		}
		String value = null;
		for ( int i = 0; i < spechChars.length; i++ ) {
			value = spechChars[i];
			//System.out.println("value="+value+"="+fileName);
			fileName = fileName.replaceAll(value," ");
		}
		while (fileName.indexOf("  ") > -1 ) {
			fileName = fileName.replaceAll("  "," ");
		}; 
		return fileName.trim();
	}	// end of getRemoveSystemCharactersForFilename()
	
	public static String toUTF8(String value) {
		if  ( value == null ) {
			return null;
		}
		try {
			return new String(value.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception ex ) {
			
		}
		return value;
	}
	public static Date getDate(final String strDate, final String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		if (strDate != null && !strDate.trim().equals("")) {
			try {
				date = sdf.parse(strDate);
			} catch (ParseException pe) {
				LOGGER.error("Date unable to parse", pe);
			}
		}
		return date;
	}

	public static Date toDate(String strDate) {
		if  ( strDate == null || strDate.length() != 10 ) {
			return null;
		}
		strDate = strDate.replaceAll("/","-");
		if (strDate.substring(4, 5).equals("-")) {// yyyy-MM-dd
			return getDate( strDate,YYYY_MM_DD_HYPHEN);
		} 
		return getDate( strDate,MM_DD_YYYY_HYPHEN);
	}

	public static Date getCurrentDate() {
		Date date = new java.util.Date(System.currentTimeMillis());
		return date;
	}

	public static int getYear(final Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
	
	public static int getDateOfMonth(final Date date) {
		if  ( date == null ) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static Calendar getCalendar() {
		Calendar cal = Calendar.getInstance();
		return cal;
	}
	
	public static String getCurrentMonthName() {
		DateFormatSymbols dfs = new DateFormatSymbols();
		String months[] = dfs.getMonths();
		return months[getCalendar().get(Calendar.MONTH)];
	}

	public static Date getCurrentMonthStartDate() {
		Calendar cal = getCalendar();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		setTimeToBeginningOfDay(cal);
		return cal.getTime();
	}

	public static Date getCurrentMonthEndDate() {
		Calendar cal = getCalendar();
		cal.set(Calendar.DAY_OF_MONTH,	cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		setTimeToEndOfDay(cal);
		return cal.getTime();
	}

	private static void setTimeToBeginningOfDay(final Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}

	private static void setTimeToEndOfDay(final Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
	}

	public static Date setTimeToEndOfDay(final Date date) {
		Calendar cal = null;
		if(date!=null) {
			cal = CommonUtils.getCalendar();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
//			cal.set(Calendar.MILLISECOND, 999);
			return cal.getTime();
		}
		return date;
	}
	
	public static int getCurrentWeekOfYear() {
		Calendar cal = getCalendar();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	public static Date getCurrentWeekStartDate() {
		Calendar cal = getCalendar();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		setTimeToBeginningOfDay(cal);
		return cal.getTime();
	}

	public static Date getWeekStartDate(final Date date) {
		Calendar cal = getCalendar();
		cal.setTime(date);
		if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
			cal.add(Calendar.DATE, -1);
		}
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		setTimeToBeginningOfDay(cal);
		return cal.getTime();
	}
	
	public static Date getMonthStartDate(final Date date) {
		Calendar cal = getCalendar();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		setTimeToBeginningOfDay(cal);
		return cal.getTime();
	}
	
	public static Date getMonthEndDate(final Date date) {
		Calendar cal = getCalendar();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH,	cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		setTimeToBeginningOfDay(cal);
		return cal.getTime();
	}
	
	public static String getWeekInYear(final Date date) {
		String weekName = CommonUtils.formatDate(date, M_DD_YYYY_HYPHEN);
		return weekName;
	}
	
	
	
	public static String getMonth(final Date date) {
		DateFormatSymbols dfs = new DateFormatSymbols();
		String months[] = dfs.getMonths();
		Calendar cal = getCalendar();
		cal.setTime(date);
		return months[cal.get(Calendar.MONTH)];
	}
	
	
	
	public static Date getWeekEndDate(final Date date) {
		Calendar cal = getCalendar();
		cal.setTime(date);
		if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
			cal.add(Calendar.DATE, -1);
		}
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		setTimeToBeginningOfDay(cal);
		cal.add(Calendar.DAY_OF_WEEK, 6);
		return cal.getTime();
	}
	
	public static Date getCurrentWeekEndDate() {
		Calendar cal = getCalendar();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.add(Calendar.DAY_OF_WEEK, 6);
		setTimeToEndOfDay(cal);
		return cal.getTime();
	}

	public static Date getCurrentQuarterStartDate() {
		Calendar cal = getCalendar();
		if (getCurrentQuater() == 1) {
			cal.set(Calendar.MONTH, Calendar.JANUARY);
		} else if (getCurrentQuater() == 2) {
			cal.set(Calendar.MONTH, Calendar.APRIL);
		} else if (getCurrentQuater() == 3) {
			cal.set(Calendar.MONTH, Calendar.JULY);
		} else {
			cal.set(Calendar.MONTH, Calendar.OCTOBER);
		}
		cal.set(Calendar.DATE, 1);
		setTimeToBeginningOfDay(cal);
		return cal.getTime();
	}

	public static Date getCurrentQuarterEndDate() {
		Calendar cal = getCalendar();
		if (getCurrentQuater() == 1) {
			cal.set(Calendar.MONTH, Calendar.MARCH);
		} else if (getCurrentQuater() == 2) {
			cal.set(Calendar.MONTH, Calendar.JUNE);
		} else if (getCurrentQuater() == 3) {
			cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		} else {
			cal.set(Calendar.MONTH, Calendar.DECEMBER);
		}
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.DAY_OF_MONTH,	cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		setTimeToEndOfDay(cal);
		return cal.getTime();
	}
	
	public static Date getQuarterStartDate(final Date date) {
		Calendar cal = getCalendar();
		cal.setTime(date);
		int month = getQuater(date);
		if (month == 1) {
			cal.set(Calendar.MONTH, Calendar.JANUARY);
		} else if (month == 2) {
			cal.set(Calendar.MONTH, Calendar.APRIL);
		} else if (month == 3) {
			cal.set(Calendar.MONTH, Calendar.JULY);
		} else {
			cal.set(Calendar.MONTH, Calendar.OCTOBER);
		}
		cal.set(Calendar.DATE, 1);
		setTimeToBeginningOfDay(cal);
		return cal.getTime();
	}
	
	public static Date getQuarterEndDate(final Date date) {
		Calendar cal = getCalendar();
		cal.setTime(date);
		int month = getQuater(date);
		if (month == 1) {
			cal.set(Calendar.MONTH, Calendar.MARCH);
		} else if (month == 2) {
			cal.set(Calendar.MONTH, Calendar.JUNE);
		} else if (month == 3) {
			cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		} else {
			cal.set(Calendar.MONTH, Calendar.DECEMBER);
		}
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.DAY_OF_MONTH,	cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		setTimeToBeginningOfDay(cal);
		return cal.getTime();
	}
	
	public static int getQuater(final Date date) {
		Calendar cal = getCalendar();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH) + 1;
		if (month <= 3) {
			return 1;
		} else if (month <= 6) {
			return 2;
		} else if (month <= 9) {
			return 3;
		} else {
			return 4;
		}
	}

	public static int getCurrentQuater() {
		Calendar cal = getCalendar();
		int month = cal.get(Calendar.MONTH) + 1;
		if (month <= 3) {
			return 1;
		} else if (month <= 6) {
			return 2;
		} else if (month <= 9) {
			return 3;
		} else {
			return 4;
		}
	}

	public static Date getGivenDaysBackDateTime(Date date, final int days) {
		Calendar cal = getCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static Date getGivenDaysBackDate(final int days) {
		Calendar cal = getCalendar();
		cal.add(Calendar.DATE, days);
		setTimeToBeginningOfDay(cal);
		return cal.getTime();
	}
	
	public static Date getGivenWeeksBackDate(final int weeks) {
		Calendar cal = getCalendar();
		cal.add(Calendar.WEEK_OF_YEAR, weeks);
		setTimeToBeginningOfDay(cal);
		return cal.getTime();
	}
	
	public static Date getGivenMonthsBackDate(final int months) {
		Calendar cal = getCalendar();
		cal.add(Calendar.MONTH, months);
		setTimeToBeginningOfDay(cal);
		return cal.getTime();
	}

	public static Date getGivenMonthsBackDate(final int months, int days) {
		Calendar cal = getCalendar();
		cal.add(Calendar.MONTH, months);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		if  ( lastDay > days ) {
			cal.set(Calendar.DAY_OF_MONTH, days);
		} else {
			cal.set(Calendar.DAY_OF_MONTH, lastDay);
		}
		setTimeToBeginningOfDay(cal);
		return cal.getTime();
	}

	public static Date getGivenMonthsBackDateFirstDay(final int months) {
		Calendar cal = getCalendar();
		cal.add(Calendar.MONTH, months);
		cal.set(Calendar.DATE, 1);
		setTimeToBeginningOfDay(cal);
		return cal.getTime();
	}

	public static Date getGivenQuartersBackDate(final int quarters) {
		Calendar cal = getCalendar();
		cal.add(Calendar.MONTH, quarters*3);
		setTimeToBeginningOfDay(cal);
		return cal.getTime();
	}
	
	public static Date getGivenYearsBackDate(final int years) {
		Calendar cal = getCalendar();
		cal.add(Calendar.YEAR, years);
		setTimeToBeginningOfDay(cal);
		return cal.getTime();
	}

	public static Date getGivenYearsBackDate(final int years, int days) {
		Calendar cal = getCalendar();
		cal.add(Calendar.YEAR, years);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		if  ( lastDay > days ) {
			cal.set(Calendar.DAY_OF_MONTH, days);
		} else {
			cal.set(Calendar.DAY_OF_MONTH, lastDay);
		}
		setTimeToBeginningOfDay(cal);
		return cal.getTime();
	}

	public static Collection<Integer> getNoOfYears(final int noOfYears) {
		Calendar cal = getCalendar();
		Collection<Integer> yearList = new ArrayList<Integer>();
		int incNoOfYears = noOfYears;
		if(incNoOfYears>0) {
			while(incNoOfYears>0) {
				yearList.add(Integer.valueOf(cal.get(Calendar.YEAR)));
				cal.add(Calendar.YEAR, 1);
				incNoOfYears--;
			}
		} else {
			cal.add(Calendar.YEAR, noOfYears+1);
			while(incNoOfYears<0) {
				yearList.add(Integer.valueOf(cal.get(Calendar.YEAR)));
				cal.add(Calendar.YEAR, 1);
				incNoOfYears++;
			}
		}
		return yearList;
	}

	
	public static Date getCurrentWeekMondayStartDate() {
		Calendar cal = getCalendar();
		if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
			cal.add(Calendar.DATE, -1);
		}
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		setTimeToBeginningOfDay(cal);
		return cal.getTime();
	}
	
	public static Date getWeekMondayStartDate(final int weeks) {
		Calendar cal = getCalendar();
		cal.add(Calendar.WEEK_OF_YEAR, weeks);
		if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
			cal.add(Calendar.DATE, -1);
		}
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		setTimeToBeginningOfDay(cal);
		return cal.getTime();
	}	

	
	public static Set<String> getGivenDaysBackAllDates(final int days, final String format) {
		Set<String> dateSet = new LinkedHashSet<String>();
		Calendar cal = getCalendar();
		if(days>0) {
			setTimeToBeginningOfDay(cal);
			for(int dayInc=days;dayInc>=0;dayInc--) {
				dateSet.add(formatDate(cal.getTime(),format));
				cal.add(Calendar.DATE, 1);
			}
		} else {
			cal.add(Calendar.DATE, days);
			setTimeToBeginningOfDay(cal);
			for(int dayInc=days;dayInc<=0;dayInc++) {
				dateSet.add(formatDate(cal.getTime(),format));
				cal.add(Calendar.DATE, 1);
			}
		}
		return dateSet;
	}

	public static String formatDate(final Date date, final String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String strDate = null;
		if (date != null) {
				strDate = sdf.format(date);
		}
		return strDate;
	}

	public static String concat(final String str1, final String str2) {
		StringBuffer sb = new StringBuffer();
		if(str1!=null) {
			sb.append(str1);
		}
		if(str2!=null) {
			sb.append(str2);
		}
		return sb.toString();
	}

	public static String concat(final String str1, final String str2, final String str3) {
		StringBuffer sb = new StringBuffer();
		if(str1!=null) {
			sb.append(str1);
		}
		if(str2!=null) {
			sb.append(str2);
		}
		if(str3!=null) {
			sb.append(str3);
		}
		return sb.toString();
	}
	
	public static String concat(final String str1, final String str2, final String str3, final String str4) {
		StringBuffer sb = new StringBuffer();
		if(str1!=null) {
			sb.append(str1);
		}
		if(str2!=null) {
			sb.append(str2);
		}
		if(str3!=null) {
			sb.append(str3);
		}
		if(str4!=null) {
			sb.append(str4);
		}
		return sb.toString();
	}

	public static String concat(final String str1, final String str2, final String str3, final String str4, final String str5) {
		StringBuffer sb = new StringBuffer();
		if(str1!=null) {
			sb.append(str1);
		}
		if(str2!=null) {
			sb.append(str2);
		}
		if(str3!=null) {
			sb.append(str3);
		}
		if(str4!=null) {
			sb.append(str4);
		}
		if(str5!=null) {
			sb.append(str5);
		}
		return sb.toString();
	}

	public static String concat(final String str1, final String str2, final String str3, final String str4, final String str5, final String str6) {
		StringBuffer sb = new StringBuffer();
		if(str1!=null) {
			sb.append(str1);
		}
		if(str2!=null) {
			sb.append(str2);
		}
		if(str3!=null) {
			sb.append(str3);
		}
		if(str4!=null) {
			sb.append(str4);
		}
		if(str5!=null) {
			sb.append(str5);
		}
		if(str6!=null) {
			sb.append(str6);
		}
		return sb.toString();
	}

	public static String getHashKey(Date date, int ageGroup, byte gender) {
		StringBuffer sb = new StringBuffer();
		if(date!=null) {
			sb.append(formatDate(date,MMDDYYYY));
			sb.append(ageGroup);
			sb.append(gender);
		}
		return sb.toString();
	}
	
	public static String getHashKey(Date date, int platformId) {
		StringBuffer sb = new StringBuffer();
		if(date!=null) {
			sb.append(formatDate(date,MMDDYYYY));
			sb.append(platformId);
		}
		return sb.toString();
	}

	public static int getCurrentAge(final Date date) {
		int age = 0;
		Calendar ageCal = Calendar.getInstance();
		Calendar currCal = Calendar.getInstance();
		if(date!=null) {
			ageCal.setTime(date);
			age = currCal.get(Calendar.YEAR) - ageCal.get(Calendar.YEAR);
		} 
		return age;
	}
	
	public static long getDifferenceDays(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public static long compareByDates(Date fromDate, Date toDate) {
		long diff = 0;
		Calendar calFrom = CommonUtils.getCalendar();
		Calendar calTo = CommonUtils.getCalendar();
		if(fromDate!=null && toDate!=null) {
			calFrom.setTime(fromDate);
			setTimeToBeginningOfDay(calFrom);
			calTo.setTime(toDate);
			setTimeToBeginningOfDay(calTo);
			diff = calFrom.getTimeInMillis()-calTo.getTimeInMillis(); 
		}
		if(diff>0) {
			return 1;
		} else if(diff<0) {
			return -1;
		} else {
			return 0;
		}
	}

	public static boolean validateEmail(String email) {
		Pattern pattern = null;
		Matcher matcher = null;
		boolean flag = false;
		try {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(email);
			flag = matcher.matches();
		} catch (Exception ex) {
			LOGGER.error("Error in CommonUtils::validateEmail at ", ex);
		}
		return flag;
	}
	
	public static boolean isNullOrBlank(String value) {
		boolean flag = false;
		if(value==null || value.trim().equals("")) {
			flag = true;
		}
		return flag;
	}
	
	public static boolean isNull(Object obj) {
		boolean flag = false;
		if(obj==null) {
			flag = true;
		}
		return flag;
	}

	public static void main(String[] args) {
		System.out.println("Curent Date\t\t:"+getCurrentDate());
		System.out.println("Six Days Back\t\t:"+getGivenDaysBackDate(-6));
		System.out.println("Six Days Back As String\t:"+getGivenDaysBackAllDates(-6, M_DD_YYYY_HYPHEN).toString());
		System.out.println("Six Days After As String\t:"+getGivenDaysBackAllDates(6, M_DD_YYYY_HYPHEN).toString());
		System.out.println("Curent Week Start Date\t:"+getCurrentWeekStartDate());
		System.out.println("Curent Week End Date\t:"+getCurrentWeekEndDate());
		System.out.println("Get Week Start Date:\t"+getCurrentWeekMondayStartDate());
		System.out.println("Get 3 Week's Back Start Date:\t"+getWeekMondayStartDate(-2));
		System.out.println("Six Weeks Back\t:"+getGivenWeeksBackDate(-6));
		System.out.println("Curent Month Name\t:"+getCurrentMonthName());
		System.out.println("Curent Month Start Date\t:"+getCurrentMonthStartDate());
		System.out.println("Curent Month End Date\t:"+getCurrentMonthEndDate());
		System.out.println("Six Month Back Date\t:"+getGivenMonthsBackDate(-6));
		System.out.println("Curent Quater\t\t:"+getCurrentQuater());
		System.out.println("Curent Quarter Start Date:"+getCurrentQuarterStartDate());
		System.out.println("Curent Quarter End Date\t:"+getCurrentQuarterEndDate());
		System.out.println("Seven Quarter Back Date\t:"+getGivenQuartersBackDate(-7));
		System.out.println("Curent Year\t\t:"+getGivenYearsBackDate(-3));
		System.out.println("Last No. of Years\t\t:"+getNoOfYears(-3));
		System.out.println("Last No. of Years\t\t:"+getNoOfYears(3));
		System.out.println("Given date \t:"+getDate("07/29/2015",MM_DD_YYYY_DATE_FORMAT));
		System.out.println("Given date of week start date\t:"+getWeekStartDate(getDate("07/29/2015",MM_DD_YYYY_DATE_FORMAT)));
		System.out.println("Given date of week end date\t:"+getWeekEndDate(getDate("07/29/2015",MM_DD_YYYY_DATE_FORMAT)));
		System.out.println("Get week in year for given date\t:"+getWeekInYear(getDate("07/03/2015",MM_DD_YYYY_DATE_FORMAT)));
		System.out.println("Get given month start date\t:"+getMonthStartDate(getDate("05/30/2015",MM_DD_YYYY_DATE_FORMAT)));
		System.out.println("Get given month end date\t:"+getMonthEndDate(getDate("05/15/2015",MM_DD_YYYY_DATE_FORMAT)));
		System.out.println("Get month name\t:"+getMonth(getDate("05/15/2015",MM_DD_YYYY_DATE_FORMAT)));
		//System.out.println("Get quarter name\t:"+getQuarter(getDate("07/01/2014",MM_DD_YYYY_DATE_FORMAT)));
		System.out.println("Get given quarter start date\t:"+getQuarterStartDate(getDate("07/01/2014",MM_DD_YYYY_DATE_FORMAT)));
		System.out.println("Get given quarter end date\t:"+getQuarterEndDate(getDate("07/01/2014",MM_DD_YYYY_DATE_FORMAT)));
		System.out.println("Get given quarter start date\t:"+getQuarterStartDate(getQuarterStartDate(getDate("07/01/2014",MM_DD_YYYY_DATE_FORMAT))));
		System.out.println("Get given quarter end date\t:"+getQuarterEndDate(getQuarterEndDate(getDate("07/01/2014",MM_DD_YYYY_DATE_FORMAT))));
		System.out.println(getHashKey(getCurrentDate(), 1, (byte)1));
		System.out.println(getCurrentAge(getDate("01/29/1995",MM_DD_YYYY_DATE_FORMAT)));
		System.out.println("current date:"+getDate("07102015",mmddyyyy_DATE_FORMAT));
		
		System.out.println("Get week name change\t:"+getWeekInYear(new Date()));
		System.out.println("Get week name change\t:"+getWeekInYear(getDate("12/30/2014",MM_DD_YYYY_DATE_FORMAT)));
		System.out.println("dob test \t:"+CommonUtils.getCurrentAge(CommonUtils.getDate("09211990", CommonUtils.mmddyyyy_DATE_FORMAT)));
		System.out.println("Compare Dates\t:"+CommonUtils.compareByDates(getGivenDaysBackDate(-1), CommonUtils.getCurrentDate()));
		System.out.println("Compare Dates\t:"+CommonUtils.compareByDates(getGivenDaysBackDate(0), CommonUtils.getCurrentDate()));
		System.out.println("Compare Dates\t:"+CommonUtils.compareByDates(getGivenDaysBackDate(1), CommonUtils.getCurrentDate()));
		System.out.println("Validate Email ID\t:"+CommonUtils.validateEmail("snulagonda@innominds.com"));
		System.out.println("MM-DD-YYYY HH:MM:S\t:"+CommonUtils.formatDate(new Date(), MM_DD_YYYY_HH_MM_SS_HYPHEN));
		Date date = getDate("01/31/2016",MM_DD_YYYY_DATE_FORMAT);
		int dateOfMonth = CommonUtils.getDateOfMonth(date);
		System.out.println("dateOfMonth="+date +":"+dateOfMonth);
		System.out.println("CommonUtils.getGivenMonthsBackDate(1,31):"+CommonUtils.getGivenMonthsBackDate(1,dateOfMonth));
		System.out.println("CommonUtils.getGivenYearsBackDate(1):"+CommonUtils.getGivenYearsBackDate(1));
		System.out.println("CommonUtils.getGivenYearsBackDate(1,31):"+CommonUtils.getGivenYearsBackDate(1,dateOfMonth));
		System.out.println("CommonUtils.validateEmail(email):"+CommonUtils.validateEmail("changsub.shin+2@gmail.com"));
		System.out.println("CommonUtils.validateEmail(email):"+CommonUtils.validateEmail("changsub.shin+2@gmailcom"));
	}

	public static Date addYearToDate(Date startDate) {
		 Calendar cal = Calendar.getInstance();
		    cal.setTime(startDate);
		    cal.add(Calendar.YEAR, 1); //minus number would decrement the days
		    return cal.getTime();
	}
	public static Date addMonthToDate(Date startDate) {
		 Calendar cal = Calendar.getInstance();
		    cal.setTime(startDate);
		    cal.add(Calendar.MONTH, 1); //minus number would decrement the days
		    return cal.getTime();
	}

}

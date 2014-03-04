package cft.commons.core.test;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import cft.commons.core.constant.Constants;

public class DateTimeTest {

	@Test
	public void convertToString() {
		System.out.println("***************** Test format functions *********************");
		Date now = new Date();
		String dateString = DateFormatUtils.format(now, Constants.C_DATETIME_PATTERN_DEFAULT);
		System.out.println(dateString);
		
		String timeString = DateFormatUtils.format(now, Constants.C_TIME_PATTERN_DEFAULT);
		System.out.println(timeString);
	}

	@Test
	public void convertFromString() throws Exception {
		System.out.println("***************** Test parse functions **********************");
		String dateString = "1978-06-01 12:10:08";
		Date date = DateUtils.parseDate(dateString, new String[]{Constants.C_DATETIME_PATTERN_DEFAULT});
		System.out.println(date);
	}
	
	@Test
	public void addFunction() {
		Date date = new Date();
		System.out.println("***************** Test add functions ************************");
        System.out.println(DateUtils.addDays(date, 1));
        System.out.println(DateUtils.addHours(date, 1));
        System.out.println(DateUtils.addMinutes(date, 1));
        System.out.println(DateUtils.addSeconds(date, 1));
        
        System.out.println("***************** Test truncate functions *******************");
        //ceiling 取上限
        System.out.println(DateUtils.ceiling(date, Calendar.MINUTE));
        //truncatedEquals truncate之后进行比较
        System.out.println(DateUtils.round(date, Calendar.MINUTE));
        //truncate 类似Oracle SQL语句中的truncate函数
        System.out.println(DateUtils.truncate(date, Calendar.MINUTE));
        
        //另有 truncatedEquals truncateCompareTo truncate之后进行比较
        
        //toCalendar方法将Date装换成Calendar (java.util.GregorianCalendar)
        System.out.println((DateUtils.toCalendar(date)).getClass());
		
	}
	

	
}

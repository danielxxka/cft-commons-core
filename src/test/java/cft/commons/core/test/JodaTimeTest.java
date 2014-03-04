package cft.commons.core.test;

import static org.junit.Assert.assertEquals; 

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

public class JodaTimeTest {
	
	@Test
	public void common(){
		//初始化时间
		DateTime dateTime=new DateTime(2012, 12, 13, 18, 23,55);
		
		// 年,月,日,时,分,秒,毫秒  
        DateTime dt3 = new DateTime(2011, 2, 13, 10, 30, 50, 333);// 2010年2月13日10点30分50秒333毫秒
		
		//下面就是按照一点的格式输出时间
		String str2 = dateTime.toString("MM/dd/yyyy hh:mm:ss.SSSa");
		String str3 = dateTime.toString("dd-MM-yyyy HH:mm:ss");
		String str4 = dateTime.toString("EEEE dd MMMM, yyyy HH:mm:ssa");
		String str5 = dateTime.toString("MM/dd/yyyy HH:mm ZZZZ");
		String str6 = dateTime.toString("MM/dd/yyyy HH:mm Z");
		
		DateTimeFormatter format = DateTimeFormat .forPattern("yyyy-MM-dd HH:mm:ss");
		//时间解析  
		DateTime dateTime2 = DateTime.parse("2012-12-21 23:22:45", format);  
		  
		//时间格式化，输出==> 2012/12/21 23:22:45 Fri  
		String string_u = dateTime2.toString("yyyy/MM/dd HH:mm:ss EE");  
		System.out.println(string_u);  
		  
		//格式化带Locale，输出==> 2012年12月21日 23:22:45 星期五  
		String string_c = dateTime2.toString("yyyy年MM月dd日 HH:mm:ss EE",Locale.CHINESE);  
		System.out.println(string_c);
		
		DateTime dt1 = new DateTime();// 取得当前时间
		 
		// 根据指定格式,将时间字符串转换成DateTime对象,这里的格式和上面的输出格式是一样的  
	    DateTime dt2 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2012-12-26 03:27:39");
	    
	    //计算两个日期间隔的天数
	    LocalDate start=new LocalDate(2012, 12,14);  
		LocalDate end=new LocalDate(2013, 01, 15);  
		int days = Days.daysBetween(start, end).getDays();
		
		//计算两个日期间隔的小时数,分钟数,秒数
		
		//增加日期
		DateTime dateTime1 = DateTime.parse("2012-12-03");
		dateTime1 = dateTime1.plusDays(30);
		dateTime1 = dateTime1.plusHours(3);
		dateTime1 = dateTime1.plusMinutes(3);
		dateTime1 = dateTime1.plusMonths(2);
		dateTime1 = dateTime1.plusSeconds(4);
		dateTime1 = dateTime1.plusWeeks(5);
		dateTime1 = dateTime1.plusYears(3);
		
		// Joda-time 各种操作.....  
		dateTime = dateTime.plusDays(1) // 增加天  
		                    .plusYears(1)// 增加年  
		                    .plusMonths(1)// 增加月  
		                    .plusWeeks(1)// 增加星期  
		                    .minusMillis(1)// 减分钟  
		                    .minusHours(1)// 减小时  
		                    .minusSeconds(1);// 减秒数
		
		//判断是否闰月  
        DateTime dt4 = new DateTime();  
        org.joda.time.DateTime.Property month = dt4.monthOfYear();  
        System.out.println("是否闰月:" + month.isLeap());
        
        //取得 3秒前的时间  
        DateTime dt5 = dateTime1.secondOfMinute().addToCopy(-3);  
        dateTime1.getSecondOfMinute();// 得到整分钟后，过的秒钟数  
        dateTime1.getSecondOfDay();// 得到整天后，过的秒钟数  
        dateTime1.secondOfMinute();// 得到分钟对象,例如做闰年判断等使用
        
        // DateTime与java.util.Date对象,当前系统TimeMillis转换  
        DateTime dt6 = new DateTime(new Date());  
        Date date = dateTime1.toDate();  
        DateTime dt7 = new DateTime(System.currentTimeMillis());  
        dateTime1.getMillis(); 
        
        Calendar calendar = Calendar.getInstance();  
        dateTime = new DateTime(calendar);
	}

	@Test
	public void convertToString() {
		String format = "yyyy-MM-dd HH:mm:ss";
		DateTime fooDate = new DateTime(2011, 6, 1, 12, 10, 8, 0);
		// 第一种方法 直接使用DateTime的toString方法
		System.out.println(fooDate.toString(format));

		// 第二种方法,使用Formatter
		DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
		System.out.println(fmt.print(fooDate));
	}

	@Test
	public void convertFromString() {
		String dateString = "1978-06-01 12:10:08";
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		// 第一种方法，直接构造函数,注意日期和时间之间用T分割
		DateTime dt1 = new DateTime("1978-06-01");
		assertEquals(1978, dt1.getYear());
		DateTime dt2 = new DateTime("1978-06-01T12:10:08");
		assertEquals(1978, dt2.getYear());

		// 第二种方法，使用Formatter
		DateTime dt3 = fmt.parseDateTime(dateString);
		assertEquals(1978, dt3.getYear());

	}

	@Test
	public void timeZone() {

		System.out.println("演示时区");

		String format = "yyyy-MM-dd HH:mm:ss zZZ";

		// DateTime的毫秒即System的毫秒,即1970到现在的UTC的毫秒数.
		System.out.println(new DateTime().getMillis() + " " + System.currentTimeMillis());

		// 将日期按默认时区打印
		DateTime fooDate = new DateTime(1978, 6, 1, 12, 10, 8, 0);
		System.out.println(fooDate.toString(format) + " " + fooDate.getMillis()); // "1978-06-01 12:10:08"

		// 将日期按UTC时区打印
		DateTime zoneWithUTC = fooDate.withZone(DateTimeZone.UTC);
		System.out.println(zoneWithUTC.toString(format) + " " + zoneWithUTC.getMillis());// "1978-06-01 04:10:08",
																							// sameMillis

		// 按不同的时区分析字符串,得到不同的时间
		String dateString = "1978-06-01 12:10:08";
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

		DateTime parserResult1 = fmt.withZone(DateTimeZone.forID("US/Pacific")).parseDateTime(dateString);
		DateTime parserResult2 = fmt.withZoneUTC().parseDateTime(dateString);

		System.out.println(parserResult1.toString(format) + " " + parserResult1.getMillis());
		System.out.println(parserResult2.toString(format) + " " + parserResult2.getMillis());
	}

	/**
	 * 打印当地语言年，月，日到写法
	 */
	@Test
	public void locale() {

		System.out.println("演示Locale");

		DateTime dateTime = new DateTime().withZone(DateTimeZone.UTC);

		// 打印中文与英文下不同长度的日期格式串
		System.out.println("S:  " + formatDateTime(dateTime, "SS", "zh"));
		System.out.println("M:  " + formatDateTime(dateTime, "MM", "zh"));
		System.out.println("L:  " + formatDateTime(dateTime, "LL", "zh"));
		System.out.println("XL: " + formatDateTime(dateTime, "FF", "zh"));
		System.out.println("");

		System.out.println("S:  " + formatDateTime(dateTime, "SS", "en"));
		System.out.println("M:  " + formatDateTime(dateTime, "MM", "en"));
		System.out.println("L:  " + formatDateTime(dateTime, "LL", "en"));
		System.out.println("XL: " + formatDateTime(dateTime, "FF", "en"));
		System.out.println("");
		System.out.println("");

		// 直接打印TimeStamp, 日期是M,时间是L
		DateTimeFormatter formatter = DateTimeFormat.forStyle("ML").withLocale(new Locale("zh"))
				.withZone(DateTimeZone.UTC);

		System.out.println("ML Mix: " + formatter.print(dateTime.getMillis()));

		// 只打印日期不打印时间
		System.out.println("Date only :" + formatDateTime(dateTime, "M-", "zh"));

	}

	public static String formatDateTime(DateTime dateTime, String style, String lang) {
		DateTimeFormatter formatter = DateTimeFormat.forStyle(style).withLocale(new Locale(lang));
		return dateTime.toString(formatter);
	}

	/**
	 * 演示日期的加减以及计算日期间的间隔，可使用任意时间单位进行加减和计算间隔.
	 */
	@Test
	public void daysPlusAndMinusBetweenAndBetweenx() {
		DateTime now = new DateTime();
		DateTime birthDate = now.minusYears(10);
		System.out.println("birthDate = " + birthDate);
		assertEquals(10, Years.yearsBetween(birthDate, new DateTime()).getYears());
		birthDate = now.minusYears(10).plusDays(2);
		assertEquals(9, Years.yearsBetween(birthDate, new DateTime()).getYears());
	}

	/**
	 * 取得月份的头一天和最后一天. 取得一天的0:00和23:59:59 其他如年，星期的头一天，最后一天同理可证
	 */
	@Test
	public void beginAndEndOfDates() {
		String dateString = "1978-06-10T12:10:08";
		DateTime dt = new DateTime(dateString);
		DateTime startOfMonth = dt.dayOfMonth().withMinimumValue().withTimeAtStartOfDay();
		System.out.println(startOfMonth.toString());

		DateTime endOfMonth = dt.dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue();
		System.out.println(endOfMonth);
	}
}

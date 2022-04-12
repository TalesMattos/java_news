package java08;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;


/*
 * 
 * https://www.baeldung.com/java-8-date-time-intro
 * 
 */
public class DateTimeAPI {

	public static void main(String[] args) {
		
		
		/*
		 * LocalDate
		 * The LocalDate represents a date in ISO format (yyyy-MM-dd) without time.
		 * 
		 */
		
		LocalDate localDate = LocalDate.now();
		LocalDate.of(2015, 02, 20);
		LocalDate.parse("2015-02-20");
		
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
		
		DayOfWeek sunday = LocalDate.parse("2016-06-12").getDayOfWeek();
		int twelve = LocalDate.parse("2016-06-12").getDayOfMonth();
		
		// se é ano bissexto
		boolean leapYear = LocalDate.now().isLeapYear();
		System.out.println(leapYear);
		
		boolean notBefore = LocalDate.parse("2016-06-12").isBefore(LocalDate.parse("2016-06-11"));
		boolean isAfter = LocalDate.parse("2016-06-12").isAfter(LocalDate.parse("2016-06-11"));

		LocalDateTime beginningOfDay = LocalDate.parse("2016-06-12").atStartOfDay();
		LocalDate firstDayOfMonth = LocalDate.parse("2016-06-12").with(TemporalAdjusters.firstDayOfMonth());
		System.out.println(beginningOfDay + " ____ " + firstDayOfMonth);
		
		
		
		/*
		 * LocalTime
		 */
		
		LocalTime now = LocalTime.now();
		LocalTime sixThirty = LocalTime.parse("06:30");
		LocalTime sixThirty2 = LocalTime.of(6, 30);
		LocalTime sevenThirty = LocalTime.parse("06:30").plus(1, ChronoUnit.HOURS);
		int six = LocalTime.parse("06:30").getHour();
		boolean isbefore = LocalTime.parse("06:30").isBefore(LocalTime.parse("07:30"));
		LocalTime maxTime = LocalTime.MAX; // 23:59:59.99
		
		
		
		/*
		 * LocalDateTime
		 */
		
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
		LocalDateTime.parse("2015-02-20T06:30:00");
		localDateTime.plusDays(1);
		localDateTime.minusHours(2);
		localDateTime.getMonth();

		
		
		
		/*
		 * ZonedDateTime 
		 * Java 8 provides ZonedDateTime when we need to deal with time-zone-specific date and time. 
		 * The ZoneId is an identifier used to represent different zones. 
		 * There are about 40 different time zones, and the ZoneId represents them as follows.
		 */
		
		ZoneId zoneId = ZoneId.of("Europe/Paris");
		Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
		System.out.println(allZoneIds);
		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
		System.out.println(zonedDateTime);
		
		// Another way to work with time zone is by using OffsetDateTime. 
		// The OffsetDateTime is an immutable representation of a date-time with an offset. 
		// This class stores all date and time fields, to a precision of nanoseconds, as well as the offset from UTC/Greenwich.
		// The OffSetDateTime instance can be created using ZoneOffset. 
		// Here, we create a LocalDateTime representing 6:30 a.m. on February 20, 2015:
		
		LocalDateTime localDateTime2 = LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
		ZoneOffset offset = ZoneOffset.of("+02:00");
		OffsetDateTime offSetByTwo = OffsetDateTime.of(localDateTime2, offset);
		System.out.println(offSetByTwo);
		
		
		
		
		/*
		 * Period
		 * The Period class represents a quantity of time in terms of years, months and days
		 */
		
		LocalDate initialDate = LocalDate.parse("2007-05-10");
		LocalDate finalDate = initialDate.plus(Period.ofDays(5));
		int five = Period.between(initialDate, finalDate).getDays();
		long five2 = ChronoUnit.DAYS.between(initialDate, finalDate);
		System.out.println(five);
		
		
		
		/*
		 * Duration
		 * The Duration class represents a quantity of time in terms of seconds and nanoseconds.
		 */
		LocalTime initialTime = LocalTime.of(6, 30, 0);
		LocalTime finalTime = initialTime.plus(Duration.ofSeconds(30));
		long thirty = Duration.between(initialTime, finalTime).getSeconds();
		long thirty2 = ChronoUnit.SECONDS.between(initialTime, finalTime);
		
		
		
		/**
		 * Compatibility With Date and Calendar
		 * Converting...
		 */
		Date date  = new Date();
		Calendar calendar = Calendar.getInstance();
		LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
		// The Unix epoch (or Unix time or POSIX time or Unix timestamp) is the number of seconds that have elapsed since 
		// January 1, 1970 (midnight UTC/GMT), not counting leap seconds (in ISO 8601: 1970-01-01T00:00:00Z).
		long javaDateInEpochSeconds = date.getTime() / 1000L; 
		long javaCalendarInEpochSeconds = calendar.getTimeInMillis() / 1000L; 
		Instant.now().getEpochSecond();
		LocalDateTime.ofEpochSecond(1465817690, 0, ZoneOffset.UTC);
		
		System.out.println(new Date() + " - " + LocalDateTime.ofEpochSecond(new Date().getTime() / 1000, 0, ZoneOffset.of("-03:00")));
		System.out.println(new Date() + " - " + LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
		
		
		
		/*
		 * Date and Time Formatting
		 */
		
		LocalDateTime localDateTime3 = LocalDateTime.of(2015, Month.JANUARY, 25, 6, 30);
		String localDateString = localDateTime3.format(DateTimeFormatter.ISO_DATE); //2015-01-25
		System.out.println(localDateTime3.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

		String formatLdt = localDateTime3.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.UK));
		System.out.println(formatLdt);

		ZoneId zoneId2 = ZoneId.of("America/Sao_Paulo");
		ZonedDateTime zonedDateTime2 = ZonedDateTime.of(localDateTime3, zoneId2);
		String formatLdt2 = zonedDateTime2.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(Locale.forLanguageTag("pt-BR")));
		System.out.println(formatLdt2);

		System.out.println(LocalDateTime.now(Clock.system(ZoneId.of("America/Santiago"))));
		
		
	}
}

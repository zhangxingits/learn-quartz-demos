package quartz.demo.spring;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xin
 *
 */
public class CronUtil {
	/***
	 * @param date
	 * @param dateFormat
	 *            : e.g:yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatDateByPattern(Date date, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String formatTimeStr = null;
		if (date != null) {
			formatTimeStr = sdf.format(date);
		}
		return formatTimeStr;
	}

	/***
	 * convert Date to cron ,eg. "0 06 10 15 1 ? 2014"
	 * 
	 * @param date : ʱ���
	 * @return
	 */
	public static String getCron(java.util.Date date) {
		String dateFormat = "ss mm HH dd MM ? yyyy";
		return formatDateByPattern(date, dateFormat);
	}
	
	public static void main(String[] args) {
		System.out.println(getCron(new Date()));
	}
}

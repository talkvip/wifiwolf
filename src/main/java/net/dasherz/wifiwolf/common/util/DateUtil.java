package net.dasherz.wifiwolf.common.util;

import java.util.Date;

public class DateUtil {
	public static long getMinutesPasted(Date date) {
		Date now = new Date();
		return (now.getTime() - date.getTime()) / (1000 * 60);
	}
}

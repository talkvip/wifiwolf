package net.dasherz.wifiwolf.common.util;

import java.util.regex.Pattern;

public class CommonUtil {
	public static String shrimUrl(String url) {
		if (Pattern.matches("^https?:\\/\\/.+?\\/.{0,}$", url) == false) {
			return url;
		} else {
			url = url.substring(url.indexOf("://") + 3);
			url = url.substring(0, url.indexOf("/"));
		}
		return url + "...";
	}
}

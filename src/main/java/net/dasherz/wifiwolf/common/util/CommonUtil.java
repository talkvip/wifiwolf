package net.dasherz.wifiwolf.common.util;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

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

	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("X-Real-IP");
		if (StringUtils.isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		} else if (StringUtils.isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		} else if (StringUtils.isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

}

package net.dasherz.wifiwolf.common.util;

public class PropertiesUtil {

	private static final PropertiesLoader PROPERTIES_LOADER = new PropertiesLoader(
			"application.properties");

	private PropertiesUtil() {

	}

	public static PropertiesLoader getInstance() {
		return PROPERTIES_LOADER;
	}
}

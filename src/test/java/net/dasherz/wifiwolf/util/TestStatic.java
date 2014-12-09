package net.dasherz.wifiwolf.util;

import net.dasherz.wifiwolf.common.util.PropertiesUtil;

public class TestStatic {

	public static void main(String[] args) {
		o(PropertiesUtil.getInstance().getProperty("server_url"));
		o(PropertiesUtil.getInstance().getProperty("customAuthPage"));
	}

	private static void o(Object obj) {
		System.out.println(obj.toString());
	}

}

package net.dasherz.wifiwolf.util;

import net.dasherz.wifiwolf.common.util.ValidationCode;

public class TestStatic {

	public static void main(String[] args) {
		o(ValidationCode.ERROR_ID_PASSWORD.name());
		o(ValidationCode.ERROR_ID_PASSWORD.toString());
		o(ValidationCode.ERROR_ID_PASSWORD.getDeclaringClass());
	}

	private static void o(Object obj) {
		System.out.println(obj.toString());
	}

}

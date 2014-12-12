package net.dasherz.wifiwolf.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestStatic {
	private static Logger logger = LoggerFactory.getLogger(TestStatic.class);

	public static void main(String[] args) {
		logger.warn(
				"Unknown request: [stage={}, ip={}, mac={},token={},incoming={},outgoing={}]",
				"stage", "ip", "mac", "token", "incoming", "outgoing");
	}

	private static void o(Object obj) {
		System.out.println(obj.toString());
	}

}

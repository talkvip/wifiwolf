package net.dasherz.wifiwolf.wifidog.constant;

public enum AuthStage {
	LOGIN, COUNTERS, LOGOUT, SYSTEM_ERROR;
	public static AuthStage getAuthStage(String stage) {
		if (stage.equalsIgnoreCase("login")) {
			return AuthStage.LOGIN;
		} else if (stage.equalsIgnoreCase("counters")) {
			return AuthStage.COUNTERS;
		} else if (stage.equalsIgnoreCase("logout")) {
			return AuthStage.LOGOUT;
		}
		return AuthStage.SYSTEM_ERROR;
	}
}

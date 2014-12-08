package net.dasherz.wifiwolf.common.util;

public class Constants {
	public static final String PAGE_TYPE_USE_ORIGINAL_URL = "useOriginUrl";
	public static final String PAGE_TYPE_USE_TEMPLATE = "useTemplate";
	public static final String PAGE_TYPE_USE_CUSTOMIZE_URL = "useCustomizeUrl";
	public static final String PAGE_TYPE_USE_CUSTOMIZE_HTML = "useCustomizeHtml";
	public static final String TEMPLATE_AUTH_PAGE_PREFIX = "authTemplate";
	public static final String TEMPLATE_PORTAL_PAGE_PREFIX = "portalTemplate";

	// group name in dict table
	public static final String DICT_GROUP_GW_MESSAGE = "gw_message";

	// status for table in database
	public static final Integer STATUS_PHONE_USER_NOT_USED = 1;
	public static final Integer STATUS_PHONE_USER_USED = 2;
	public static final Integer STATUS_PHONE_NO_VERIFY = 0;

	public static final Integer STATUS_TOKEN_NORMAL = 1;

	public static final Integer STATUS_CONNECTION_NORMAL = 1;
	public static final Integer STATUS_CONNECTION_CLOSED = 2;

	public static final Integer STATUS_USER_WIFI_ENABLED = 1;
	public static final Integer STATUS_USER_WIFI_DISABLED = 2;

	public static final Integer STATUS_USER_ACCOUNT_NORMAL = 1;
	public static final Integer STATUS_USER_ACCOUNT_DISABLED = 2;


	public static final Integer STATUS_USER_ROLE_ADMIN = 1;
	public static final Integer STATUS_USER_ROLE_NORMAL = 2;

	public static final Integer STATUS_USER_PHONE_VERIFIED = 1;
	public static final Integer STATUS_USER_PHONE_UNVERIFIED = 2;

	public static final Integer STATUS_USER_EMAIL_VERIFIED = 1;
	public static final Integer STATUS_USER_EMAIL_UNVERIFIED = 2;

	public static final Integer STATUS_AUTH_TYPE_ENABLE = 1;
	public static final Integer STATUS_AUTH_TYPE_DISABLED = 2;


	// auth type status
	public static final String AUTH_TYPE_NONE = "NONE";
	public static final String AUTH_TYPE_PHONE = "PHONE";
	public static final String AUTH_TYPE_PHONE_SMS = "PHONE_SMS";
	public static final String AUTH_TYPE_PHONE_PASSWORD = "PHONE_PASSWORD";
	public static final String AUTH_TYPE_PHONE_PASSWORD_SMS = "PHONE_PASSWORD_SMS";

	// Session attribute
	public static final String SESSION_ATTR_GW_ID = "GW_ID";
	public static final String SESSION_ATTR_ORIGIN_URL = "origin_url";
	public static final String SESSION_ATTR_USER_ID = "userId";
	public static final String SESSION_ATTR_WIFIDOG_HOST = "wifidogHost";
	public static final String SESSION_ATTR_WIFIDOG_PORT = "wifidogPort";
	public static final String SESSION_ATTR_TOKEN_ID = "tokenId";

}

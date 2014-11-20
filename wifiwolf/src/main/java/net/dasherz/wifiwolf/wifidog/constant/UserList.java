package net.dasherz.wifiwolf.wifidog.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserList {

	private static Map<String, String> tokens = new HashMap<String, String>();
	private static List<String> users = new ArrayList<String>();

	public static Map<String, String> getTokens() {
		return tokens;
	}

	public static void setTokens(Map<String, String> tokens) {
		UserList.tokens = tokens;
	}

	public static List<String> getUsers() {
		return users;
	}

	public static void setUsers(List<String> users) {
		UserList.users = users;
	}

	public static void addUser(String userId) {
		users.add(userId);

	}

	public static void removeUser(String userId) {
		// TODO Auto-generated method stub
		users.remove(userId);

	}

}

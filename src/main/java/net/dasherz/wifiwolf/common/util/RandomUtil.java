package net.dasherz.wifiwolf.common.util;


public class RandomUtil {
	/**
	 * 创建指定数量的随机字符串
	 * 
	 * @param numberFlag
	 *            是否是数字
	 * @param length
	 * @return
	 */
	public static String createRandom(boolean numberFlag, int length) {
		String result = "";
		String strTable = numberFlag ? "1234567890"
				: "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		for (int i = 0; i < length; i++) {
			double lR = Math.random() * len;
			int r = (int) Math.floor(lR);
			result += strTable.charAt(r);
		}

		return result;
	}
}

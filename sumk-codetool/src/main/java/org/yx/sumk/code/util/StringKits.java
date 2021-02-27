package org.yx.sumk.code.util;

public class StringKits {

	/*
	 * 首字母大写
	 */
	public static String capFirst(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/*
	 * 首字母小写
	 */
	public static String unCapFirst(String s) {
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}

}

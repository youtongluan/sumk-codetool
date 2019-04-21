package org.yx.sumk.code.util;

public class Adpter {
	/*
	 * 判断列的字段名和java的属性名是不是同一个。
	 * 
	 * @param col_dbName
	 * @param col_javaName
	 * @return
	 */
	public static boolean isSameColumn(String col_dbName, String col_javaName) {
		if (col_dbName == null || col_javaName == null)
			return false;
		col_dbName = col_dbName.toLowerCase().replace("_", "");
		col_javaName = col_javaName.toLowerCase().replace("_", "");
		return col_dbName.equals(col_javaName);
	}

	/*
	 * 判断类和表是同一个吗
	 * 
	 * @param table_dbName
	 * @param clz_javaName
	 * @return
	 */
	public static boolean isSameClass(String table_dbName, String clz_javaName) {
		if (table_dbName == null || clz_javaName == null)
			return false;
		table_dbName = table_dbName.toLowerCase().replace("_", "");
		clz_javaName = clz_javaName.toLowerCase().replace("_", "");
		return table_dbName.equals(clz_javaName);
	}
}

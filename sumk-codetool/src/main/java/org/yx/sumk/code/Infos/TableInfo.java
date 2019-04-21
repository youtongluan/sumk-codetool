package org.yx.sumk.code.Infos;

import org.yx.util.StringUtil;

public class TableInfo {

	/*
	 * 表名
	 */
	private String dbName;
	/*
	 * 类名
	 */
	private String className;

	private String comment = null;

	private String packageName;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = StringUtil.isEmpty(comment) ? null : comment;
	}

}

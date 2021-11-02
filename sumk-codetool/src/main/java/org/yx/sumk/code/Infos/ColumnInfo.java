package org.yx.sumk.code.Infos;

import org.yx.util.StringUtil;

public class ColumnInfo {

	/**
	 * 数据库的列名
	 */
	private String dbName;
	/**
	 * java中的列名
	 */
	private String javaName;

	/**
	 * 数据库类型
	 */
	private String dbType;
	/**
	 * java类型
	 */
	private String javaType;
	/**
	 * 数据库长度
	 */
	private int dbLength;
	/**
	 * 是否主键
	 */
	private boolean primaryKey;

	/**
	 * 是否为空，true表示允许为空
	 */
	private boolean canNull = true;
	private String comment = null;

	public String getComment() {
		return comment;
	}

	public boolean isCanNull() {
		return canNull;
	}

	public void setCanNull(boolean canNull) {
		this.canNull = canNull;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName.toLowerCase();
	}

	public String getJavaName() {
		return javaName;
	}

	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		if ("java.sql.Timestamp".equals(javaType)) {
			this.javaType = "java.util.Date";
			return;
		}
		this.javaType = javaType.replace("java.lang.", "");
	}

	public int getDbLength() {
		return dbLength;
	}

	public void setDbLength(int dbLength) {
		this.dbLength = dbLength;
	}

	public void setComment(String comment) {
		this.comment = StringUtil.isEmpty(comment) ? null : comment.trim();

	}

	@Override
	public String toString() {
		return "ColumnInfo [dbName=" + dbName + ", javaName=" + javaName + ", javaType=" + javaType + ", primaryKey="
				+ primaryKey + ", canNull=" + canNull + ", comment=" + comment + "]";
	}

}

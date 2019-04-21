/**
 * Copyright (C) 2016 - 2030 youtongluan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

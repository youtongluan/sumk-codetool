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
package org.yx.db.sql;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.yx.common.ItemJoiner;
import org.yx.conf.AppInfo;
import org.yx.db.DB;
import org.yx.db.exec.Databases;
import org.yx.db.mapper.RawExecutor;
import org.yx.exception.SumkException;
import org.yx.log.Log;

public class DBTableMaker {

	public void exec() {
		try {
			List<PojoMeta> pms = PojoMetaHolder.allPojoMeta();
			for (PojoMeta pm : pms) {
				try {
					generate(pm);
				} catch (Exception e) {
					Log.get("sumk.codetool").error(e.getMessage(),e);
				}
			}
		} catch (Exception e) {
			Log.get("sumk.codetool").error(e.getMessage(),e);
		}
		Log.get("sumk.code.generator").info("表格生成结束");
	}
	
	private void generate(PojoMeta pm) throws Exception {
		if (pm.getTableName().contains(PojoMeta.WILDCHAR)) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE ").append(name(pm.getTableName())).append(" (\n\t");
		ColumnMeta[] cms = pm.fieldMetas;
		ItemJoiner joiner = ItemJoiner.create(",\n\t", null, null);
		Set<String> columnNames=new HashSet<>();
		for (ColumnMeta cm : cms) {
			columnNames.add(cm.dbColumn);
			joiner.item().append(name(cm.dbColumn)).append(' ').append(dbType(cm.field.getType()));
			if (cm.isDBID() || cm.isRedisID()) {
				joiner.append(" NOT NULL ");
			} else {
				joiner.append(" NULL DEFAULT NULL ");
			}
			if (cm.comment != null && cm.comment.length() > 0) {
				joiner.append(" COMMENT \'").append(cm.comment).append('\'');
			}
		}
		if (pm.isSoftDelete() && !columnNames.contains(pm.softDelete.columnName)) {
			Class<?> softType = pm.softDelete.columnType;
			String valid = Boolean.class == softType ? "1" : String.valueOf(pm.softDelete.validValue);
			String invalid = Boolean.class == softType ? "0" : String.valueOf(pm.softDelete.inValidValue);
			String comment = valid + "表示有效记录，" + invalid + "表示记录已被删除";
			joiner.item().append(name(pm.softDelete.columnName)).append(' ')
					.append(softType == String.class ? "CHAR(1)" : dbType(softType)).append(" DEFAULT ");
			if (softType == String.class) {
				joiner.append("'").append(valid).append("'");
			} else {
				joiner.append(valid);
			}
			joiner.append(" COMMENT '").append(AppInfo.get("sumk.code.generator.softdelete.comment", comment))
					.append('\'');
		}

		ColumnMeta[] keys = pm.getPrimaryIDs();
		if (keys != null && keys.length > 0) {
			joiner.item().append("PRIMARY KEY (");
			ItemJoiner keyJoiner = ItemJoiner.create(",", null, null);
			Arrays.stream(keys).forEachOrdered(c -> {
				keyJoiner.item().append(name(c.dbColumn));
			});
			joiner.append(keyJoiner.toCharSequence()).append(')');
		}

		ColumnMeta[] foreigner = pm.getRedisIDs();
		if (foreigner != null && foreigner.length > 0 && !Arrays.equals(foreigner, keys)) {
			joiner.item().append("KEY ").append(pm.getTableName() + "_index1").append(" (");
			ItemJoiner keyJoiner = ItemJoiner.create(",", null, null);
			for (ColumnMeta c : foreigner) {
				keyJoiner.item().append(name(c.dbColumn));
			}
			joiner.append(keyJoiner.toCharSequence()).append(')');
		}

		sb.append(joiner.toCharSequence()).append("\n)");

		String tableComment = pm.table.comment();
		if (tableComment != null && tableComment.length() > 0) {
			sb.append("COMMENT='").append(tableComment).append("'");
		}
		String sql = sb.toString();
		Log.get("sumk.code.generator").info("\n{}", sql);

		if (AppInfo.get("s.db.sumk.1.url", null) == null) {
			Log.get("sumk.code.generator").info("没有配置数据库，不会自动写入数据库。自此sql生成结束");
			return;
		}
		DB.execute(Databases.any(), ds -> {
			createTable(sql, pm.getTableName());
			return null;
		});

	}
	
	void createTable(String sql,String table){
		try {
			RawExecutor.execute(sql);
		} catch (Exception e) {
			Log.get("sumk.code.generator").warn("create table {} failed because : ", table,e.getMessage());
		}
	}

	private String name(String tableName) {
		String quote = AppInfo.get("sumk.code.generator.quote", "");
		return quote + tableName + quote;
	}

	private String dbType(Class<?> clz) {
		if (clz == Boolean.class || clz == boolean.class) {
			return AppInfo.get("sumk.code.generator.type.boolean", "BIT(1)");
		}
		if (clz == Byte.class || clz == byte.class) {
			return AppInfo.get("sumk.code.generator.type.byte", "TINYINT");
		}
		if (clz == Short.class || clz == short.class) {
			return AppInfo.get("sumk.code.generator.type.short", "SMALLINT");
		}
		if (clz == Integer.class || clz == int.class) {
			return AppInfo.get("sumk.code.generator.type.int", "INT");
		}
		if (clz == Long.class || clz == long.class) {
			return AppInfo.get("sumk.code.generator.type.long", "BIGINT");
		}

		if (clz == Float.class || clz == float.class) {
			return AppInfo.get("sumk.code.generator.type.float", "DECIMAL(12,3)");
		}
		if (clz == Double.class || clz == double.class) {
			return AppInfo.get("sumk.code.generator.type.double", "DECIMAL(20,5)");
		}

		if (clz == String.class) {
			return AppInfo.get("sumk.code.generator.type.string", "VARCHAR(255)");
		}
		if (clz == java.util.Date.class || clz == Timestamp.class || clz == LocalDateTime.class) {
			return AppInfo.get("sumk.code.generator.type.timestamp", "TIMESTAMP");
		}
		if (clz == java.sql.Date.class || clz == LocalDate.class) {
			return AppInfo.get("sumk.code.generator.type.date", "DATE");
		}
		if (clz == java.sql.Time.class || clz == LocalTime.class) {
			return AppInfo.get("sumk.code.generator.type.time", "TIME");
		}
		if (clz == byte[].class) {
			return AppInfo.get("sumk.code.generator.type.bytearray", "MEDIUMBLOB");
		}
		throw new SumkException(2545123, clz.getName() + " is un supported");
	}

}

package org.yx.sumk.code.Infos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TableInfoManager {
	private List<TableInfo> tableInfoList;

	public TableInfoManager(List<TableInfo> tableInfoList) {
		super();
		this.tableInfoList = tableInfoList;
	}

	public void println() {
		System.out.println(this.tableInfoList);
	}

	public List<String> getDbNames() {
		List<String> dbs = new ArrayList<String>();
		for (int i = 0; i < tableInfoList.size(); i++) {
			TableInfo t = tableInfoList.get(i);
			dbs.add(t.getDbName());
		}
		return dbs;
	}

	public Iterator<TableInfo> getIterator() {
		return this.tableInfoList.iterator();
	}

	public List<TableInfo> getTableInfoList() {
		return tableInfoList;
	}

}

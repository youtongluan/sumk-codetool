package org.yx.sumk.code.Infos;

import java.util.ArrayList;
import java.util.List;

public class SimpleTableInfo extends TableInfo {
	private String minclassname = "";
	private List<IdInfo> idInfos = null;
	private List<String> cols=new ArrayList<>();

	public String getMinclassname() {
		return minclassname;
	}

	public void setJavaPackage(String name) {
		this.setPackageName(name);
	}

	public void setMinclassname(String minclassname) {
		this.minclassname = minclassname;
	}

	public List<IdInfo> getIdInfos() {
		return idInfos;
	}

	public void setIdInfos(List<IdInfo> idInfos) {
		this.idInfos = idInfos;
	}

	public List<String> getCols() {
		return cols;
	}

	public void setCols(List<String> cols) {
		this.cols = cols;
	}
	
}
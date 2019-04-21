package org.yx.sumk.code.Infos;

public class IdInfo {
	private String id;
	private String idtype;
	private String rowidtype;
	private String getid;
	private String setid;

	public String getId() {
		return id;
	}

	public void parseType(Class<?> type) {
		if (Number.class.isAssignableFrom(type)) {
			this.idtype = type.getSimpleName();
			if (Integer.class == type) {
				this.rowidtype = "int";
			} else {
				this.rowidtype = this.idtype.toLowerCase();
			}
		}
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdtype() {
		return idtype;
	}

	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}

	public String getGetid() {
		return getid;
	}

	public void setGetid(String getid) {
		this.getid = getid;
	}

	public String getSetid() {
		return setid;
	}

	public void setSetid(String setid) {
		this.setid = setid;
	}

	public String getRawIdType() {
		return rowidtype;
	}
}

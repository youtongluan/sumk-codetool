package org.git.member.pojo;

import org.yx.annotation.db.Column;
import org.yx.annotation.db.Table;
import org.yx.annotation.doc.Comment;
import org.yx.db.enums.ColumnType;

@Table
@Comment("多主键表")
public class Multikey{
	
	@Column(type=ColumnType.ID_BOTH)
	private String id1;

	@Column(type=ColumnType.ID_BOTH)
	private String id2;

	@Comment("姓名")
	private String name;

	private Integer age;


	public String getId1() {
		return id1;
	}
	public Multikey setId1(String id1) { 
		this.id1=id1;
		return this;
	}
	
	public String getId2() {
		return id2;
	}
	public Multikey setId2(String id2) { 
		this.id2=id2;
		return this;
	}
	
	public String getName() {
		return name;
	}
	public Multikey setName(String name) { 
		this.name=name;
		return this;
	}
	
	public Integer getAge() {
		return age;
	}
	public Multikey setAge(Integer age) { 
		this.age=age;
		return this;
	}
	
}
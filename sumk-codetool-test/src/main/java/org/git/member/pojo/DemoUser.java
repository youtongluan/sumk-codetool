package org.git.member.pojo;

import org.yx.annotation.db.Column;
import org.yx.annotation.db.SoftDelete;
import org.yx.annotation.db.Table;
import org.yx.annotation.doc.Comment;
import org.yx.db.enums.ColumnType;

@Table("demo_user")
@SoftDelete(value="enable",type=Integer.class)
public class DemoUser{
	
	@Column(type=ColumnType.ID_BOTH)
	private Long id;

	private String name;

	private Integer age;

	@Column(value="last_update")
	@Comment("最后更新时间")
	private java.util.Date lastUpdate;


	public Long getId() {
		return id;
	}
	public DemoUser setId(Long id) { 
		this.id=id;
		return this;
	}
	
	public String getName() {
		return name;
	}
	public DemoUser setName(String name) { 
		this.name=name;
		return this;
	}
	
	public Integer getAge() {
		return age;
	}
	public DemoUser setAge(Integer age) { 
		this.age=age;
		return this;
	}
	
	public java.util.Date getLastUpdate() {
		return lastUpdate;
	}
	public DemoUser setLastUpdate(java.util.Date lastUpdate) { 
		this.lastUpdate=lastUpdate;
		return this;
	}
	
}
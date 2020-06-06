package org.git.member.pojo;

import org.yx.annotation.db.Column;
import org.yx.annotation.db.SoftDelete;
import org.yx.annotation.db.Table;
import org.yx.annotation.doc.Comment;
import org.yx.db.enums.ColumnType;

@Table
@SoftDelete(value="valid",type=Boolean.class)
@Comment("用来测试表格自动生成")
public class TestTable {
	
	@Column(type=ColumnType.ID_CACHE)
	public String userId;
	
	@Comment("Short 类型,第4列")
	public Short height;
	
	@Comment("byte 类型,第5列")
	public byte age;
	
	@Comment("float 类型,第6列")
	public float f;
	
	@Column(type=ColumnType.ID_DB)
	@Comment("Long 类型,第1列")
	public Long id;
	
	@Comment("double 类型,第7列")
	public double d;
	
	@Column(type=ColumnType.ID_CACHE)
	@Comment("boolean 类型，联合索引2,第3列")
	public boolean enable;
	
}

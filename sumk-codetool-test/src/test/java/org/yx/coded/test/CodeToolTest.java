package org.yx.coded.test;

import org.git.member.pojo.DemoUser;
import org.git.member.pojo.Multikey;
import org.junit.Test;
import org.yx.sumk.code.CodeTool;

public class CodeToolTest {

	//要在bean生成之后
	@Test
	public void generateDao(){
		CodeTool.generateDao(DemoUser.class,Multikey.class);
	}
	
	@Test
	public void generateDBTable(){
		CodeTool.generateDBTable();
	}

}

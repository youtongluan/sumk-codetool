package org.git.member;

import java.sql.SQLException;

import org.junit.Test;
import org.yx.bean.IOC;
import org.yx.main.SumkServer;

public class TestRunner {

	@Test
	public void test() throws SQLException {
		SumkServer.start();
		IOC.get(DemoUserDaoTest.class).demoUserTest();
		IOC.get(MultiDaoTest.class).multi();
	}
	
}

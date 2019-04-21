package org.git.member.dao.impl;

import org.yx.annotation.Bean;
import org.yx.annotation.db.Cached;
import org.git.member.dao.abstr.AbstractDemoUserDao;
import org.git.member.dao.DemoUserDao;

@Bean
@Cached
public class DemoUserDaoImpl extends AbstractDemoUserDao implements DemoUserDao{
	
}
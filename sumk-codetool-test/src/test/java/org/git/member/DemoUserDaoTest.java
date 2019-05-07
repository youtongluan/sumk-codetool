package org.git.member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.git.member.dao.DemoUserDao;
import org.git.member.pojo.DemoUser;
import org.junit.Assert;
import org.yx.annotation.Bean;
import org.yx.annotation.Box;
import org.yx.bean.IOC;
import org.yx.db.DB;
import org.yx.db.dao.CountedResult;
import org.yx.db.dao.Paged;
import org.yx.db.mapper.RawExecutor;
import org.yx.util.S;

// @Bean @Cache注解的对象，不一定要继承接口
@Bean
public class DemoUserDaoTest {

	private DemoUser getUser(List<DemoUser> list,Long id){
		for(DemoUser u:list){
			if(id.equals(u.getId())){
				return u;
			}
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	@Box
	public void demoUserTest() throws SQLException {
		RawExecutor.execute("delete from demo_user");
		List<DemoUser> users=new ArrayList<>();
//		DemoUserDao dao=IOC.get(DemoUserDao.class); //非缓存模式
		DemoUserDao dao=IOC.cache(null,DemoUserDao.class); //缓存模式
		
		//写入
		DemoUser demoUser=null;
		for(int i=0;i<2;i++){
			demoUser=new DemoUser();
			demoUser.setName("test");
			demoUser.setAge(11);
			demoUser.setLastUpdate(new Date(116,1,1,1,1,1));
			dao.insert(demoUser);
			users.add(demoUser);
		}
		DB.commit();
		users.forEach(u->{
			DemoUser u2=dao.queryById(u.getId());
			Assert.assertEquals(S.json.toJson(u), S.json.toJson(u2));
		});
		//list
		demoUser=new DemoUser();
		demoUser.setName("test");
		List<DemoUser> listByUsers=dao.list(demoUser,0,50);
		Assert.assertEquals(2, listByUsers.size());
		listByUsers.forEach(u2->{
			DemoUser u=this.getUser(users, u2.getId());
			Assert.assertEquals(S.json.toJson(u), S.json.toJson(u2));
		});
		
		List<Long> ids=dao.listIds(demoUser,0,50);
		Assert.assertEquals(2, dao.count(demoUser));
		CountedResult<DemoUser> result=dao.listAndCount(demoUser, new Paged(50));
		Assert.assertEquals(2, result.getCount());
		result.getResult().forEach(u2->{
			DemoUser u=this.getUser(users, u2.getId());
			Assert.assertEquals(S.json.toJson(u), S.json.toJson(u2));
		});
		
		Set<Long> idSet2=new HashSet<>(ids);
		Assert.assertEquals(2, idSet2.size());
		users.forEach(u->{
			Assert.assertNotNull(idSet2.remove(u.getId()));
		});
		Assert.assertEquals(0, idSet2.size());
		final List<DemoUser> users2=dao.listByIds(ids);
		Assert.assertEquals(2, users2.size());
		users.forEach(u->{
			for(DemoUser u2:users2){
				if(S.json.toJson(u).equals(S.json.toJson(u2))){
					return;
				}
			}
			Assert.fail("listDemoUserByIds 失败");
		});
		
		//update
		demoUser=new DemoUser();
		demoUser.setName("test2");
		demoUser.setId(users.get(0).getId());
		dao.updatePart(demoUser);
		DB.commit();
		DemoUser user2=dao.queryById(users.get(0).getId());
		Assert.assertEquals(users.get(0).getId(), user2.getId());
		Assert.assertEquals(users.get(0).getAge(), user2.getAge());
		Assert.assertEquals(users.get(0).getLastUpdate(), user2.getLastUpdate());
		Assert.assertEquals(demoUser.getName(), user2.getName());
		
		demoUser=new DemoUser();
		demoUser.setName("test3");
		DemoUser oldStatus=new DemoUser();
		oldStatus.setLastUpdate(users.get(0).getLastUpdate());
		oldStatus.setAge(users.get(0).getAge());
		Assert.assertEquals(2,dao.updatePartByIds(ids, oldStatus, demoUser));
		DB.commit();
		List<DemoUser> users3=dao.listByIds(ids);
		Assert.assertEquals(2, users3.size());
		users.forEach(u->{
			DemoUser u2=this.getUser(users3, u.getId());//数据库里面新的数据
			Assert.assertEquals(u.getId(), u2.getId());
			Assert.assertEquals(u.getAge(), u2.getAge());
			Assert.assertEquals(u.getLastUpdate(), u2.getLastUpdate());
			Assert.assertEquals("test3", u2.getName());
		});
		
		//fullUpdate
		demoUser=new DemoUser();
		demoUser.setName("full update");
		demoUser.setLastUpdate(new Date(116,2,2,2,12,12));
		demoUser.setId(users.get(0).getId());
		Assert.assertEquals(1,dao.updateFull(demoUser));
		DB.commit();
		Assert.assertEquals(S.json.toJson(demoUser),S.json.toJson(dao.queryById(demoUser.getId())));
		
		Assert.assertEquals(2,RawExecutor.count("select count(1) from demo_user where enable=1"));
		
		// delete
		demoUser=new DemoUser();
		demoUser.setName("test");
		demoUser.setAge(11);
		demoUser.setLastUpdate(new Date(116,1,1,1,1,1));
		dao.insert(demoUser);
		DB.commit();
		Assert.assertEquals(1,dao.deleteById(demoUser.getId()));
		DB.commit();
		Assert.assertNull(dao.queryById(demoUser.getId()));
		
		Assert.assertEquals(2,dao.deleteByIds(ids));
		DB.commit();
		ids.forEach(id->Assert.assertNull(dao.queryById(id)));
		
		Assert.assertEquals(0,RawExecutor.count("select count(1) from demo_user where enable=1")); //测试是否被删除干净
	}

}

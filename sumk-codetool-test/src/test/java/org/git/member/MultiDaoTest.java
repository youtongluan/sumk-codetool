package org.git.member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.git.member.dao.MultikeyDao;
import org.git.member.pojo.Multikey;
import org.junit.Assert;
import org.yx.annotation.Bean;
import org.yx.annotation.Box;
import org.yx.bean.IOC;
import org.yx.db.DB;
import org.yx.db.mapper.RawExecutor;
import org.yx.util.S;
import org.yx.util.SeqUtil;

// @Bean @Cache注解的对象，不一定要继承接口
@Bean
public class MultiDaoTest {

	private Multikey get(List<Multikey> list,String id1,String di2){
		for(Multikey u:list){
			if(id1.equals(u.getId1()) && di2.equals(u.getId2())){
				return u;
			}
		}
		return null;
	}
	
	@Box
	public void multi() throws SQLException {
		RawExecutor.execute("delete from multikey");
		List<Multikey> users=new ArrayList<>();
		MultikeyDao dao=IOC.get(MultikeyDao.class); //非缓存模式
		
		//写入
		Multikey multi=null;
		for(int i=0;i<2;i++){
			multi=new Multikey();
			multi.setName("test");
			multi.setAge(11);
			multi.setId1(""+SeqUtil.next());
			multi.setId2(""+SeqUtil.next());
			dao.insert(multi);
			users.add(multi);
		}
		DB.commit();
		users.forEach(u->{
			Multikey m=new Multikey();
			m.setId1(u.getId1());
			m.setId2(u.getId2());
			Multikey u2=dao.list(m,0,10).get(0);
			Assert.assertEquals(S.json.toJson(u), S.json.toJson(u2));
		});
		
		//list
		multi=new Multikey();
		multi.setName("test");
		List<Multikey> list=dao.list(multi,0,10);
		Assert.assertEquals(2, list.size());
		list.forEach(u2->{
			Multikey u=this.get(users, u2.getId1(),u2.getId2());
			Assert.assertEquals(S.json.toJson(u), S.json.toJson(u2));
		});
		
		Assert.assertEquals(2, dao.count(multi));
		
		//update part
		multi=new Multikey();
		multi.setName("test2");
		multi.setId1(users.get(0).getId1());
		multi.setId2(users.get(0).getId2());
		dao.updatePart(multi);
		DB.commit();
		Multikey user2=dao.list(multi,0,10).get(0);
		Assert.assertEquals(users.get(0).getId1(), user2.getId1());
		Assert.assertEquals(users.get(0).getId2(), user2.getId2());
		Assert.assertEquals(users.get(0).getAge(), user2.getAge());
		Assert.assertEquals(multi.getName(), user2.getName());
		
		
		//fullUpdate
		multi=new Multikey();
		multi.setName("full update");
		multi.setId1(users.get(0).getId1());
		multi.setId2(users.get(0).getId2());
		Assert.assertEquals(1,dao.updateFull(multi));
		DB.commit();
		Assert.assertEquals(S.json.toJson(multi),S.json.toJson(dao.list(multi,0,10).get(0)));
		
		Assert.assertEquals(2,RawExecutor.count("select count(1) from multikey"));
		
		// delete
		users.forEach(u->{
			Multikey m=new Multikey();
			m.setId1(u.getId1());
			m.setId2(u.getId2());
			Assert.assertEquals(1,dao.delete(m));
		});
		DB.commit();
		Assert.assertEquals(0,RawExecutor.count("select count(1) from multikey")); //测试是否被删除干净
	}

}

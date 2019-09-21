package org.git.member.dao;

import java.util.List;
import org.git.member.pojo.DemoUser;
import org.yx.db.dao.CountedResult;
import org.yx.db.dao.Pagable;


public interface DemoUserDao {
	
	int insert(DemoUser demoUser);
	
	int updateFull(DemoUser demoUser);
	
	int delete(DemoUser demoUser);

	List<DemoUser> list(DemoUser demoUser,int offset,int pageSize);
	
	long count(DemoUser demoUser);
	
	int updatePart(DemoUser demoUser);
	
	CountedResult<DemoUser> listAndCount(DemoUser obj,Pagable page);
	

	DemoUser queryById(long id);
    
    int deleteById(long id);
    
    /*
	 * 不保证返回值的顺序
	 */
	List<DemoUser> listByIds(List<Long> ids);
	
	List<Long> listIds(DemoUser demoUser,int offset,int pageSize);
    
    int deleteByIds(List<Long> ids);
	
	int updatePartByIds(List<Long> ids,DemoUser oldStatus,DemoUser newStatus);
	
}
package org.git.member.dao;

import java.util.List;
import org.git.member.pojo.DemoUser;
import org.yx.db.dao.CountedResult;
import org.yx.db.dao.Pagable;


/*
 * 自动生成，不能修改。
 */
public interface DemoUserDao {
	
	/** 增加  **/
	int insert(DemoUser demoUser);
	
	/** 更新整条记录 **/
	int updateFull(DemoUser demoUser);
	
	/** 删除 **/
	int delete(DemoUser demoUser);

	/** 查询 **/
	List<DemoUser> list(DemoUser demoUser,int offset,int pageSize);
	
	/** 查询数量 **/
	long count(DemoUser demoUser);
	
	/** 更新**/
	int updatePart(DemoUser demoUser);
	
	/**demoUser、page都不能为null */
	CountedResult<DemoUser> listAndCount(DemoUser obj,Pagable page);
	

	/** 加载 **/
	DemoUser queryById(long id);
    
    int deleteById(long id);
    
    /**
	 * 不保证返回值的顺序
	 */
	List<DemoUser> listByIds(List<Long> ids);
	
	List<Long> listIds(DemoUser demoUser,int offset,int pageSize);
    
    int deleteByIds(List<Long> ids);
	
	int updatePartByIds(List<Long> ids,DemoUser oldStatus,DemoUser newStatus);
	
}
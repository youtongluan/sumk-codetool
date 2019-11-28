package org.git.member.dao;

import java.util.List;
import org.git.member.pojo.DemoUser;
import org.yx.db.dao.CountedResult;


public interface DemoUserDao {
	
	int insert(DemoUser demoUser);
	
	//根据主键更新所有字段，如果某个属性的值为null，也会把数据库对应的字段更新为null
	int updateFull(DemoUser demoUser);
	
	/*
	 * 根据对象中不为null的字段（用and进行组合）更新数据库。
	 * 如果对象中没有包含所有的redis主键（一般就是数据库主键），会抛出异常，但不会影响数据库执行的结果。
	 * 关闭该表的缓存功能，就可以去掉那个异常
	 */
	int delete(DemoUser demoUser);

	//mysql的offset是从0开始的，表示第几条数据库记录，不是第几页。如果想查全部数据，只要把limit设置很大就可以了
	List<DemoUser> list(DemoUser demoUser,int offset,int limit);
	
	long count(DemoUser demoUser);
	
	//部分更新，只更新对象中不为null的字段
	int updatePart(DemoUser demoUser);
	
	CountedResult<DemoUser> listAndCount(DemoUser obj,int offset,int limit);
	

	DemoUser queryById(long id);
    
    int deleteById(long id);
    
    //根据主键列表查询。不保证返回值的顺序
	List<DemoUser> listByIds(List<Long> ids);
	
	//根据对象中的属性查询所有符合条件的id列表
	List<Long> listIds(DemoUser demoUser,int offset,int limit);
    
    //根据id列表删除数据
    int deleteByIds(List<Long> ids);
	
	//根据主键列表以及oldStatus中的额外条件更新数据库,newStatus是要更新的数据库字段以及更新后的值
	int updatePartByIds(List<Long> ids,DemoUser oldStatus,DemoUser newStatus);
	
}
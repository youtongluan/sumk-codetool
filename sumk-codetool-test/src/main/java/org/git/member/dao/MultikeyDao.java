package org.git.member.dao;

import java.util.List;
import org.git.member.pojo.Multikey;
import org.yx.db.dao.CountedResult;


public interface MultikeyDao {
	
	int insert(Multikey multikey);
	
	//根据主键更新所有字段，如果某个属性的值为null，也会把数据库对应的字段更新为null
	int updateFull(Multikey multikey);
	
	/*
	 * 根据对象中不为null的字段（用and进行组合）更新数据库。
	 * 如果对象中没有包含所有的redis主键（一般就是数据库主键），会抛出异常，但不会影响数据库执行的结果。
	 * 关闭该表的缓存功能，就可以去掉那个异常
	 */
	int delete(Multikey multikey);

	//mysql的offset是从0开始的，表示第几条数据库记录，不是第几页。如果想查全部数据，只要把limit设置很大就可以了
	List<Multikey> list(Multikey multikey,int offset,int limit);
	
	long count(Multikey multikey);
	
	//部分更新，只更新对象中不为null的字段
	int updatePart(Multikey multikey);
	
	CountedResult<Multikey> listAndCount(Multikey obj,int offset,int limit);
	

	
}
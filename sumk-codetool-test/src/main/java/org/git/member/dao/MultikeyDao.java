package org.git.member.dao;

import java.util.List;
import org.git.member.pojo.Multikey;
import org.yx.db.dao.CountedResult;
import org.yx.db.dao.Pagable;


/*
 * 自动生成，不能修改。
 */
public interface MultikeyDao {
	
	/** 增加  **/
	int insert(Multikey multikey);
	
	/** 更新整条记录 **/
	int updateFull(Multikey multikey);
	
	/** 删除 **/
	int delete(Multikey multikey);

	/** 查询 **/
	List<Multikey> list(Multikey multikey,int offset,int pageSize);
	
	/** 查询数量 **/
	long count(Multikey multikey);
	
	/** 更新**/
	int updatePart(Multikey multikey);
	
	/**demoUser、page都不能为null */
	CountedResult<Multikey> listAndCount(Multikey obj,Pagable page);
	

	
}
package org.git.member.dao;

import java.util.List;
import org.git.member.pojo.Multikey;
import org.yx.db.dao.CountedResult;
import org.yx.db.dao.Pagable;


/*
 * 自动生成，不能修改。
 */
public interface MultikeyDao {
	
	int insert(Multikey multikey);
	
	int updateFull(Multikey multikey);
	
	int delete(Multikey multikey);

	List<Multikey> list(Multikey multikey,int offset,int pageSize);
	
	long count(Multikey multikey);
	
	int updatePart(Multikey multikey);
	
	CountedResult<Multikey> listAndCount(Multikey obj,Pagable page);
	

	
}
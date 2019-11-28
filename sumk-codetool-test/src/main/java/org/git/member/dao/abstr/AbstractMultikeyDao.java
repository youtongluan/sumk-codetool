package org.git.member.dao.abstr;

import java.util.List;
import org.yx.db.dao.AbstractCachable;
import org.yx.db.DB;
import org.yx.db.sql.Select;
import org.git.member.pojo.Multikey;
import org.yx.db.dao.CountedResult;
import org.git.member.dao.MultikeyDao;


/*
 * 自动生成，不能修改。
 */
public abstract class AbstractMultikeyDao extends AbstractCachable implements MultikeyDao{
	
	public int insert(Multikey multikey){
		return DB.insert(multikey).execute();
	}
	
	public int updateFull(Multikey multikey){
		return DB.update(multikey).fullUpdate(true).execute();
	}
	
	public int delete(Multikey multikey){
		return DB.delete(multikey).execute();
	}

	public List<Multikey> list(Multikey multikey,int offset,int limit){
		return DB.select(multikey).tableClass(Multikey.class).fromCache(this.isCacheEnable())
				.offset(offset)
				.limit(limit).queryList();
	}
	
	public long count(Multikey multikey){
		return DB.select(multikey).tableClass(Multikey.class).count();
	}
	
	public int updatePart(Multikey multikey){
		return DB.update(multikey).execute();
	}
	
	public CountedResult<Multikey> listAndCount(Multikey obj,int offset,int limit){
		Select select = DB.select(obj).tableClass(Multikey.class).fromCache(this.isCacheEnable())
				.offset(offset)
				.limit(limit);
		
		return new CountedResult<>(select.queryList(),select.count());
	}
	

	
}
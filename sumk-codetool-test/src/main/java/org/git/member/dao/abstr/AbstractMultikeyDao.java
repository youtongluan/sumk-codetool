package org.git.member.dao.abstr;

import java.util.List;
import org.yx.db.AbstractCachable;
import org.yx.db.DB;
import org.yx.db.sql.Select;
import org.git.member.pojo.Multikey;
import org.yx.db.dao.CountedResult;
import org.yx.db.dao.Pagable;


/*
 * 自动生成，不能修改。
 */
public abstract class AbstractMultikeyDao extends AbstractCachable {
	
	/** 增加  **/
	public int insert(Multikey multikey){
		return DB.insert(multikey).execute();
	}
	
	/** 更新整条记录 **/
	public int updateFull(Multikey multikey){
		return DB.update(multikey).fullUpdate(true).execute();
	}
	
	/** 删除 **/
	public int delete(Multikey multikey){
		return DB.delete(multikey).execute();
	}

	/** 查询 **/
	public List<Multikey> list(Multikey multikey,int offset,int pageSize){
		return DB.select(multikey).tableClass(Multikey.class).fromCache(this.isCacheEnable())
				.offset(offset)
				.limit(pageSize).queryList();
	}
	
	/** 查询数量 **/
	public long count(Multikey multikey){
		return DB.select(multikey).tableClass(Multikey.class).count();
	}
	
	/** 更新**/
	public int updatePart(Multikey multikey){
		return DB.update(multikey).execute();
	}
	
	/**demoUser、page都不能为null */
	public CountedResult<Multikey> listAndCount(Multikey obj,Pagable page){
		Select select = DB.select(obj).tableClass(Multikey.class).fromCache(this.isCacheEnable())
				.offset(page.getBeginDATAIndex())
				.limit(page.getPageSize());
		
		return new CountedResult<>(select.queryList(),select.count());
	}
	

	
}
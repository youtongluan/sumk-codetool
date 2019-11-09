package org.git.member.dao.abstr;

import java.util.List;
import org.yx.db.dao.AbstractCachable;
import org.yx.db.DB;
import org.yx.db.sql.Select;
import org.git.member.pojo.DemoUser;
import org.yx.db.dao.CountedResult;
import org.yx.db.dao.Pagable;
import org.git.member.dao.DemoUserDao;

import java.util.ArrayList;
import java.util.Collections;
import org.yx.db.sql.Delete;
import org.yx.db.sql.Update;

/*
 * 自动生成，不能修改。
 */
public abstract class AbstractDemoUserDao extends AbstractCachable implements DemoUserDao{
	
	public int insert(DemoUser demoUser){
		return DB.insert(demoUser).execute();
	}
	
	public int updateFull(DemoUser demoUser){
		return DB.update(demoUser).fullUpdate(true).execute();
	}
	
	public int delete(DemoUser demoUser){
		return DB.delete(demoUser).execute();
	}

	public List<DemoUser> list(DemoUser demoUser,int offset,int pageSize){
		return DB.select(demoUser).tableClass(DemoUser.class).fromCache(this.isCacheEnable())
				.offset(offset)
				.limit(pageSize).queryList();
	}
	
	public long count(DemoUser demoUser){
		return DB.select(demoUser).tableClass(DemoUser.class).count();
	}
	
	public int updatePart(DemoUser demoUser){
		return DB.update(demoUser).execute();
	}
	
	public CountedResult<DemoUser> listAndCount(DemoUser obj,Pagable page){
		Select select = DB.select(obj).tableClass(DemoUser.class).fromCache(this.isCacheEnable())
				.offset(page.getBeginDATAIndex())
				.limit(page.getPageSize());
		
		return new CountedResult<>(select.queryList(),select.count());
	}
	

	public DemoUser queryById(long id){
		return DB.select().tableClass(DemoUser.class).byPrimaryId(id).fromCache(this.isCacheEnable()).queryOne();
    }
    
    public int deleteById(long id){
    	DemoUser demoUser=new DemoUser();
		demoUser.setId(id);
		return DB.delete(demoUser).execute();
    }
    
    private List<DemoUser> listByIds(List<Long> ids,boolean useCache){
		Select select=DB.select().fromCache(useCache).tableClass(DemoUser.class);
		for(Long id:ids){
			DemoUser demoUser=new DemoUser();
			demoUser.setId(id);
			select.addEqual(demoUser);
		}
		return select.queryList();
	}
	
	public List<DemoUser> listByIdsFromDB(List<Long> ids){
		return listByIds(ids,false);
	}
    
	public List<DemoUser> listByIds(List<Long> ids){
		return listByIds(ids,this.isCacheEnable());
	}
	
	public List<Long> listIds(DemoUser demoUser,int offset,int pageSize){
		List<DemoUser> list= DB.select(demoUser)
			.selectColumns("id")
			.offset(offset)
			.limit(pageSize)
			.queryList();
		if(list==null || list.isEmpty()){
			return Collections.emptyList();
		}
		List<Long> ids=new ArrayList<>(list.size());
		list.forEach(u->ids.add(u.getId()));
		return ids;
	}
    
    public int deleteByIds(List<Long> ids){
		Delete delete=DB.delete();
		for(Long id:ids){
			DemoUser demoUser=new DemoUser();
			demoUser.setId(id);
			delete.delete(demoUser);
		}
		return delete.execute();
	}
	
	public int updatePartByIds(List<Long> ids,DemoUser oldStatus,DemoUser newStatus){
		Update update=DB.update(newStatus);
		for(Long id:ids){
			oldStatus.setId(id);
			update.addWhere(oldStatus);
		}
		return update.execute();
	}
	
}
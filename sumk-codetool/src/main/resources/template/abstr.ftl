package ${module}.dao.abstr;

import java.util.List;
import org.yx.db.AbstractCachable;
import org.yx.db.DB;
import org.yx.db.sql.Select;
import ${javaPackage}.${ClassName};
import org.yx.db.dao.CountedResult;
import org.yx.db.dao.Pagable;

<#if id??>
import java.util.ArrayList;
import java.util.Collections;
import org.yx.db.sql.Delete;
import org.yx.db.sql.Update;
</#if>

/*
 * 自动生成，不能修改。
 */
public abstract class Abstract${ClassName}Dao extends AbstractCachable {
	
	public int insert(${ClassName} ${classname}){
		return DB.insert(${classname}).execute();
	}
	
	public int updateFull(${ClassName} ${classname}){
		return DB.update(${classname}).fullUpdate(true).execute();
	}
	
	public int delete(${ClassName} ${classname}){
		return DB.delete(${classname}).execute();
	}

	public List<${ClassName}> list(${ClassName} ${classname},int offset,int pageSize){
		return DB.select(${classname}).tableClass(${ClassName}.class).fromCache(this.isCacheEnable())
				.offset(offset)
				.limit(pageSize).queryList();
	}
	
	public long count(${ClassName} ${classname}){
		return DB.select(${classname}).tableClass(${ClassName}.class).count();
	}
	
	public int updatePart(${ClassName} ${classname}){
		return DB.update(${classname}).execute();
	}
	
	public CountedResult<${ClassName}> listAndCount(${ClassName} obj,Pagable page){
		Select select = DB.select(obj).tableClass(${ClassName}.class).fromCache(this.isCacheEnable())
				.offset(page.getBeginDATAIndex())
				.limit(page.getPageSize());
		
		return new CountedResult<>(select.queryList(),select.count());
	}
	

	<#-- 单主键才有的 --> 
<#if id??>
	public ${ClassName} queryById(${rawidtype} ${id}){
		return DB.select().tableClass(${ClassName}.class).byPrimaryId(${id}).fromCache(this.isCacheEnable()).queryOne();
    }
    
    public int deleteById(${rawidtype} ${id}){
    	${ClassName} ${classname}=new ${ClassName}();
		${classname}.${setid}(${id});
		return DB.delete(${classname}).execute();
    }
    
    private List<${ClassName}> listByIds(List<${idtype}> ${id}s,boolean useCache){
		Select select=DB.select().fromCache(useCache).tableClass(${ClassName}.class);
		for(${idtype} ${id}:${id}s){
			${ClassName} ${classname}=new ${ClassName}();
			${classname}.${setid}(${id});
			select.addEqual(${classname});
		}
		return select.queryList();
	}
	
	public List<${ClassName}> listByIdsFromDB(List<${idtype}> ${id}s){
		return listByIds(${id}s,false);
	}
    
	public List<${ClassName}> listByIds(List<${idtype}> ${id}s){
		return listByIds(${id}s,this.isCacheEnable());
	}
	
	public List<${idtype}> listIds(${ClassName} ${classname},int offset,int pageSize){
		List<DemoUser> list= DB.select(${classname})
			.selectColumns("${id}")
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
    
    public int deleteByIds(List<${idtype}> ${id}s){
		Delete delete=DB.delete();
		for(${idtype} ${id}:${id}s){
			${ClassName} ${classname}=new ${ClassName}();
			${classname}.${setid}(${id});
			delete.delete(${classname});
		}
		return delete.execute();
	}
	
	public int updatePartByIds(List<${idtype}> ${id}s,${ClassName} oldStatus,${ClassName} newStatus){
		Update update=DB.update(newStatus);
		for(${idtype} ${id}:${id}s){
			oldStatus.${setid}(${id});
			update.addWhere(oldStatus);
		}
		return update.execute();
	}
</#if>
	
}
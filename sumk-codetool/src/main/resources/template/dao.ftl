package ${module}.dao;

import java.util.List;
import ${javaPackage}.${ClassName};
import org.yx.db.dao.CountedResult;
import org.yx.db.dao.Pagable;


/*
 * 自动生成，不能修改。
 */
public interface ${ClassName}Dao {
	
	int insert(${ClassName} ${classname});
	
	int updateFull(${ClassName} ${classname});
	
	int delete(${ClassName} ${classname});

	List<${ClassName}> list(${ClassName} ${classname},int offset,int pageSize);
	
	long count(${ClassName} ${classname});
	
	int updatePart(${ClassName} ${classname});
	
	CountedResult<${ClassName}> listAndCount(${ClassName} obj,Pagable page);
	

	<#-- 单主键才有的 --> 
<#if id??>
	${ClassName} queryById(${rawidtype} ${id});
    
    int deleteById(${rawidtype} ${id});
    
    /*
	 * 不保证返回值的顺序
	 */
	List<${ClassName}> listByIds(List<${idtype}> ${id}s);
	
	List<${idtype}> listIds(${ClassName} ${classname},int offset,int pageSize);
    
    int deleteByIds(List<${idtype}> ${id}s);
	
	int updatePartByIds(List<${idtype}> ${id}s,${ClassName} oldStatus,${ClassName} newStatus);
</#if>
	
}
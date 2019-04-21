package ${module}.dao;

import java.util.List;
import ${javaPackage}.${ClassName};
import org.yx.db.dao.CountedResult;
import org.yx.db.dao.Pagable;


/*
 * 自动生成，不能修改。
 */
public interface ${ClassName}Dao {
	
	/** 增加  **/
	int insert(${ClassName} ${classname});
	
	/** 更新整条记录 **/
	int updateFull(${ClassName} ${classname});
	
	/** 删除 **/
	int delete(${ClassName} ${classname});

	/** 查询 **/
	List<${ClassName}> list(${ClassName} ${classname},int offset,int pageSize);
	
	/** 查询数量 **/
	long count(${ClassName} ${classname});
	
	/** 更新**/
	int updatePart(${ClassName} ${classname});
	
	/**demoUser、page都不能为null */
	CountedResult<${ClassName}> listAndCount(${ClassName} obj,Pagable page);
	

	<#-- 单主键才有的 --> 
<#if id??>
	/** 加载 **/
	${ClassName} queryById(${rawidtype} ${id});
    
    int deleteById(${rawidtype} ${id});
    
    /**
	 * 不保证返回值的顺序
	 */
	List<${ClassName}> listByIds(List<${idtype}> ${id}s);
	
	List<${idtype}> listIds(${ClassName} ${classname},int offset,int pageSize);
    
    int deleteByIds(List<${idtype}> ${id}s);
	
	int updatePartByIds(List<${idtype}> ${id}s,${ClassName} oldStatus,${ClassName} newStatus);
</#if>
	
}
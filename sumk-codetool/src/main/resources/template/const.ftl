package ${module}.constant;

public interface ${ClassName}Const {
	
	<#list cols as col>
	String ${col} = "${col}";
	</#list>
	
}
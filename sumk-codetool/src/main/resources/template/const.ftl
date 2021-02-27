package ${module}.constant;

public interface ${ClassName}Const {
	
	<#list cols as col>
	/**
	 * ${comments[col]}
	 */
	String ${col} = "${col}";
	
	</#list>
	
}
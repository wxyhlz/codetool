/**   
 * @Package	: com.dotblue.generate.generatecode.funtemplate.fun.impl 
 * @Title	: ControllerFunTemplate.java 
 * @date 	: 2013-12-16 上午11:35:51 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.generate.zcode.impl.gcontroller.fun;

import com.syscore.generate.zcode.inter.IFunTemplate;

/** 
 * @Description	:  Controller层方法模板
 * @ClassName: ControllerFunTemplate 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public class ControllerFunTemplate implements IFunTemplate {
	//去除Entity结尾
	private String removeEntity(String infoClassName){
		return infoClassName.replace("Entity","");
	}
	/**
	 * 返回Service的Str 
	 * @param infoClassName
	 * @return
	 */
	private String getServiceStr(String infoClassName){
		String classNameFirstLower = infoClassName.substring(0,1).toLowerCase() + infoClassName.substring(1);
		String classService = classNameFirstLower.substring(0, classNameFirstLower.lastIndexOf("Entity"))+"Facade"; 
		return classService;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#batchDelete(java.lang.String)
	 */
	public String batchDelete(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String classService = getServiceStr(infoClassName);
		
		String resultStr = "	/**\n"
			+ "	 * 批量删除\n"
			+ "	 * @param request\n"
			+ "	 */\n"
			+ "	@RequestMapping(value = \"/delete"+shortName+"Batch\")\n"
			+ "	@ResponseBody\n"
			+ "	public AdusResponse delete"+shortName+"Batch(HttpServletRequest request){\n"
			+ "		List<"+infoClassName+"> list = new ArrayList<"+infoClassName+">();\n"
			+ "		"+classService+".delete"+shortName+"Batch(list);\n"
			+ "		getAdusResponse().setSuccessed(true);\n"
			+ "		return getAdusResponse();\n"
			+ "	}\n\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#batchInsert(java.lang.String)
	 */
	public String batchInsert(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String classService = getServiceStr(infoClassName);
		
		String resultStr = "	/**\n"
			+ "	 * 批量新增\n"
			+ "	 * @param request\n"
			+ "	 */\n"
			+ "	@RequestMapping(value = \"/insert"+shortName+"Batch\")\n"
			+ "	@ResponseBody\n"
			+ "	public AdusResponse insert"+shortName+"Batch(HttpServletRequest request){\n"
			+ "		List<"+infoClassName+"> list = new ArrayList<"+infoClassName+">();\n"
			+ "		"+classService+".insert"+shortName+"Batch(list);\n"
			+ "		getAdusResponse().setSuccessed(true);\n"
			+ "		return getAdusResponse();\n"
			+ "	}\n\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#batchUpdate(java.lang.String)
	 */
	public String batchUpdate(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String classService = getServiceStr(infoClassName);
		
		String resultStr = "	/**\n"
			+ "	 * 批量修改\n"
			+ "	 * @param request\n"
			+ "	 */\n"
			+ "	@RequestMapping(value = \"/update"+shortName+"Batch\")\n"
			+ "	@ResponseBody\n"
			+ "	public AdusResponse update"+shortName+"Batch(HttpServletRequest request){\n"
			+ "		List<"+infoClassName+"> list = new ArrayList<"+infoClassName+">();\n"
			+ "		"+classService+".update"+shortName+"Batch(list);\n"
			+ "		getAdusResponse().setSuccessed(true);\n"
			+ "		return getAdusResponse();\n"
			+ "	}\n\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#delete(java.lang.String)
	 */
	public String delete(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String classService = getServiceStr(infoClassName);
		
		String resultStr = "	/**\n"
			+ "	 * 删除\n"
			+ "	 * @param entityInfo\n"
			+ "	 */\n"
			+ "	@RequestMapping(value = \"/delete"+shortName+"\")\n"
			+ "	@ResponseBody\n"
			+ "	public AdusResponse delete"+shortName+"("+infoClassName+" entityInfo){\n"
			+ "		"+classService+".delete"+shortName+"(entityInfo);\n"
			+ "		getAdusResponse().setSuccessed(true);\n"
			+ "		return getAdusResponse();\n"
			+ "	}\n\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#insert(java.lang.String)
	 */
	public String insert(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String classService = getServiceStr(infoClassName);
		
		String resultStr = "	/**\n"
			+ "	 * 新增\n"
			+ "	 * @param entityInfo\n"
			+ "	 */\n"
			+ "	@RequestMapping(value = \"/insert"+shortName+"\")\n"
			+ "	@ResponseBody\n"
			+ "	public AdusResponse insert"+shortName+"("+infoClassName+" entityInfo){\n"
			+ "		"+classService+".insert"+shortName+"(entityInfo);\n"
			+ "		getAdusResponse().setSuccessed(true);\n"
			+ "		return getAdusResponse();\n"
			+ "	}\n\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#selectEntites(java.lang.String)
	 */
	public String selectEntites(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String classService = getServiceStr(infoClassName);
		
		String resultStr = "	/**\n"
			+ "	 * 查询\n"
			+ "	 * @param entityInfo\n"
			+ "	 * @return\n"
			+ "	 */\n"
			+ "	@RequestMapping(value = \"/select"+shortName+"Entites\")\n"
			+ "	@ResponseBody\n"
			+ "	public AdusResponse select"+shortName+"Entites("+infoClassName+" entityInfo){\n"			
			+ "		List<"+infoClassName+"> infoList = "+classService+".select"+shortName+"Entites(entityInfo);\n"
			+ "		Map<String,Object> resultMap = new HashMap<String,Object>();\n"
			+ "		resultMap.put(\"data\",infoList);\n"
			+ "		getAdusResponse().setDataMap(resultMap);\n"
			+ "		getAdusResponse().setSuccessed(true);\n"
			+ "		return getAdusResponse();\n"
			+ "	}\n\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#selectPage(java.lang.String)
	 */
	public String selectPage(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String classService = getServiceStr(infoClassName);
		
		String resultStr = "	/**\n"
			+ "	 * 分页查询\n"
			+ "	 * @param request\n"
			+ "	 * @return\n"
			+ "	 */\n"
			+ "	@RequestMapping(value = \"/select"+shortName+"Page\")\n"
			+ "	@ResponseBody\n"
			+ "	public AdusResponse select"+shortName+"Page(HttpServletRequest request){\n"
			+ "		String pageNumber = request.getParameter(\"pageNumber\");\n"
			+ "		String pageSize = request.getParameter(\"pageSize\");\n"
			+ "		Map<String,Object> paramMap = new HashMap<String,Object>();\n"
			+ "		paramMap.put(\"pageNumber\", Integer.valueOf(pageNumber));\n"
			+ "		paramMap.put(\"pageSize\", Integer.valueOf(pageSize));\n"
			+ "		Map<String,Object> dataMap =  "+classService+".select"+shortName+"Page(paramMap);\n\n"
				
			+ "		getAdusResponse().setSuccessed(true);\n"
			+ "		getAdusResponse().setDataMap(dataMap);\n"
			+ "		return getAdusResponse();\n"
			+ "	}\n\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#selectSigle(java.lang.String)
	 */
	public String selectSigle(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String classService = getServiceStr(infoClassName);
		
		String resultStr = "	/**\n"
			+ "	 * 单个查询\n"
			+ "	 * @param entityInfo\n"
			+ "	 * @return\n"
			+ "	 */\n"
			+ "	@RequestMapping(value = \"/select"+shortName+"Entity\")\n"
			+ "	@ResponseBody\n"
			+ "	public AdusResponse select"+shortName+"Entity("+infoClassName+" entityInfo){\n"			
			+ "		"+infoClassName+" info = "+classService+".select"+shortName+"Entity(entityInfo);\n"
			+ "		Map<String,Object> resultMap = new HashMap<String,Object>();\n"
			+ "		resultMap.put(\"data\",info);\n"
			+ "		getAdusResponse().setDataMap(resultMap);\n"
			+ "		getAdusResponse().setSuccessed(true);\n"
			+ "		return getAdusResponse();\n"
			+ "	}\n\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#update(java.lang.String)
	 */
	public String update(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String classService = getServiceStr(infoClassName);
		
		String resultStr = "	/**\n"
			+ "	 * 修改\n"
			+ "	 * @param entityInfo\n"
			+ "	 */\n"
			+ "	@RequestMapping(value = \"/update"+shortName+"\")\n"
			+ "	@ResponseBody\n"
			+ "	public AdusResponse update"+shortName+"("+infoClassName+" entityInfo){\n"
			+ "		"+classService+".update"+shortName+"(entityInfo);\n"
			+ "		getAdusResponse().setSuccessed(true);\n"
			+ "		return getAdusResponse();\n"
			+ "	}\n\n";
		return resultStr;
	}
	
	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#update(java.lang.String)
	 */
	public String invalid(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String classService = getServiceStr(infoClassName);
		
		String resultStr = "	/**\n"
			+ "	 * 作废\n"
			+ "	 * @param entityInfo\n"
			+ "	 */\n"
			+ "	@RequestMapping(value = \"/invalid"+shortName+"\")\n"
			+ "	@ResponseBody\n"
			+ "	public AdusResponse invalid"+shortName+"("+infoClassName+" entityInfo){\n"
			+ "		"+classService+".invalid"+shortName+"(entityInfo);\n"
			+ "		getAdusResponse().setSuccessed(true);\n"
			+ "		return getAdusResponse();\n"
			+ "	}\n\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#selectMap(java.lang.String)
	 */
	public String selectMap(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String classService = getServiceStr(infoClassName);
		
		String resultStr = "/**\n"
			+ "	 * 查询Map\n"
			+ "	 * @param request\n"
			+ "	 * @return\n"
			+ "	 */\n"
			+ "	@RequestMapping(value = \"/select"+shortName+"Map\")\n"
			+ "	@ResponseBody\n"
			+ "	public AdusResponse select"+shortName+"Map(HttpServletRequest request){\n"	
			+ "		Map<String,Object> paramMap = new HashMap<String,Object>();\n"
			+ "		Map<String,Object> dataMap = "+classService+".select"+shortName+"Map(paramMap);\n"
			+ "		getAdusResponse().setDataMap(dataMap);\n"
			+ "		getAdusResponse().setSuccessed(true);\n"
			+ "		return getAdusResponse();\n"
			+ "	}\n\n";
		return resultStr;
	}

}

/**   
 * @Package	: com.dotblue.generate.generatecode.funtemplate.fun.impl 
 * @Title	: ServiceFunTemplate.java 
 * @date 	: 2013-12-16 上午11:35:16 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.generate.zcode.impl.gservice.fun;

import com.syscore.generate.zcode.inter.IFunTemplate;

/** 
 * @Description	:  Service业务层方法模板
 * @ClassName: ServiceFunTemplate 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public class ServiceIFunTemplate implements IFunTemplate {
	//去除Entity结尾
	private String removeEntity(String infoClassName){
		return infoClassName.replace("Entity","");
	}
	
	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#batchDelete(java.lang.String)
	 */
	public String batchDelete(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String resultStr =	"	/**\n"
			+ "	 * 批量删除\n"
			+ "	 * @param list\n"
			+ "	 */\n"
			+ "	public void delete"+shortName+"Batch(List<"+infoClassName+"> list);\n\n"; 
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#batchInsert(java.lang.String)
	 */
	public String batchInsert(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String resultStr =	"	/**\n"
			+ "	 * 批量新增\n"
			+ "	 * @param list\n"
			+ "	 */\n"
			+ "	public void insert"+shortName+"Batch(List<"+infoClassName+"> list);\n\n"; 
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#batchUpdate(java.lang.String)
	 */
	public String batchUpdate(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String resultStr =	"	/**\n"
			+ "	 * 批量修改\n"
			+ "	 * @param list\n"
			+ "	 */\n"
			+ "	public void update"+shortName+"Batch(List<"+infoClassName+"> list);\n\n"; 
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#delete(java.lang.String)
	 */
	public String delete(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String resultStr =	"	/**\n"
			+"	 * 删除\n"
			+"	 * @param entityInfo\n"
			+"	 */\n"
			+"	public void delete"+shortName+"("+infoClassName+" entityInfo);\n\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#insert(java.lang.String)
	 */
	public String insert(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String resultStr = "	/**\n"
			+ "	 * 新增\n"
			+ "	 * @param entityInfo\n"
			+ "	 */\n"
			+ "	public void insert"+shortName+"("+infoClassName+" entityInfo);\n\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#selectEntites(java.lang.String)
	 */
	public String selectEntites(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String resultStr = "	/**\n"
			+ "	 * 列表查询\n"
			+ "	 * @param entityInfo\n"
			+ "	 * @return\n"
			+ "	 */\n"
			+ "	public List<"+infoClassName+"> select"+shortName+"Entites("+infoClassName+" entityInfo);\n\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#selectPage(java.lang.String)
	 */
	public String selectPage(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String resultStr = "	/**\n"
			+ "	 * 分页查询\n"
			+ "	 * @param paramMap\n"
			+ "	 * @return\n"
			+ "	 */\n"
			+ "	public Map<String, Object> select"+shortName+"Page(Map<String, Object> paramMap);\n\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#selectSigle(java.lang.String)
	 */
	public String selectSigle(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String resultStr = "	/**\n"
			+ "	 * 查询\n"
			+ "	 * @param entityInfo\n"
			+ "	 * @return\n"
			+ "	 */\n"
			+ "	public "+infoClassName+" select"+shortName+"Entity("+infoClassName+" entityInfo);\n\n";
		return resultStr;  
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#update(java.lang.String)
	 */
	public String update(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String resultStr = "	/**\n"
			+ "	 * 修改\n"
			+ "	 * @param entityInfo\n"
			+ "	 */\n"
			+ "	public void update"+shortName+"("+infoClassName+" entityInfo);\n\n";
		return resultStr;
	}
	
	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#update(java.lang.String)
	 */
	public String invalid(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String resultStr = "	/**\n"
			+ "	 * 作废\n"
			+ "	 * @param entityInfo\n"
			+ "	 */\n"
			+ "	public void invalid"+shortName+"("+infoClassName+" entityInfo);\n\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#selectMap(java.lang.String)
	 */
	public String selectMap(String infoClassName) {
		String shortName = removeEntity(infoClassName);
		String resultStr = "	/**\n"
			+ "	 * 列表查询Map\n"
			+ "	 * @param paramMap\n"
			+ "	 * @return\n"
			+ "	 */\n"
			+ "	public Map<String,Object> select"+shortName+"Map(Map<String,Object> paramMap);\n\n";
		return resultStr;
	}

}

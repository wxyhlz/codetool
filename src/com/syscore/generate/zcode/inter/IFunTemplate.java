/**   
 * @Package	: com.dotblue.generate.generatecode.funtemplate.fun 
 * @Title	: IFunTemplate.java 
 * @date 	: 2013-12-16 上午11:22:20 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.generate.zcode.inter;

/** 
 * @Description	:  方法模板
 * @ClassName: IFunTemplate 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public interface IFunTemplate {
	
	/**
	 * 新增方法
	 * @param infoClassName
	 * @return
	 */
	public String insert(String infoClassName);
	
	/**
	 * 删除方法
	 * @param infoClassName
	 * @return
	 */
	public String delete(String infoClassName);
	
	/**
	 * 修改方法
	 * @param infoClassName
	 * @return
	 */
	public String update(String infoClassName);
	
	/**
	 * 作废方法
	 * @param infoClassName
	 * @return
	 */
	public String invalid(String infoClassName);
	
	/**
	 * 查询方法
	 * @param infoClassName
	 * @return
	 */
	public String selectEntites(String infoClassName);
	
	/**
	 * 分页查询方法
	 * @param infoClassName
	 * @return
	 */
	public String selectPage(String infoClassName);
	
	/**
	 * 单个查询方法
	 * @param infoPathClassName
	 * @return
	 */
	public String selectSigle(String infoPathClassName);
	
	/**
	 * 查询方法Map
	 * @param infoClassName
	 * @return
	 */
	public String selectMap(String infoClassName);
	
	/**
	 * 批量新增方法
	 * @param infoClassName
	 * @return
	 */
	public String batchInsert(String infoClassName);
	
	/**
	 * 批量删除方法
	 * @param infoClassName
	 * @return
	 */
	public String batchDelete(String infoClassName);
	
	/**
	 * 批量修改方法
	 * @param infoClassName
	 * @return
	 */
	public String batchUpdate(String infoClassName);

}

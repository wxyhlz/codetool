/**   
 * @Package	: com.dotblue.generate.generatecode.filetemplate.file 
 * @Title	: IFileTemplate.java 
 * @date 	: 2013-12-16 下午03:38:30 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.generate.zcode.inter;

import java.util.List;

import com.syscore.generate.table.model.ProjectColumnInfo;
import com.syscore.generate.table.model.ProjectTableInfo;
import com.syscore.generate.zcode.ctrldto.CtrlFileFunDto;

/** 
 * @Description	:  文件模板
 * @ClassName: IFileTemplate 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public interface IFileTemplate {
	/**
	 * 模板头
	 * @param info
	 */
	public String getHeader(ProjectTableInfo ftInfo,List<ProjectColumnInfo> fcList);
	
	/**
	 * 模板躯干	 
	 * @return
	 */
	public String getBody(ProjectTableInfo fcInfo, CtrlFileFunDto gnDto);
	
	/**
	 * 模板尾
	 */
	public String getEnd();

}

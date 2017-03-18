/**   
 * @Package	: com.dotblue.generate.generatecode.writecodetofile 
 * @Title	: ICodeToFile.java 
 * @date 	: 2013-12-16 下午06:46:55 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.generate.zcode.zfile;

import java.util.List;

import com.syscore.generate.table.model.ProjectColumnInfo;
import com.syscore.generate.table.model.ProjectTableInfo;
import com.syscore.generate.zcode.ctrldto.CtrlFileFunDto;

/** 
 * @Description	: 代码写成文件 
 * @ClassName: ICodeToFile 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public interface ICodeToFile {
	
	/**
	 * 生成Entity的实体文件
	 * @param filePath(文件路径)
	 * @param cffDto(文件方法控制)
	 * @param ptInfo(数据表)
	 * @param pcList(数据列)
	 */
	public void writeEntity(String filePath, CtrlFileFunDto cffDto, ProjectTableInfo ptInfo,List<ProjectColumnInfo> pcList);
	
	/**
	 * 生成Mapper的XML文件
	 * @param filePath(文件路径)
	 * @param cffDto(文件方法控制)
	 * @param ptInfo(数据表)
	 * @param pcList(数据列)
	 */
	public void writeMapperXML(String filePath, CtrlFileFunDto cffDto, ProjectTableInfo ptInfo,List<ProjectColumnInfo> pcList);
	
	/**
	 * 生成Mapper的接口文件
	 * @param filePath(文件路径)
	 * @param cffDto(文件方法控制)
	 * @param ptInfo(数据表)
	 * @param pcList(数据列)
	 */
	public void writeMapper(String filePath, CtrlFileFunDto cffDto, ProjectTableInfo ptInfo,List<ProjectColumnInfo> pcList);

	/**
	 * 生成Service的接口服务文件
	 * @param filePath(文件路径)
	 * @param cffDto(文件方法控制)
	 * @param ptInfo(数据表)
	 * @param pcList(数据列)
	 */
	public void writeIService(String filePath, CtrlFileFunDto cffDto, ProjectTableInfo ptInfo,List<ProjectColumnInfo> pcList);
	
	/**
	 * 生成Service的服务文件
	 * @param filePath(文件路径)
	 * @param cffDto(文件方法控制)
	 * @param ptInfo(数据表)
	 * @param pcList(数据列)
	 */
	public void writeService(String filePath, CtrlFileFunDto cffDto, ProjectTableInfo ptInfo,List<ProjectColumnInfo> pcList);
	
	/**
	 * 生成Facade的接口服务文件
	 * @param filePath(文件路径)
	 * @param cffDto(文件方法控制)
	 * @param ptInfo(数据表)
	 * @param pcList(数据列)
	 */
	public void writeIFacade(String filePath, CtrlFileFunDto cffDto, ProjectTableInfo ptInfo,List<ProjectColumnInfo> pcList);
	
	/**
	 * 生成Facade的服务文件
	 * @param filePath(文件路径)
	 * @param cffDto(文件方法控制)
	 * @param ptInfo(数据表)
	 * @param pcList(数据列)
	 */
	public void writeFacade(String filePath, CtrlFileFunDto cffDto, ProjectTableInfo ptInfo,List<ProjectColumnInfo> pcList);
	
	/**
	 * 生成Controller的MVC文件
	 * @param filePath(文件路径)
	 * @param cffDto(文件方法控制)
	 * @param ptInfo(数据表)
	 * @param pcList(数据列)
	 */
	public void writeController(String filePath, CtrlFileFunDto cffDto, ProjectTableInfo ptInfo,List<ProjectColumnInfo> pcList);
}

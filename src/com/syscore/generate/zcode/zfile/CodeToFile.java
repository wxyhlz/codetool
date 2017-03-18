/**   
 * @Package	: com.dotblue.generate.generatecode.writecodetofile.impl 
 * @Title	: CodeToFile.java 
 * @date 	: 2013-12-16 下午06:51:04 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.generate.zcode.zfile;

import java.util.List;

import com.syscore.generate.table.model.ProjectColumnInfo;
import com.syscore.generate.table.model.ProjectTableInfo;
import com.syscore.generate.zcode.ctrldto.CtrlFileFunDto;
import com.syscore.generate.zcode.impl.factory.FileInsatanceFactory;
import com.syscore.generate.zcode.inter.IFileTemplate;
import com.util.file.FileConstants;

/** 
 * @Description	:  
 * @ClassName: CodeToFile 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public class CodeToFile implements ICodeToFile {

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.writecodetofile.ICodeToFile#writeModel(com.dotblue.generate.generatecode.dto.GenerateDto, com.dotblue.generate.generateframe.model.FrameTableInfo, java.util.List)
	 */	
	public void writeEntity(String filePath, CtrlFileFunDto cffDto, ProjectTableInfo ptInfo,List<ProjectColumnInfo> pcList) {
		String className = ptInfo.getEntityclass();
		String classPath = ptInfo.getPkgmodelpath();
		
		IFileTemplate fileTemplate = FileInsatanceFactory.insatnceFunction("model");
		
		//内容
		String headerStr = fileTemplate.getHeader(ptInfo, pcList);
		String bodyStr = fileTemplate.getBody(ptInfo, cffDto);
		String endStr = fileTemplate.getEnd();
		
		String content = headerStr + bodyStr + endStr;
		
		//路径
		String controllerPath = filePath + "." + classPath;
		controllerPath = controllerPath.replaceAll("\\.", "//");
		
		//文件名
		String fileName = className+".java";
		
		//生成文件 
		FileConstants.writeFile(controllerPath, fileName, content);
		
	}
	
	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.writecodetofile.ICodeToFile#writeMapper(com.dotblue.generate.generatecode.dto.GenerateDto, com.dotblue.generate.generateframe.model.FrameTableInfo, java.util.List)
	 */
	public void writeMapper(String filePath, CtrlFileFunDto cffDto, ProjectTableInfo ptInfo,List<ProjectColumnInfo> pcList) {
		String className = ptInfo.getEntityclass();
		String classPath = ptInfo.getPkgmodelpath();
		
		IFileTemplate fileTemplate = FileInsatanceFactory.insatnceFunction("mapper");
		
		//内容
		String headerStr = fileTemplate.getHeader(ptInfo, pcList);
		String bodyStr = fileTemplate.getBody(ptInfo, cffDto);
		String endStr = fileTemplate.getEnd();
		
		String content = headerStr + bodyStr + endStr;
		
		//路径
		String controllerPath = filePath + "." + classPath.substring(0,classPath.lastIndexOf("model"))+"mapper";
		controllerPath = controllerPath.replaceAll("\\.", "//");
		
		//文件名
		String fileName = className.substring(0,className.lastIndexOf("Entity"))+"Mapper.java";
		
		//生成文件 
		FileConstants.writeFile(controllerPath, fileName, content);
		
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.writecodetofile.ICodeToFile#writeMapperXML(com.dotblue.generate.generatecode.dto.GenerateDto, com.dotblue.generate.generateframe.model.FrameTableInfo, java.util.List)
	 */
	public void writeMapperXML(String filePath, CtrlFileFunDto cffDto, ProjectTableInfo ptInfo,List<ProjectColumnInfo> pcList) {
		String className = ptInfo.getEntityclass();
		String classPath = ptInfo.getPkgmodelpath();
		
		IFileTemplate fileTemplate = FileInsatanceFactory.insatnceFunction("mapperxml");
		
		//内容
		String headerStr = fileTemplate.getHeader(ptInfo, pcList);
		String bodyStr = fileTemplate.getBody(ptInfo, cffDto);
		String endStr = fileTemplate.getEnd();
		
		String content = headerStr + bodyStr + endStr;
		
		//路径
		String controllerPath = filePath + "." + classPath.substring(0,classPath.lastIndexOf("model"))+"mapper";
		controllerPath = controllerPath.replaceAll("\\.", "//");
		
		//文件名
		String fileName = className.substring(0,className.lastIndexOf("Entity"))+"Mapper.xml";
		
		//生成文件 
		FileConstants.writeFile(controllerPath, fileName, content);
		
	}
	
	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.writecodetofile.ICodeToFile#writeService(com.dotblue.generate.generatecode.dto.GenerateDto, com.dotblue.generate.generateframe.model.FrameTableInfo, java.util.List)
	 */
	public void writeIService(String filePath, CtrlFileFunDto cffDto, ProjectTableInfo ptInfo,List<ProjectColumnInfo> pcList) {
		String className = ptInfo.getEntityclass();
		String classPath = ptInfo.getPkgmodelpath();
		
		IFileTemplate fileTemplate = FileInsatanceFactory.insatnceFunction("Iservice");
		
		//内容
		String headerStr = fileTemplate.getHeader(ptInfo, pcList);
		String bodyStr = fileTemplate.getBody(ptInfo, cffDto);
		String endStr = fileTemplate.getEnd();
		
		String content = headerStr + bodyStr + endStr;
		
		//路径
		String controllerPath = filePath + "." + classPath.substring(0,classPath.lastIndexOf("model"))+"service";
		controllerPath = controllerPath.replaceAll("\\.", "//");
		
		//文件名
		String fileName = "I"+className.substring(0,className.lastIndexOf("Entity"))+"Service.java";
		
		//生成文件 
		FileConstants.writeFile(controllerPath, fileName, content);
		
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.writecodetofile.ICodeToFile#writeService(com.dotblue.generate.generatecode.dto.GenerateDto, com.dotblue.generate.generateframe.model.FrameTableInfo, java.util.List)
	 */
	public void writeService(String filePath, CtrlFileFunDto cffDto, ProjectTableInfo ptInfo,List<ProjectColumnInfo> pcList) {
		String className = ptInfo.getEntityclass();
		String classPath = ptInfo.getPkgmodelpath();
		
		IFileTemplate fileTemplate = FileInsatanceFactory.insatnceFunction("service");
		
		//内容
		String headerStr = fileTemplate.getHeader(ptInfo, pcList);
		String bodyStr = fileTemplate.getBody(ptInfo, cffDto);
		String endStr = fileTemplate.getEnd();
		
		String content = headerStr + bodyStr + endStr;
		
		//路径
		String controllerPath = filePath + "." + classPath.substring(0,classPath.lastIndexOf("model"))+"service.impl";
		controllerPath = controllerPath.replaceAll("\\.", "//");
		
		//文件名
		String fileName = className.substring(0,className.lastIndexOf("Entity"))+"Service.java";
		
		//生成文件 
		FileConstants.writeFile(controllerPath, fileName, content);
		
	}
	
	@Override
	public void writeIFacade(String filePath, CtrlFileFunDto cffDto,	ProjectTableInfo ptInfo, List<ProjectColumnInfo> pcList) {
		String className = ptInfo.getEntityclass();
		String classPath = ptInfo.getPkgmodelpath();
		
		IFileTemplate fileTemplate = FileInsatanceFactory.insatnceFunction("Ifacade");
		
		//内容
		String headerStr = fileTemplate.getHeader(ptInfo, pcList);
		String bodyStr = fileTemplate.getBody(ptInfo, cffDto);
		String endStr = fileTemplate.getEnd();
		
		String content = headerStr + bodyStr + endStr;
		
		//路径
		String controllerPath = filePath + "." + classPath.substring(0,classPath.lastIndexOf("model"))+"facade";
		controllerPath = controllerPath.replaceAll("\\.", "//");
		
		//文件名
		String fileName = "I"+className.substring(0,className.lastIndexOf("Entity"))+"Facade.java";
		
		//生成文件 
		FileConstants.writeFile(controllerPath, fileName, content);
		
	}

	@Override
	public void writeFacade(String filePath, CtrlFileFunDto cffDto,	ProjectTableInfo ptInfo, List<ProjectColumnInfo> pcList) {
		String className = ptInfo.getEntityclass();
		String classPath = ptInfo.getPkgmodelpath();
		
		IFileTemplate fileTemplate = FileInsatanceFactory.insatnceFunction("facade");
		
		//内容
		String headerStr = fileTemplate.getHeader(ptInfo, pcList);
		String bodyStr = fileTemplate.getBody(ptInfo, cffDto);
		String endStr = fileTemplate.getEnd();
		
		String content = headerStr + bodyStr + endStr;
		
		//路径
		String controllerPath = filePath + "." + classPath.substring(0,classPath.lastIndexOf("model"))+"facade.impl";
		controllerPath = controllerPath.replaceAll("\\.", "//");
		
		//文件名
		String fileName = className.substring(0,className.lastIndexOf("Entity"))+"Facade.java";
		
		//生成文件 
		FileConstants.writeFile(controllerPath, fileName, content);
		
	}
	
	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.writecodetofile.ICodeToFile#writeController(com.dotblue.generate.generatecode.dto.GenerateDto, com.dotblue.generate.generateframe.model.FrameTableInfo, java.util.List)
	 */
	public void writeController(String filePath, CtrlFileFunDto cffDto, ProjectTableInfo ptInfo,List<ProjectColumnInfo> pcList) {
		String className = ptInfo.getEntityclass();
		String classPath = ptInfo.getPkgmodelpath();
		
		IFileTemplate fileTemplate = FileInsatanceFactory.insatnceFunction("controller");
		
		//内容
		String headerStr = fileTemplate.getHeader(ptInfo, pcList);
		String bodyStr = fileTemplate.getBody(ptInfo, cffDto);
		String endStr = fileTemplate.getEnd();
		
		String content = headerStr + bodyStr + endStr;
		
		//路径
		String controllerPath = filePath + "." + classPath.substring(0,classPath.lastIndexOf("model"))+"controller";
		controllerPath = controllerPath.replaceAll("\\.", "//");
		
		//文件名
		String fileName = className.substring(0,className.lastIndexOf("Entity"))+"Controller.java";
		
		//生成文件 
		FileConstants.writeFile(controllerPath, fileName, content);
		
	}
	 

}

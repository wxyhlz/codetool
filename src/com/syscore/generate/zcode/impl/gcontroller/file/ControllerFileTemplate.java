/**   
 * @Package	: com.dotblue.generate.generatecode.filetemplate.file.impl 
 * @Title	: ControllerFileTemplate.java 
 * @date 	: 2013-12-16 下午03:42:59 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.generate.zcode.impl.gcontroller.file;

import java.util.List;

import com.syscore.constants.ConstantsWeb;
import com.syscore.generate.table.model.ProjectColumnInfo;
import com.syscore.generate.table.model.ProjectTableInfo;
import com.syscore.generate.zcode.ctrldto.CtrlFileFunDto;
import com.syscore.generate.zcode.impl.factory.FunInsatanceFactory;
import com.syscore.generate.zcode.inter.IFileTemplate;
import com.syscore.generate.zcode.inter.IFunTemplate;
import com.util.sonar.SonarCode;

/** 
 * @Description	:  
 * @ClassName: ControllerFileTemplate 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public class ControllerFileTemplate implements IFileTemplate {

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.filetemplate.file.IFileTemplate#getBody(java.lang.String)
	 */
	public String getBody(ProjectTableInfo ftInfo, CtrlFileFunDto gnDto) {
		String infoClassName = ftInfo.getEntityclass();
		IFunTemplate funTemplate = FunInsatanceFactory.insatnceFunction("controller");
		String resultStr = "";
		if(gnDto.isFunInsert()){
			if(!"".equals(resultStr)) resultStr = resultStr + "\n";
			resultStr = resultStr + funTemplate.insert(infoClassName);
		}
		if(gnDto.isFunDelete()){
			if(!"".equals(resultStr)) resultStr = resultStr + "\n";
			resultStr = resultStr + funTemplate.delete(infoClassName);
		}
		if(gnDto.isFunUpdate()){
			if(!"".equals(resultStr)) resultStr = resultStr + "\n";
			resultStr = resultStr + funTemplate.update(infoClassName);
		}
		if(gnDto.isFunInvalid()){
			if(!"".equals(resultStr)) resultStr = resultStr + "\n";
			resultStr = resultStr + funTemplate.invalid(infoClassName);
		}
		if(gnDto.isFunSelectEntites()){
			if(!"".equals(resultStr)) resultStr = resultStr + "\n";
			resultStr = resultStr + funTemplate.selectEntites(infoClassName);
		}
		if(gnDto.isFunSelectMap()){
			if(!"".equals(resultStr)) resultStr = resultStr + "\n";
			resultStr = resultStr + funTemplate.selectMap(infoClassName);
		}
		if(gnDto.isFunSelectPage()){
			if(!"".equals(resultStr)) resultStr = resultStr + "\n";
			resultStr = resultStr + funTemplate.selectPage(infoClassName);
		}
		if(gnDto.isFunSelectSigle()){
			if(!"".equals(resultStr)) resultStr = resultStr + "\n";
			resultStr = resultStr + funTemplate.selectSigle(infoClassName);
		}
		if(gnDto.isFunBatchInsert()){
			if(!"".equals(resultStr)) resultStr = resultStr + "\n";
			resultStr = resultStr + funTemplate.batchInsert(infoClassName);
		}
		if(gnDto.isFunBatchDelete()){
			if(!"".equals(resultStr)) resultStr = resultStr + "\n";
			resultStr = resultStr + funTemplate.batchDelete(infoClassName);
		}
		if(gnDto.isFunBatchUpdate()){
			if(!"".equals(resultStr)) resultStr = resultStr + "\n";
			resultStr = resultStr + funTemplate.batchUpdate(infoClassName);
		}
		
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.filetemplate.file.IFileTemplate#getEnd()
	 */
	public String getEnd() {
		return "}";
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.filetemplate.file.IFileTemplate#getHeader(com.dotblue.generate.generateframe.model.FrameTableInfo, java.util.List)
	 */
	public String getHeader(ProjectTableInfo ftInfo,
			List<ProjectColumnInfo> fcList) {
		
		String comment = ftInfo.getTblcomment();
		String author = ftInfo.getAuthor();
		String classPath = ftInfo.getPkgmodelpath();
		String className = ftInfo.getEntityclass(); 
		String servicePath = classPath.substring(0, classPath.lastIndexOf("."))+".facade";
		String serviceClass = "I"+className.substring(0, className.lastIndexOf("Entity"))+"Facade";
		String serviceClassFirstLower =  serviceClass.substring(1, 2).toLowerCase()+serviceClass.substring(2);
		String controllerPath = classPath.substring(0, classPath.lastIndexOf("."))+".controller";
		String controllerClass = className.substring(0, className.lastIndexOf("Entity"))+"Controller";
		
		String requestPath = className.substring(0,className.lastIndexOf("Entity"));
		String requestPathFirstLower = requestPath.substring(0, 1).toLowerCase()+requestPath.substring(1);
		
		
		
		
		String resultStr = "/**\n"   
			+ " * @Package	: " + controllerPath + "\n" 
			+ " * @Title	: " + controllerClass + ".java\n"
			+ " * @date 	: " + ConstantsWeb.getSystemtime("-")+"\n"
			+ " * @author	: " + author + "\n"
			+ " * @version : V1.0\n"
			+ " */\n\n"
	
			+ "package " + controllerPath + ";\n\n"
	 
			+ "import java.util.ArrayList;\n"
			+ "import java.util.HashMap;\n"
			+ "import java.util.List;\n"
			+ "import java.util.Map;\n\n"
	
			+ "import javax.annotation.Resource;\n"
			+ "import javax.servlet.http.HttpServletRequest;\n"
			
			+ "import org.springframework.stereotype.Controller;\n"
			+ "import org.springframework.web.bind.annotation.RequestMapping;\n"
			+ "import org.springframework.web.bind.annotation.ResponseBody;\n\n"
				
			+ "import com.tuniu."+SonarCode.getProjectName(classPath)+".syscore.base.controller.BaseController;\n"
			+ "import com.tuniu."+SonarCode.getProjectName(classPath)+".syscore.utils.response.AdusResponse;\n"
			+ "import " + classPath + "." + className + ";\n"
			+ "import " + servicePath + "." + serviceClass + ";\n\n"
	
			+ "/**\n"
			+ " * @Description	: " + comment + "Controller\n"  
			+ " * @ClassName: " + controllerClass + "\n" 
			+ " * @author	 : " + author + "\n"
			+ " * @modified :\n"
			+ " * @modfTime :\n"
			+ " */\n"
			+ "@Controller\n"
			+ "@RequestMapping(\"/" + requestPathFirstLower + "\")\n"
			+ "public class " + controllerClass + " extends BaseController {\n\n"
				
			+ "	@Resource\n"
			+ "	private " + serviceClass + " " + serviceClassFirstLower + ";\n";
		
		return resultStr;
	}

}

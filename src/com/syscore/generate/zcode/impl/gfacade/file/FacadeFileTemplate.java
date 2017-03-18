/**   
 * @Package	: com.dotblue.generate.generatecode.filetemplate.file.impl 
 * @Title	: ServiceFileTemplate.java 
 * @date 	: 2013-12-16 下午03:42:30 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.generate.zcode.impl.gfacade.file;

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
 * @ClassName: ServiceFileTemplate 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public class FacadeFileTemplate implements IFileTemplate {

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.filetemplate.file.IFileTemplate#getBody(java.lang.String)
	 */
	public String getBody(ProjectTableInfo ftInfo, CtrlFileFunDto gnDto) {
		String infoClassName = ftInfo.getEntityclass();
		IFunTemplate funTemplate = FunInsatanceFactory.insatnceFunction("facade");
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
		String iServicePath = classPath.substring(0, classPath.lastIndexOf("."))+".service";
		String iServiceClass = "I"+className.substring(0, className.lastIndexOf("Entity"))+"Service";
		String iSeviceClassFirstLower = iServiceClass.substring(1, 2).toLowerCase()+iServiceClass.substring(2);
		String iFacadePath = classPath.substring(0, classPath.lastIndexOf("."))+".facade";
		String iFacadeClass = "I"+className.substring(0, className.lastIndexOf("Entity"))+"Facade";
		String facadePath = classPath.substring(0, classPath.lastIndexOf("."))+".facade.impl";
		String facadeClass = className.substring(0, className.lastIndexOf("Entity"))+"Facade";
		
		String resultStr = "/**\n"   
			+ " * @Package	: " + facadePath + "\n"
			+ " * @Title	: " + facadeClass + ".java\n"
			+ " * @date 	: " + ConstantsWeb.getSystemtime("-")+"\n" 
			+ " * @author	: " + author + "\n"
			+ " * @version : V1.0\n" 
			+ " */\n\n" 
	
			+ "package " + facadePath + ";\n\n"
			
			+ "import java.util.HashMap;\n"
			+ "import java.util.List;\n"
			+ "import java.util.Map;\n\n"
			
			+ "import javax.annotation.Resource;\n\n"			
			+ "import org.springframework.stereotype.Service;\n\n" 
	
			+ "import " + iFacadePath + "." + iFacadeClass + ";\n"
			+ "import " + classPath + "." + className + ";\n"
			+ "import " + iServicePath + "." + iServiceClass + ";\n"
			+ "import com.tuniu."+SonarCode.getProjectName(classPath)+".syscore.base.facade.impl.BaseFacade;\n\n"
	
	
			+ "/**\n"
			+ " * @Description	: " + comment + "Service\n"  
			+ " * @ClassName: " + facadeClass + "\n"
			+ " * @author	: " + author + "\n"
			+ " * @modified :\n"
			+ " * @modfTime :\n" 
			+ " */\n"
			+ "@Service\n"
			+ "public class " + facadeClass + " extends BaseFacade<" + className + "> implements " + iFacadeClass + "{\n\n"
				
			+ "	@Resource\n"
			+ "	private " + iServiceClass + " " + iSeviceClassFirstLower + ";\n\n";
		
		return resultStr;
	}

}

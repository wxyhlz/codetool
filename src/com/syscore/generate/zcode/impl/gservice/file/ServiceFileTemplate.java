/**   
 * @Package	: com.dotblue.generate.generatecode.filetemplate.file.impl 
 * @Title	: ServiceFileTemplate.java 
 * @date 	: 2013-12-16 下午03:42:30 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.generate.zcode.impl.gservice.file;

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

public class ServiceFileTemplate implements IFileTemplate {

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.filetemplate.file.IFileTemplate#getBody(java.lang.String)
	 */
	public String getBody(ProjectTableInfo ftInfo, CtrlFileFunDto gnDto) {
		String infoClassName = ftInfo.getEntityclass();
		IFunTemplate funTemplate = FunInsatanceFactory.insatnceFunction("service");
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
		String mapperPath = classPath.substring(0, classPath.lastIndexOf("."))+".mapper";
		String mapperClass = className.substring(0, className.lastIndexOf("Entity"))+"Mapper";
		String mapperClassFirstLower = mapperClass.substring(0, 1).toLowerCase()+mapperClass.substring(1);
		String iServicePath = classPath.substring(0, classPath.lastIndexOf("."));
		String servicePath = iServicePath+".service.impl";
		String serviceClass = className.substring(0, className.lastIndexOf("Entity"))+"Service";
		
		String resultStr = "/**\n"   
			+ " * @Package	: " + servicePath + "\n"
			+ " * @Title	: " + serviceClass + ".java\n"
			+ " * @date 	: " + ConstantsWeb.getSystemtime("-")+"\n" 
			+ " * @author	: " + author + "\n"
			+ " * @version : V1.0\n" 
			+ " */\n\n" 
	
			+ "package " + servicePath + ";\n\n"
	
			+ "import java.util.HashMap;\n"
			+ "import java.util.List;\n"
			+ "import java.util.Map;\n\n"
	 		
			+ "import javax.annotation.Resource;\n\n"
			+ "import org.springframework.stereotype.Service;\n\n"
	
			+ "import com.tuniu."+SonarCode.getProjectName(classPath)+".syscore.utils.except.BusinessException;\n"
			+ "import com.tuniu."+SonarCode.getProjectName(classPath)+".syscore.base.service.impl.BaseService;\n"
			+ "import com.tuniu."+SonarCode.getProjectName(classPath)+".syscore.base.page.Page;\n"
			+ "import com.tuniu."+SonarCode.getProjectName(classPath)+".syscore.base.page.PageUtil;\n"
			+ "import " + mapperPath + "." + mapperClass + ";\n"
			+ "import " + classPath + "." + className + ";\n"
			+ "import " + iServicePath +".service."+"I"+serviceClass+";\n\n"
	
	
			+ "/**\n"
			+ " * @Description	: " + comment + "Service\n"  
			+ " * @ClassName: " + serviceClass + "\n"
			+ " * @author	: " + author + "\n"
			+ " * @modified :\n"
			+ " * @modfTime :\n" 
			+ " */\n"
			+ "@Service\n"
			+ "public class " + serviceClass + " extends BaseService<" + className + "> implements I"+serviceClass+" {\n\n"
				
			+ "	@Resource\n"
			+ "	private " + mapperClass + " " + mapperClassFirstLower + ";\n\n";
		
		return resultStr;
	}

}

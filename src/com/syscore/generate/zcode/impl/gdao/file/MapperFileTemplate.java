/**   
 * @Package	: com.dotblue.generate.generatecode.filetemplate.file.impl 
 * @Title	: MapperFileTemplate.java 
 * @date 	: 2013-12-16 下午03:42:07 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.generate.zcode.impl.gdao.file;

import java.util.List;

import com.syscore.constants.ConstantsWeb;
import com.syscore.generate.table.model.ProjectColumnInfo;
import com.syscore.generate.table.model.ProjectTableInfo;
import com.syscore.generate.zcode.ctrldto.CtrlFileFunDto;
import com.syscore.generate.zcode.inter.IFileTemplate;
import com.util.sonar.SonarCode;

/** 
 * @Description	:  
 * @ClassName: MapperFileTemplate 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public class MapperFileTemplate implements IFileTemplate {

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.filetemplate.file.IFileTemplate#getBody(java.lang.String)
	 */
	public String getBody(ProjectTableInfo ftInfo, CtrlFileFunDto gnDto) {
		return "\n\n";
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.filetemplate.file.IFileTemplate#getEnd()
	 */
	public String getEnd() {
		return "}\n";
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
		
		String resultStr = "/**\n"
			+ " * @Package	: " + mapperPath + "\n" 
			+ " * @Title	: " + mapperClass + ".java\n"
			+ " * @date 	: " + ConstantsWeb.getSystemtime("-")+"\n" 
			+ " * @author	: " + author + "\n"
			+ " * @version : V1.0\n"
			+ " */\n\n"
	
			+ "package "+mapperPath+";\n\n"
		
			+ "import com.tuniu."+SonarCode.getProjectName(classPath)+".syscore.base.dao.IBaseMapper;\n"
			+ "import " + classPath + "." + className + ";\n\n"
	
			+ "/**\n"
			+ " * @Description	:  " + comment + "Mapper\n"
			+ " * @ClassName: " + mapperClass + "\n"
			+ " * @author	 : " + author + "\n"
			+ " * @modified :\n"
			+ " * @modfTime :\n"
			+ " */\n"
	
			+ "public interface " + mapperClass + " extends IBaseMapper<" + className + "> {";
		return resultStr;
	}

}

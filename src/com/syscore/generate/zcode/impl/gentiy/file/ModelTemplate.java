/**   
 * @Package	: com.dotblue.generate.generatecode.filetemplate.file.impl 
 * @Title	: ModelTemplate.java 
 * @date 	: 2013-12-16 下午06:24:01 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.generate.zcode.impl.gentiy.file;

import java.util.List;

import com.syscore.constants.ConstantsWeb;
import com.syscore.generate.table.model.ProjectColumnInfo;
import com.syscore.generate.table.model.ProjectTableInfo;
import com.syscore.generate.zcode.ctrldto.CtrlFileFunDto;
import com.syscore.generate.zcode.inter.IFileTemplate;
import com.util.sonar.SonarCode;

/** 
 * @Description	:  
 * @ClassName: ModelTemplate 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public class ModelTemplate implements IFileTemplate {

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.filetemplate.file.IFileTemplate#getBody(java.lang.String, com.dotblue.generate.generatecode.dto.GenerateDto)
	 */
	public String getBody(ProjectTableInfo ftInfo, CtrlFileFunDto gnDto) {
		return "\n";
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
	public String getHeader(ProjectTableInfo ftInfo, List<ProjectColumnInfo> fcList) {
		String comment = ftInfo.getTblcomment();
		String author = ftInfo.getAuthor();
		String classPath = ftInfo.getPkgmodelpath();
		String className = ftInfo.getEntityclass();
		
		String headStr = "/**\n"   
			+ " * @Package	: " + classPath + "\n"
			+ " * @Title	: " + className + ".java\n"
			+ " * @date 	: " + ConstantsWeb.getSystemtime("-") + "\n" 
			+ " * @author	: " + author + "\n"
			+ " * @version : V1.0\n"
			+ " */\n\n"
	
			+ "package " + classPath + ";\n\n"
			
			+ "import java.util.Date;\n"
			+ "import com.tuniu."+SonarCode.getProjectName(classPath)+".syscore.base.entity.BaseEntity;\n\n"
	
			+ "/**\n"
			+ " * @Description	: " + comment + "Entity\n" 
			+ " * @ClassName: " + className + "\n"
			+ " * @author	 : " + author + "\n"
			+ " * @modified :\n"
			+ " * @modfTime :\n"
			+ " */\n\n"
	
			+ "public class " + className + " extends BaseEntity {\n\n"
	
			+ "	/**\n" 
			+ "	 * @Fields serialVersionUID : TODO(实例化标识)\n" 
			+ "	 */\n\n"
				
			+ "	private static final long serialVersionUID = 1L;";
		
		String propertyStr = "";
		String setGetProperty = "";
		for(ProjectColumnInfo v : fcList){
			//id 基类定义
			if(!"id".equals(v.getJavacolname().toLowerCase())){
				// get set 方法名
				String gsFunName = SonarCode.upperFirstCase(v.getJavacolname());
				// java 类型
				String javaType = v.getJavatype();
				// java 属性名
				String javaColName = v.getJavacolname();
				// 注释
				String fieldComment = v.getColcomment();	
				
					
				propertyStr = propertyStr + "\n\n	/**\n"
					+ "	 * "+fieldComment+"\n"
					+ "	 */\n"
					+ "	private "+javaType+" "+javaColName+";";
				
				setGetProperty = setGetProperty + "\n\n	/**\n"
					+ "	 * @return the "+javaColName+"\n"
					+ "	 */\n"
					+ "	public "+javaType+" get"+gsFunName+"() {\n"
					+ "		return "+javaColName+";\n"
					+ "	}\n\n"
				
					+ "	/**\n"
					+ "	 * @param "+javaColName+" the "+javaColName+" to set\n"
					+ "	 */\n"
					+ "	public void set"+gsFunName+"("+javaType+" "+javaColName+") {\n"
					+ "		this."+javaColName+" = "+javaColName+";\n"
					+ "	}"; 
			}
		}
		
		String resultStr = headStr + propertyStr + setGetProperty;
			
		return resultStr;
	}

}

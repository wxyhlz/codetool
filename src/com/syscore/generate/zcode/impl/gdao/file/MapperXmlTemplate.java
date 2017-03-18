/**   
 * @Package	: com.dotblue.generate.generatecode.filetemplate.file.impl 
 * @Title	: MapperXmlTemplate.java 
 * @date 	: 2013-12-16 下午03:41:42 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.generate.zcode.impl.gdao.file;

import java.util.List;

import com.syscore.generate.table.model.ProjectColumnInfo;
import com.syscore.generate.table.model.ProjectTableInfo;
import com.syscore.generate.zcode.ctrldto.CtrlFileFunDto;
import com.syscore.generate.zcode.impl.factory.FunInsatanceFactory;
import com.syscore.generate.zcode.inter.IFileTemplate;
import com.syscore.generate.zcode.inter.IFunTemplate;

/** 
 * @Description	:  
 * @ClassName: MapperXmlTemplate 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public class MapperXmlTemplate implements IFileTemplate {

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.filetemplate.file.IFileTemplate#getBody(java.lang.String)
	 */
	public String getBody(ProjectTableInfo ftInfo, CtrlFileFunDto gnDto) {
		//String infoClassName = ftInfo.getEntityclass();
		String infoClassName = ftInfo.getEntityclass() + ";" + ftInfo.getTblname();
		String pkgModelPath = ftInfo.getPkgmodelpath();
		IFunTemplate funTemplate = FunInsatanceFactory.insatnceFunction("mapper");
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
			String pathClass = pkgModelPath + "." + infoClassName;
			resultStr = resultStr + funTemplate.selectSigle(pathClass);
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
		String resultStr = "</mapper>";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.filetemplate.file.IFileTemplate#getHeader(com.dotblue.generate.generateframe.model.FrameTableInfo, java.util.List)
	 */
	public String getHeader(ProjectTableInfo ftInfo,
			List<ProjectColumnInfo> fcList) {
		String classPath = ftInfo.getPkgmodelpath();
		String className = ftInfo.getEntityclass();
		String classMapper = className.substring(0, className.lastIndexOf("Entity"))+"Mapper"; 	
		String classMapperPath = classPath.substring(0, classPath.lastIndexOf("."))+".mapper";
		String resultMapper = classMapper.substring(0,1).toLowerCase() + classMapper.substring(1);
		resultMapper = resultMapper.substring(0, resultMapper.lastIndexOf("Mapper"))+"Map";
		
		String resultStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
		+ "<!DOCTYPE mapper\n"
		+ "  PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n"
		+ "  \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n"
		+ "<mapper namespace=\"" + classMapperPath + "." + classMapper + "\">\n"
		+ "	<resultMap id=\"" + resultMapper + "\" type=\"" + classPath + "." + className + "\">\n";
		
		//属性
		String propertyMapStr = ""; //resultMap
		String sqlFields = "";		//sql_fields
		String sqlValues = "";		//sql_values
		String sqlForeachObj = "";	//sql_foreachObj
		String sqlSets = "";		//sql_sets;
		String sqlConditions = "";	//sql_conditions
		for(ProjectColumnInfo v:fcList){
			String colName = v.getColname();
			String javaColName = v.getJavacolname();	//add 2017-02-04 09:20
			String colComment = v.getColcomment();
	
			if("".equals(propertyMapStr)){
				sqlFields = colName;
				sqlValues = "#{" + javaColName+ "}";
				sqlForeachObj = "#{obj." + javaColName + "}";
				sqlSets = "<if test=\"" + javaColName + " != null\">" + colName + " = #{" + javaColName + "},</if>";
				sqlConditions = "<if test=\"" + javaColName + " != null\">" + colName + " = #{" + javaColName + "}</if>";
				
				propertyMapStr = "		<!-- "+colComment+" -->\n"
					+"		<result property=\""+javaColName+"\" column=\""+colName+"\"/>\n";
				
			}else{
				sqlFields = sqlFields + ", " + colName;
				sqlValues = sqlValues + ",#{" + javaColName+ "}";
				sqlForeachObj = sqlForeachObj + ",#{obj." + javaColName + "}";
				sqlSets = sqlSets + "\n			<if test=\"" + javaColName + " != null\">" + colName + " = #{" + javaColName + "},</if>";
				sqlConditions = sqlConditions + "\n			<if test=\"" + javaColName + " != null\">" + colName + " = #{" + javaColName + "}</if>";
				
				propertyMapStr = propertyMapStr + "		<!-- "+colComment+" -->\n"
					+"		<result property=\""+javaColName+"\" column=\""+colName+"\"/>\n";
				
			} 
		}
		
		resultStr = resultStr + propertyMapStr + "	</resultMap>\n\n";


		resultStr = resultStr + "<!-- Begin Base Config -->\n"		
			+ "	<sql id=\"fields\" ><!-- ALl Fields -->\n"
			+ "		"+sqlFields+"\n"
			+ "	</sql>\n\n"
				
			+ "	<sql id=\"values\">\n"
			+ "		"+sqlValues+"\n"
			+ "	</sql>\n\n"
				
			+ "	<sql id=\"foreachObj\">\n"
			+ "		"+sqlForeachObj+"\n"
			+ "	</sql>\n\n"
				 
			+ "	<sql id=\"sets\"><!-- Set Fields -->\n"
			+ "		<trim  suffixOverrides=\",\" >\n"
			+ "			"+sqlSets+"\n" 
			+ "		</trim>\n"
			+ "	</sql>\n\n"
				
			+ "	<sql id=\"conditions\"><!-- Search Condition -->\n"
			+ "		<trim prefix=\"where\" prefixOverrides=\"and |or\">\n"
			+ "	        "+sqlConditions+"\n"
			+ "	    </trim>\n"
			+ "	</sql>\n"
			+ "<!-- End Base Config -->\n\n";
		return resultStr;
	}

}

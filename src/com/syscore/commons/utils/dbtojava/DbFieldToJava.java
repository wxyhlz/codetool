package com.syscore.commons.utils.dbtojava;

/** 
 * @ClassName:  DbFieldToJava
 * 
 * @Description: 数据库字段类型转换成Java类型
 *  
 * @author: tide
 *  
 * @date: 2013-02-27 
 *  
 */

public class DbFieldToJava {

	public static String changeType(String fieldType) {
		String resultType = null;
		if ("int".equals(fieldType.toLowerCase())) {
			resultType = "Integer";
		} else if ("integer".equals(fieldType.toLowerCase())) {
			resultType = "Integer";
		} else if ("varchar".equals(fieldType.toLowerCase())) {
			resultType = "String";
		} else if ("char".equals(fieldType.toLowerCase())) {
			resultType = "String";
		} else if ("text".equals(fieldType.toLowerCase())) {
			resultType = "String";
		} else if ("datetime".equals(fieldType.toLowerCase())) {
			resultType = "Date";
		} else if ("boolean".equals(fieldType.toLowerCase())) {
			resultType = "Boolean";
		} else if ("double".equals(fieldType.toLowerCase())) {
			resultType = "Double";
		} else if ("float".equals(fieldType.toLowerCase())) {
			resultType = "Double";
		} else if ("varchar2".equals(fieldType.toLowerCase())) {
			resultType = "String";
		} else if ("number".equals(fieldType.toLowerCase())) {
			resultType = "Integer";
		} else if ("nvarchar2".equals(fieldType.toLowerCase())){
			resultType = "String";
		} else if ("smallint".equals(fieldType.toLowerCase())){
			resultType = "Integer";
		} else if ("tinyint".equals(fieldType.toLowerCase())){
			resultType = "Integer";
		} else if ("mediumint".equals(fieldType.toLowerCase())){
			resultType = "Integer";
		} else if ("decimal".equals(fieldType.toLowerCase())){
			resultType = "Double";
		}else if ("date".equals(fieldType.toLowerCase())){
			resultType = "Date";
		}else if ("timestamp".equals(fieldType.toLowerCase())){
			resultType = "Date";
		}else if ("enum".equals(fieldType.toLowerCase())){
			resultType = "Integer";
		}else{
			System.out.println("please input change type : " + fieldType);
		}

		return resultType;
	}

}

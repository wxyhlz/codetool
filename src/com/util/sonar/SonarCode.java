package com.util.sonar;

public class SonarCode {

	/**
	 * 首字母大写
	 * 
	 * @param name
	 * @return
	 */
	public static String upperFirstCase(String source) {
		// name = name.substring(0, 1).toUpperCase() + name.substring(1);
		// return name;		
		char[] cs = source.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}
 	
	private static String camelUpperFirstCase(String source){
		// name = name.substring(0, 1).toUpperCase() + name.substring(1);
		// return name;
		char[] cs = source.toLowerCase().toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}

	/**
	 * 驼峰
	 * 
	 * @param source
	 * @param splitSymbol
	 * @return
	 */
	public static String camelCode(String source, String splitSymbol) {
		String[] arryField;
		if(splitSymbol==null){
			arryField = source.split("_");
		} else {
			arryField = source.split(splitSymbol);
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arryField.length; i++) {
			if (i == 0) {
				sb.append(arryField[i]);
			} else {
				sb.append(camelUpperFirstCase(arryField[i]));
			}
		}
		return sb.toString();
	}
	 
	/**
	 * 项目名称替换
	 * @param source
	 * @param destination
	 * @return
	 */
	public static String projectPkgReplace(String source,String destination){		
		if((source!=null) && (destination!=null)){
			String[] arrySource = source.split("\\.");
			if(arrySource.length>2){
				String[] arryDest = destination.split("\\.");
				if(arryDest.length>2){
					arryDest[2] = arrySource[2];
					return String.valueOf(arryDest);
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取项目名称
	 * @param source
	 * @param destination
	 * @return
	 */
	public static String getProjectName(String pckPath){		
		if(pckPath!=null){
			String[] arryPck = pckPath.split("\\.");
			if(arryPck.length>2){
				return arryPck[2]; 
			}
		}
		return null;
	}
}

/**   
 * @Package	: com.dotblue.generate.generatecode.funtemplate.fun.impl 
 * @Title	: MapperxmlFunTemplate.java 
 * @date 	: 2013-12-16 上午11:36:17 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.generate.zcode.impl.gdao.fun;

import com.syscore.generate.zcode.inter.IFunTemplate;

/** 
 * @Description	:  Mapper层业务模板
 * @ClassName: MapperxmlFunTemplate 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public class MapperFunTemplate implements IFunTemplate {
	
	
	private String[] getEntityAndTable(String infoClassName){
		String[] entityAndTable = infoClassName.split(";");
		String entity = entityAndTable[0];
		entity = entity.substring(0,entity.length()-6);
		entityAndTable[0] = entity;
		return entityAndTable;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#batchDelete(java.lang.String)
	 */
	public String batchDelete(String infoClassName) {
		String tableName = getEntityAndTable(infoClassName)[1];
		
		String resultStr = "	<!-- 批量删除 参数放入id集合 -->\n"
		    + "	<delete id=\"deleteBatch\" parameterType=\"Map\">\n"
		    + "		delete from "+tableName.toLowerCase()+"\n"
		    + "		where id in #{ids}\n"
		    + "	</delete>\n";
		return resultStr; 
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#batchInsert(java.lang.String)
	 */
	public String batchInsert(String infoClassName) {
		String tableName = getEntityAndTable(infoClassName)[1];
		String resultStr="	<!-- 批量增加 -->\n"
			+ "	<insert id=\"insertBatch\" parameterType=\"List\" >\n"
			+ "		insert into "+tableName+"(<include refid=\"fields\"/>)\n"
			+ "		values\n"
			+ "			<foreach collection=\"list\" item=\"obj\" index=\"index\" separator=\",\">\n" 
			+ "				(<include refid=\"foreachObj\"/>)\n"
			+ "			</foreach>\n"
			+ "	</insert>\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#batchUpdate(java.lang.String)
	 */
	public String batchUpdate(String infoClassName) {
		/**
		 * 所谓的批量修改，在只知道ID的情况下，就是单条循环的修改
		 */
		return "";
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#delete(java.lang.String)
	 */
	public String delete(String infoClassName) {
		String tableName = getEntityAndTable(infoClassName)[1];
		
		String resultStr = "	<!-- 删除数据 -->\n"
		    + "	<delete id=\"delete\" parameterType=\"Object\">\n"
		    + "		delete from "+tableName.toLowerCase()+"\n"
		    + "		<include refid=\"conditions\"/>\n"
		    + "	</delete>\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#insert(java.lang.String)
	 */
	public String insert(String infoClassName) {
		String tableName = getEntityAndTable(infoClassName)[1];
		
		String resultStr = "	<!-- 新增 -->\n"
			+ "	<insert id=\"insert\" parameterType=\"Object\" >\n"
			+ "		insert into "+tableName.toLowerCase()+"(<include refid=\"fields\"/>)\n"
			+ "		values(<include refid=\"values\"/>)\n"
			+ "	</insert>\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#selectEntites(java.lang.String)
	 */
	public String selectEntites(String infoClassName) {
		String[] entityAndTable = getEntityAndTable(infoClassName);
		String tableName = entityAndTable[1];
		
		String resultMapStr = entityAndTable[0]; 
		resultMapStr = resultMapStr.substring(0, 1).toLowerCase() + resultMapStr.substring(1)+"Map"; ;
		
		String resultStr = "	<!-- 获取List<T>对象 -->\n"
		    + "	<select id=\"selectEntities\" parameterType=\"Object\" resultMap=\""+resultMapStr+"\">\n" 
		    + "		select <include refid=\"fields\"/> from "+tableName.toLowerCase()+"\n"
		    + "		<include refid=\"conditions\"/>\n"
		    + "		order by id\n"
		    + "	</select>\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#selectPage(java.lang.String)
	 */
	public String selectPage(String infoClassName) {
		String[] entityAndTable = getEntityAndTable(infoClassName);
		String entity = entityAndTable[0]; 
		String tableName = entityAndTable[1];
		
		String resultMapStr = entity +"Map";
		resultMapStr = resultMapStr.substring(0, 1).toLowerCase() + resultMapStr.substring(1);
				
		String resultStr = "	<!-- 获取记录Count数量 -->\n"
			+"	<select id=\"selectPageCount\" parameterType=\"Map\" resultType=\"Integer\">\n"
			+"		select count(*) from "+tableName.toLowerCase()+"\n" 
			+"	</select>\n"
			+"	<!-- 获取List<T>分页对象 -->\n"
			+"	<select id=\"selectPageEntities\" parameterType=\"Map\" resultMap=\""+resultMapStr+"\">\n" 
			+"		select * from "+tableName.toLowerCase()+"\n"
			+"		order by id desc limit #{pageBegin},#{pageSize}\n"	
			+"	</select>\n";
		
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#selectSigle(java.lang.String)
	 */
	public String selectSigle(String infoPathClassName) {		
		String[] entityAndTable = getEntityAndTable(infoPathClassName);
		String entity = entityAndTable[0]+"Entity"; 
		String tableName = entityAndTable[1];
				
		String resultStr = "	<!--获取T对象 -->\n"
			+ "	<select id=\"selectEntity\" parameterType=\"Object\" resultType=\""+entity+"\">\n" 
			+ "		select <include refid=\"fields\"/> from "+tableName.toLowerCase()+"\n"
			+ "		<include refid=\"conditions\"/>\n"
			+ "	</select>\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#update(java.lang.String)
	 */
	public String update(String infoClassName) {
		String[] entityAndTable = getEntityAndTable(infoClassName);
		String tableName = entityAndTable[1];
		
		String resultStr = "	<!-- 修改数据 -->\n"
			+ "	<update id=\"update\" parameterType=\"Object\">\n"
			+ "		update "+tableName.toLowerCase()+" set\n"
			+ "		<include refid=\"sets\"/>\n"
			+ "		where id = #{id}\n"
			+ "	</update>\n";
		return resultStr;
	}
	
	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#update(java.lang.String)
	 */
	public String invalid(String infoClassName) {
		String[] entityAndTable = getEntityAndTable(infoClassName);
		String tableName = entityAndTable[1];
		
		String resultStr = "	<!-- 作废数据 -->\n"
			+ "	<update id=\"invalid\" parameterType=\"Object\">\n"
			+ "		update "+tableName.toLowerCase()+" set mark=-1\n"
			+ "		where id = #{id}\n"
			+ "	</update>\n";
		return resultStr;
	}

	/* (non-Javadoc)
	 * @see com.dotblue.generate.generatecode.funtemplate.fun.IFunTemplate#selectMap(java.lang.String)
	 */
	public String selectMap(String infoClassName) {
		String[] entityAndTable = getEntityAndTable(infoClassName);
		String tableName = entityAndTable[1];
		String entity = entityAndTable[0];
		
		String resultMapStr = entity +"Map";
		resultMapStr = resultMapStr.substring(0, 1).toLowerCase() + resultMapStr.substring(1); 
		
		String resultStr = "	<!-- 获取List<T>对象 -->\n"
		    + "	<select id=\"selectMap\" parameterType=\"Map\" resultMap=\""+resultMapStr+"\">\n" 
		    + "		select <include refid=\"fields\"/> from "+tableName.toLowerCase()+"\n"
		    + "		<include refid=\"conditions\"/>\n"
		    + "		order by id\n"
		    + "	</select>\n";
		return resultStr;
	}

}

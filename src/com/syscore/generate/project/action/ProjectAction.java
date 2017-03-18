/*
 * 版　权: 江苏百年软件  Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ProjectAction.java
 * 
 */
package com.syscore.generate.project.action;

import java.util.HashMap;
import java.util.Map;

import com.syscore.commons.action.BasAction;
import com.syscore.constants.ConstantsWeb;
import com.syscore.generate.project.model.ProjectInfo;
import com.syscore.generate.project.service.IProjectService;

/**
 * @ClassName : ProjectAction
 * 
 * @Description : 项目_ACTION
 * 
 * @author cqf
 * 
 * @date 2014-11-21
 * 
 * @version 1.0
 *
 */
public class ProjectAction extends BasAction<ProjectInfo, IProjectService> {

	/**
 	*
 	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 新增
	 * @return
	 */
	public String insertProject(){
		//页面参数
		String code = getParameter("code");
		String name = getParameter("name");
		String dbtype = getParameter("dbtype");
		String ipport = getParameter("ipport");
		String dbname = getParameter("dbname");
		String dbuser = getParameter("dbuser");
		String dbpassword = getParameter("dbpassword");
		String owner = getParameter("owner"); 

		//参数封装
		ProjectInfo vInfo = new ProjectInfo();
		vInfo.setId(code);
		vInfo.setCode(code);
		vInfo.setName(name);
		vInfo.setDbtype(dbtype);
		vInfo.setIpport(ipport);
		vInfo.setDbname(dbname);
		vInfo.setDbuser(dbuser);
		vInfo.setDbpassword(dbpassword);
		vInfo.setOwner(owner);
		vInfo.setRegtime(ConstantsWeb.getSystemtime("-"));

		//服务请求
		service.insertProject(vInfo);

		//数据返回
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}

	/**
	 *删除
	 * @return
	 */
	public String deleteProject(){ 
		//页面参数
		String id = getParameter("id"); 

		//参数封装
		ProjectInfo vInfo = new ProjectInfo();
		vInfo.setId(id);

		//服务请求
		service.deleteProject(vInfo);

		//数据返回
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}

	/**
	 * 修改
	 * @return
	 */
	public String updateProject(){ 
		//页面参数
		String id = getParameter("id");
		String code = getParameter("code");
		String name = getParameter("name");
		String dbtype = getParameter("dbtype");
		String ipport = getParameter("ipport");
		String dbname = getParameter("dbname");
		String dbuser = getParameter("dbuser");
		String dbpassword = getParameter("dbpassword");
		String owner = getParameter("owner"); 

		//参数封装
		ProjectInfo vInfo = new ProjectInfo();
		vInfo.setId(id);
		vInfo.setCode(code);
		vInfo.setName(name);		
		vInfo.setDbtype(dbtype);
		vInfo.setIpport(ipport);
		vInfo.setDbname(dbname);
		vInfo.setDbuser(dbuser);
		vInfo.setDbpassword(dbpassword);
		vInfo.setOwner(owner);

		//服务请求
		service.updateProject(vInfo);

		//数据返回
		getBadusResponse().setSuccessed(true);

		return SUCCESS;
	}

	/**
	 * 数据查询
	 * @return
	 */
	public String findProjectByMap(){
		//页面参数
		String code = getParameter("code");

		//参数封装
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		if(!"".equals(code))
			paramMap.put("code", code);

		//服务请求
		Map<String, Object> resultMap = service.findProjectByMap(paramMap);

		//数据返回
		getBadusResponse().setDataMap(resultMap);
		getBadusResponse().setSuccessed(true);

		return SUCCESS;
	}
	
	/**
	 * 数据库连接测试
	 * @return
	 */
	public String dbconnect(){
		String dbtype = getParameter("dbtype");
		String ipport = getParameter("ipport");
		String dbname = getParameter("dbname");
		String dbuser = getParameter("dbuser");
		String dbpassword = getParameter("dbpassword");
		String owner = getParameter("owner");

		//参数封装
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dbtype", dbtype);
		paramMap.put("ipport", ipport);		
		paramMap.put("dbname", dbname);
		paramMap.put("dbuser", dbuser);
		paramMap.put("dbpassword", dbpassword);
		paramMap.put("owner", owner);
		

		//服务请求
		service.dbconnect(paramMap);

		//数据返回 
		getBadusResponse().setSuccessed(true);

		
		return SUCCESS;
	}

}

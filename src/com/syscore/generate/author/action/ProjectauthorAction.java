/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ProjectauthorAction.java
 * 
 */
package com.syscore.generate.author.action;

import java.util.HashMap;
import java.util.Map;

import com.syscore.commons.action.BasAction;
import com.syscore.generate.author.model.ProjectauthorInfo;
import com.syscore.generate.author.service.IProjectauthorService;

/**
 * @ClassName : ProjectauthorAction
 * 
 * @Description : 编写者_ACTION
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class ProjectauthorAction extends BasAction<ProjectauthorInfo, IProjectauthorService> {

	/**
 	*
 	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 添加数据
	 * @return
	 */
	public String insertProjectauthor(){
		//页面参数
		String prjid = getParameter("prjid");
		String name = getParameter("name");
		String nickname = getParameter("nickname");		
		String filepath = getParameter("filepath");

		//参数
		ProjectauthorInfo info = new ProjectauthorInfo();
		info.setPrjid(prjid);
		info.setName(name);
		info.setNickname(nickname);
		info.setFilepath(filepath);

		//服务请求
		service.insert(info);

		//数据返回
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}

	/**
	 *删除数据
	 * @return
	 */
	public String deleteProjectauthor(){ 
		//页面参数
		String id = getParameter("id"); 

		//参数
		ProjectauthorInfo info = new ProjectauthorInfo();
		info.setId(id);

		//服务请求
		service.delete(info);

		//数据返回
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}

	/**
	 * 修改数据
	 * @return
	 */
	public String updateProjectauthor(){ 
		//页面参数
		String id = getParameter("id"); 
		String prjid = getParameter("prjid");
		String name = getParameter("name");
		String nickname = getParameter("nickname");		
		String filepath = getParameter("filepath");

		//参数
		ProjectauthorInfo info = new ProjectauthorInfo();
		info.setId(id);
		info.setPrjid(prjid);
		info.setName(name);
		info.setNickname(nickname);
		info.setFilepath(filepath);

		//服务请求
		service.update(info);

		//数据返回
		getBadusResponse().setSuccessed(true);

		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return
	 */
	public String findProjectauthorByPage(){
		//页面参数
		String pageNumber = getParameter("pageNumber");
		String pageSize= getParameter("pageSize");
		String prjid = getParameter("prjid");

		//参数
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("pageNumber", Integer.parseInt(pageNumber));
		paramMap.put("pageSize", Integer.parseInt(pageSize));
		paramMap.put("prjid", prjid);

		//服务请求
		Map<String, Object> resultMap = service.selectByPage(paramMap);

		//数据返回
		getBadusResponse().setDataMap(resultMap);
		getBadusResponse().setSuccessed(true);

		return SUCCESS;
	}
 
}

/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ProjectTableAction.java
 * 
 */
package com.syscore.generate.table.action;

import java.util.HashMap;
import java.util.Map;

import com.syscore.commons.action.BasAction;
import com.syscore.generate.table.model.ProjectTableInfo;
import com.syscore.generate.table.service.IProjectTableService;

/**
 * @ClassName : ProjectTableAction
 * 
 * @Description : 关系表和实体类对应关系_ACTION
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class ProjectTableAction extends BasAction<ProjectTableInfo, IProjectTableService> {

	/**
 	*
 	*/
	private static final long serialVersionUID = 1L;
 
	/**
	 * 添加数据
	 * @return
	 */
	public String insertProjectTable(){
		//页面参数
		String prjid = getParameter("prjid");
		String author = getParameter("author");
		String tblname = getParameter("tblname");
		String pkgmodelpath = getParameter("pkgmodelpath");
		String entityclass = getParameter("entityclass"); 

		//参数
		ProjectTableInfo info = new ProjectTableInfo();
		info.setPrjid(prjid);
		info.setAuthor(author);
		info.setTblname(tblname);
		info.setPkgmodelpath(pkgmodelpath);
		info.setEntityclass(entityclass);

		//服务请求
		service.insertProjectTable(info);

		//数据返回
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}

	/**
	 *删除数据
	 * @return
	 */
	public String deleteProjectTable(){ 
		//页面参数
		String id = getParameter("id"); 

		//参数
		ProjectTableInfo info = new ProjectTableInfo();
		info.setId(id);

		//服务请求
		service.deleteProjectTable(info);

		//数据返回
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}

	/**
	 * 修改数据
	 * @return
	 */
	public String updateProjectTable(){ 
		//页面参数
		String id = getParameter("id");		
		String prjid = getParameter("prjid");
		String author = getParameter("author");
		String tblname = getParameter("tblname");
		String pkgmodelpath = getParameter("pkgmodelpath");
		String entityclass = getParameter("entityclass"); 

		//参数
		ProjectTableInfo info = new ProjectTableInfo();
		info.setId(id);
		info.setPrjid(prjid);
		info.setAuthor(author);
		info.setTblname(tblname);
		info.setPkgmodelpath(pkgmodelpath);
		info.setEntityclass(entityclass);

		//服务请求
		service.updateProjectTable(info);

		//数据返回
		getBadusResponse().setSuccessed(true);

		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return
	 */
	public String findProjectTableByPage(){
		//页面参数
		String pageNumber = getParameter("pageNumber");
		String pageSize = getParameter("pageSize");

		//参数
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("pageNumber", Integer.parseInt(pageNumber));
		paramMap.put("pageSize", Integer.parseInt(pageSize));

		//服务请求
		Map<String, Object> resultMap = service.findProjectTableByPage(paramMap);

		//数据返回
		getBadusResponse().setDataMap(resultMap);
		getBadusResponse().setSuccessed(true);

		return SUCCESS;
	}

	 

}

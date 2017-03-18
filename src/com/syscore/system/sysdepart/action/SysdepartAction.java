/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: SysdepartAction.java
 * 
 */
package com.syscore.system.sysdepart.action;

import java.util.HashMap;
import java.util.Map;

import com.syscore.commons.action.BasAction;
import com.syscore.system.sysdepart.model.SysdepartInfo;
import com.syscore.system.sysdepart.service.ISysdepartService;

/**
 * @ClassName : SysdepartAction
 * 
 * @Description : 部门_ACTION
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class SysdepartAction extends BasAction<SysdepartInfo, ISysdepartService> {

	/**
 	*
 	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 新增
	 * @return
	 */
	public String insertSysdepart(){
		//页面参数
		String id = getParameter("id");

		//参数
		SysdepartInfo info = new SysdepartInfo();
		info.setId(id);

		//服务请求
		service.insertSysdepart(info);

		//数据返回
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}

	/**
	 *删除数据
	 * @return
	 */
	public String deleteSysdepart(){ 
		//页面参数
		String id = getParameter("id"); 

		//参数
		SysdepartInfo info = new SysdepartInfo();
		info.setId(id);

		//服务请求
		service.deleteSysdepart(info);

		//数据返回
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}

	/**
	 * 修改数据
	 * @return
	 */
	public String updateSysdepart(){ 
		//页面参数
		String id = getParameter("id");

		//参数
		SysdepartInfo info = new SysdepartInfo();
		info.setId(id);

		//服务请求
		service.updateSysdepart(info);

		//数据返回
		getBadusResponse().setSuccessed(true);

		return SUCCESS;
	}
	
	/**
	 * 数据查询
	 * @return
	 */
	public String findSysdepartByMap(){
		//页面参数
		String id = getParameter("id");

		//参数
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("id", id);

		//服务请求
		Map<String, Object> resultMap = service.findSysdepartByMap(paramMap);

		//数据返回
		getBadusResponse().setDataMap(resultMap);
		getBadusResponse().setSuccessed(true);

		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return
	 */
	public String findSysdepartByPage(){
		//页面参数
		String pageNumber = getParameter("pageNumber");
		String pageSize= getParameter("pageSize");

		//参数
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("pageNumber", Integer.parseInt(pageNumber));
		paramMap.put("pageSize", Integer.parseInt(pageSize));

		//服务请求
		Map<String, Object> resultMap = service.findSysdepartByPage(paramMap);

		//数据返回
		getBadusResponse().setDataMap(resultMap);
		getBadusResponse().setSuccessed(true);

		return SUCCESS;
	}

}

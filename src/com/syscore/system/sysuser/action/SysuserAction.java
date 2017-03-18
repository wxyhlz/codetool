/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: SysuserAction.java
 * 
 */
package com.syscore.system.sysuser.action;

import java.util.HashMap;
import java.util.Map;

import com.syscore.commons.action.BasAction;
import com.syscore.system.sysuser.model.SysuserInfo;
import com.syscore.system.sysuser.service.ISysuserService;

/**
 * @ClassName : SysuserAction
 * 
 * @Description : 人员_ACTION
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class SysuserAction extends BasAction<SysuserInfo, ISysuserService> {

	/**
 	*
 	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 新增
	 * @return
	 */
	public String insertSysuser(){
		SysuserInfo vInfo = new SysuserInfo();
		service.insertSysuser(vInfo);
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}


	/**
	 * 删除
	 * @return
	 */

	public String deleteSysuser(){
		SysuserInfo vInfo = new SysuserInfo();
		service.deleteSysuser(vInfo);
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}


	/**
	 * 修改
	 * @return
	 */ 
	public String updateSysuser(){
		SysuserInfo vInfo = new SysuserInfo();	
		service.updateSysuser(vInfo);
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}


/**
	 * 查询
	 * @return
	 */ 
	public String findSysuerByMap(){
		//参数
		String pid = getRequest().getParameter("pid");
		String pids = getRequest().getParameter("pids");
		String ctuser = getRequest().getParameter("ctuser");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if("1".equals(ctuser)){
			if(!"null".equals(pids)){
				pids = pids+"-"+pid;
				paramMap.put("dpids", pids);
			}
		}else{
			paramMap.put("dpid", pid);
		}
		
		Map<String,Object> dataMap = service.findSysuserByMap(paramMap);
	
		getBadusResponse().setDataMap(dataMap);
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}


	/**
	 * 分页查询
	 * @return
	 */ 
	public String findSysuserByPage(){
		String pageNumber = getRequest().getParameter("pageNumber");
		String pageSize = getRequest().getParameter("pageSize");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("pageNumber", Integer.valueOf(pageNumber));
		paramMap.put("pageSize", Integer.valueOf(pageSize));
		Map<String,Object> dataMap =  service.findSysuserByPage(paramMap);


		getBadusResponse().setDataMap(dataMap);
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}
	
	/**
	 * 获取所有角色并标志人员已经选中的模块
	 * @return
	 */
	public String findAllRoleAndMarkCheckedByUser(){
		SysuserInfo vInfo = new SysuserInfo();
		Map<String,Object> resultMap = service.findAllRoleAndMarkCheckedByUser(vInfo);
		
		getBadusResponse().setDataMap(resultMap);
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}
	
	/**
	 * 设置人员分配角色
	 * @return
	 */ 
	public String updateRolesToUser(){
		//request.getAttribute()方法返回request范围内存在的对象，
		//而request.getParameter()方法是获取http提交过来的数据
		String userId = (String) getRequest().getParameter("userId");
		String roleIds = (String) getRequest().getParameter("roleIds");
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("userId", userId);
		paramMap.put("roleIds", roleIds);
		service.updateRolesToUser(paramMap);
		
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}

}

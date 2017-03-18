/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: SysroleService.java
 * 
 */
package com.syscore.system.sysrole.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syscore.commons.except.BusinessException;
import com.syscore.commons.service.BasService;
import com.syscore.system.sysmodule.model.SysmoduleInfo;
import com.syscore.system.sysmodule.service.ISysmoduleService;
import com.syscore.system.sysrole.dao.ISysroleDao;
import com.syscore.system.sysrole.model.SysroleInfo;
import com.syscore.system.sysrole.service.ISysroleService;
import com.syscore.system.sysrolemodule.model.SysrolemoduleInfo;
import com.syscore.system.sysrolemodule.service.ISysrolemoduleService;

/**
 * @ClassName : SysroleService
 * 
 * @Description : 角色_SERVICE
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class SysroleService extends BasService<SysroleInfo, ISysroleDao> implements ISysroleService {
	
	/**
	 * 模块service
	 */
	private ISysmoduleService sysmoduleService;
	
	/**
	 * 角色模块service
	 */
	private ISysrolemoduleService sysrolemoduleService;
			
	public ISysmoduleService getSysmoduleService() {
		return sysmoduleService;
	}

	public void setSysmoduleService(ISysmoduleService sysmoduleService) {
		this.sysmoduleService = sysmoduleService;
	}

	public ISysrolemoduleService getSysrolemoduleService() {
		return sysrolemoduleService;
	}

	public void setSysrolemoduleService(ISysrolemoduleService sysrolemoduleService) {
		this.sysrolemoduleService = sysrolemoduleService;
	}

	public void insertSysrole(SysroleInfo entityInfo) {
		//判断对象是否存在
		SysroleInfo info = new SysroleInfo();
		info.setName(entityInfo.getName());
		SysroleInfo vInfo = dao.selectEntity(info);
		if(vInfo!=null){ 
			throw new BusinessException("名称“"+entityInfo.getName()+"”已经存在，不允许重复增加。");
		}else{
			//新增
			entityInfo.setValid("1");
			entityInfo = setEntityID(entityInfo);
			dao.insert(entityInfo);
		}
	}

	public void deleteSysrole(SysroleInfo info) {
		dao.delete(info);
	}

	public void updateSysrole(SysroleInfo info) {
		dao.update(info);
	}
 
	/**
	 * 查询
	 * @param entityInfo
	 * @return
	 */
	public SysroleInfo findSysrole(SysroleInfo entityInfo){
		SysroleInfo info = dao.selectEntity(entityInfo);
		return info;
	} 	
	
	public Map<String, Object> findSysroleByMap(Map<String, Object> paramMap) {
		List<SysroleInfo> infoList = dao.selectEntitys("SysroleInfo.selectByMap", paramMap);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("data", infoList);
		
		return resultMap;
	}
	
	/**
	 * 获取所有模块并标志角色已经选中的模块
	 * @param entityInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> findAllModulesAndMarkCheckedByRole(SysroleInfo entityInfo){
		//获取所有模块
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("valid", "1");
		Map<String,Object> allmodulesMap = sysmoduleService.findModuleByMap(paramMap);
		
		List<SysmoduleInfo> sysmoduleList = (List<SysmoduleInfo>) allmodulesMap.get("data");
		
		//获取角色中的模块
		SysrolemoduleInfo sysrolemoduleInfo = new SysrolemoduleInfo();
		sysrolemoduleInfo.setRid(entityInfo.getId());
		Map<String,Object> rolemoduleMap = sysrolemoduleService.findSysrolemoduleByEntity(sysrolemoduleInfo);
		List<SysrolemoduleInfo> sysrolemoduleList = (List<SysrolemoduleInfo>) rolemoduleMap.get("data");
		
		//在所有模块中标志角色中已经选中的模块				
		for(SysmoduleInfo v : sysmoduleList){
			v.set_parentId(v.getPid());
			for(SysrolemoduleInfo srm : sysrolemoduleList){
				if(srm.getMid().equals(v.getId())){
					v.setChecked(true);
				}
			} 
		}
		 
		return allmodulesMap;
	}
	
	/**
	 * 设置角色分配模块
	 * @param paramMap
	 */
	public void updateModulesToRole(Map<String,String> paramMap){
		String roleId = paramMap.get("roleId");
		String moduleIds = paramMap.get("moduleIds");
		List<SysrolemoduleInfo> rdList = null;
		if((!"".equals(moduleIds))&&(!"null".equals(moduleIds))){
			String[] modules = moduleIds.split(",");
			rdList = new ArrayList<SysrolemoduleInfo>();
			for(String v:modules){
				SysrolemoduleInfo srmInfo = new SysrolemoduleInfo();
				srmInfo.setRid(roleId);
				srmInfo.setMid(v);
				rdList.add(srmInfo);
			}
			Map<String,Object> vParamMap = new HashMap<String,Object>();
			vParamMap.put("data", rdList);
			
			sysrolemoduleService.insertBatch(vParamMap);			
		}	
	}

}

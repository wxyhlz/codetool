/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: SysmoduleService.java
 * 
 */
package com.syscore.system.sysmodule.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syscore.commons.except.BusinessException;
import com.syscore.commons.service.BasService;
import com.syscore.commons.utils.page.Page;
import com.syscore.commons.utils.page.PageUtil;
import com.syscore.system.sysmodule.dao.ISysmoduleDao;
import com.syscore.system.sysmodule.model.SysmoduleInfo;
import com.syscore.system.sysmodule.service.ISysmoduleService; 

/**
 * @ClassName : SysmoduleService
 * 
 * @Description : 模块_SERVICE
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class SysmoduleService extends BasService<SysmoduleInfo, ISysmoduleDao> implements ISysmoduleService {
	
	public void insertModule(SysmoduleInfo entityInfo) {
		//判断对象是否存在
		SysmoduleInfo info = new SysmoduleInfo();
		info.setName(entityInfo.getName());
		SysmoduleInfo vInfo = dao.selectEntity(info);
		if(vInfo!=null){ 
			throw new BusinessException("名称“"+entityInfo.getName()+"”已经存在，不允许重复增加。");
		}else{
			//生成code
			String maxCode = "";
			if(entityInfo.getPid()!=null){
				//如果有父节点，则寻找最大子节点code
				maxCode = (String)dao.selectObject("SysmoduleInfo.selectMaxChildrenCode",entityInfo);
			}else{
				//如果无父节点，则寻找最大节点code
				maxCode = (String)dao.selectObject("SysmoduleInfo.selectMaxCode");
			}			
			if((maxCode!=null)&&(!"".equals(maxCode))){
				String suffixCode = maxCode.substring(maxCode.length()-2);
				String addCode = String.valueOf((Integer.valueOf(suffixCode)+1));
				if(addCode.length()==1) addCode = "0" + addCode;
				maxCode = maxCode.substring(0,maxCode.length()-2)+addCode;
			}else{				
				if((entityInfo.getCode()!=null)&&(!"".equals(entityInfo.getCode()))){
					//存放父节点code
					maxCode = entityInfo.getCode() + "01";
				}else{
					maxCode = "01";
				}
			}			
			entityInfo.setCode(maxCode);	
			
			//设置pids，控制子节点查询
			if((entityInfo.getPid()!=null)&&(!"".equals(entityInfo.getPid()))){
				if(!"null".equals(entityInfo.getPids())){
					entityInfo.setPids(entityInfo.getPids()+"-"+entityInfo.getPid());
				} else{
					entityInfo.setPids(entityInfo.getPid());
				}
			}else{
				entityInfo.setPids(entityInfo.getPid());				
			}
			
			//标志设置
			entityInfo.setLastnode("1");	
			entityInfo.setValid("1");	
			
			//新增
			entityInfo = setEntityID(entityInfo);			
			dao.insert(entityInfo);
			
			//修改父节点末尾标志
			if(entityInfo.getPid()!=null){
				SysmoduleInfo psdInfo = new SysmoduleInfo();
				psdInfo.setId(entityInfo.getPid());
				psdInfo.setLastnode("0");
				psdInfo.setActionurl("");
				psdInfo.setOpentype(null);
				dao.update(psdInfo);
			}
		}
	}

	public void deleteModule(SysmoduleInfo entityInfo) {
		dao.delete(entityInfo);
	}

	public void updateModule(SysmoduleInfo entityInfo) {
		dao.update(entityInfo);
	}
	
	public Map<String, Object> findModuleByMap(Map<String, Object> paramMap) {
		List<SysmoduleInfo> infoList = dao.selectEntitys("SysmoduleInfo.selectByMap", paramMap);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("data", infoList);
		
		return resultMap;
	}

	public Map<String, Object> findModuleByPage(Map<String, Object> paramMap) {
		//查询跟节点
		Page<SysmoduleInfo> page = new PageUtil<SysmoduleInfo>().doPageCalculate("SysmoduleInfo", dao, paramMap);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<SysmoduleInfo> moduleList = page.getList();
		
		//查询子节点
		String inSQL = "";
		for(SysmoduleInfo v : moduleList){
			if("".equals(inSQL)){
				inSQL = "'" + v.getId() + "'";
			}else{
				inSQL = inSQL + ",'" + v.getId() + "'";
			}
		}		
		paramMap.put("inItem", inSQL);
		List<SysmoduleInfo> childrenList = dao.selectEntitys("SysmoduleInfo.selectPageChildren", paramMap);
		
		//所有节点
		List<SysmoduleInfo> collectionList = new ArrayList<SysmoduleInfo>();
		for(SysmoduleInfo v : moduleList){			
			collectionList.add(v);
			v.setState("");
			for(SysmoduleInfo c : childrenList){
				String pid = c.getPids().substring(0, 32); 
				if(pid.equals(v.getId())){
					v.setState("closed");
					c.set_parentId(c.getPid());
					c.setState("");
					collectionList.add(c);
				}
			}
		}
		
		//返回数据
		resultMap.put("total", page.getTotal());
		resultMap.put("rows", collectionList);
		return resultMap;
	}


	public Map<String,Object> findLoginModules(Map<String,Object> paramMap) {		
		String userCode = (String) paramMap.get("userCode");
		List<SysmoduleInfo>  infoList = null;
		if(userCode.equals("admin")){
			Map<String,Object> vParamMap = new HashMap<String,Object>();
			infoList = (List<SysmoduleInfo>)dao.selectEntitys("SysmoduleInfo.selectByMap", vParamMap);
		}else{
			infoList = (List<SysmoduleInfo>)dao.selectEntitys("SysmoduleInfo.loginModules", paramMap);
		}
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("data", infoList);
		return dataMap;
	}

}

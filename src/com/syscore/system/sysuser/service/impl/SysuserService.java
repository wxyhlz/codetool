/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: SysuserService.java
 * 
 */
package com.syscore.system.sysuser.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Element;

import com.syscore.commons.except.BusinessException;
import com.syscore.commons.service.BasService;
import com.syscore.constants.ConstantsWeb;
import com.syscore.system.sysdepart.model.SysdepartInfo;
import com.syscore.system.sysrole.model.SysroleInfo;
import com.syscore.system.sysrole.service.ISysroleService;
import com.syscore.system.sysuser.dao.ISysuserDao;
import com.syscore.system.sysuser.model.SysuserInfo;
import com.syscore.system.sysuser.service.ISysuserService;
import com.syscore.system.sysuserrole.model.SysuserroleInfo;
import com.syscore.system.sysuserrole.service.ISysuserroleService;

/**
 * @ClassName : SysuserService
 * 
 * @Description : 人员_SERVICE
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class SysuserService extends BasService<SysuserInfo, ISysuserDao> implements ISysuserService {
	
	/**
	 * 角色
	 */
	private ISysroleService sysroleService;
	
	/**
	 * 用户角色关系
	 */
	private ISysuserroleService sysuserroleService;
	
	public ISysroleService getSysroleService() {
		return sysroleService;
	}

	public void setSysroleService(ISysroleService sysroleService) {
		this.sysroleService = sysroleService;
	}

	public ISysuserroleService getSysuserroleService() {
		return sysuserroleService;
	}

	public void setSysuserroleService(ISysuserroleService sysuserroleService) {
		this.sysuserroleService = sysuserroleService;
	}

	public void insertSysuser(SysuserInfo info) {
		dao.insert(info);
	}

	public void deleteSysuser(SysuserInfo info) {
		dao.delete(info);
	}

	public void updateSysuser(SysuserInfo info) {
		dao.update(info);
	}

	public Map<String, Object> findSysuserByPage(Map<String, Object> paramMap) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//Page<SysuserInfo> page = dao.selectPageEntitys("SysuserInfo.selectPage", paramMap);
		//resultMap.put("total", page.gEntityotalCount());
		//resultMap.put("rows",page.getRtnObject());
		return resultMap;
	}
	

	@SuppressWarnings("unchecked")
	public Map<String, Object> findSysuserByMap(Map<String, Object> paramMap) {
		List<SysuserInfo> infoList = dao.selectEntitys("SysuserInfo.selectByMap", paramMap);
		//获取cache中的值
		Element element = ConstantsWeb.mapCache.get("depart");
		Map<String,SysdepartInfo> departMap = (Map<String,SysdepartInfo>)element.getObjectValue();
		for(SysuserInfo v : infoList){
			v.setDepart(departMap.get(v.getDpid()));
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("data", infoList);
		
		return resultMap;
	}
	
	/**
	 * 查询{登陆}
	 * @param entityInfo
	 * @return
	 */
	public SysuserInfo findLogin(SysuserInfo entityInfo){		
		SysuserInfo info = new SysuserInfo();
		info.setCode(entityInfo.getCode());
		/*List<SysuserInfo> aList = dao.selectEntitys("SysuserInfo.selectByMap");
		System.out.println(aList.size());*/
		SysuserInfo vInfo = dao.selectEntity(info);
		if(vInfo!=null){
			if(!vInfo.getPassword().equals(entityInfo.getPassword())){
				throw new BusinessException("密码输入错误！");
			}
		}else{
			throw new BusinessException("用户名不存在！");
		}
		
		return vInfo;
	}
 
	/**
	 * 获取所有角色并标志人员已经选中的模块
	 * @param entityInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> findAllRoleAndMarkCheckedByUser(SysuserInfo entityInfo){
		//获取所有角色
		Map<String,Object> roleMap = sysroleService.findSysroleByMap(null);
		List<SysroleInfo> srList = (List<SysroleInfo>) roleMap.get("data");
		
		//获取分配角色
		SysuserroleInfo surInfo = new SysuserroleInfo();
		surInfo.setEid(entityInfo.getId());
		Map<String,Object> userroleMap = sysuserroleService.findSysuserroleByEntity(surInfo);
		List<SysuserroleInfo> surList = (List<SysuserroleInfo>) userroleMap.get("data");
		
		//在所有角色中标注已分配角色
		for(SysroleInfo v : srList){
			for(SysuserroleInfo sur : surList){
				if(sur.getRid().equals(v.getId())){
					v.setChecked(true);
				}
			}
		}
		
		return roleMap;
	}
	
	/**
	 * 设置人员分配角色
	 * @param paramMap
	 */
	public void updateRolesToUser(Map<String,String> paramMap){
		
		String userId = paramMap.get("userId");
		String roleIds = paramMap.get("roleIds");
		List<SysuserroleInfo> surList = null;
		
		if((!"".equals(roleIds))&&(!"null".equals(roleIds))){
			String[] roles = roleIds.split(",");
			surList = new ArrayList<SysuserroleInfo>();
			for(String v:roles){
				SysuserroleInfo surInfo = new SysuserroleInfo();
				surInfo.setRid(v);
				surInfo.setEid(userId);
				surList.add(surInfo);
			}
			sysuserroleService.insertBatch(surList);	
		}	
	}
 

}

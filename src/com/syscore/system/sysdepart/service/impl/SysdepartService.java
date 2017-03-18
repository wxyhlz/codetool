/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: SysdepartService.java
 * 
 */
package com.syscore.system.sysdepart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syscore.commons.except.BusinessException;
import com.syscore.commons.service.BasService;
import com.syscore.commons.utils.page.Page;
import com.syscore.commons.utils.page.PageUtil;
import com.syscore.system.sysdepart.dao.ISysdepartDao;
import com.syscore.system.sysdepart.model.SysdepartInfo;
import com.syscore.system.sysdepart.service.ISysdepartService;

/**
 * @ClassName : SysdepartService
 * 
 * @Description : 部门_SERVICE
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class SysdepartService extends BasService<SysdepartInfo, ISysdepartDao> implements ISysdepartService {

	public void insertSysdepart(SysdepartInfo entityInfo) {
		//判断对象是否存在
		SysdepartInfo info = new SysdepartInfo();
		info.setName(entityInfo.getName());
		SysdepartInfo vInfo = dao.selectEntity(info);
		if(vInfo!=null){ 
			throw new BusinessException("名称“"+entityInfo.getName()+"”已经存在，不允许重复增加。");
		}else{
			//生成code
			String maxCode = "";
			if(entityInfo.getPid()!=null){
				//如果有父节点，则寻找最大子节点code
				maxCode = (String)dao.selectObject("SysdepartInfo.selectMaxChildrenCode",entityInfo);
			}else{
				//如果无父节点，则寻找最大节点code
				maxCode = (String)dao.selectObject("SysdepartInfo.selectMaxCode");
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
				SysdepartInfo psdInfo = new SysdepartInfo();
				psdInfo.setId(entityInfo.getPid());
				psdInfo.setLastnode("0"); 
				dao.update(psdInfo);
			}
		} 
	}

	public void deleteSysdepart(SysdepartInfo entityInfo) {
		dao.delete(entityInfo);
	}

	public void updateSysdepart(SysdepartInfo entityInfo) {
		dao.update(entityInfo);
	}
	
	public Map<String, Object> findSysdepartByMap(Map<String, Object> paramMap) {
		List<SysdepartInfo> infoList = dao.selectEntitys("SysdepartInfo.selectByMap", paramMap);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("data", infoList);
		
		return resultMap;
	}

	public Map<String, Object> findSysdepartByPage(Map<String, Object> paramMap) {
		//查询跟节点
		Page<SysdepartInfo> page = new PageUtil<SysdepartInfo>().doPageCalculate("SysdepartInfo", dao, paramMap);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<SysdepartInfo> departList = page.getList();
//		for(SysdepartInfo v : departList){
//			v.set_parentId(v.getPid());
//		}
		
		//查询子节点
		String inSQL = "";
		for(SysdepartInfo v : departList){
			if("".equals(inSQL)){
				inSQL = "'" + v.getId() + "'";
			}else{
				inSQL = inSQL + ",'" + v.getId() + "'";
			}
		}		
		paramMap.put("inItem", inSQL );
		List<SysdepartInfo> childrenList = dao.selectEntitys("SysdepartInfo.selectPageChildren", paramMap);
		
		//所有节点
		List<SysdepartInfo> collectionList = new ArrayList<SysdepartInfo>();
		for(SysdepartInfo v : departList){
			v.setState("open");
			collectionList.add(v);			
			for(SysdepartInfo c : childrenList){
				String pid = c.getPids().substring(0, 32); 
				if(pid.equals(v.getId())){
					c.set_parentId(c.getPid());
					if("0".equals(c.getLastnode())){
						c.setState("closed");
					}else{
						c.setState("");
					}
					collectionList.add(c);
				}
			}
		}
		
		//返回数据
		resultMap.put("total", page.getTotal());
		resultMap.put("rows", collectionList);
		return resultMap;
	}

}

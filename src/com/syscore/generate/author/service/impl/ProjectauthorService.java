/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ProjectauthorService.java
 * 
 */
package com.syscore.generate.author.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syscore.commons.except.BusinessException;
import com.syscore.commons.service.BasService;
import com.syscore.commons.utils.page.Page;
import com.syscore.commons.utils.page.PageUtil;
import com.syscore.constants.ConstantsWeb;
import com.syscore.generate.author.dao.IProjectauthorDao;
import com.syscore.generate.author.model.ProjectauthorInfo;
import com.syscore.generate.author.service.IProjectauthorService;
import com.syscore.generate.project.model.ProjectInfo;
import com.syscore.generate.project.service.IProjectService;
/**
 * @ClassName : ProjectauthorService
 * 
 * @Description : 编写者_SERVICE
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class ProjectauthorService extends BasService<ProjectauthorInfo, IProjectauthorDao> implements IProjectauthorService {
	
	private IProjectService prjService;
	
	public IProjectService getPrjService() {
		return prjService;
	}

	public void setPrjService(IProjectService prjService) {
		this.prjService = prjService;
	}

	public void insert(ProjectauthorInfo entityInfo) {
		//项目中如果昵称已经存在，则不允许添加
		ProjectauthorInfo faInfo = new ProjectauthorInfo();
		faInfo.setPrjid(entityInfo.getPrjid());
		faInfo.setNickname(entityInfo.getNickname());
		ProjectauthorInfo sFaInfo = dao.selectEntity(faInfo);
		if(sFaInfo!=null){
			throw new BusinessException("该项目中，昵称："+entityInfo.getName()+"已经存在，不允许重复增加。");
		}else{
			entityInfo = setEntityID(entityInfo);
			entityInfo.setRegtime(ConstantsWeb.getSystemtime("-"));
			//新增
			dao.insert(entityInfo);
		} 
	}

	public void delete(ProjectauthorInfo entityInfo) {
		dao.delete(entityInfo);
	}

	public void update(ProjectauthorInfo entityInfo) {
		dao.update(entityInfo);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectByPage(Map<String, Object> paramMap) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Page<ProjectauthorInfo> page = new PageUtil<ProjectauthorInfo>().doPageCalculate("ProjectauthorInfo", dao, paramMap); 
		
		//获取所有项目
		Map<String,Object> projectMap = prjService.findProjectByMap(null);
		List<ProjectInfo> projectList = (List<ProjectInfo>)projectMap.get("data");
		//查询项目-进行匹配设置
		List<ProjectauthorInfo> authorList = page.getList();
		for(ProjectauthorInfo v : authorList){
			for(ProjectInfo t : projectList){
				if(t.getId().equals(v.getPrjid())){
					v.setPrjname(t.getName());
					break;
				}
			}
		}
		//返回数据
		resultMap.put("total", page.getTotal());
		resultMap.put("rows", authorList);
		return resultMap;
	}

	public ProjectauthorInfo findProjectauthorEntity(ProjectauthorInfo entityInfo) {
		ProjectauthorInfo faInfo = dao.selectEntity(entityInfo);
		return faInfo;
	} 

}

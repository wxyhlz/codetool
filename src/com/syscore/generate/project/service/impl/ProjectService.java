/*
 * 版　权: 江苏百年软件 Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ProjectService.java
 * 
 */
package com.syscore.generate.project.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syscore.commons.service.BasService;
import com.syscore.generate.project.dao.IProjectDao;
import com.syscore.generate.project.model.ProjectInfo;
import com.syscore.generate.project.service.IProjectService;
import com.syscore.utils.jdbc.Dbconn;

/**
 * @ClassName : ProjectService
 * 
 * @Description : 项目_SERVICE
 * 
 * @author cqf
 * 
 * @date 2014-11-21
 * 
 * @version 1.0
 *
 */
public class ProjectService extends BasService<ProjectInfo, IProjectDao> implements IProjectService {
	public void insertProject(ProjectInfo entityInfo) {
		dao.insert(entityInfo);
	}

	public void deleteProject(ProjectInfo entityInfo) {
		dao.delete(entityInfo);
	}

	public void updateProject(ProjectInfo entityInfo) {
		dao.update(entityInfo);
	}

	public Map<String, Object> findProjectByMap(Map<String, Object> paramMap) {
		List<ProjectInfo> infoList = dao.selectEntitys("ProjectInfo.selectByMap", paramMap);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("data", infoList);
		
		return resultMap;
	}
	
	public void dbconnect(Map<String,Object> paramMap){
		String dbtype = (String)paramMap.get("dbtype");
		String ipport = (String)paramMap.get("ipport");
		String dbname = (String)paramMap.get("dbname");
		String dbuser = (String)paramMap.get("dbuser");
		String dbpassword = (String)paramMap.get("dbpassword");
		String owner = (String)paramMap.get("owner");
		
		ProjectInfo info = new ProjectInfo();
		info.setIpport(ipport);
		info.setDbtype(dbtype);
		info.setDbname(dbname);
		info.setDbuser(dbuser);
		info.setDbpassword(dbpassword);
		info.setOwner(owner);		
		
		Dbconn.dbconnLink(info);
	}

	public ProjectInfo findProjectInfo(ProjectInfo entityInfo) {
		return dao.selectEntity(entityInfo);
	}

}

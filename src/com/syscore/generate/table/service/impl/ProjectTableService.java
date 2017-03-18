/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ProjectTableService.java
 * 
 */
package com.syscore.generate.table.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syscore.commons.except.BusinessException;
import com.syscore.commons.service.BasService;
import com.syscore.commons.utils.page.Page;
import com.syscore.commons.utils.page.PageUtil;
import com.syscore.constants.ConstantsWeb;
import com.syscore.generate.project.model.ProjectInfo;
import com.syscore.generate.project.service.IProjectService;
import com.syscore.generate.table.dao.IProjectTableDao;
import com.syscore.generate.table.model.ProjectColumnInfo;
import com.syscore.generate.table.model.ProjectTableInfo;
import com.syscore.generate.table.service.IProjectColumnService;
import com.syscore.generate.table.service.IProjectTableService;
import com.syscore.utils.jdbc.Dbconn;

/**
 * @ClassName : ProjectTableService
 * 
 * @Description : 关系表和实体类对应关系_SERVICE
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class ProjectTableService extends BasService<ProjectTableInfo, IProjectTableDao> implements IProjectTableService {
	
	//所属项目信息
	private IProjectService prjService;	
	
	//数据列服务
	private IProjectColumnService frameColumnService;
	
	public IProjectService getPrjService() {
		return prjService;
	}

	public void setPrjService(IProjectService prjService) {
		this.prjService = prjService;
	}

	public IProjectColumnService getProjectColumnService() {
		return frameColumnService;
	}

	public void setProjectColumnService(IProjectColumnService frameColumnService) {
		this.frameColumnService = frameColumnService;
	}

	/**
	 * 1、分页查询
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> findProjectTableByPage(Map<String, Object> paramMap){
		
		//所有项目信息
		Map<String,Object> prjMap = prjService.findProjectByMap(null);
		List<ProjectInfo> prjList = (List<ProjectInfo>) prjMap.get("data");
		
		//表信息
		Page<ProjectTableInfo> page = new PageUtil<ProjectTableInfo>().doPageCalculate("ProjectTableInfo", dao, paramMap);
		
		//设置所属项目
		List<ProjectTableInfo> prjtblList = page.getList();
		for(ProjectInfo v : prjList){
			for(ProjectTableInfo t : prjtblList){
				if(v.getId().equals(t.getPrjid())){
					t.setPrjname(v.getName());
				}
			}
		}
		
		//返回数据信息
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("total", page.getTotal());
		resultMap.put("rows", prjtblList);
		return resultMap;
		
	}
	
	/**
	 * 2、新增
	 * @param entityInfo
	 */
	public void insertProjectTable(ProjectTableInfo entityInfo){
		//项目中，如果表名已存在，则不用于加入
		ProjectTableInfo ftInfo = new ProjectTableInfo();
		ftInfo.setTblname(entityInfo.getTblname());
		ftInfo.setPrjid(entityInfo.getPrjid());
		ProjectTableInfo sFtInfo = dao.selectEntity(ftInfo);
		if(sFtInfo!=null){ 
			throw new BusinessException("表"+entityInfo.getTblname()+"已经存在，不允许重复增加。");
		}else{
			//表注释Old
			//new 
			ProjectInfo pInfo = new ProjectInfo();
			pInfo.setId(entityInfo.getPrjid());
			pInfo = prjService.findProjectInfo(pInfo);			
			String tblcomment = Dbconn.getTableComment(pInfo, entityInfo);
			if(tblcomment==null){
				throw new BusinessException("表不存在或表注释内容");
			}else if((tblcomment.indexOf("not table")>=0)||(tblcomment.indexOf("conn database error。")>=0)){
				throw new BusinessException(tblcomment);
			}
			
			entityInfo = setEntityID(entityInfo);
			entityInfo.setRegtime(ConstantsWeb.getSystemtime("-"));
			entityInfo.setTblcomment(tblcomment);
			//新增
			dao.insert(entityInfo);
		}		
	}
	
	/**
	 * 3、修改
	 * @param entityInfo
	 */
	public void updateProjectTable(ProjectTableInfo entityInfo){
		try{
			//1、先删除
			dao.delete(entityInfo);
			//2、删除列
			ProjectColumnInfo pcInfo = new ProjectColumnInfo();
			pcInfo.setPid(entityInfo.getId());
			frameColumnService.deleteProjectColumn(pcInfo);
			//3、后插入
			insertProjectTable(entityInfo);
		}catch(Exception e){
			throw new BusinessException("修改失败。");
		}
	}
	
	/**
	 * 4、删除
	 * @param entityInfo
	 */
	public void deleteProjectTable(ProjectTableInfo entityInfo){
		ProjectColumnInfo fcInfo = new ProjectColumnInfo();
		fcInfo.setPid(entityInfo.getId());
		frameColumnService.deleteProjectColumn(fcInfo);
		dao.delete(entityInfo);
	}
 
	
	 

}

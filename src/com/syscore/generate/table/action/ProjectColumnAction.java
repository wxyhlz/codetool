/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ProjectColumnAction.java
 * 
 */
package com.syscore.generate.table.action;

import java.util.HashMap;
import java.util.Map;

import com.syscore.commons.action.BasAction;
import com.syscore.generate.table.model.ProjectColumnInfo;
import com.syscore.generate.table.model.ProjectTableInfo;
import com.syscore.generate.table.service.IProjectColumnService;
import com.syscore.generate.zcode.ctrldto.CtrlFileFunDto;

/**
 * @ClassName : ProjectColumnAction
 * 
 * @Description : 关系表字段和实体类对应关系_ACTION
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class ProjectColumnAction extends BasAction<ProjectColumnInfo, IProjectColumnService> {

	/**
 	*
 	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 数据查询
	 * @return
	 */
	public String findProjectColumnByMap(){
		//页面参数
		String pid = getParameter("id");

		//参数
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("pid", pid);

		//服务请求
		Map<String, Object> resultMap = service.findProjectColumnByMap(paramMap);

		//数据返回
		getBadusResponse().setDataMap(resultMap);
		getBadusResponse().setSuccessed(true);

		return SUCCESS;
	}
	
	/**
	 * 刷新
	 * @return
	 */
	public String updateRefresh(){
		//页面参数
		String id = getParameter("id");
		String prjid = getParameter("prjid");
 		String author = getParameter("author");	//编写者	
 		String tblname = getParameter("tblname");	//表名
 		String tblcomment = getParameter("tblcomment");	//表注释
 		String pkgmodelpath = getParameter("pkgmodelpath");//包路径
 		String entityclass = getParameter("entityclass");	//实体类
 		
 		ProjectTableInfo vInfo = new ProjectTableInfo();
 		vInfo.setId(id);
 		vInfo.setPrjid(prjid);
 		vInfo.setAuthor(author);
 		vInfo.setTblname(tblname);
 		vInfo.setTblcomment(tblcomment);
 		vInfo.setPkgmodelpath(pkgmodelpath);
 		vInfo.setEntityclass(entityclass);

		//参数
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("ProjectTableInfo", vInfo);

		//服务请求
		service.updateRefresh(paramMap);

		//数据返回
		getBadusResponse().setSuccessed(true);

		return SUCCESS;
	}

	/**
	 *删除数据
	 * @return
	 */
	public String deleteProjectColumn(){ 
		//页面参数
		String id = getParameter("id"); 

		//参数
		ProjectColumnInfo info = new ProjectColumnInfo();
		info.setId(id);

		//服务请求
		service.deleteProjectColumn(info);

		//数据返回
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}
	 
	/**
	 * 自动生成功能模块文件
	 * @return
	 */
	public String updateGenerateFile(){
		//项目、表、基本配置
		String id = getParameter("id");
		String prjid = getParameter("prjid");
		String author = getParameter("author");
		String tblname = getParameter("tblname");
		String tblcomment = getParameter("tblcomment");
		String pkgmodelpath = getParameter("pkgmodelpath");
		String entityclass = getParameter("entityclass");
		//文件控制
		String strController = getParameter("vcontroller");
		String strFacade = getParameter("vfacade");
		String strService = getParameter("vservice");
		String strDao = getParameter("vdao");
		String strEntity = getParameter("ventity");
		//方法控制
		String strInsert = getParameter("vinsert");
		String strDelete = getParameter("vdelete");
		String strUpdate = getParameter("vupdate");		
		String strInvalid = getParameter("vinvalid");
		String strSelectEntity = getParameter("selectEntity");
		String strSelectList = getParameter("selectList");
		String strSelectByPage = getParameter("selectByPage");
		String strSelectByMap = getParameter("selectByMap");
		String strInsertBatch = getParameter("insertBatch");
		String strDeleteBatch = getParameter("deleteBatch");
		String strUpdateBatch = getParameter("updateBatch");
		
		//参数封装
		ProjectTableInfo ptInfo = new ProjectTableInfo();
		ptInfo.setId(id);
		ptInfo.setPrjid(prjid);
		ptInfo.setAuthor(author);
		ptInfo.setTblname(tblname);
		ptInfo.setTblcomment(tblcomment);
		ptInfo.setPkgmodelpath(pkgmodelpath);
		ptInfo.setEntityclass(entityclass);
		
		CtrlFileFunDto cffDto = new CtrlFileFunDto();
		//文件
		if("true".equals(strController)){cffDto.setFileController(true);}
		if("true".equals(strFacade)){cffDto.setFileFacade(true);}
		if("true".equals(strService)){cffDto.setFileService(true);}
		if("true".equals(strDao)){cffDto.setFileDao(true);}
		if("true".equals(strEntity)){cffDto.setFileEntity(true);}
		//方法
		if("true".equals(strInsert)){cffDto.setFunInsert(true);}
		if("true".equals(strDelete)){cffDto.setFunDelete(true);}
		if("true".equals(strUpdate)){cffDto.setFunUpdate(true);}
		if("true".equals(strInvalid)){cffDto.setFunInvalid(true);}
		if("true".equals(strSelectEntity)){cffDto.setFunSelectSigle(true);}
		if("true".equals(strSelectList)){cffDto.setFunSelectEntites(true);}
		if("true".equals(strSelectByPage)){cffDto.setFunSelectPage(true);}
		if("true".equals(strSelectByMap)){cffDto.setFunSelectMap(true);}
		if("true".equals(strInsertBatch)){cffDto.setFunBatchInsert(true);}
		if("true".equals(strDeleteBatch)){cffDto.setFunBatchDelete(true);}
		if("true".equals(strUpdateBatch)){cffDto.setFunBatchUpdate(true);}
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("ProjectTableInfo", ptInfo);
		paramMap.put("GenerateDto", cffDto);
		
		//服务请求
		service.updateGenerateFile(paramMap);
		
		//数据返回
		getBadusResponse().setSuccessed(true); 
		return SUCCESS;
	}
  

}

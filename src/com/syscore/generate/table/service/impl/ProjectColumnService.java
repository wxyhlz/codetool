/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ProjectColumnService.java
 * 
 */
package com.syscore.generate.table.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syscore.commons.service.BasService;
import com.syscore.commons.utils.dbtojava.DbFieldToJava;
import com.syscore.generate.author.model.ProjectauthorInfo;
import com.syscore.generate.author.service.IProjectauthorService;
import com.syscore.generate.project.model.ProjectInfo;
import com.syscore.generate.project.service.IProjectService;
import com.syscore.generate.table.dao.IProjectColumnDao;
import com.syscore.generate.table.dto.ColumnsDto;
import com.syscore.generate.table.model.ProjectColumnInfo;
import com.syscore.generate.table.model.ProjectTableInfo;
import com.syscore.generate.table.service.IProjectColumnService;
import com.syscore.generate.zcode.ctrldto.CtrlFileFunDto;
import com.syscore.generate.zcode.zfile.ICodeToFile;
import com.syscore.utils.jdbc.Dbconn;
import com.util.sonar.SonarCode;

/**
 * @ClassName : ProjectColumnService
 * 
 * @Description : 关系表字段和实体类对应关系_SERVICE
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class ProjectColumnService extends BasService<ProjectColumnInfo, IProjectColumnDao> implements IProjectColumnService {
	
	//所属项目信息
	private IProjectService prjService;	
	
	//文件
	private ICodeToFile generateFile;
	
	//作者
	private IProjectauthorService authorService;
	
	public IProjectService getPrjService() {
		return prjService;
	}

	public void setPrjService(IProjectService prjService) {
		this.prjService = prjService;
	}

	public ICodeToFile getGenerateFile() {
		return generateFile;
	}

	public void setGenerateFile(ICodeToFile generateFile) {
		this.generateFile = generateFile;
	}

	public IProjectauthorService getAuthorService() {
		return authorService;
	}

	public void setAuthorService(IProjectauthorService authorService) {
		this.authorService = authorService;
	}

	public Map<String, Object> findProjectColumnByMap(Map<String, Object> paramMap) {
		List<ProjectColumnInfo> infoList = dao.selectEntitys("ProjectColumnInfo.selectByMap", paramMap);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("data", infoList);
		
		return resultMap;
	}

 

	public void deleteProjectColumn(ProjectColumnInfo info) {
		dao.delete(info);
	}
   
	public void updateRefresh(Map<String,Object> paramMap) {
		ProjectTableInfo vInfo = (ProjectTableInfo)paramMap.get("ProjectTableInfo");
		//获取列信息
		ProjectInfo pInfo = new ProjectInfo();
		pInfo.setId(vInfo.getPrjid());
		pInfo = prjService.findProjectInfo(pInfo);		 
		List<ColumnsDto> columnsDto = Dbconn.getTableColumn(pInfo, vInfo);
		
		//封装列信息
		List<ProjectColumnInfo> fcList = new ArrayList<ProjectColumnInfo>();
		Integer ordernum = 1;
		for(ColumnsDto v : columnsDto){
			ProjectColumnInfo fcInfo = new ProjectColumnInfo();
			fcInfo.setColcomment(v.getColumn_comment());
			fcInfo.setColname(v.getColumn_name().toLowerCase());
			fcInfo.setJavacolname(SonarCode.camelCode(fcInfo.getColname(), null));
			fcInfo.setPid(vInfo.getId());			
			fcInfo.setColtype(v.getData_type());
			fcInfo.setJavatype(DbFieldToJava.changeType(v.getData_type()));
			fcInfo.setOrdernum(ordernum++);
			fcList.add(fcInfo);
		}
		
		//删除已存在列信息
		ProjectColumnInfo fcInfo = new ProjectColumnInfo();
		fcInfo.setPid(vInfo.getId());
		dao.delete(fcInfo);
		
		//批量保存列信息
		fcList = this.setEntitiesID(fcList);
		dao.batchInsert("ProjectColumnInfo.insert", fcList);		 
	}

	public void updateGenerateFile(Map<String, Object> paramMap) {
		//获取磁盘地址
		ProjectTableInfo vPtInfo = (ProjectTableInfo)paramMap.get("ProjectTableInfo");
		String nickname = vPtInfo.getAuthor();		//昵称唯一
		String prjid = vPtInfo.getPrjid();
		ProjectauthorInfo vFaInfo = new ProjectauthorInfo();
		vFaInfo.setNickname(nickname);
		vFaInfo.setPrjid(prjid);					//项目id
		ProjectauthorInfo faInfo = authorService.findProjectauthorEntity(vFaInfo);		
		String strBaseFilePath = faInfo.getFilepath().substring(0,faInfo.getFilepath().lastIndexOf("-"));	//磁盘文件路径
		
		//获取列属性
		Map<String,Object> vParamMap = new HashMap<String,Object>();
		vParamMap.put("pid", vPtInfo.getId());
		
		List<ProjectColumnInfo> infoList = dao.selectEntitys("ProjectColumnInfo.selectByMap", vParamMap);
		paramMap.put("ProjectColumnInfo", infoList);
		
		//生成文件
		//D:\framework\nfmis-dao\src\main\java
		CtrlFileFunDto cffDto = (CtrlFileFunDto)paramMap.get("GenerateDto");
		//--Entity File
		if(cffDto.isFileEntity()){
			String filePath = strBaseFilePath+"-entity\\src\\main\\java";
			generateFile.writeEntity(filePath, cffDto, vPtInfo, infoList);
		}
		//--Dao File
		if(cffDto.isFileDao()){
			String filePath = strBaseFilePath+"-dao\\src\\main\\java";
			generateFile.writeMapper(filePath, cffDto, vPtInfo, infoList);
			generateFile.writeMapperXML(filePath, cffDto, vPtInfo, infoList);
		}
		//--Service File
		if(cffDto.isFileService()){
			String filePath = strBaseFilePath+"-service\\src\\main\\java"; 
			generateFile.writeIService(filePath, cffDto, vPtInfo, infoList);
			generateFile.writeService(filePath, cffDto, vPtInfo, infoList);
		}
		//--Facade File
		if(cffDto.isFileFacade()){
			String filePath = strBaseFilePath+"-facade\\src\\main\\java";
			generateFile.writeIFacade(filePath, cffDto, vPtInfo, infoList);
			generateFile.writeFacade(filePath, cffDto, vPtInfo, infoList);
		}
		//--Controller File
		if(cffDto.isFileController()){
			String filePath = strBaseFilePath+"-web\\src\\main\\java";
			generateFile.writeController(filePath, cffDto, vPtInfo, infoList);
		} 
		
	} 
	
}

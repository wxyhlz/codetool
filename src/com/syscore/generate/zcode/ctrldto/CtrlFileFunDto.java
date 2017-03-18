/**   
 * @Package	: com.syscore.generate.zcode.ctrldto
 * @Title	: CtrlFileFunDto.java 
 * @date 	: 2013-12-16 上午10:58:37 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.generate.zcode.ctrldto;

import java.io.Serializable;

/** 
 * @Description	: 文件和方法的控制Dto 
 * @ClassName: GenerateDto 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public class CtrlFileFunDto implements Serializable {

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 新增方法
	 */
	private boolean funInsert = false;
	
	/**
	 * 删除方法
	 */
	private boolean funDelete = false;
	
	/**
	 * 修改方法
	 */
	private boolean funUpdate = false;
	
	/**
	 * 作废方法
	 */
	private boolean funInvalid = false;
	
	/**
	 * 查询方法
	 */
	private boolean funSelectEntites = false;
	
	/**
	 * 分页查询方法
	 */
	private boolean funSelectPage = false;
	
	/**
	 * 单个查询方法
	 */
	private boolean funSelectSigle = false;
	
	/**
	 * Map查询方法
	 */
	private boolean funSelectMap = false;
	
	/**
	 * 批量新增方法
	 */
	private boolean funBatchInsert = false;
	
	/**
	 * 批量删除方法
	 */
	private boolean funBatchDelete = false;
	
	/**
	 * 批量修改方法
	 */
	private boolean funBatchUpdate = false;
	
	/**
	 * Entity
	 */
	private boolean fileEntity = false;
	
	/**
	 * Dao
	 */
	private boolean fileDao = false;
	
	/**
	 * Service
	 */
	private boolean fileService = false;
	
	/**
	 * Facade
	 */
	private boolean fileFacade = false;
	
	/**
	 * Controller
	 */
	private boolean fileController = false;

	public boolean isFunInsert() {
		return funInsert;
	}

	public void setFunInsert(boolean funInsert) {
		this.funInsert = funInsert;
	}

	public boolean isFunDelete() {
		return funDelete;
	}

	public void setFunDelete(boolean funDelete) {
		this.funDelete = funDelete;
	}

	public boolean isFunUpdate() {
		return funUpdate;
	}

	public void setFunUpdate(boolean funUpdate) {
		this.funUpdate = funUpdate;
	}

	public boolean isFunSelectEntites() {
		return funSelectEntites;
	}

	public void setFunSelectEntites(boolean funSelectEntites) {
		this.funSelectEntites = funSelectEntites;
	}

	public boolean isFunSelectPage() {
		return funSelectPage;
	}

	public void setFunSelectPage(boolean funSelectPage) {
		this.funSelectPage = funSelectPage;
	}

	public boolean isFunSelectSigle() {
		return funSelectSigle;
	}

	public void setFunSelectSigle(boolean funSelectSigle) {
		this.funSelectSigle = funSelectSigle;
	}

	public boolean isFunSelectMap() {
		return funSelectMap;
	}

	public void setFunSelectMap(boolean funSelectMap) {
		this.funSelectMap = funSelectMap;
	}

	public boolean isFunBatchInsert() {
		return funBatchInsert;
	}

	public void setFunBatchInsert(boolean funBatchInsert) {
		this.funBatchInsert = funBatchInsert;
	}

	public boolean isFunBatchDelete() {
		return funBatchDelete;
	}

	public void setFunBatchDelete(boolean funBatchDelete) {
		this.funBatchDelete = funBatchDelete;
	}

	public boolean isFileEntity() {
		return fileEntity;
	}

	public void setFileEntity(boolean fileEntity) {
		this.fileEntity = fileEntity;
	}

	public boolean isFileDao() {
		return fileDao;
	}

	public void setFileDao(boolean fileDao) {
		this.fileDao = fileDao;
	}

	public boolean isFileService() {
		return fileService;
	}

	public void setFileService(boolean fileService) {
		this.fileService = fileService;
	}

	public boolean isFileFacade() {
		return fileFacade;
	}

	public void setFileFacade(boolean fileFacade) {
		this.fileFacade = fileFacade;
	}

	public boolean isFileController() {
		return fileController;
	}

	public void setFileController(boolean fileController) {
		this.fileController = fileController;
	}

	public boolean isFunBatchUpdate() {
		return funBatchUpdate;
	}

	public void setFunBatchUpdate(boolean funBatchUpdate) {
		this.funBatchUpdate = funBatchUpdate;
	}

	public boolean isFunInvalid() {
		return funInvalid;
	}

	public void setFunInvalid(boolean funInvalid) {
		this.funInvalid = funInvalid;
	}
	
	
}

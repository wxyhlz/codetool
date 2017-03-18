/**   
 * @Package	: com.dotblue.commons.utils.page 
 * @Title	: Page.java 
 * @date 	: 2013-12-9 上午09:56:37 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.commons.utils.page;

import java.io.Serializable;
import java.util.List;

import com.syscore.commons.model.BasInfo;

/** 
 * @Description	:  分页结果集
 * @ClassName: Page 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public class Page<T extends BasInfo> implements Serializable {
	
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */
	
	private static final long serialVersionUID = 1L;

	/**
	 * 总记录数
	 */
	private Integer total;
	
	/**
	 * 总页数
	 */
	private Integer pageCount;
	
	/**
	 * 当前记录数
	 */
	private List<T> list;

	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	/**
	 * @return the pageCount
	 */
	public Integer getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<T> list) {
		this.list = list;
	}	

}

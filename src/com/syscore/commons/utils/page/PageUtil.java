/**   
 * @Package	: com.dotblue.utils.page 
 * @Title	: PageUtil.java 
 * @date 	: 2013-12-6 下午 02:37:49 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.commons.utils.page;

import java.util.List;
import java.util.Map;

import com.syscore.commons.dao.IBasDao;
import com.syscore.commons.model.BasInfo;
import com.syscore.constants.ConstantsWeb;


/** 
 * @Description	:  分页工具类
 * @ClassName: PageUtil 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public class PageUtil<T extends BasInfo> {

	/**
	 * 获取分页结果集
	 * @param paramMap
	 * @return
	 */
	public Page<T> doPageCalculate(String entityName, IBasDao<T> dao,Map<String,Object> paramMap){
		//参数
		Integer pageNumber = (Integer)paramMap.get("pageNumber");
		Integer pageSize = (Integer)paramMap.get("pageSize");
		//计算
		if("mysql".equals(ConstantsWeb.databaseType)){
			/**
			 * {pageBegin,pageSize}
			 * 
			 *  SELECT * FROM SWBK_ENTRY  ORDER BY ENTRY_MC,HITS DESC
  			 *	LIMIT #pageBegin#,#pageSize# 
			 */
			Integer page = pageNumber-1;
			Integer pageBegin = page * pageSize;
			
			paramMap.put("pageBegin", pageBegin);
		}else if("sqlserver".equals(ConstantsWeb.databaseType)){
			/**
			 * {pageBegin,pageSize}
			 * 
			 * SELECT TOP $pageSize$ * FROM UT_DM_GY_JSXX
			 *	WHERE (	
			 *  	seq_id < (	
			 *			SELECT ISNULL(MIN(seq_id),99999999) FROM ( SELECT Top $pageBegin$ seq_id  FROM UT_DM_GY_JSXX ) AS T
			 *  	)
			 *	)
			 */			
			Integer page = pageNumber-1;
			Integer pageBegin = page * pageSize;
			
			paramMap.put("pageBegin", pageBegin);			 
		}else if("oracle".equals(ConstantsWeb.databaseType)){
			/**
			 * {pageBegin,pageEnd}
			 * 
			 * SELECT * FROM
			 *	(SELECT A.*,ROWNUM ROW_NUM FROM
			 *		(SELECT M.* FROM UT_QS_JD_QYJDXXB M ) A
			 *	) B 
			 * WHERE B.ROW_NUM BETWEEN $pageBegin$ AND $pageEnd$
			 */
			Integer page = pageNumber-1;
			
			Integer pageBegin = page * pageSize;
			Integer pageEnd = pageBegin + pageSize;
			
			paramMap.put("pageBegin", pageBegin);
			paramMap.put("pageEnd", pageEnd);
		}
		//dao获取数据 
		Integer total = (Integer)dao.selectObject(entityName+".selectPageCount");
		List<T> list = dao.selectEntitys(entityName+".selectPage", paramMap);
		//创建page
		Page<T> page = new Page<T>();
		if(total>0){
			Integer rows = Integer.valueOf(pageSize);
			Integer pageCount = (Integer) ((total + rows)/rows);
			page.setPageCount(pageCount);
		}
		page.setTotal(total);
		page.setList(list);
		//返回
		return page;		
	}
	
}

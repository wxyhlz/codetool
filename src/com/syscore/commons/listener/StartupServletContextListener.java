/********************************
 * @Tide
 *  
 * @Copyright © by  
 * @Create Author: Tide
 * @Create Date: Jun 28, 2011 
 * @Modify Date:
 * @Version: V 1.0 
 */ 

package com.syscore.commons.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.ehcache.Cache;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.syscore.constants.ConstantsWeb;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author Administrator
 * @version [版本号, 2011-5-13]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class StartupServletContextListener implements ServletContextListener {

	private static Logger logger = Logger
			.getLogger(StartupServletContextListener.class);

	public void contextDestroyed(ServletContextEvent arg0) {

	}
 
	public void contextInitialized(ServletContextEvent arg0) {
		ConstantsWeb.webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());

		logger.info("加载系统管理数据业务字典");
		ConstantsWeb.listCache = (Cache) ConstantsWeb.webApplicationContext.getBean("listCache");
		ConstantsWeb.mapCache = (Cache) ConstantsWeb.webApplicationContext.getBean("mapCache");
		try { 
			LoadToCache ltc = new LoadToCache();
			ltc.loadCache();
		} catch (Exception e) {
			logger.error("加载业务字典失败！");
			e.printStackTrace();
		}
		 
	}

}

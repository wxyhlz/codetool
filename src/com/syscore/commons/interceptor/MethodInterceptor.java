/*
 * 版    权:  chanjet Copyright 2010-2012,All rights reserved
 * 文 件 名:  MethodInterceptor.java
 * 描       述:  <描述>
 * 修改人:  Sky Ask
 * 修改时间:  2012-3-19
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.syscore.commons.interceptor;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

/**
 * <功能详细描述>
 * 
 * @author zhangzhw
 * @version [版本号, 2012-3-19]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MethodInterceptor implements
		org.aopalliance.intercept.MethodInterceptor {
	private static Logger logger = Logger.getLogger(MethodInterceptor.class);

	/** {@inheritDoc} */

	public Object invoke(MethodInvocation invocation) throws Throwable {
		StopWatch watch = new StopWatch();
		Object returnValue = null;
		Method m = invocation.getMethod();
		Object target = invocation.getThis();
		logger.info(target.getClass().getName() + "." + m.getName() + "方法调用开始");
		watch.start();
		returnValue = invocation.proceed();
		watch.stop();
		logger.info(target.getClass().getName() + "." + m.getName() + "方法调用结束");
		logger.info("总使用时间: " + watch.getTime() + " ms");
		return returnValue;
	}
}

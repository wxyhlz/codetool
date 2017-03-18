/********************************
 *  
 * @Copyright (c) by  
 * @Create Author: Tide
 * @Create Date: Jun 28, 2011 
 * @Modify Date:
 * @Version: V 1.0 
 */ 
package com.syscore.commons.interceptor;


import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 4949812834762901805L;
	
	private static Logger logger = Logger.getLogger(AuthorityInterceptor.class);
 
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//return invocation.invoke();
		
		// 取得请求的Action名// action 的名称，在xml中配置的
		// 获取到namespace，还能够获取到要执行的方法，class等		
		String name = invocation.getInvocationContext().getName();		
		String namespace = invocation.getProxy().getNamespace();
		if ((namespace != null) && (namespace.trim().length() > 0)) {
			if ("/".equals(namespace.trim())) {// 说明是根路径，不需要再增加反斜杠了。
			} else {
				namespace += "/";
			}
		}
		String URL = namespace + invocation.getProxy().getActionName();

		URL += ".action";
		logger.debug("actionname=" + name + "||fullActionName=" + URL);


		// 如果用户想登录，则使之通过
		//if (name.equals("selectBySchool")|| name.equals("accountLogin")|| name.equals("image")) {			
			return invocation.invoke();
		//}

		/*Map<String, Object> session = invocation.getInvocationContext().getSession();
		AccountInfo account =(AccountInfo) session.get("loginAccount");
		// 如果没有登陆，那么就退出系统
		if (account == null) {
			logger.debug("please login");
			return "logindirect";
		}else{
			logger.debug("登陆人员=" + account.getName());
			return invocation.invoke();
		}	*/	 
	}
}

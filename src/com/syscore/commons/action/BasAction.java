/********************************
 *  
 * @Copyright (c) by  
 * @Create Author: Tide
 * @Create Date: Jun 28, 2011 
 * @Modify Date:
 * @Version: V 1.0 
 */ 

package com.syscore.commons.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.syscore.commons.service.IBasService;
import com.syscore.commons.utils.response.BadusResponse;


public class BasAction<T,SERVICE extends IBasService<T>> extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;  
 
	protected SERVICE service;
	
	//返回界面信息——增-删-改 操作
	private BadusResponse badusResponse = new BadusResponse();
	
	/**
	 * @return the service
	 */
	public SERVICE getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(SERVICE service) {
		this.service = service;
	}

	public BadusResponse getBadusResponse() {
		return badusResponse;
	}

	public void setBadusResponse(BadusResponse badusResponse) {
		this.badusResponse = badusResponse;
	}
	
	public String getParameter(String name) {
		return ServletActionContext.getRequest().getParameter(name);
	}
	
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

 
	public void SetExceptionInfo(Exception e,Boolean needPrint){
		if(needPrint){
			e.printStackTrace();
		}
		List<String> errors = new ArrayList<String>();
		errors.add(e.getMessage());
		getBadusResponse().setErrors(errors);
		getBadusResponse().setSuccessed(false);
	}

	/**
	 * 读取 session 中的属性值
	 * 
	 * @param name
	 * @return
	 */
	public static Object getSession(String name) {
		ActionContext ctx = ActionContext.getContext(); 
		Map<String,Object> session = ctx.getSession();
		return session.get(name);
	}

	/**
	 * 向 session 设置属性值
	 * 
	 * @param name
	 * @param value
	 */
	public static void setSession(String name, Object value) {
		ActionContext ctx = ActionContext.getContext();
		Map<String,Object> session = ctx.getSession();
		session.put(name, value);
	} 
	
	/**
	 * 返回struts上下文
	 * @return
	 */
	public static ActionContext getActionContext(){
		ActionContext ctx = ActionContext.getContext();
		return ctx;
	}
	
}

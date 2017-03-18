/********************************
 *  
 * @Copyright (c) by  
 * @Create Author: Tide
 * @Create Date: Jun 28, 2011 
 * @Modify Date:
 * @Version: V 1.0 
 */ 
package com.syscore.commons.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.syscore.commons.except.BusinessException;
import com.syscore.commons.utils.response.BadusResponse;
 
public class ExcepInterceptor extends AbstractInterceptor {

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = -6056153506268769118L;
 
	/*****************************************************/
	//私有方法
	
	//判断请求类型
	private boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header))
			return true;
		else
			return false;
	}
	
	/*****************************************************/ 
	
	public String intercept(ActionInvocation actioninvocation) throws Exception {
		
		String result = null; // Action的返回值
		try {
			// 运行被拦截的Action,期间如果发生异常会被catch住
			result = actioninvocation.invoke();
			return result;
		} catch (Exception e) {			 
			String errorMsg = "未知错误！";
			String errorDetail = "";
			
			
			if (e instanceof BusinessException) {//教员学员信息发布异常
				BusinessException ee = (BusinessException) e;
				if (ee.getMessage() != null){
					errorMsg = ee.getMessage().trim();
				}
			} else if (e instanceof RuntimeException) { 
				errorMsg = "在运行"
						+ actioninvocation.getProxy().getActionName()
						+ "时发生严重错误！";
			} else {
				// 未知的严重异常
				e.printStackTrace();
				errorMsg = "在运行"
						+ actioninvocation.getProxy().getActionName()
						+ "时发生严重错误！";
			}

			/**
			 * log4j记录日志
			 */
			Logger log = Logger.getLogger(actioninvocation.getAction().getClass());
			if (e.getCause() != null) {
				log.error(errorMsg, e);
			} else {
				Throwable re = (Throwable) e;
				if (re.getCause() == null) {
					errorDetail = re.getMessage();
					StackTraceElement[] stackTraceElements = re.getStackTrace();
					for (int i = 0; i < stackTraceElements.length - 1; i++) {
						errorDetail = errorDetail + "\n" + "		at "
								+ stackTraceElements[i].getClassName() + "."
								+ stackTraceElements[i].getMethodName() + "("
								+ stackTraceElements[i].getFileName() + ":"
								+ stackTraceElements[i].getLineNumber() + ")";
					}
				}
				log.error(errorDetail);
			}

			
			HttpServletRequest request = (HttpServletRequest) actioninvocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);

			
			
			//判断是ajax请求还是其他请求
			if (!isAjaxRequest(request)) {
				/**
				 * 发送错误消息到页面
				 */
				request.setAttribute("errorMsg", errorMsg);
				request.setAttribute("errorDetail", e.toString());
				/**
				 * 读取文件，获取对应错误消息
				 */
				HttpServletResponse response = (HttpServletResponse) actioninvocation.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);

				response.setContentType("text/html;charset=UTF-8"); 
				/**
				 * 发送错误消息到页面
				 */
				PrintWriter out;
				try {
					out = response.getWriter();
					BadusResponse badusResponse = new BadusResponse();
					List<String> errors = new ArrayList<String>();
					errors.add(errorMsg);
					badusResponse.setSuccessed(false);
					badusResponse.setErrors(errors);
					// 把异常信息转换成json格式返回给前台
					
					out.print(JSONObject.fromObject(badusResponse).toString());
				} catch (IOException e1) {
					throw e;
				}
				return null;
			} else {
				/**
				 * 读取文件，获取对应错误消息
				 */
				HttpServletResponse response = (HttpServletResponse) actioninvocation
						.getInvocationContext()
						.get(StrutsStatics.HTTP_RESPONSE);

				response.setCharacterEncoding(request.getCharacterEncoding());
				/**
				 * 发送错误消息到页面
				 */
				PrintWriter out;
				try {
					out = response.getWriter();
					BadusResponse badusResponse = new BadusResponse();
					List<String> errors = new ArrayList<String>();
					errors.add(errorMsg);
					badusResponse.setSuccessed(false);
					badusResponse.setErrors(errors);
					// 把异常信息转换成json格式返回给前台
					out.print(JSONObject.fromObject(badusResponse).toString());
				} catch (IOException e1) {
					throw e;
				}
				return null;
			} 
		}
	}
 
	
}

/********************************
 * @Tide
 *  
 * @Copyright © by  
 * @Create Author: Tide
 * @Create Date: Jun 28, 2011 
 * @Modify Date:
 * @Version: V 1.0 
 */ 
package com.syscore.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.ehcache.Cache;

import org.springframework.web.context.WebApplicationContext;

public class ConstantsWeb {
	public static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();
	public static Cache listCache = null;
	public static Cache mapCache = null;
	public static WebApplicationContext webApplicationContext = null;
	
	//数据库类型
	public static final String databaseType = "mysql";//mysql,oracle,sqlserver,sybase
	//数据库
	public static final String databaseName = "dotblue";
 
	/**
	 * 异常信息统一头信息<br>
	 * 非常遗憾的通知您,程序发生了异常
	 */
	public static final String Exception_Head = "非常遗憾的通知您,程序发生了异常.\n" + "异常信息如下:\n";

	public static final String BLANK = "";
	
	/**
	 * 
	 * @param fdf -:yyyy-MM-dd HH:mm:ss;-_d:yyyy-MM-dd
	 * @return
	 */
	public static final String getSystemtime(String fdf){
		Date date = new Date();
		DateFormat df = null;
		if("-".equalsIgnoreCase(fdf)){
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}else if("/".equals(fdf)){
			df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		}else if("no".equals(fdf)){
			df = new SimpleDateFormat("yyyyMMddHHmmss");
		}else if("all".equals(fdf)){
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		}else if("-_d".equalsIgnoreCase(fdf)){
			df = new SimpleDateFormat("yyyy-MM-dd");
		}else if("/_d".equals(fdf)){
			df = new SimpleDateFormat("yyyy/MM/dd");
		}else if("no_d".equals(fdf)){
			df = new SimpleDateFormat("yyyyMMdd");
		}
		return df.format(date); 
	}
	
	//当前年
	public static final String getSystemyear(){
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy");
		return df.format(date); 
	}
	
	//获取第几周期
	public static final String getSlideWeeks(){
		Calendar cal=Calendar.getInstance(); 
		cal.setTime(new Date());
		Integer iYear = cal.get(Calendar.YEAR);
		Integer iWeek = cal.get(Calendar.WEEK_OF_YEAR);
		String year = iYear.toString();
		String week = iWeek.toString();
		if(week.length()==1) week = "0"+week;			
		String years = year+week;	//年周
		return years;
	}
	
	@SuppressWarnings("unchecked")
	public static final String getDaoPrefix(Class entityClass){ 
		String className = entityClass.getName();
		String shortName = className.replace(entityClass.getPackage().getName()+ ".", "");
		return shortName+".";
	}
}

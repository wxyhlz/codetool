/**   
 * @Package	: com.dotblue.generate.generatecode.funtemplate.fun 
 * @Title	: FunInsatanceFactory.java 
 * @date 	: 2013-12-16 上午11:21:26 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.generate.zcode.impl.factory;

import com.syscore.generate.zcode.impl.gcontroller.fun.ControllerFunTemplate;
import com.syscore.generate.zcode.impl.gdao.fun.MapperFunTemplate;
import com.syscore.generate.zcode.impl.gfacade.fun.FacadeFunTemplate;
import com.syscore.generate.zcode.impl.gfacade.fun.FacadeIFunTemplate;
import com.syscore.generate.zcode.impl.gservice.fun.ServiceFunTemplate;
import com.syscore.generate.zcode.impl.gservice.fun.ServiceIFunTemplate;
import com.syscore.generate.zcode.inter.IFunTemplate;
 

/** 
 * @Description	:  方法的静态工厂
 * @ClassName: FunInsatanceFactory 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public class FunInsatanceFactory { 
	
	/**
	 * 实例化通用方法函数
	 * 
	 * @param fileType
	 *            (action,service,bp,struts)
	 * @return
	 */
	public static IFunTemplate insatnceFunction(String fileType) {
		IFunTemplate instanceClass = null;
		if ("controller".equals(fileType)) {
			instanceClass = new ControllerFunTemplate();
		} else if ("ifacade".equals(fileType)) {
			instanceClass = new FacadeIFunTemplate();
		} else if ("facade".equals(fileType)) {
			instanceClass = new FacadeFunTemplate();
		} else if ("iservice".equals(fileType)) {
			instanceClass = new ServiceIFunTemplate();
		} else if ("service".equals(fileType)) {	
			instanceClass = new ServiceFunTemplate();		
		} else if ("mapper".equals(fileType)) {
			instanceClass = new MapperFunTemplate();
		}
		return instanceClass;
	}
}

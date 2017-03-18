/**   
 * @Package	: com.dotblue.generate.generatecode.funtemplate.fun 
 * @Title	: FunInsatanceFactory.java 
 * @date 	: 2013-12-16 上午11:21:26 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.generate.zcode.impl.factory;

import com.syscore.generate.zcode.impl.gcontroller.file.ControllerFileTemplate;
import com.syscore.generate.zcode.impl.gdao.file.MapperFileTemplate;
import com.syscore.generate.zcode.impl.gdao.file.MapperXmlTemplate;
import com.syscore.generate.zcode.impl.gentiy.file.ModelTemplate;
import com.syscore.generate.zcode.impl.gfacade.file.FacadeFileTemplate;
import com.syscore.generate.zcode.impl.gfacade.file.FacadeIFileTemplate;
import com.syscore.generate.zcode.impl.gservice.file.ServiceFileTemplate;
import com.syscore.generate.zcode.impl.gservice.file.ServiceIFileTemplate;
import com.syscore.generate.zcode.inter.IFileTemplate;
 

/** 
 * @Description	:  方法的静态工厂
 * @ClassName: FunInsatanceFactory 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public class FileInsatanceFactory { 
	
	/**
	 * 实例化通用方法函数
	 * 
	 * @param fileType
	 *            (action,service,bp,struts)
	 * @return
	 */
	public static IFileTemplate insatnceFunction(String fileType) {
		IFileTemplate instanceClass = null;
		if ("controller".equals(fileType)) {
			instanceClass = new ControllerFileTemplate();
		}else if("Ifacade".equals(fileType)){
			instanceClass = new FacadeIFileTemplate();
		}else if("facade".equals(fileType)){
			instanceClass = new FacadeFileTemplate();
		} else if ("Iservice".equals(fileType)) {
			instanceClass = new ServiceIFileTemplate();
		} else if ("service".equals(fileType)) {
			instanceClass = new ServiceFileTemplate();		
		} else if ("mapper".equals(fileType)) {
			instanceClass = new MapperFileTemplate();
		} else if ("mapperxml".equals(fileType)) {
			instanceClass = new MapperXmlTemplate();
		} else if ("model".equals(fileType)){
			instanceClass = new ModelTemplate();
		}
		return instanceClass;
	}
}

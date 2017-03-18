/*
 * 文 件 名:  DefaultSequenceGenerator.java
 * 版    权:  changjet Co., Ltd. Copyright 2010-2011,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  张卓卫
 * 修改时间:  2012-1-4
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.syscore.commons.utils.generate;

import java.util.Stack;
import java.util.UUID;

import org.apache.log4j.Logger;


/**
 * 默认序列号生成器
 * 
 * @author Tide
 * @version [版本号, 2012-1-4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DefaultSequenceGenerator {
	protected static Logger logger = Logger.getLogger(DefaultSequenceGenerator.class);
	private static DefaultSequenceGenerator instance = new DefaultSequenceGenerator();
	private Stack<String> uuidStack = new Stack<String>();
	private Integer setpSequence = 1000;

	private DefaultSequenceGenerator() {}

	/** {@inheritDoc} */

	public static DefaultSequenceGenerator getInstance() {
		return instance;
	} 
	
	/**
	 * Java中的UUID生成全球唯一的ID
	 * @return
	 * @throws SequenceException
	 */
	public synchronized String uuid() throws SequenceException {

		if (uuidStack.empty()) {
			try { 				
				for (Integer i = setpSequence; i >0 ; i--) {
					String sid=UUID.randomUUID().toString();			        
			        sid = sid.substring(0,8)+sid.substring(9,13)+sid.substring(14,18)+sid.substring(19,23)+sid.substring(24);
					uuidStack.push(sid);
				}

			} catch (Exception e) {
				logger.error(this.getClass() + "uuid方法发生错误！");
				throw new SequenceException("生成序列号出错！", e);
			}
		}

		String id = uuidStack.pop(); 
		return id;
	} 

}

/*
 * 文 件 名:  TreeNode.java
 * 版    权:  changjet Co., Ltd. Copyright 2010-2011,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  张卓卫
 * 修改时间:  2011-4-8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.util.node;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author Administrator
 * @version [版本号, 2011-4-8]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TreeNode implements Comparable<TreeNode> {

	private String id;
	private String pId;
	private String name;
	private String action_url;
	private String isParent;
	private String openType;

	/**
	 * @return 返回 id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param 对id进行赋值
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return 返回 pId
	 */
	public String getpId() {
		return pId;
	}

	/**
	 * @param 对pId进行赋值
	 */
	public void setpId(String pId) {
		this.pId = pId;
	}

	/**
	 * @return 返回 name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param 对name进行赋值
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 返回 action_url
	 */
	public String getAction_url() {
		return action_url;
	}

	/**
	 * @param 对action_url进行赋值
	 */
	public void setAction_url(String actionUrl) {
		action_url = actionUrl;
	}

	/**
	 * @return 返回 isParent
	 */
	public String getIsParent() {
		return isParent;
	}

	/**
	 * @param 对isParent进行赋值
	 */
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	/**
	 * @return the openType
	 */
	public String getOpenType() {
		return openType;
	}

	/**
	 * @param openType the openType to set
	 */
	public void setOpenType(String openType) {
		this.openType = openType;
	}

	/** {@inheritDoc} */
	 
	public int compareTo(TreeNode o) {
		return Long.valueOf(this.getId()).intValue() - Long.valueOf(o.getId()).intValue();
	}
	
	

}

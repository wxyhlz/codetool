/*
 * 版    权:  chanjet Copyright 2010-2012,All rights reserved
 * 文 件 名:  TreeRoleModule.java
 * 描       述:  <描述>
 * 修改人:  Sky Ask
 * 修改时间:  2012-2-20
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.util.node;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2012-2-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TreeCheck extends TreeNode {
	
	private Boolean checked;
	
	private Boolean open;	

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}
	
}

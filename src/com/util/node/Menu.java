package com.util.node;

import java.util.ArrayList;
import java.util.List;


public class Menu implements Comparable<Menu> {
	
	private String menuid;
	
	private String menuname;
	
	private String icon;
	
	private String url; 
	
	private List<Menu> menus = new ArrayList<Menu>();
		
	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	} 
	
	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public int compareTo(Menu o) {
		return Long.valueOf(this.getMenuid()).intValue() - Long.valueOf(o.getMenuid()).intValue();
	}

}

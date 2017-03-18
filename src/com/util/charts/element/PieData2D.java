package com.util.charts.element;

public class PieData2D {
	/**
	 * 颜色
	 */
	private String color;
	
	/**
	 * 标题
	 */
	private String label;
	
	/**
	 * 数值
	 */
	private String value;
	
	/**
	 * 是否凸出：1凸
	 */
	private String isSliced;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIsSliced() {
		return isSliced;
	}

	public void setIsSliced(String isSliced) {
		this.isSliced = isSliced;
	}
}

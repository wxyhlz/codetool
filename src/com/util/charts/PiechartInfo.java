package com.util.charts;

import java.util.ArrayList;
import java.util.List;

import com.util.charts.element.PieData2D;
import com.util.charts.element.PieTitle2D;

public class PiechartInfo {
	
	private PieTitle2D chart;
	
	private List<PieData2D> data = new ArrayList<PieData2D>();

	public PieTitle2D getChart() {
		return chart;
	}

	public void setChart(PieTitle2D chart) {
		this.chart = chart;
	}

	public List<PieData2D> getData() {
		return data;
	}

	public void setData(List<PieData2D> data) {
		this.data = data;
	}
	
	
}

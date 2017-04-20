package com.xu.common.domain;

public class PieChartData {

	private String name;
	private int y;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "PieChartData [name=" + name + ", y=" + y + "]";
	}
	
}

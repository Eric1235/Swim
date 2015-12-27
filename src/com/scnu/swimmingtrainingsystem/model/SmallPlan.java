package com.scnu.swimmingtrainingsystem.model;

public class SmallPlan {
	private int distance;
	private String pool;
	private String extra;
	private int strokeNumber;
	private int time;//游泳圈数

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getPool() {
		return pool;
	}

	public void setPool(String pool) {
		this.pool = pool;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	

	public int getStrokeNumber() {
		return strokeNumber;
	}

	public void setStrokeNumber(int strokeNumber) {
		this.strokeNumber = strokeNumber;
	}

	@Override
	public String toString() {
		return "SmallPlan{" +
				"distance=" + distance +
				", pool='" + pool + '\'' +
				", extra='" + extra + '\'' +
				", strokeNumber=" + strokeNumber +
				", time=" + time +
				'}';
	}
}

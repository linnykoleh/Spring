package com.learning.linnyk.jb._1_di.beans;

/**
 * @author LinnykOleh
 */
public class Triangle {

	private String type;
	private String color;
	private int height;

	private Point pointA;
	private Point pointB;
	private Point pointC;

	public Triangle() {

	}

	public Triangle(String type) {
		this.type = type;
	}

	public Triangle(String type, int height) {
		this.type = type;
		this.height = height;
	}

	public Triangle(Point pointA, Point pointB, Point pointC) {
		this.pointA = pointA;
		this.pointB = pointB;
		this.pointC = pointC;
	}

	public void draw(){
		System.out.println(toString());
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Point getPointA() {
		return pointA;
	}

	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	public Point getPointB() {
		return pointB;
	}

	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}

	public Point getPointC() {
		return pointC;
	}

	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}


	@Override
	public String toString() {
		return "Triangle{" +
				"type='" + type + '\'' +
				", color='" + color + '\'' +
				", height=" + height +
				", pointA=" + pointA +
				", pointB=" + pointB +
				", pointC=" + pointC +
				'}';
	}
}

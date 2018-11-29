package com.learning.linnyk.jb._2_scope_lifecycle.beans;

import com.learning.linnyk.jb._1_di.beans.Point;

/**
 * @author LinnykOleh
 */
public class TriangleInheritance{

	private Point pointA;
	private Point pointB;
	private Point pointC;

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

	public void draw() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "TriangleInheritance{" +
				"pointA=" + pointA +
				", pointB=" + pointB +
				", pointC=" + pointC +
				'}';
	}
}

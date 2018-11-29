package com.learning.linnyk.jb._1_di;

import com.learning.linnyk.jb._1_di.beans.Point;

/**
 * @author LinnykOleh
 */
public class TriangleAutowiringByName {

	private Point pointD;
	private Point pointE;
	private Point pointF;

	public void setPointD(Point pointD) {
		this.pointD = pointD;
	}

	public void setPointE(Point pointE) {
		this.pointE = pointE;
	}

	public void setPointF(Point pointF) {
		this.pointF = pointF;
	}

	public void draw(){
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "TriangleAutowiringByName{" +
				"pointD=" + pointD +
				", pointE=" + pointE +
				", pointF=" + pointF +
				'}';
	}
}

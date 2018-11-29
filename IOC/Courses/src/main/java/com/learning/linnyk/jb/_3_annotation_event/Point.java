package com.learning.linnyk.jb._3_annotation_event;

import org.springframework.stereotype.Service;

/**
 * @author LinnykOleh
 */
@Service
public class Point {

	private int x = 110;
	private int y = 220;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point{" + "\n" +
				"\t" + "x=" + x + "\n" +
				"\t" + "y=" + y + "\n" +
				"\t" + '}';
	}
}

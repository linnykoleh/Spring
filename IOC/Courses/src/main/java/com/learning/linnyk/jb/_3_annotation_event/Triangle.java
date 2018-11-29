package com.learning.linnyk.jb._3_annotation_event;

import org.springframework.stereotype.Repository;

/**
 * @author LinnykOleh
 */
@Repository
public class Triangle {

	private String type = "shape";
	private String color = "red";

	@Override
	public String toString() {
		return "Triangle{" + "\n" +
				"\t" + "type=" + type + "\n" +
				"\t" + "color=" + color + "\n" +
				"\t" + '}';
	}
}

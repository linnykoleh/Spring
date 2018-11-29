package com.learning.linnyk.jb._1_di;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.learning.linnyk.jb._1_di.beans.Point;

/**
 * @author LinnykOleh
 */
public class TriangleCollections {

	private List<Point> pointsList;
	private Set<Point> pointsSet;
	private Map<String, Point> pointsMap;

	public TriangleCollections() {

	}

	public void draw(){
		System.out.println(toString());
	}

	public List<Point> getPointsList() {
		return pointsList;
	}

	public void setPointsList(List<Point> pointsList) {
		this.pointsList = pointsList;
	}

	public Set<Point> getPointsSet() {
		return pointsSet;
	}

	public void setPointsSet(Set<Point> pointsSet) {
		this.pointsSet = pointsSet;
	}

	public Map<String, Point> getPointsMap() {
		return pointsMap;
	}

	public void setPointsMap(Map<String, Point> pointsMap) {
		this.pointsMap = pointsMap;
	}

	@Override
	public String toString() {
		return "TriangleCollections{" +
				"pointsList=" + pointsList +
				", pointsSet=" + pointsSet +
				", pointsMap=" + pointsMap +
				'}';
	}
}

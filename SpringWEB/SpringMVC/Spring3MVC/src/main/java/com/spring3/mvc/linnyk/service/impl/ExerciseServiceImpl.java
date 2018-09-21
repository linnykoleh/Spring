package com.spring3.mvc.linnyk.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring3.mvc.linnyk.model.Activity;
import com.spring3.mvc.linnyk.service.ExerciseService;

@Service
public class ExerciseServiceImpl implements ExerciseService {

	@Override
	public List<Activity> findAllActivities() {
		final List<Activity> activities = new ArrayList<>();

		final Activity run = new Activity();
		run.setDesc("Run");
		activities.add(run);

		final Activity bike = new Activity();
		bike.setDesc("Bike");
		activities.add(bike);

		final Activity swim = new Activity();
		swim.setDesc("Swim");
		activities.add(swim);

		return activities;
	}
}

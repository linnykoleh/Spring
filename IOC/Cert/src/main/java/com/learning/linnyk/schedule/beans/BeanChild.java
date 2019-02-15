package com.learning.linnyk.schedule.beans;

import org.springframework.stereotype.Service;

@Service
public class BeanChild extends BeanParent {

	@Override
	public void scheduleFixedDelayTask() {
		System.out.println("Fixed delay task - " + 2000);
	}
}

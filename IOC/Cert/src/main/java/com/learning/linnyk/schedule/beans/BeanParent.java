package com.learning.linnyk.schedule.beans;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BeanParent implements Bean {

	@Override
	@Scheduled(fixedDelay = 1000)
	public void scheduleFixedDelayTask() {
		System.out.println("Fixed delay task - " + 1000);
	}
}

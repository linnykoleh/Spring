package com.leaning.linnyk.cloud.limitsservice.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LimitConfiguration {

	private int maximum;
	private int minimum;
}

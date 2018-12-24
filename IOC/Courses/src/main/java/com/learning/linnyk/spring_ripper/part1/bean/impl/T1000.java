package com.learning.linnyk.spring_ripper.part1.bean.impl;

import com.learning.linnyk.spring_ripper.part1.bean.Quoter;

/**
 * @author LinnykOleh
 */
public class T1000 extends TerminatorQuoter implements Quoter {

	@Override
	public void sayQuote() {
		System.out.println("I'm liquid");
	}
}

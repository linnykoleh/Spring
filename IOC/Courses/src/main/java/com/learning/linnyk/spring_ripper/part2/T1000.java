package com.learning.linnyk.spring_ripper.part2;

import com.learning.linnyk.spring_ripper.TerminatorQuoter;
import com.learning.linnyk.spring_ripper.part1.Quoter;

/**
 * @author LinnykOleh
 */
public class T1000 extends TerminatorQuoter implements Quoter {

	@Override
	public void sayQuote() {
		System.out.println("I'm liquid");
	}
}

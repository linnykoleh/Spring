package com.ewolff.aspects;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ewolff.service.PlainService;
import com.ewolff.transaction.StubPlatformTransactionManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/system-configuration.xml")
public class TransactionTest {

	@Autowired
	PlainService plainService;

	@Autowired
	StubPlatformTransactionManager stubPlatformTransactionManager;
	
	@Test
	public void callService() {
		assertThat(stubPlatformTransactionManager.getTotal(), equalTo(0));
		plainService.doSomething();
		assertThat(stubPlatformTransactionManager.getTotal(), equalTo(1));
		assertThat(stubPlatformTransactionManager.getCommit(), equalTo(1));
	}

}

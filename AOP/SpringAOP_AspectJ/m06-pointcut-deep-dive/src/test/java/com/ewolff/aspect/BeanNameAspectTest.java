package com.ewolff.aspect;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ewolff.aspect.BeanNameAspect;
import com.ewolff.configuration.SystemConfiguration;
import com.ewolff.repository.SimpleRepository;
import com.ewolff.service.SimpleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SystemConfiguration.class)
public class BeanNameAspectTest {

	@Autowired
	BeanNameAspect beanNameAspect;

	@Autowired
	SimpleService simpleService;

	@Autowired
	SimpleRepository simpleRepository;
	
	@Before
	public void setUp() {
		beanNameAspect.resetCalled();
	}

	@Test
	public void tracingOnServiceIsCalled() {
		assertThat(beanNameAspect.getCalled(), is(0));
		simpleService.doSomething();
		assertThat(beanNameAspect.getCalled(), is(1));
	}
	
	@Test
	public void tracingOnRepsositoryIsNotCalled() {
		assertThat(beanNameAspect.getCalled(), is(0));
		simpleRepository.doSomething();
		assertThat(beanNameAspect.getCalled(), is(0));
	}

}

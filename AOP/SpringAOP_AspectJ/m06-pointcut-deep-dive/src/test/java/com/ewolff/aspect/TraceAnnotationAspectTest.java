package com.ewolff.aspect;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ewolff.aspect.TraceAnnotationAspect;
import com.ewolff.configuration.SystemConfiguration;
import com.ewolff.service.SimpleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SystemConfiguration.class)
public class TraceAnnotationAspectTest {

	@Autowired
	TraceAnnotationAspect traceAnnotationAspect;

	@Autowired
	SimpleService simpleService;

	@Before
	public void setUp() {
		traceAnnotationAspect.resetCalled();
	}

	@Test
	public void tracingOnNotAnnotatedMethodIsNotCalled() {
		assertThat(traceAnnotationAspect.getCalled(), is(0));
		simpleService.doSomething();
		assertThat(traceAnnotationAspect.getCalled(), is(0));
	}

	@Test
	public void tracingOnAnnotatedMethodIsCalled() {
		assertThat(traceAnnotationAspect.getCalled(), is(0));
		simpleService.annotated();
		assertThat(traceAnnotationAspect.getCalled(), is(1));
	}

}

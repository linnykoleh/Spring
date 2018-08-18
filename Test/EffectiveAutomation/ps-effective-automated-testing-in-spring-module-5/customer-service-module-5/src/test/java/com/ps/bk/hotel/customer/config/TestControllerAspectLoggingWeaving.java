package com.ps.bk.hotel.customer.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ps.bk.hotel.customer.controller.CustomerController;
import com.ps.bk.hotel.customer.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CustomerController.class, secure = false)
@Import(ControllerLoggingAspect.class)
@TestPropertySource(properties="logging.config=classpath:logback-static-appender.xml")
@DirtiesContext(classMode=ClassMode.BEFORE_CLASS)
public class TestControllerAspectLoggingWeaving {

	@MockBean
	private CustomerService service;

	@Autowired
	private MockMvc mockMvc;

	@After
	public void cleanUp() {
		TestAppender.clearEvents();
	}

	@Test
	public void testLoggingAspectForSuccessfulRequest() throws Exception {
		mockMvc.perform(get("/customers"));
		assertThat(TestAppender.getEvents()).extracting("message").contains("Entering Method: findAllCustomers",
				"Exiting Method: findAllCustomers");
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testLoggingAspectWhenExceptionIsThrown() throws Exception {
		when(service.findAllCustomers()).thenThrow(RuntimeException.class);
		try {
			mockMvc.perform(get("/customers"));
		} catch (Exception e) {
			// Ignore thrown exception.
		}
		assertThat(TestAppender.getEvents()).extracting("message").contains("Entering Method: findAllCustomers",
				"Exception occurred in method: findAllCustomers");
	}

	@Test
	public void veryifyAllEndPointsAreBeingWeaved() throws Exception {
		mockMvc.perform(get("/customers"));
		mockMvc.perform(get("/customers/search/byFNameLName?fName=test&lName=test"));

		assertThat(TestAppender.getEvents()).extracting("message").contains("Entering Method: findAllCustomers",
				"Exiting Method: findAllCustomers", "Entering Method: findCustomersByFNameLName",
				"Exiting Method: findCustomersByFNameLName");
	}
}

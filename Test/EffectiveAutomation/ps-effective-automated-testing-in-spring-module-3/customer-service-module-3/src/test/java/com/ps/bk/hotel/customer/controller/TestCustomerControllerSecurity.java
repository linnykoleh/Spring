package com.ps.bk.hotel.customer.controller;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ps.bk.hotel.customer.config.SecurityConfig;
import com.ps.bk.hotel.customer.model.Customer;
import com.ps.bk.hotel.customer.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CustomerController.class)
@Import(SecurityConfig.class)
public class TestCustomerControllerSecurity {

	@MockBean
	private CustomerService service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testFindAllCustomersWithNoUser() throws Exception {
		mockMvc.perform(get("/customers")).andExpect(status().isUnauthorized());
	}
	
	@Test
	public void testFindAllCustomersWithInvalidUser() throws Exception {
		when(service.findAllCustomers()).thenReturn(Arrays.asList(new Customer(), new Customer()));
		mockMvc.perform(get("/customers").with(user("invalidUser")))
			.andExpect(status().isForbidden());
	}
	
	@Test
	public void testFindAllCustomersWithUserWhoDoesntHaveRightRole() throws Exception {
		when(service.findAllCustomers()).thenReturn(Arrays.asList(new Customer(), new Customer()));
		mockMvc.perform(get("/customers").with(user("todd")
				.password("abc123").roles("USER"))).andExpect(status().isForbidden());
	}
	
	@Test
	public void testFindAllCustomersWithValidUser() throws Exception {
		mockMvc.perform(get("/customers").with(user("bojack")
				.password("abc123").roles("ADMIN"))).andExpect(status().isOk());
	}
}

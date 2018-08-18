package com.ps.bk.hotel.customer.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ps.bk.hotel.customer.exception.CustomerSerivceClientException;
import com.ps.bk.hotel.customer.model.Customer;
import com.ps.bk.hotel.customer.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CustomerController.class, secure=false)
@DirtiesContext
public class TestCustomerController {

	@MockBean
	private CustomerService service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testSuccessfulFindAllCustomers() throws Exception {
		when(service.findAllCustomers()).thenReturn(Arrays.asList(new Customer(), new Customer()));
		mockMvc.perform(get("/customers")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	public void testSuccessSearchCustomersByFNameLName() throws Exception {
		when(service.findCustomersByFNameLName("test", "test"))
		.thenReturn(Arrays.asList(new Customer()));
		mockMvc.perform(get("/customers/search/byFNameLName?fName=test&lName=test"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(1)));
	}

	@Test
	public void testMissingParamSearchCustomersByFNameLName() throws Exception {
		mockMvc.perform(get("/customers/search/byFNameLName?fName=test")).andExpect(status().isBadRequest())
				.andExpect(content().string(""));
		verify(service, times(0)).findCustomersByFNameLName(any(), any());
	}

	@Test
	public void testInvalidParamSearchCustomersByFNameLName() throws Exception {
		when(service.findCustomersByFNameLName(any(), any()))
				.thenThrow(new CustomerSerivceClientException("Invalid parameter passed in!"));
		mockMvc.perform(get("/customers/search/byFNameLName?fName=test&lName=invalid"))
				.andExpect(status().isBadRequest()).andExpect(content().contentType("text/plain;charset=UTF-8"))
				.andExpect(content().string("Invalid parameter passed in!"));
	}

}
